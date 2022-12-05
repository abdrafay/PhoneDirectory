package com.example.phone_directory;

import java.util.HashMap;
//((String)word.get("first_name")+word.get("last_name"))
public class Trie {
    private class Node{
        private char value;
        private HashMap<Character, Node> children = new HashMap<>();
        private boolean isEndOfWord;

        public Node(char value) {
            this.value = value;
        }
        public boolean hasChild(char ch){
            return children.containsKey(ch);
        }
        public void addChild(char ch){
            children.put(ch,new Node(ch));
        }
        public Node getChild(char ch){
            return children.get(ch);
        }
        public boolean hasChildren(){
            return !children.isEmpty();
        }
        public void removeChild(char ch){
            children.remove(ch);
        }
    }
    private Node root = new Node(' ');
    public void insert(String word){
        if(word == null){
            return;
        }
        var current = root;
        for (var ch: word.toCharArray()) {
            if(!current.hasChild(ch))
                current.addChild(ch);
            current.getChild(ch);
        }
        current.isEndOfWord = true;
    }
    public void remove(String word) {
        if(word == null)
            return;
        remove(root, word, 0);
    }
    private void remove(Node root, String word, int index){
        if(index == word.length()){
            root.isEndOfWord = false;
            return;
        }
        var ch = word.charAt(index);
        var child = root.getChild(ch);
        if(child == null)
            return;
        remove(child, word, index + 1);
        if(!child.hasChildren() && !child.isEndOfWord){
            root.removeChild(ch);
        }
    }
}
