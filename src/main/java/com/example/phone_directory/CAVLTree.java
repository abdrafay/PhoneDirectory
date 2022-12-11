package com.example.phone_directory;
import org.bson.Document;


public class CAVLTree {
    private class Node {
        Document data;
        int height;
        Node left, right;

        public Node(Document data) {
            this.data = data;
        }
    }
    private Node root;
    public void insert(Document item) {
        root = insert(root, item);
    }
    private Node insert(Node node, Document data) {
        if(node == null)
            return new Node(data);
        if(name(data).compareToIgnoreCase(name(node.data)) < 0)
            node.left = insert(node.left, data);
        else
            node.right = insert(node.right, data);
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return balance(node);
    }
    private int balanceFactor(Node node) {
        return height(node.left) - height(node.right);
    }
    public Node balance(Node node) {
        if (isLeftHeavy(node)) {
            if (balanceFactor(node.left) < 0)
                node.left = leftRotate(node.left);
            return rightRotate(node);
        } else if (isRightHeavy(node)) {
            if (balanceFactor(node.right) > 0)
                node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    private Node leftRotate(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
        return newRoot;
    }
    private Node rightRotate(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return newRoot;
    }
    private boolean isLeftHeavy(Node node) {
        return height(node.left) - height(node.right) > 1;
    }
    private boolean isRightHeavy(Node node) {
        return height(node.left) - height(node.right) < -1;
    }
    private int height(Node node) {
        if(node == null)
            return -1;
        return node.height;
    }
    public void delete(String target){
        target = target.replaceAll(" ","");
        target = target.toLowerCase();
        root = delete(root,target);
    }
    private Node delete(Node node, String target) {
        if(node == null)
            return null;
        if(node.left == null && node.right == null){
            node = null;
            return null;
        }
        if(target.compareToIgnoreCase(name(node.data)) < 0)
            node.left = delete(node.left, target);
        else if (target.compareToIgnoreCase(name(node.data)) > 0)
            node.right = delete(node.right, target);
        else{
            Node successor = succ(node.right);
            node.data = successor.data;
            node.right = delete(node.right, name(successor.data));
        }
        return balance(node);
    }
    private Node succ(Node node) {
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
    private String name(Document doc){
        return (doc.get("first_name")+(String)doc.get("last_name")).replaceAll(" ","").toLowerCase();
    }
    public LinkedList InOrder(){
        LinkedList list = new LinkedList();
        InOrder(root,list);
        return list;
    }
    private void InOrder(Node root, LinkedList list){
        if(root == null){
            return;
        }
        InOrder(root.left, list);
        list.insert(root.data);
        InOrder(root.right, list);

    }
    public Document search(String name){
        name = name.replaceAll(" ","");
        name = name.toLowerCase();
        return search(root,name);
    }
    private Document search(Node root, String name){
        if(root == null){
            return null;
        }
        if(name(root.data).compareToIgnoreCase(name) == 0){
            return root.data;
        }
        else if(name(root.data).compareToIgnoreCase(name) > 0)
            return search(root.left, name);
        else
            return search(root.right, name);
    }
}