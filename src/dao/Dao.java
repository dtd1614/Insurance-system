package dao;

import java.sql.*;

public abstract class Dao {
    protected Connection connection = null;
    protected Statement statement = null;
    protected ResultSet resultSet = null;
    public Dao() throws Exception{
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance?useSSL=false", "root", "1234");
//            System.out.println("DB 연결 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected boolean create(String query) {
//        System.out.println(query);
        try {
            statement = connection.createStatement();
//            if(!statement.execute(query)) System.out.println("insert OK!!");
//            return true;
            return !statement.execute(query);
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    protected ResultSet retrieve(String query) {
//        System.out.println(query);
        try {
            statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    protected boolean update(String query) {
//        System.out.println(query);
        try {
            statement = connection.createStatement();
//            if(!statement.execute(query)) System.out.println("update OK!!!");
//            return true;
            return !statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    protected boolean delete(String query) {
//        System.out.println(query);
        try {
            statement = connection.createStatement();
//            if(!statement.execute(query)) System.out.println("delete OK!!!");
//            return true;
            return !statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
