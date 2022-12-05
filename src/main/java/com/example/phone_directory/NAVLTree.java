package com.example.phone_directory;

import org.bson.Document;

class NAVLTree {
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
        if(number(data) < number(node.data))
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
    public void delete(long target){
        delete(root,target);
    }
    private Node delete(Node node, long target) {
        if(node == null)
            return null;
        if(node.left == null && node.right == null){
            node = null;
            return null;
        }
        if(target < number(node.data))
            node.left = delete(node.left, target);
        else if (target > number(node.data))
            node.right = delete(node.right, target);
        else{
            Node successor = succ(node.right);
            node.data = successor.data;
            node.right = delete(node.right, number(successor.data));
        }
        return balance(node);
    }
    private Node succ(Node node) {
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
    private long number(Document doc){
        return Long.parseLong((String)doc.get("phone_no"));
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
    public Document search(Long number){
        return search(root,number);
    }
    private Document search(Node root, Long number){
        if(root == null){
            return null;
        }
        System.out.println(number(root.data)+"=="+number);
        if(number(root.data) == number){
            return root.data;
        }
        else if(number < number(root.data))
            return search(root.left, number);
        else
            return search(root.right, number);
    }

}
