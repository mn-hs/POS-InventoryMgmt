package com.InventoryMgmt.POSInventoryManagement.model;

import javax.validation.constraints.NotEmpty;

public class RegisterUserDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    private String name;

    private int employeeId;
    @NotEmpty(message = "Please select a role for this user.")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getConfirmPassword() {return confirmPassword;}

    public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getEmployeeId() {return employeeId;}

    public void setEmployeeId(int employeeId) {this.employeeId = employeeId;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}
}
