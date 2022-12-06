package com.example.bookmanagementproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.events.MouseEvent;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AllBooksUser implements Initializable {

    @FXML
    private TableView<Books> booksTableUser;

    @FXML
    private TableColumn<Books, String> col_ISBN;

    @FXML
    private TableColumn<Books, String> col_author;

    @FXML
    private TableColumn<Books, String> col_bookName;

    @FXML
    private TableColumn<Books, String> col_category;

    @FXML
    private TableColumn<Books, Integer> col_inventory;

    @FXML
    private Text title;

    @FXML
    private Text description;

    private Connection connect;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    public ObservableList<Books> dataList(){

        ObservableList<Books> listBooks = FXCollections.observableArrayList();

        String sql = "select * from Book";
        connect = Database.connectDB();
        try {
            Books books;
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                books = new Books(resultSet.getString("bookName"), resultSet.getString("author"), resultSet.getString("ISBN"), resultSet.getString("category"), resultSet.getInt("inventory"));
                listBooks.add(books);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listBooks;
    }

    private ObservableList<Books> listBook;

    public void showBooksUser(){
        listBook = dataList();
        col_bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_ISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        col_inventory.setCellValueFactory(new PropertyValueFactory<>("inventory"));

        booksTableUser.setItems(listBook);
    }

    public void displaySelected(){
        Books book = booksTableUser.getSelectionModel().getSelectedItem();
        String isbn = book.getISBN();

        try {
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn );

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                //throw new RuntimeException("HttpResponseCode: " + responseCode);
                System.out.println("error");
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                //String to JSONObject
                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));


                JSONArray itemsArray = (JSONArray) dataObject.get("items");
                for (int i = 0; i < itemsArray.size(); i++) {
                    JSONObject itemsObj = (JSONObject) itemsArray.get(i);
                    JSONObject volumeObj = (JSONObject) itemsObj.get("volumeInfo");


                    String titl = volumeObj.get("title").toString();
                    String desc = volumeObj.get("description").toString();

                    title.setText(titl);
                    description.setText(desc);
                }

                //Get Information
//                title.setText(dataObject.get("title").toString());
//                rating.setText(dataObject.get("averageRating").toString());
//                description.setText(dataObject.get("description").toString());

//                String str = dataObject.get("items").toString();
//                System.out.println(str);
//
//                JSONParser parser = new JSONParser();
//                JSONObject obj  = (JSONObject) parser.parse(str.substring(1, str.length()-1));
//                JSONArray array = new JSONArray();
//                array.add(obj);
//
//                title.setText(obj.get("title").toString());
//                title.setText(obj.get("averageRating").toString());
//                title.setText(obj.get("description").toString());
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBooksUser();
    }
}
