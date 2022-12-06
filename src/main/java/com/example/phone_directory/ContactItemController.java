package com.example.phone_directory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactItemController implements Initializable {

    @FXML
    private Button editBtn;

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label phone;

    @FXML
    private Button viewBtn;

    public void setData(Document document) {
        this.name.setText(document.getString("first_name") + " " + document.getString("last_name"));
        this.phone.setText(document.getString("phone_no"));
        this.email.setText(document.getString("email"));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
