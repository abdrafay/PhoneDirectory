package com.example.phone_directory;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DB {
    MongoClient client;
    MongoDatabase database;
    DB() {
        client = MongoClients.create("mongodb+srv://zrdatastore:PYSs2Xuydz7maLG5@cluster0.qnire.mongodb.net");
        database = client.getDatabase("PhoneDirectory");
    }
     List<Document> getCollection(String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection.find().into(new ArrayList<>());
    }

}
