package com.example.phone_directory;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.bson.Document;

import java.awt.*;

class LinkedList {
    private class Node{
        Document data;
        Node previous, next;

        public Node(Document data) {
            this.data = data;
        }
    }
    private Node head , tail;

    public void insert(Document data){
        Node node  = new Node(data);
        if(head == null){
            head = tail = node;
        }
        else{
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
    }
    public void delete(Document data){
        int index = search(head,data);
        if(head == null)
        {
            System.out.println("List is empty");
            return;
        }
        Node current = head,temp;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        if(current == head){
            temp = head;
            temp = temp.next;
            head = temp;
            temp.previous = null;
        } else if (current == tail) {
            temp = tail;
            temp = temp.previous;
            tail = temp;
            temp.next = null;
        }
        else{
            current.previous.next = current.next;
            current.next.previous = current.previous;
            current = null;
        }
    }
    private int search(Node head_ref, Document x) {
        Node temp = head_ref;
        int pos = 0;
        while (temp.data != x && temp.next != null) {
            pos++;
            temp = temp.next;
        }
        if (temp.data != x)
            return -1;
        return pos;
    }
    public int size() {
        Node temp = head;
        int count = 0;
        while(temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }
    public void getData(VBox obj) {
        Node temp = head;
        if(head == null)
        {
            Label label = new Label();
            label.setText("No Item Found");
//            obj.getChildren().add(label);

            return;
        }
        while(temp != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("contact-item.fxml"));
            System.out.println("loaded");
            try{
                HBox hbox = loader.load();
                ContactItemController cic = loader.getController();
                cic.setData(temp.data);
                obj.getChildren().add(hbox);

            }catch (Exception e) {
                e.printStackTrace();
            }
            temp = temp.next;
        }
    }
}