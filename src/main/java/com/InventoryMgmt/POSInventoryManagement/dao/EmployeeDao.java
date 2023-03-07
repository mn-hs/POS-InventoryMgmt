package com.InventoryMgmt.POSInventoryManagement.dao;

import com.InventoryMgmt.POSInventoryManagement.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();

    Employee getEmployeeById(int employeeId);

    Employee findByUsername(String username);

    int findIdByUsername(String username);

    Employee create(Employee newEmployee);
}
