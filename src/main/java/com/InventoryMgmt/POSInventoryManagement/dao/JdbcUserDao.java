package com.InventoryMgmt.POSInventoryManagement.dao;

import com.InventoryMgmt.POSInventoryManagement.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash, role, name, employee_id " +
                "FROM users;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT user_id, username, password_hash, role, name, employee_id " +
                "FROM users " +
                "WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToUser(results);
        } else return null;
    }

    @Override
    public User getUserByEmployeeId(int employeeId) {
        SqlRowSet results;
        if (employeeId == Integer.MIN_VALUE) throw new IllegalArgumentException("Employee Id cannot be null");
        try {
            String sql = "SELECT user_id, username, password_hash, role, name, employee_id " +
                    "FROM users " +
                    "WHERE employeeId = ?";
            results = jdbcTemplate.queryForRowSet(sql, employeeId);
        } catch (EmptyResultDataAccessException e){
            throw new UsernameNotFoundException("Employee " + employeeId + " was not found.");
        }
        return mapRowToUser(results);
    }

    @Override
    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        for (User user : this.findAll()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public int findIdByUsername(String username){
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        int userId;
        try {
            userId = jdbcTemplate.queryForObject("SELECT user_id FROM users WHERE username = ?", int.class, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }

        return userId;
    }

    @Override
    public User create(User newUser) {
        String sql = "INSERT INTO users (username, password_hash, role, name, employee_id) " +
                "values (?,?,?,?,?) " +
                "RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(newUser.getPassword());

        int userId = jdbcTemplate.queryForObject(sql, int.class,
                newUser.getUsername(), password_hash, newUser.getAuthoritiesString(), newUser.getName(), newUser.getEmployeeId());
        return getUserById(userId);
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setName(rs.getString("name"));
        user.setEmployeeId(rs.getInt("employee_id"));
        user.setActivated(true);
        return user;
    }
}
