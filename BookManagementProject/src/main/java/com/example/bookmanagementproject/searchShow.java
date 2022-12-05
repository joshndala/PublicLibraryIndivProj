package com.example.bookmanagementproject;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.net.HttpURLConnection;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class searchShow{

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private Button cancelButton;

    @FXML
    private Label nameLabel;
    @FXML
    private Label genreLabel;
    @FXML
    private Label runtimeLabel;
    @FXML
    private Label ratingLabel;
    @FXML
    private Label premieredLabel;

    @FXML
    private Label summaryLabel;

    @FXML
    private Text name;

    @FXML
    private Text genre;

    @FXML
    private Text runtime;

    @FXML
    private Text rating;

    @FXML
    private Text premiered;

    @FXML
    private Text summary;

    @FXML
    private Text noSearch;

    @FXML
    private ImageView imageView;

    private String searchText;

    public void search(){
        setSearchText(searchField.getText());

        try {
            URL url = new URL("https://api.tvmaze.com/singlesearch/shows?q=\"" + getSearchText() + "\"");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                //throw new RuntimeException("HttpResponseCode: " + responseCode);
                name.setText("");
                genre.setText("");
                runtime.setText("");
                rating.setText("");
                premiered.setText("");
                summary.setText("");

                nameLabel.setText("");
                genreLabel.setText("");
                runtimeLabel.setText("");
                ratingLabel.setText("");
                premieredLabel.setText("");
                summaryLabel.setText("");

                noSearch.setText("Nothing Found");
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

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
                noSearch.setText("");
                nameLabel.setText("Name:");
                name.setText(dataObject.get("name").toString());

                genreLabel.setText("Genres:");
                String str = dataObject.get("genres").toString();
                genre.setText(str.substring(1, str.length() - 1));

                runtimeLabel.setText("Runtime:");
                runtime.setText(dataObject.get("averageRuntime").toString());

                premieredLabel.setText("Premiered:");
                premiered.setText(dataObject.get("premiered").toString());

                str = dataObject.get("rating").toString();

                if(!str.equals("null")) {
                    ratingLabel.setText("Rating:");
                    rating.setText(str.substring(11, str.length() - 1));
                }

                summaryLabel.setText("Description:");
                str = dataObject.get("summary").toString();
                String plainText = str.replaceAll("<p>", "");
                plainText = plainText.replaceAll("<b>", "");
                plainText = plainText.replaceAll("</b>", "");
                plainText = plainText.replaceAll("</p>", "");
                plainText = plainText.replaceAll("<i>", "");
                plainText = plainText.replaceAll("</i>", "");
                summary.setText(plainText);


                str = dataObject.get("image").toString();
                System.out.println(str);

                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parse.parse(str);

                str = jsonObject.get("original").toString();

                System.out.println(str);

                if(!str.equals(null)) {
                    imageView = new ImageView();
                    Image image1 = new Image(str, 0, 100, false, false);
                    imageView.setImage(image1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancel(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public String getSearchText(){
        return searchText;
    }

    public void setSearchText(String s){
        searchText = s;
    }


}
