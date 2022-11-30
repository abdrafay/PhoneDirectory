package com.example.phone_directory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.bson.Document;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private Label myname;

    public void initialize(URL url, ResourceBundle rs) {
        System.out.println("hello");
        for(Document item : Main.contacts){
            System.out.println(item.get("first_name"));
        }
    }

}
