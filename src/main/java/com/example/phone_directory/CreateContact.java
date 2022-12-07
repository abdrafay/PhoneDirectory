package com.example.phone_directory;

import com.mongodb.client.MongoCollection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateContact implements Initializable {
    @FXML
    private  TextField emailField;

    @FXML
    private TextField firstNField;

    @FXML
    private TextField lastNField;

    @FXML
    private Button newContact;

    @FXML
    private TextField phoneField;
    public Document document;

    public Document setLayout(Scene scn) {
        System.out.println("in create contact");
        Stage stage = new Stage();
        stage.setScene(scn);
        newContact.setOnAction(e -> {
            create();
            stage.close();
        });
        stage.showAndWait();
        return document;
    }
    public Document getDocument() {
        if (document != null) return document;
        return null;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void create() {
        this.document = new Document("_id", new ObjectId());
        document.append("first_name", firstNField.getText()).append("last_name", lastNField.getText()).append("email", emailField.getText()).append("phone_no", phoneField.getText());
        MongoCollection<Document> collection = Main.db.getCollection("Contacts");
        var c = collection.insertOne(document);
        Stage stage = (Stage) newContact.getScene().getWindow();
        stage.close();
//        return document;
    }
}
