package com.InventoryMgmt.POSInventoryManagement.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String role;
    private String position;
    private int pin;
    private LocalDate employmentStartDate;
    private int age;
    private String email;
    private String phoneNumber;
    private double hourlyWage;
    private boolean activated;

    public Employee(){}

    public Employee(int employeeId, String firstName, String lastName, String username, String password, String role,
                    String position, int pin, LocalDate employmentStartDate, int age, String email, String phoneNumber,
                    double hourlyWage, boolean activated) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.position = position;
        this.pin = pin;
        this.employmentStartDate = employmentStartDate;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hourlyWage = hourlyWage;
        this.activated = activated;
    }

    public Employee(int employeeId, String firstName, String lastName, String username, String password, String role,
                    String position, int pin, String phoneNumber,
                    double hourlyWage, boolean activated) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.position = position;
        this.pin = pin;
        this.phoneNumber = phoneNumber;
        this.hourlyWage = hourlyWage;
        this.activated = activated;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(String phoneNumber) {
        this.pin = Integer.parseInt(phoneNumber.substring(phoneNumber.length() - 4));
    }

    public LocalDate getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(LocalDate employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId &&
                activated == employee.activated &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(username, employee.username) &&
                Objects.equals(password, employee.password) &&
                Objects.equals(role, employee.role) &&
                Objects.equals(position, employee.position) &&
                Objects.equals(pin, employee.pin) &&
                Objects.equals(employmentStartDate, employee.employmentStartDate) &&
                Objects.equals(age, employee.age) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(phoneNumber, employee.phoneNumber) &&
                Objects.equals(hourlyWage, employee.hourlyWage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, pin, username, password, activated, role);
    }
}
