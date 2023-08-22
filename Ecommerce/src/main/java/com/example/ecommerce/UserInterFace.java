package com.example.ecommerce;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class UserInterFace {
     GridPane loginPage;
     TextField searchbar;
     Button searchbutton;
     Button cartbutton;
     Button buynowbutton;
     HBox headerbar;
     VBox body;
     customer loggedincustomer;
     Button addtocartbutton;
     Button signbutton;
     Button home;

     Label welcomelable;
     HBox footerbar;

     productlist productlist=new productlist();
     ordertable ordertable=new ordertable();
     Button placeorderbutton=new Button("Place Order");
     ObservableList<Product> itemincart= FXCollections.observableArrayList();
    ObservableList<ordersproduct> iteminorder= FXCollections.observableArrayList();

    VBox productpage;
     VBox orderpage;
     public UserInterFace(){
         CreateloginPage();
         createheaderbar();
         createfooterbar();

     }
     BorderPane createpane(){
        BorderPane root=new BorderPane();
        root.setPrefSize(800,400);
       // root.getChildren().add(loginPage);//method to add node as children to pane
        // root.setCenter(loginPage);
         root.setTop(headerbar);
         body=new VBox();
         body.setPadding(new Insets(10));
         body.setAlignment(Pos.CENTER);
         body.setStyle("-fx-background-color:lightgray");
         root.setCenter(body);
         productpage = productlist.getallproduct();
         orderpage =ordertable.getoder();


         body.getChildren().add(productpage);

         root.setBottom(footerbar);
         return root;
     }

    private void CreateloginPage(){
         Text usernametext=new Text("User Name");
         Text passwordtext=new Text("Password");

         Button loginbutton=new Button("Login");

         TextField username=new TextField();
         username.setPromptText("Enter your username here");
        PasswordField password =new PasswordField();
        password.setPromptText("Enter your password");
        Label messagelable=new Label();

        loginPage=new GridPane();
        loginPage.setStyle("-fx-background-color:lightgray");
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setHgap(20);
        loginPage.setVgap(20);
        loginPage.add(usernametext,0,0); // col 0 row 0
        loginPage.add(passwordtext,0,1);//col 0 row 2
        loginPage.add(username,1,0);
        loginPage.add(password,1,1);
        loginPage.add(loginbutton,1,2);
        loginPage.add(messagelable,0,2);

        loginbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name=username.getText();

                String pass=password.getText();

                Login login = new Login();

                loggedincustomer=login.customerslogin(name,pass);
                if(loggedincustomer!=null){
                    body.getChildren().clear();
                    messagelable.setText("! Login successful welcome"+name);
                    welcomelable.setText("! Welcome "+loggedincustomer.getName());
                    headerbar.getChildren().add(welcomelable);
                    cartbutton.setVisible(true);
                    searchbar.setVisible(true);
                    searchbutton.setVisible(true);
                    home.setVisible(true);

                    buynowbutton.setVisible(true);
                    addtocartbutton.setVisible(true);



                    body.getChildren().add(productpage);
                }
                else{
                    messagelable.setText("Login !! failed try again");
                    home.setVisible(true);
                }

            }
        });



    }
    private void createheaderbar(){
         home=new Button();
        Image image=new Image("C:\\Users\\parth\\Desktop\\Ecommerce\\src\\360_F_29500682_uUsyql4YP9ZMGkl7DWdhL9dPNxua0XMk.jpg");
        ImageView imageView=new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(80);
        imageView.setFitHeight(30);

        home.setGraphic(imageView);
         searchbar=new TextField();
         searchbar.setStyle("-fx-background-color:lightgray");
         searchbar.setPromptText("search here......");
         searchbar.setPrefSize(350,30);


         searchbutton=new Button("search");

         signbutton=new Button("LogIn");
         signbutton.setStyle("-fx-background-color:lightblue");
         welcomelable=new Label();
         cartbutton=new Button("MY Cart");
         Button orderbutton =new Button("Order");
         orderbutton.setStyle("-fx-background-color:orange");
         cartbutton.setStyle("-fx-background-color:yellow");
         searchbutton.setStyle("-fx-color:lightgray");
         searchbutton.setPrefHeight(30);
         signbutton.setPrefHeight(30);
         headerbar=new HBox(10);
         headerbar.setStyle("-fx-background-color:gray");
         headerbar.setPrefHeight(70);
         headerbar.setPadding(new Insets(10));
         headerbar.setAlignment(Pos.CENTER);
         home.setPrefHeight(30);
         headerbar.getChildren().addAll(home,searchbar,searchbutton,signbutton,cartbutton,orderbutton);

         signbutton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 body.getChildren().clear();//remove
                 body.getChildren().add(loginPage);//put loginpage
                 headerbar.getChildren().remove(signbutton);

             }
         });
         cartbutton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 if(loggedincustomer==null){
                     showdialog("Please login to go to cart");
                 }
                 else {
                     body.getChildren().clear();
                     VBox prodpage = productlist.getproductsincart(itemincart);
                     prodpage.setAlignment(Pos.CENTER);
                     prodpage.setSpacing(10);
                     prodpage.getChildren().add(placeorderbutton);
                     body.getChildren().add(prodpage);
                     footerbar.setVisible(false);
                 }
             }
         });

         orderbutton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 body.getChildren().clear();
                 body.getChildren().add(orderpage);

                 footerbar.setVisible(true);
                 if(loggedincustomer==null){
                     if( headerbar.getChildren().indexOf(signbutton)==-1){
                         headerbar.getChildren().add(signbutton);
                     }


                 }
             }
         });

         placeorderbutton.setAlignment(Pos.CENTER);
         placeorderbutton.setStyle("-fx-background-color:orange");
         placeorderbutton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {

                 if(itemincart==null){

                     showdialog("! Please add some products int the cart to place order");
                     return;
                 }
                 if(loggedincustomer==null){
                     showdialog("Please login first to Place order");
                     return;
                 }
                 int count=order.placemutipleorder(loggedincustomer,itemincart);
                 if(count!=0){
                     showdialog("Order for "+count+" Products Placed Successful");
                 }
                 else{
                     showdialog("Failed to Place Order");
                 }

             }
         });

    }

    private void createfooterbar(){


         buynowbutton=new Button("BuyNow");
         addtocartbutton=new Button("Add to Cart");

        footerbar=new HBox(10);
        footerbar.setStyle("-fx-background-color:gray");

        footerbar.setPrefHeight(70);
        footerbar.setPadding(new Insets(10));
        footerbar.setAlignment(Pos.CENTER);
        addtocartbutton.setStyle("-fx-background-color:yellow");
        buynowbutton.setStyle("-fx-background-color:orange");
        footerbar.getChildren().addAll(buynowbutton,addtocartbutton);
        buynowbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product1 = productlist.getselectedproduct();
                ordersproduct or=ordertable.getselectedorders();

                if(product1==null ) {
                    showdialog("!Please select product to place order");
                    return;
                }
                if(loggedincustomer==null){
                    //please select product
                    showdialog("! Please login first to place order");
                    return;
                }
                boolean status=order.placeorder(loggedincustomer,product1);
                if(status){
                    showdialog("Order Placed Successful");
                }
                else{
                    showdialog("Failed to Place Order");
                }
            }
        });
        addtocartbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product= productlist.getselectedproduct();

                if(product==null){
                    showdialog("Please select product to add to cart");
                    return;
                }
                if(loggedincustomer==null){
                    //please select product
                    showdialog("! Please Login first to add it to cart");
                    return;
                }

                    itemincart.add(product);
                    showdialog("Selected Item hs been added to cart successfully.");

            }
        });
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(productpage);
                footerbar.setVisible(true);
                if(loggedincustomer==null){
                    if( headerbar.getChildren().indexOf(signbutton)==-1){
                        headerbar.getChildren().add(signbutton);
                    }


                }



            }
        });

    }
    private void showdialog(String message){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("Message");
        alert.showAndWait();
    }


}

