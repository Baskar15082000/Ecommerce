package com.example.ecommerce;


import java.sql.ResultSet;
import java.sql.SQLException;

public class signup {
    public static boolean getsignup(String name,String email,String password,String mobile){
        DBconnection dBconnection=new DBconnection();



            String placeOrder = "insert into customers(name,email,password,mobile) values('" + name + "','" + email + "','" + password + "','" + mobile + "')";
            return dBconnection.updateDatabasesign(placeOrder) != 0;





    }


}
