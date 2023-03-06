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
import java.util.Objects;

@Component
public class JdbcUserDao implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.user_id, u.employee_id, u.username, u.password_hash, u.pin, e.role" +
                " FROM users AS u" +
                " JOIN employees AS e ON u.employee_id = u.employee_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT u.user_id, u.employee_id, u.username, u.password_hash, u.pin, e.role" +
                " FROM users AS u" +
                " JOIN employees AS e ON u.employee_id = u.employee_id;" +
                " WHERE u.user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToUser(results);
        } else {
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        String sql = "SELECT u.user_id, u.employee_id, u.username, u.password_hash, u.pin, e.role" +
                " FROM users AS u" +
                " JOIN employees AS e ON u.employee_id = u.employee_id;" +
                " WHERE u.username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if (results.next()){
            return mapRowToUser(results);
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public int findIdByUsername(String username) {
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
        String insertUserSql = "INSERT INTO users (employee_id, username, password_hash, pin) " +
                "values (?,?,?,?) RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(newUser.getPassword());
            int userId = jdbcTemplate.queryForObject(insertUserSql, int.class, newUser.getEmployeeId(),
                    newUser.getUsername(), password_hash, newUser.getPin());
            return getUserById(userId);
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setEmployeeId(rs.getInt("employee_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setPin(rs.getInt("pin"));
        user.setActivated(true);
        return user;
    }
}
