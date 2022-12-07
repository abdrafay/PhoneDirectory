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
import org.controlsfx.control.textfield.TextFields;
import static com.mongodb.client.model.Filters.eq;

import javax.print.Doc;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;


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

    private Set<String> suggestionsList = new HashSet<>(Arrays.asList(suggestions));
    Contacts allContacts;
    public void initialize(URL url, ResourceBundle rs) {
        System.out.println("hello");
//        for(Document item : Main.contacts){
//
//        }
        this.allContacts = new Contacts();
        LinkedList l = allContacts.getSortedContacts();
        l.getData(contactsLayout, this);
        autoCompletionBinding = TextFields.bindAutoCompletion(searchField, suggestions);
        srcBtn.setOnAction(e -> {
            System.out.println("Searched");
            var d = allContacts.searchByName(searchField.getText());

            if(d != null) {
                setSideView(d);
            }
        });

        searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                if(keyEvent.getCode() == KeyCode.ENTER) {
                    System.out.println("Entered");
                    var d = allContacts.searchByName(searchField.getText());
                    if(d != null)
                        setSideView(d);
                } else {
                    autoCompletionBinding.dispose();
                    try {
                        keyEvent.wait();
                    } catch (Exception e) {
                        System.out.println("waiting");
                    }
                    updateFieldData(searchField.getText());
                }
//                switch (keyEvent.getCode()) {
//                    case ENTER:
//
//                        break;
//                    default:
//
//                        break;
//                }
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
        System.out.println("text");
//        System.out.println(text);
        System.out.println(text);
        System.out.println(allContacts.getSuggestions(text));
//        suggestionsList.
//        suggestionsList = new HashSet<>(allContacts.getSuggestions(text));
//        if(autoCompletionBinding != null) {
//            autoCompletionBinding.dispose();
//        }


        autoCompletionBinding.dispose();
        autoCompletionBinding = TextFields.bindAutoCompletion(searchField, allContacts.getSuggestions(text.trim()));
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
