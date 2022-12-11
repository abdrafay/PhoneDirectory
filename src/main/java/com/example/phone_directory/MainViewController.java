package com.example.phone_directory;

import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.*;

import org.controlsfx.control.textfield.TextFields;

import static com.mongodb.client.model.Filters.all;
import static com.mongodb.client.model.Filters.eq;

import javax.print.Doc;
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

    private AutoCompletionBinding<String> autoCompletionBinding;

    private String[] suggestions = {""};

    private Set<String> suggestionsList = new HashSet<>();

    Contacts allContacts;
    public void initialize(URL url, ResourceBundle rs) {
        this.allContacts = new Contacts();
        LinkedList l = allContacts.getSortedContacts();
        l.getData(contactsLayout, this);
        autoCompletionBinding = TextFields.bindAutoCompletion(searchField, suggestionsList);
        srcBtn.setOnAction(e -> {
            System.out.println("Searched");
            var d = allContacts.searchByName(searchField.getText());

            if(d != null) {
                setSideView(d);
            }
        });

        searchField.setOnKeyPressed((KeyEvent e) -> {
                autoCompletionBinding.dispose();
                switch (e.getCode()) {
                    case ENTER:
                        var d = allContacts.searchByName(searchField.getText());
                        if(d != null)
                            setSideView(d);
                        break;
                    default:
                        updateFieldData(searchField.getText());
                        break;
                }
           });


    }
    public void deleteDocument(Document document) {
        MongoCollection<Document> collection = Main.db.getCollection("Contacts");
//        Bson filter = eq("phone_no", document.getString("phone_no"));
//        collection.deleteOne(filter);
//        collection.findOneAndDelete(document);
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
    private void updateFieldData(String text) {
//        https://stackoverflow.com/questions/45778462/update-autocomplete-javafx
        List<String> list = allContacts.getSuggestions(text.trim());
        suggestionsList.clear();
        for(String item : list) {
            suggestionsList.add(item);
        }
        autoCompletionBinding.dispose();
        if(list != null) {
            autoCompletionBinding = TextFields.bindAutoCompletion(searchField, suggestionsList);
        }

    }
    @FXML
    private void createContactButtonAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
//            Parent parent = Main.loadFXML("create-contact").load();
            loader.setLocation(getClass().getResource("create-contact.fxml"));
            loader.load();
            CreateContact c = loader.getController();
            Parent  parent = loader.getRoot();
            Scene scene = new Scene(parent);
//            System.out.println(c.setLayout(scene));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            contactsLayout.getChildren().clear();
//            allContacts.update(c.getDocument());
            if(c.getDocument() != null) {
                allContacts.update(c.getDocument());
                LinkedList l = allContacts.getSortedContacts();
                l.getData(contactsLayout, this);
            }

        }catch (Exception e) {
            e.getMessage();
        }
    }

}
