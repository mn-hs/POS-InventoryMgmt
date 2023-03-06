BEGIN TRANSACTION;

DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS timesheet;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tabs;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS inventory;
DROP TABLE IF EXISTS menu_items;

CREATE TABLE menu_items (
	item_id SERIAL,
	item_name varchar(100) NOT NULL,
	description varchar(300) NOT NULL,
	price decimal(4, 2) NOT NULL UNIQUE,
	item_count integer DEFAULT 0,
	weekly_sales integer NULL,
	weekly_revenue decimal(6, 2) NULL,
	total_revenue decimal(11, 2) NULL,
	CONSTRAINT PK_item_id PRIMARY KEY (item_id)
);

CREATE TABLE inventory (
	ingredient_id SERIAL,
	name varchar(100) NOT NULL,
	description varchar(300) NOT NULL,
	unit_measurement varchar(200) NOT NULL,
	units integer DEFAULT 0,
	price_per_unit decimal(4, 2) NOT NULL,
	date_received date NOT NULL,
	CONSTRAINT PK_ingredient_id PRIMARY KEY (ingredient_id)
);

CREATE TABLE employees (
    employee_id SERIAL,
    first_name varchar(80) NOT NULL,
    last_name varchar(80) NOT NULL,
    role varchar(50) NOT NULL,
    employment_start_date date NULL,
    age smallint NULL,
    email varchar(100) NULL,
    phone_number varchar(10) NULL,
    hourly_wage decimal(3, 2) NOT NULL,
    CONSTRAINT PK_employee_id PRIMARY KEY (employee_id)
);

CREATE TABLE users (
	user_id SERIAL,
    employee_id int NOT NULL,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    pin varchar(4) NOT NULL,
	CONSTRAINT PK_user_id PRIMARY KEY (user_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES employees(employee_id)
);

CREATE TABLE timesheet (
    timesheet_id SERIAL,
    employee_id int NOT NULL,
    hours_date date NOT NULL,
    clock_in time NULL,
    clock_out time NULL,
    total_hours smallint NULL,
    CONSTRAINT PK_timesheet_id PRIMARY KEY (timesheet_id),
    CONSTRAINT FK_employee_id FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE tabs (
    tab_id SERIAL,
    subtotal decimal(6, 2) NOT NULL,
    tax decimal(4, 2) NOT NULL,
    tip decimal(4, 2) NULL,
    total decimal(6, 2) NOT NULL,
    isProcessed boolean NOT NULL,
    CONSTRAINT PK_tab_id PRIMARY KEY (tab_id)
);

CREATE TABLE sales (
    sale_id SERIAL,
    tab_id integer NOT NULL,
    user_id integer NOT NULL,
    item_id integer NOT NULL,
    price decimal(4, 2) NOT NULL,
    units smallint NOT NULL,
    CONSTRAINT PK_sale_id PRIMARY KEY (sale_id),
    CONSTRAINT FK_tab_id FOREIGN KEY (tab_id) REFERENCES tabs(tab_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_item_id FOREIGN KEY (item_id) REFERENCES menu_items(item_id),
    CONSTRAINT FK_price FOREIGN KEY (price) REFERENCES menu_items(price)
);

COMMIT TRANSACTION;
