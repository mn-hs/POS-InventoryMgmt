BEGIN TRANSACTION;

DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS timesheet;
DROP TABLE IF EXISTS tabs;
DROP TABLE IF EXISTS inventory;
DROP TABLE IF EXISTS menu_items;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS employees;

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
    position varchar(50)  NOT NULL,
    employment_start_date date NULL,
    age smallint NULL,
    email varchar(100) NULL,
    phone_number varchar(13) NOT NULL,
    hourly_wage decimal(3, 2) NOT NULL,
    CONSTRAINT PK_employee_id PRIMARY KEY (employee_id)
);

CREATE TABLE timesheet (
    timesheet_id SERIAL,
    employee_id int NOT NULL,
    hours_date date NOT NULL,
    clock_in time NULL,
    clock_out time NULL,
    total_hours smallint NULL,
    CONSTRAINT PK_timesheet_id PRIMARY KEY (timesheet_id),
    CONSTRAINT FK_employee_id_timesheet FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE tabs (
    tab_id SERIAL,
    subtotal decimal(6, 2) NOT NULL,
    tax decimal(4, 2) NOT NULL,
    tip decimal(4, 2) NULL,
    total decimal(6, 2) NOT NULL,
    isProcessed boolean NOT NULL,
    employee_id int NOT NULL,
    CONSTRAINT PK_tab_id PRIMARY KEY (tab_id)
);

CREATE TABLE sales (
    sale_id SERIAL,
    tab_id integer NOT NULL,
    employee_id integer NOT NULL,
    item_id integer NOT NULL,
    price decimal(4, 2) NOT NULL,
    units smallint NOT NULL,
    CONSTRAINT PK_sale_id PRIMARY KEY (sale_id),
    CONSTRAINT FK_tab_id FOREIGN KEY (tab_id) REFERENCES tabs(tab_id),
    CONSTRAINT FK_employee_id_sales FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    CONSTRAINT FK_item_id FOREIGN KEY (item_id) REFERENCES menu_items(item_id),
    CONSTRAINT FK_price FOREIGN KEY (price) REFERENCES menu_items(price)
);

CREATE TABLE users (
    user_id SERIAL,
    username varchar(45) NOT NULL,
    password_hash varchar(64) NOT NULL,
    role varchar(45) NOT NULL,
    enabled boolean NOT NULL,
    employee_id integer NOT NULL,
    CONSTRAINT FK_employee_user_id FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

INSERT INTO employees (first_name, last_name, position, phone_number, hourly_wage) VALUES
('Hunter', 'Mnich', 'Barback', '000-000-0000', 5.00);

INSERT INTO users (username, password_hash, role, enabled, employee_id) VALUES
('admin', 'admin1', 'ROLE_ADMIN', true, 1);

COMMIT TRANSACTION;
