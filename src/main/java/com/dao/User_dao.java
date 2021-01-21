package com.dao;

import com.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User_dao {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
        }
    }
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Account",
                "root", "doverth");
    }
    private void closeConnection(Connection connection) {
        if (connection == null)
            return;
        try {
            connection.close();
        } catch (SQLException ex) {
        }
    }
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();

        String sql = "select * from User";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = ((PreparedStatement) statement).executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));

                result.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }
    public User getUser (String username) {
        User user = new User();
        String sql = "select * from User where username = '" +username+ "'";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return user;
    }

    public boolean insertUser (User user) {
        boolean status = false;
        String sql = "insert into User values ('"
                + user.getUsername() +"', "
                + "'" + user.getPassword() + "', "
                + "'" + user.getRole() + "'"
                +");";
        System.out.println(sql);
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            status = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            status = false;
        } finally {
            closeConnection(connection);
        }
        return status;
    }

    public void updateUser (User user) {

    }

    public void deleteUser (User user) {

    }
}
