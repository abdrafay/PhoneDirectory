package com.example.phone_directory;

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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class MainViewController implements Initializable {
    @FXML
    private Label myname;
    @FXML
    private VBox contactsLayout;
    @FXML
    private Button createContactBtn;
    @FXML
    private TextField searchField;

    private AutoCompletionBinding<String> autoCompletionBinding;

    private String[] suggestions = {""};

    private Set<String> suggestionsList = new HashSet<>(Arrays.asList(suggestions));

    public void initialize(URL url, ResourceBundle rs) {
        System.out.println("hello");
//        for(Document item : Main.contacts){
//
//        }
        Contacts c = new Contacts();
        LinkedList l = c.getSortedContacts();
        l.getData(contactsLayout);
        autoCompletionBinding = TextFields.bindAutoCompletion(searchField, suggestionsList);
        searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case ENTER:
                        System.out.println("Entered");
                        break;
                    default:
                        updateFieldData();
                        break;
                }
            }
        });

    }
    private void updateFieldData() {
        suggestionsList = new HashSet<>(Arrays.asList("heelo", "testing"));
        if(autoCompletionBinding != null) {
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(searchField, suggestionsList);
    }
    @FXML
    private void createContactButtonAction(ActionEvent event) throws IOException {
        try {
            Parent parent = Main.loadFXML("create-contact").load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e) {
            e.getMessage();
        }
    }

}
