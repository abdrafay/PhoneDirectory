package com.example.phone_directory;

import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;
import org.controlsfx.control.textfield.TextFields;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class MainViewController implements Initializable {
    @FXML
    Label name,email, number;
    @FXML
    private Button srcBtn;
    @FXML
    private VBox contactsLayout;
    @FXML
    private Button createContactBtn;
    @FXML
    private TextField searchField;

    private String[] suggestions = {""};

    private Set<String> suggestionsList = new HashSet<>();

    Contacts allContacts;
    public void initialize(URL url, ResourceBundle rs) {
        this.allContacts = new Contacts();
        LinkedList l = allContacts.getSortedContacts();
        l.getData(contactsLayout, this);
        TextFields.bindAutoCompletion(searchField, allContacts.getSuggestions(searchField.getText()));
        srcBtn.setOnAction(e -> {
            var d = allContacts.searchByName(searchField.getText());

            if(d != null) {
                setSideView(d);
            }
        });

        searchField.setOnKeyPressed((KeyEvent e) -> {
                switch (e.getCode()) {
                    case ENTER:
                        var d = allContacts.searchByName(searchField.getText());
                        if(d != null)
                            setSideView(d);
                        break;
                    default:
                        break;
                }
           });


    }
    public void deleteDocument(Document document) {
        MongoCollection<Document> collection = Main.db.getCollection("Contacts");
        allContacts.delete(document);
        contactsLayout.getChildren().clear();
        LinkedList l = allContacts.getSortedContacts();
        l.getData(contactsLayout, this);
    }
    private void setSideView(Document d) {
        this.name.setText(d.getString("first_name") + " " + d.getString("last_name"));
        this.number.setText(d.getString("phone_no"));
        this.email.setText(d.getString("email"));
    }
    @FXML
    private void createContactButtonAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("create-contact.fxml"));
            loader.load();
            CreateContact c = loader.getController();
            Parent  parent = loader.getRoot();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            contactsLayout.getChildren().clear();
            if(c.getDocument() != null) {
                allContacts.update(c.getDocument());
            }
            LinkedList l = allContacts.getSortedContacts();
            l.getData(contactsLayout, this);

        }catch (Exception e) {
            e.getMessage();
        }
    }

}
