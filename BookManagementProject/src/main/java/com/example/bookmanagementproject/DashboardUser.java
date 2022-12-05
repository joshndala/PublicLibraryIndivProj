package com.example.bookmanagementproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DashboardUser implements Initializable {
    @FXML
    private Button books;

    @FXML
    private Button checkoutBook;

    @FXML
    private Label labelTopUser;

    @FXML
    private Button logout;

    @FXML
    private Button ownedBooks;

    @FXML
    private Button returnBook;

    @FXML
    private Button search;

    @FXML
    private Button tvshow;

    @FXML
    private Label setup;

    @FXML
    private Label delivery;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelTopUser.setText("Welcome " + LoginController.usernameUsed + "!");

        try{
            URL url2 = new URL("https://v2.jokeapi.dev/joke/Miscellaneous,Pun,Spooky," +
                    "Christmas?blacklistFlags=nsfw,racist,sexist,explicit&type=twopart");

            HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            StringBuilder informationString = new StringBuilder();
            Scanner scanner = new Scanner(url2.openStream());

            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
            //Close the scanner
            scanner.close();

            System.out.println(informationString);

            //String to JSONObject
            JSONParser parse = new JSONParser();
            JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));

            //Get Information
            setup.setText("\"" + dataObject.get("setup").toString() + "\"");
            delivery.setText("\"" + dataObject.get("delivery").toString() + "\"");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void booksPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("AllBooksUser.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkoutPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("checkoutBookForUser.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void returnPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("returnBookForUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void issuedPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("issuedBookForUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("searchBookForUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logoutPressed(){
        try{
            logout.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTvshow(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("searchShow.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
