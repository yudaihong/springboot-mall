package com.robertyu.springboot_mall.rowmapper;

import com.robertyu.springboot_mall.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowmapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setCreatedDate(resultSet.getTimestamp("last_modified_date"));
        return  user;
    }
}
