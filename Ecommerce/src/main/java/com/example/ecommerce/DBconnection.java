package com.example.ecommerce;
import java.sql.*;
public class DBconnection {
    private final String dburl = "jdbc:mysql://localhost:3306/ecommerce";
    private final String username = "root";
    private final String password = "Raja@1234";
    private Statement getstatement(){
        try{
           Connection connection = DriverManager.getConnection(dburl ,username ,password );
           return connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getQueryTable(String query){
        try{
            Statement statement=getstatement();
            return statement.executeQuery(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public int updateDatabase(String Query){
        try{
            Statement statement=getstatement();
            return statement.executeUpdate(Query);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }
    public int updateDatabasesign(String Query){
        try{
            Statement statement=getstatement();
            return statement.executeUpdate(Query);
        }

        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DBconnection con=new DBconnection();
        ResultSet rs = con.getQueryTable("select * from customers");
        if(rs!=null){
            System.out.println("connection successful");
        }
        else{
            System.out.println("connection failed");
        }
    }
}
