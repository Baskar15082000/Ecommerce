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

import java.util.function.UnaryOperator;

public class UserInterFace {
    GridPane loginPage;


    GridPane signuppage;
    TextField username=new TextField();



    Button singup=new Button("SignUp");
    PasswordField password =new PasswordField();


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
    Button logoutbutton;

    Label welcomelable;
    HBox footerbar;

    productlist productlist=new productlist();


    ordertable ordertable=new ordertable();
    Button placeorderbutton=new Button("Place Order");
    ObservableList<Product> itemincart= FXCollections.observableArrayList();
    ObservableList<ordersproduct> iteminorder= FXCollections.observableArrayList();
    Label messagelable;
    Button signin=new Button("SignIn");
    VBox productpage;
    VBox orderpage;
    public UserInterFace(){
        CreateloginPage();
        createheaderbar();
        createfooterbar();
        signup();

    }
    BorderPane createpane(){
        BorderPane root=new BorderPane();
        root.setPrefSize(800,400);
        // root.getChildren().add(loginPage);//method to add node as children to pane
        // root.setCenter(loginPage);

        root.setTop(headerbar);

        body=new VBox();
        body.setStyle("-fx-background-color:lightyellow");
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        root.setCenter(body);
        productpage = productlist.getallproduct();
        orderpage =ordertable.getoder();


        body.getChildren().add(productpage);

        root.setBottom(footerbar);
        return root;
    }
    private void signup() {

        Text name = new Text("User Name ");
        Text mobile = new Text("Mobile No");
        Text usernametext = new Text("User Email ");
        Text passwordtext = new Text("Password");

        TextField mobil=new TextField();
        TextField email=new TextField();
        TextField signpassword=new TextField();
        TextField signupname=new TextField();

        mobil.setPromptText("Enter Mobile No");
        email.setPromptText("Enter Email Id");

        signupname.setPromptText("Enter your name");

        signpassword.setPromptText("Enter your password");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,10}")) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        mobil.setTextFormatter(textFormatter);
        messagelable = new Label();

        signuppage = new GridPane();
        signuppage.setStyle("-fx-background-color:lightgray");
        signuppage.setAlignment(Pos.CENTER);
        signuppage.setHgap(20);
        signuppage.setVgap(20);
        signuppage.add(usernametext, 0, 0); // col 0 row 0
        signuppage.add(passwordtext, 0, 1);//col 0 row 2
        signuppage.add(name,0,2);
        signuppage.add(mobile,0,3);
        signuppage.add(email, 1, 0);
        signuppage.add(signpassword, 1, 1);
        signuppage.add(signupname,1,2);
        signuppage.add(mobil,1,3);
        signuppage.add(signin, 1, 4);
        singup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(signuppage);
                signupname.clear();
                email.clear();
                signpassword.clear();
                mobil.clear();
            }
        });
        signin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Login login = new Login();

                customer si=login.customerslogin(email.getText());
                if(si==null){
                   signup.getsignup(signupname.getText(),email.getText(),signpassword.getText(),mobil.getText());
                   body.getChildren().clear();
                   body.getChildren().add(loginPage);
                   return ;
               }
               else{
                        showdialog("Email already exits try another");
                    }
            }
        });
    }
        private void CreateloginPage(){
            singup.setStyle("-fx-background-color:lightblue");
            signin.setStyle("-fx-background-color:lightblue");
        Text usernametext=new Text("User Name");
        Text passwordtext=new Text("Password");

        Button loginbutton=new Button("Login");
            loginbutton.setStyle("-fx-backgrount-color:blue");


        username.setPromptText("Enter your username here");

        password.setPromptText("Enter your password");
        messagelable=new Label("Hi");
        logoutbutton=new Button("LogOut");
        logoutbutton.setStyle("-fx-background-color:lightblue");
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
        loginPage.add(singup,1,3);
        Label signlable=new Label("If You Don't An Have Account Please SignIn !");
        loginPage.add(signlable,0,3);


        loginbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name=username.getText();

                String pass=password.getText();

                Login login = new Login();

                loggedincustomer=login.customerslogin(name,pass);

                if(loggedincustomer!=null){
                    welcomelable.setText("! Welcome "+loggedincustomer.getName());
                    headerbar.getChildren().add(logoutbutton);
                    headerbar.getChildren().add(welcomelable);
                    body.getChildren().clear();
                    body.getChildren().add(productpage);

                }
                else {
                    messagelable.setText("Login failed try again");
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

        headerbar.getChildren().addAll(home,searchbar,searchbutton,cartbutton,orderbutton,signbutton);

        searchbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                String str=searchbar.getText();
                productpage = productlist.getallproduct(str);
                if(productpage == null)
                {
                    Image productImage = new Image("C:\\Users\\parth\\Desktop\\Ecommerce\\src\\png no-search-found-2511608-2133696.png");
                    ImageView productImageView = new ImageView();
                    productImageView.setImage(productImage);
                    productImageView.setFitWidth(500);
                    productImageView.setFitHeight(400);
                    body.getChildren().add(productImageView);
                    return;
                }
                body.getChildren().add(productpage);


            }
        });
        signbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                username.setPromptText("Enter user name");
                password.setPromptText("enter your password");
                body.getChildren().clear();//remove
                body.getChildren().add(loginPage);//put loginpage
                headerbar.getChildren().remove(signbutton);
                username.clear();
                password.clear();
                messagelable.setText("Hi");

            }
        });
        logoutbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                headerbar.getChildren().removeAll(logoutbutton,welcomelable);
                headerbar.getChildren().add(signbutton);

                loggedincustomer=null;

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
                if(loggedincustomer!=null) {
                    orderpage = ordertable.getoder();
                    body.getChildren().clear();
                    body.getChildren().add(orderpage);

                    footerbar.setVisible(true);
                    if (loggedincustomer == null) {
                        if (headerbar.getChildren().indexOf(signbutton) == -1) {
                            headerbar.getChildren().add(signbutton);
                        }


                    }
                }
                else{
                    showdialog("Please Login to go to Orders");
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
                productpage = productlist.getallproduct();
                body.getChildren().clear();
                body.getChildren().add(productpage);
                if(loggedincustomer==null){
                    logoutbutton.setVisible(false);
                }
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
