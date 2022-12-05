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
    public int size() {
        Node temp = head;
        int count = 0;
        while(temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }
    private boolean isEmpty() {
        return (head == null);
    }
    public Document get(int index) {
        if(isEmpty()) throw new IllegalStateException();
        Node a = head;
        Node b = head;
        for(int i=0; i < index-1;i++) {
            b = b.next;
            if(b==null) throw new IllegalArgumentException();
        }
        while(b != tail) {
            a = a.next;
            b = b.next;
        }
        return a.data;
    }



}