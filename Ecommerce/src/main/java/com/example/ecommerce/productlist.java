package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class productlist {
    private TableView<Product> productTable;
    public VBox createTable(ObservableList<Product> data){
        //columns
        TableColumn id=new TableColumn("PRODUCT ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name =new TableColumn("PRODUCT NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn description=new TableColumn("PRODUCT OVERVIEW");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.setStyle("");


        productTable =new TableView<>();
        productTable.setItems(data);

        productTable.getColumns().addAll(id,name,price,description);
        description.setStyle("");
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox=new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(productTable);
        return vBox;

    }
//    public VBox getDummyTable(){
//        ObservableList<Product> data =FXCollections.observableArrayList();
//        data.add(new Product(1,"Iphone",85000,"none"));
//        data.add(new Product(2,"laptop",140000,"none"));
//        return createTable(data);
//    }
    public  VBox getallproduct(){
        ObservableList<Product> data=Product.getAllproducts();
        return createTable(data);
    }
   public VBox getallproduct(String str){
        ObservableList<Product> sr=Product.getAllproducts(str);
        if(sr.isEmpty() || sr==null){
            return null;
        }
        return createTable(sr);
    }
    public Product getselectedproduct(){
       return productTable.getSelectionModel().getSelectedItem();
    }
    public VBox getproductsincart(ObservableList<Product> data){
        return createTable(data);
    }
}
