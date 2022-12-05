package com.example.phone_directory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private Label myname;
    @FXML
    private VBox contactsLayout;
    @FXML
    private Button createContactBtn;

    public void initialize(URL url, ResourceBundle rs) {
        System.out.println("hello");
//        for(Document item : Main.contacts){
//
//        }
        Contacts c = new Contacts();
        LinkedList l = c.getSortedContacts();
        for(int i =0; i < l.size(); i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("contact-item.fxml"));
            try{
                HBox hbox = loader.load();
                ContactItemController cic = loader.getController();
                cic.setData(l.get(i));
                contactsLayout.getChildren().add(hbox);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
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
