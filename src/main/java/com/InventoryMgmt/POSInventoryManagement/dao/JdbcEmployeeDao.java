package com.InventoryMgmt.POSInventoryManagement.dao;

import com.InventoryMgmt.POSInventoryManagement.model.Employee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcEmployeeDao implements EmployeeDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcEmployeeDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT u.user_id, u.employee_id, u.username, u.password_hash, u.pin, e.role" +
                " FROM users AS u" +
                " JOIN employees AS e ON u.employee_id = u.employee_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Employee employee = mapRowToUser(results);
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        String sql = "SELECT employee_id, first_name, last_name, username," +
                " password_hash, role, position, pin, employment_start_date," +
                " age, email, phone_number, hourly_wage" +
                " FROM employees;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, employeeId);
        if (results.next()) {
            return mapRowToUser(results);
        } else {
            return null;
        }
    }

    @Override
    public Employee findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        String sql =  "SELECT employee_id, first_name, last_name, username," +
                " password_hash, role, position, pin, employment_start_date," +
                " age, email, phone_number, hourly_wage" +
                " FROM employees" +
                " WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if (results.next()){
            return mapRowToUser(results);
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public int findIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        int employeeId;
        try {
            employeeId = jdbcTemplate.queryForObject("SELECT employee_id FROM employees WHERE username = ?",
                    int.class, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }
        return employeeId;
    }

    @Override
    public Employee create(Employee newEmployee) {
        String insertUserSql = "INSERT INTO users (employee_id, first_name, last_name, username,\" +\n" +
                "            \" password_hash, role, position, pin, employment_start_date,\" +\n" +
                "            \" age, email, phone_number, hourly_wage) " +
                "values (?,?,?,?) RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(newEmployee.getPassword());
            int userId = jdbcTemplate.queryForObject(insertUserSql, int.class, newEmployee.getEmployeeId(),
                    newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getUsername(), newEmployee.getPassword(),
                    newEmployee.getRole(), newEmployee.getPosition(), newEmployee.getPin(), newEmployee.getEmploymentStartDate(),
                    newEmployee.getEmail(), newEmployee.getPhoneNumber(), newEmployee.getHourlyWage());
            return getEmployeeById(userId);
    }

    private Employee mapRowToUser(SqlRowSet rs) {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setUsername(rs.getString("username"));
        employee.setPassword(rs.getString("password_hash"));
        employee.setRole(rs.getString("role"));
        employee.setPosition(rs.getString("position"));
        employee.setPin(Objects.requireNonNull(rs.getString("phone_number")));
        employee.setEmploymentStartDate(rs.getDate("employment_start_date").toLocalDate());
        employee.setAge(rs.getInt("age"));
        employee.setEmail(rs.getString("email"));
        employee.setPhoneNumber(rs.getString("phone_number"));
        employee.setHourlyWage(rs.getDouble("hourly_wage"));
        employee.setActivated(true);
        return employee;
    }
}
