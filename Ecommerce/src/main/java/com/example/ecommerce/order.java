package com.example.ecommerce;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class order {
    public static boolean placeorder(customer customer,Product product){
        String grouporderid="select max(group_order_id)+1 id from orders";
        DBconnection dBconnection=new DBconnection();
        try {
            ResultSet rs=dBconnection.getQueryTable(grouporderid);
            if(rs.next()){
                String placeOrder="insert into orders(group_order_id,customer_id,product_id) values("+rs.getInt("id")+","+customer.getId()+","+product.getId()+")";
                return dBconnection.updateDatabase(placeOrder)!=0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }
    public static int placemutipleorder(customer customer, ObservableList<Product> productlist){
        String grouporderid="select max(group_order_id)+1 id from orders";
        DBconnection dBconnection = new DBconnection();
        try {
            ResultSet rs=dBconnection.getQueryTable(grouporderid);
            int count=0;
            if(rs.next()){
                for(Product product:productlist){
                    String placeOrder="insert into orders(group_order_id,customer_id,product_id) values("+rs.getInt("id")+","+customer.getId()+","+product.getId()+")";
                    count += dBconnection.updateDatabase(placeOrder);
                }
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;

    }
}
