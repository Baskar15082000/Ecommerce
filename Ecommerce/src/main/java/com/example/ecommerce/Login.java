package com.example.ecommerce;

import java.sql.ResultSet;

public class Login {
    public customer customerslogin(String username,String password){
        String loginQuery = "select * from customers where email = '"+username+"' and password = '"+password+"'";
        DBconnection conn = new DBconnection();
        ResultSet rs=conn.getQueryTable(loginQuery);
        try{
            if(rs.next())
                return new customer(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("mobile"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        Login login = new Login();
//        customer customer =login.customerslogin("baskar15082000@gmail.com","Raja@1234");
//        System.out.println("welcome "+customer.getName());
//    }
}
