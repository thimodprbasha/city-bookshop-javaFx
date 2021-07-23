package com.company;

import com.company.models.Book;
import com.company.models.Cashier;
import com.company.models.Category;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static List<Cashier> cashierUsersList;
    private static List<Category> categoryList;
    private static List<Book> booksList;



    public static void main(String[] args) throws IOException {
        cashierUsersList = new ArrayList<>();
        categoryList = new ArrayList<>();
        booksList = new ArrayList<>();

        loadDataFromFile();



        System.out.println("thimod");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loginApplication();

    }

    public void textFieldProp(TextField textField, double layoutX, double layoutY, double prefWidth, double prefHeight, String promptText) {
        textField.setLayoutX(layoutX);
        textField.setLayoutY(layoutY);
        textField.setPrefSize(prefWidth, prefHeight);
        textField.setPromptText(promptText);
        textField.setStyle("-fx-background-color: #6ea7c6, #ffffff;" +
                "-fx-background-radius: 15;" +
                "-fx-border-radius: 15;" +
                "-fx-border-color: #5c9ccd;");
    }

    public void labelProp(Label label, double layoutX, double layoutY, String fontSize) {
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setStyle(fontSize);
    }


    public void loginApplication(){
        Stage stage = new Stage();
        AnchorPane anchorPane = new AnchorPane();

        javafx.scene.control.Button singInButton = new javafx.scene.control.Button("Sign In");

        javafx.scene.control.Label userName = new javafx.scene.control.Label("User Name : ");
        javafx.scene.control.Label password = new javafx.scene.control.Label("Password : ");
        javafx.scene.control.Label userType = new javafx.scene.control.Label("User Type : ");
        javafx.scene.control.Label label = new javafx.scene.control.Label();

        javafx.scene.control.TextField textFieldUserName = new javafx.scene.control.TextField();
        javafx.scene.control.TextField textFieldCPass = new TextField();
        ComboBox<String> dropDown = new ComboBox<String>();

        dropDown.getItems().add("Manager");
        dropDown.getItems().add("Cashier");

        dropDown.setLayoutX(140);
        dropDown.setLayoutY(170);

        labelProp(userName, 60, 60, "-fx-font-size: 12;");
        labelProp(password, 60, 120, "-fx-font-size: 12;");
        labelProp(userType, 60, 180, "-fx-font-size: 12;");
        labelProp(label, 165, 20, "-fx-font-size: 16;");


        singInButton.setLayoutX(160);
        singInButton.setLayoutY(245);

        textFieldProp(textFieldUserName, 140, 55, 130, 35, "Enter User Name ");
        textFieldProp(textFieldCPass, 140, 115, 130, 35, "Enter Password ");

        singInButton.setOnAction(event -> {
            if (!(dropDown.getValue() == null)) {
                if (dropDown.getValue().equals("Cashier") & findUser(textFieldUserName.getText() ,textFieldCPass.getText())) {
                    stage.close();
                    cashierApplication();
                } else if (dropDown.getValue().equals("Manager") & textFieldUserName.getText().equals("") & textFieldCPass.getText().equals("")) {
                    stage.close();
                    managerApplication();
                } else
                    label.setText("Invalid ");
            } else
                label.setText("Invalid ");
        });


        anchorPane.getChildren().addAll(userName, userType, password, textFieldCPass, textFieldUserName, dropDown, singInButton, label);
        stage.setScene(new Scene(anchorPane, 400, 300));
        stage.show();

    }

    private void cashierApplication() {
        Stage stageGrid = new Stage();
        StackPane stackPane =  new StackPane();

        Button button_1 = new Button("View BookDetails");
        Button button_2 = new Button("Add Book");
        Button button_3 = new Button("Search BookDetails");
        Button button_5 = new Button("Save Data");
        VBox vBox1 =  new VBox();
        vBox1.getChildren().addAll(
                button_1,
                button_2,
                button_3,
                button_5
        );
        stackPane.getChildren().add(vBox1);
        stageGrid.setScene(new Scene(stackPane, 300 ,300));
        stageGrid.show();

        button_1.setOnAction(event -> {
            viewBooksDetailsApplication();
        });

        button_2.setOnAction(event -> {
            editBooksDetailsAndCatApplication();
        });

        button_3.setOnAction(event -> {
            searchBooksDetailsApplication();
        });
        button_5.setOnAction(event -> {
            try {
                storeDataIntoFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void managerApplication() {
        Stage stageGrid = new Stage();
        StackPane stackPane =  new StackPane();

        Button button_1 = new Button("View BookDetails");
        Button button_2 = new Button("Add Book");
        Button button_3 = new Button("Search BookDetails");
        Button button_4 = new Button("Add Cashier");
        Button button_5 = new Button("Save Data");


        VBox vBox1 =  new VBox();
        vBox1.getChildren().addAll(
                button_1,
                button_2,
                button_3,
                button_4,
                button_5
        );

        stackPane.getChildren().add(vBox1);
        stageGrid.setScene(new Scene(stackPane, 300 ,300));
        stageGrid.show();

        button_1.setOnAction(event -> {
            viewBooksDetailsApplication();
        });

        button_2.setOnAction(event -> {
           editBooksDetailsAndCatApplication();
        });

        button_3.setOnAction(event -> {
            searchBooksDetailsApplication();
        });

        button_4.setOnAction(event -> {
            createUserApplication();
        });

        button_5.setOnAction(event -> {
            try {
                storeDataIntoFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void viewBooksDetailsApplication(){
        Stage stageGrid = new Stage();
        StackPane stackPane =  new StackPane();

        TableView<Book> tableView = new TableView();

        TableColumn<Book,String >firstNameColumn = new  TableColumn<>("Book ID");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> secondNameColumn = new TableColumn<>("Book Name");
        secondNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));

        TableColumn<Book, String> thirdNameColumn = new  TableColumn<>("author");
        thirdNameColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> fourthNameColumn = new TableColumn<>("Price");
        fourthNameColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.getColumns().addAll(firstNameColumn,secondNameColumn,thirdNameColumn,fourthNameColumn);

        for (Book book:booksList) {
            tableView.getItems().add(book);
        }

        VBox vBox1 =  new VBox();
        vBox1.getChildren().add(tableView);

        stackPane.getChildren().add(vBox1);
        stageGrid.setScene(new Scene(stackPane, 600 ,600));
        stageGrid.show();

    }
    public void refreshDropDown(ComboBox<String> comboBox ){
        for (Category category :categoryList) {
            comboBox.getItems().add(category.getCategoryName());
        }
    }

    private void editBooksDetailsAndCatApplication(){
        Stage stageGrid = new Stage();
        Button button_1 =  new Button("Add new Category");
        Button button_2 = new Button("Add new Book with existing category");
        StackPane stackPane =  new StackPane();

        ComboBox<String> dropDown = new ComboBox<String>();

        refreshDropDown(dropDown);


        VBox vBox1 =  new VBox();
        vBox1.setSpacing(8);
        vBox1.setPadding(new Insets(10,10,10,10));
        vBox1.getChildren().addAll(
                button_1,
                dropDown,
                button_2
        );

        stackPane.getChildren().add(vBox1);
        stageGrid.setScene(new Scene(stackPane, 300 ,300));
        stageGrid.show();

        button_1.setOnAction(event -> {
            stageGrid.close();
            Stage stageGrid_1 = new Stage();
            StackPane stackPane_1 = new StackPane();
            Button button_add = new Button("Add");
            TextField textField_cat = new TextField();
            VBox vBox_1 = new VBox();
            vBox_1.setSpacing(8);
            vBox_1.setPadding(new Insets(10,10,10,10));
            vBox_1.getChildren().addAll(
                    new Label("Category Name : "),
                    textField_cat,
                    button_add

            );
            stackPane_1.getChildren().add(vBox_1);
            stageGrid_1.setScene(new Scene(stackPane_1, 300, 300));
            stageGrid_1.show();
            button_add.setOnAction(event1 -> {
                if (!textField_cat.getText().isEmpty()){
                    Category category =  new Category(textField_cat.getText());
                    categoryList.add(category);
                    stageGrid_1.close();
                    editBooksDetailsAndCatApplication();
                }
            });
        });

            button_2.setOnAction(event -> {
                Stage stageGrid_1 = new Stage();
                StackPane stackPane_1 = new StackPane();
                Button button_add = new Button("Add");

                TextField textField_1 = new TextField();
                TextField textField_2 = new TextField();
                TextField textField_3 = new TextField();
                TextField textField_4 = new TextField();


                VBox vBox_1 = new VBox();

                vBox_1.setSpacing(8);
                vBox_1.setPadding(new Insets(10,10,10,10));
                vBox_1.getChildren().addAll(
                        new Label("Book Name : "),
                        textField_1,
                        new Label("Author : "),
                        textField_2,
                        new Label("Price : "),
                        textField_3,
                        new Label("Book ID  : "),
                        textField_4,
                        button_add);

                stackPane_1.getChildren().add(vBox_1);
                stageGrid_1.setScene(new Scene(stackPane_1, 300, 300));
                stageGrid_1.show();

                button_add.setOnAction(event1 -> {
                    if (!(findCat(dropDown.getValue()) == null)){
                        Category categoryObj = findCat(dropDown.getValue());
                        Book book =  new Book(textField_4.getText() , textField_1.getText() , textField_2.getText() , Double.parseDouble(textField_3.getText()));
                        categoryObj.addBooks(book);
                        booksList.add(book);
                        stageGrid_1.close();
                        editBooksDetailsAndCatApplication();
                    }
                });
            });
    }

    private void searchBooksDetailsApplication(){
        Stage stageGrid = new Stage();
        StackPane stackPane =  new StackPane();

        TextField textField_1 = new TextField();
        TextField textField_2 = new TextField();
        TextField textField_3 = new TextField();
        TextField textField_4 = new TextField();
        Button button_1 =  new Button("Find");


        TableView<Book> tableView = new TableView();


        TableColumn<Book,String >firstNameColumn = new  TableColumn<>("Book ID");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> secondNameColumn = new TableColumn<>("Book Name");
        secondNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));

        TableColumn<Book, String> thirdNameColumn = new  TableColumn<>("author");
        thirdNameColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> fourthNameColumn = new TableColumn<>("Price");
        fourthNameColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.getColumns().addAll(firstNameColumn,secondNameColumn,thirdNameColumn,fourthNameColumn);

        VBox vBox_1 = new VBox();

        vBox_1.setSpacing(8);
        vBox_1.setPadding(new Insets(10,10,10,10));
        vBox_1.getChildren().addAll(
                new Label("Book Name : "),
                textField_1,
                new Label("Author : "),
                textField_2,
                new Label("Category : "),
                textField_3,
                new Label("Book ID  : "),
                textField_4,
                tableView,
                button_1
        );

        stackPane.getChildren().add(vBox_1);
        stageGrid.setScene(new Scene(stackPane, 600 ,600));
        stageGrid.show();

        button_1.setOnAction(event -> {
            tableView.getItems().clear();
            if (!(textField_1.getText().isEmpty())){
                for (Book books: booksList) {
                    if (books.getBookName().equals(textField_1.getText())){
                        tableView.getItems().add(books);

                    }
                }
            }else if (!textField_2.getText().isEmpty()){
                for (Book books: booksList) {
                    if (books.getAuthor().equals(textField_2.getText())){
                        tableView.getItems().add(books);
                    }
                }
            }else if (!textField_4.getText().isEmpty()){
                for (Book books: booksList) {
                    if (books.getBookId().equals(textField_4.getText())){
                        tableView.getItems().add(books);
                    }
                }
            }else if (!textField_3.getText().isEmpty()){
                for (Category books: categoryList) {
                    if (books.getCategoryName().equals(textField_3.getText())){
                        for (Book book: books.getAllBooks()) {
                            tableView.getItems().add(book);
                        }

                    }
                }
            }
        });


    }
    private void createUserApplication(){
        Stage stageGrid_1 = new Stage();
        StackPane stackPane_1 = new StackPane();
        Button button_add = new Button("Add");

        TextField textField_1 = new TextField();
        TextField textField_2 = new TextField();
        TextField textField_3 = new TextField();
        TextField textField_4 = new TextField();
        TextField textField_5 = new TextField();
        TextField textField_6 = new TextField();


        VBox vBox_1 = new VBox();

        vBox_1.setSpacing(8);
        vBox_1.setPadding(new Insets(10,10,10,10));
        vBox_1.getChildren().addAll(
                new Label("Name : "),
                textField_1,
                new Label("Password : "),
                textField_2,
                new Label("Age : "),
                textField_3,
                new Label("Address : "),
                textField_6,
                new Label("TeleNo  : "),
                textField_4,
                new Label("User ID  : "),
                textField_5,
                button_add);

        stackPane_1.getChildren().add(vBox_1);
        stageGrid_1.setScene(new Scene(stackPane_1, 600, 600));
        stageGrid_1.show();

        button_add.setOnAction(event -> {
            Cashier cashier =  new Cashier(
                    textField_1.getText(),
                    textField_2.getText() ,
                    textField_6.getText(),
                    Integer.parseInt(textField_3.getText()),
                    Integer.parseInt(textField_4.getText()),
                    textField_5.getText());

            cashierUsersList.add(cashier);
        });

    }

    private void storeDataIntoFile() throws Exception {
        String dataFile = "USERS.txt";
        String catFile = "CATEGORY.txt";
        String booksFile = "BOOKS.txt";

        FileOutputStream fileOut = new FileOutputStream(dataFile);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

        for (Cashier user: cashierUsersList) {
            if (!cashierUsersList.isEmpty()){
                objectOut.writeObject(user);
            }else
                throw new Exception("NO DATA !");

        }
        objectOut.close();

        FileOutputStream fileOut1 = new FileOutputStream(catFile);
        ObjectOutputStream objectOut1 = new ObjectOutputStream(fileOut1);

        for (Category category: categoryList) {
            if (!categoryList.isEmpty()){
                objectOut1.writeObject(category);
            }else
                throw new Exception("NO DATA !");

        }
        objectOut1.close();

        FileOutputStream fileOut2 = new FileOutputStream(booksFile);
        ObjectOutputStream objectOut2 = new ObjectOutputStream(fileOut2);

        for (Book book: booksList) {
            if (!booksFile.isEmpty()){
                objectOut2.writeObject(book);
            }else
                throw new Exception("NO DATA !");

        }
        objectOut2.close();

    }
    public <T> void  ReadObjectFromFile(String filepath  , List<T> list , Object OBJ) throws IOException {

//        FileInputStream fileInputStream = new FileInputStream(filepath);
//        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//        while (true) {
//            try {
//                list.add((OBJ) objectInputStream.readObject());
//            } catch (EOFException | ClassNotFoundException e) {
//                break;
//            }
//        }
//        objectInputStream.close();
//        fileInputStream.close();


    }

    private static void loadDataFromFile() throws IOException {
        String dataFile = "USERS.txt";
        String catFile = "CATEGORY.txt";
        String booksFile = "BOOKS.txt";


        FileInputStream fileInputStream = new FileInputStream(dataFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    cashierUsersList.add((Cashier) objectInputStream.readObject());
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }
            objectInputStream.close();
            fileInputStream.close();

        FileInputStream fileInputStream1 = new FileInputStream(catFile);
        ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);
        while (true) {
            try {
                categoryList.add((Category) objectInputStream1.readObject());
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        objectInputStream1.close();
        fileInputStream1.close();

        FileInputStream fileInputStream2 = new FileInputStream(booksFile);
        ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);
        while (true) {
            try {
                booksList.add((Book) objectInputStream2.readObject());
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        objectInputStream2.close();
        fileInputStream2.close();






    }

    public boolean findUser(String name ,  String pass){
        for (Cashier cashier : cashierUsersList) {
            if (cashier.getName().equals(name) & cashier.getPass().equals(pass)){
                return true;
            }
        }
        return false;
    }

    public Category findCat(String catName){
        for (Category category : categoryList) {
            if (category.getCategoryName().equals(catName)){
                return category;
            }
        }
        return null;

    }

}
