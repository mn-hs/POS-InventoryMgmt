package com.InventoryMgmt.POSInventoryManagement.dao;

import com.InventoryMgmt.POSInventoryManagement.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();

    Employee findById(int employeeId);

    Employee create(Employee newEmployee);

    Employee update(Employee updatedEmployee);

    boolean delete(int employeeId, int userId);
}
