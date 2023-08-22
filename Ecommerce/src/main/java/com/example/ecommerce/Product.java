package com.example.ecommerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    private SimpleStringProperty description;

    public Product(int id, String name, double price,  String description) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.description =new SimpleStringProperty(description);
    }
    public static ObservableList<Product> getAllproducts(){
        String selectAllProduct="select id,name,price,description from product";
        return fectchproduct(selectAllProduct);
    }
    public static ObservableList<Product> fectchproduct(String Query){
        ObservableList<Product> data= FXCollections.observableArrayList();
        DBconnection dBconnection = new DBconnection();
        try{
            ResultSet rs=dBconnection.getQueryTable(Query);
            while(rs.next()){
                Product product = new Product(rs.getInt("id"),rs.getString("name"),rs.getDouble("price"),rs.getString("description"));
                data.add(product);
            }
            return data;
        }
        catch(Exception e){
            e.printStackTrace();

        }
        return null;
    }
    public int getId() {
        return id.get();
    }



    public String getName() {
        return name.get();
    }



    public double getPrice() {
        return price.get();
    }

    public String getDescription() {
        return description.get();
    }


}
