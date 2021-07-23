//package com.company;
//
//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
//import java.io.File;
//import java.sql.*;
//
//public class Main extends Application {
//
//    private static Connection connect = null;
//
//    public static void main(String[] args) {
//        System.out.println("Connecting to MySQL database .......");
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            String dbName = "item_ms";
//            String dbUserName = "root";
//            String dbPassword = "";
//            String connectionString = "jdbc:mysql://localhost/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword;
//            connect = DriverManager
//                    .getConnection(connectionString);
//
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Connection Failed !");
//            e.printStackTrace();
//        }
//
//        Application.launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Stage stage = new Stage();
//        AnchorPane anchorPane = new AnchorPane();
//
//        Button singInButton = new Button("Sign In");
//
//        Label userName = new Label("User Name : ");
//        Label password = new Label("Password : ");
//        Label label = new Label();
//
//        TextField textFieldUserName = new TextField();
//        TextField textFieldCPass = new TextField();
//
//
//        labelProp(userName, 60, 60, "-fx-font-size: 12;");
//        labelProp(password, 60, 120, "-fx-font-size: 12;");
//        labelProp(label, 165, 20, "-fx-font-size: 16;");
//
//
//        singInButton.setLayoutX(160);
//        singInButton.setLayoutY(245);
//
//        textFieldProp(textFieldUserName, 140, 55, 130, 35, "Enter User Name ");
//        textFieldProp(textFieldCPass, 140, 115, 130, 35, "Enter Password ");
//
//        singInButton.setOnAction(event -> {
//            if (textFieldUserName.getText().equals("admin") & textFieldCPass.getText().equals("admin")) {
//                System.out.println("Success !");
//                stage.close();
//                formInput();
//            } else
//                label.setText("Invalid ");
//        });
//
//
//        anchorPane.getChildren().addAll(userName, password, textFieldCPass, textFieldUserName, singInButton, label);
//        stage.setScene(new Scene(anchorPane, 400, 300));
//        stage.show();
//
//    }
//
//    public void openTableView(){
//        Stage stageGrid = new Stage();
//        StackPane stackPane =  new StackPane();
//
//        TableView<Item> tableView = new TableView();
//
//        TableColumn<Item, String> firstNameColumn = new  TableColumn<>("ID");
//        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//        TableColumn<Item, String> secondNameColumn = new TableColumn<>("Name");
//        secondNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//        TableColumn<Item, String> thirdNameColumn = new  TableColumn<>("Brand");
//        thirdNameColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
//
//        TableColumn<Item, String> fourthNameColumn = new TableColumn<>("Price");
//        fourthNameColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
//
//        TableColumn<Item, String> fifthNameColumn = new  TableColumn<>("Image");
//        fifthNameColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
//
//        tableView.getColumns().addAll(firstNameColumn,secondNameColumn,thirdNameColumn,fourthNameColumn,fifthNameColumn);
//
//        String query = "SELECT * FROM test_table";
//
//        try {
//            Statement preparedStmt = connect.createStatement();
//            ResultSet results = preparedStmt.executeQuery(query);
//            while (results.next()){
//                Item item =  new Item();
//                item.setId(results.getInt("id"));
//                item.setName(results.getString("name"));
//                item.setBrand(results.getString("brand"));
//                item.setPrice(results.getString("price"));
//                item.setImage(results.getString("image"));
//
//                tableView.getItems().add(item);
//
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//
//        VBox vBox1 =  new VBox();
//        vBox1.getChildren().add(tableView);
//
//        stackPane.getChildren().add(vBox1);
//        stageGrid.setScene(new Scene(stackPane, 600 ,600));
//        stageGrid.show();
//
//    }
//
//    private void formInput() {
//        final String[] fileName = {""};
//        StackPane root = new StackPane();
//        Stage stage = null;
//        Button button = new Button("Save");
//        Button imageSelect =  new Button("Select image");
//        Button openGridView =  new Button("Open items");
//        VBox vBox = new VBox();
//        TextField textField1 = new TextField();
//        TextField textField2 = new TextField();
//        TextField textField3 = new TextField();
//
//        vBox.setSpacing(8);
//        vBox.setPadding(new Insets(10,10,10,10));
//        vBox.getChildren().addAll(
//                new Label("Name : "),
//                textField1,
//                new Label("Brand : "),
//                textField2,
//                new Label("Price : "),
//                textField3,
//                new Label("Image  : "),
//                imageSelect,
//                button,
//                openGridView);
//
//
//
//        openGridView.setOnAction(event -> {
//            openTableView();
//
//        });
//
//
//        imageSelect.setOnAction(event -> {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("Open Resource File");
//            fileChooser.getExtensionFilters().add(
//                    new FileChooser.ExtensionFilter("IMAGE FILES", ".jpg", ".png", "*.gif")
//            );
//
//            File file = fileChooser.showOpenDialog(root.getScene().getWindow());
//            if (file != null) {
//                // pickUpPathField it's your TextField fx:id
//                fileName[0] = file.getName();
//                System.out.println(fileName[0]);
//
//            } else  {
//                System.out.println("error"); // or something else
//            }
//        });
//        button.setOnAction(event -> {
//            String query = "INSERT INTO `item` (`name`, `brand`, `price`, `image_path`)"
//                    + " VALUES (?, ?, ?, ?)";
//
//            try {
//                PreparedStatement preparedStmt = connect.prepareStatement(query);
//                preparedStmt.setString(1, textField1.getText());
//                preparedStmt.setString(2, textField2.getText());
//                preparedStmt.setString(3, textField3.getText());
//                preparedStmt.setString(4, fileName[0]);
//                preparedStmt.execute();
//
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setContentText("Successfully saved !");
//                alert.show();
//
//
//
//            } catch (SQLException throwables) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("Error when saving !");
//                alert.show();
//                throwables.printStackTrace();
//            }
//
//
//        });
//        root.getChildren().addAll(vBox);
//
//
//        stage = new Stage();
//        stage.setScene(new Scene(root, 400,600));
//        stage.show();
//    }
//
//    public void textFieldProp(TextField textField, double layoutX, double layoutY, double prefWidth, double prefHeight, String promptText) {
//        textField.setLayoutX(layoutX);
//        textField.setLayoutY(layoutY);
//        textField.setPrefSize(prefWidth, prefHeight);
//        textField.setPromptText(promptText);
//        textField.setStyle("-fx-background-color: #6ea7c6, #ffffff;" +
//                "-fx-background-radius: 15;" +
//                "-fx-border-radius: 15;" +
//                "-fx-border-color: #5c9ccd;");
//    }
//
//    public void labelProp(Label label, double layoutX, double layoutY, String fontSize) {
//        label.setLayoutX(layoutX);
//        label.setLayoutY(layoutY);
//        label.setStyle(fontSize);
//    }
//}
