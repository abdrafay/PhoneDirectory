package com.example.phone_directory;

import org.bson.Document;
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
    public void print(){
        if(head == null)
        {
            System.out.println("List is empty");
            return;
        }
        Node current = head;
        while(current != null){
            System.out.println(current.data+" ");
            current = current.next;
        }
        System.out.println();
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

}