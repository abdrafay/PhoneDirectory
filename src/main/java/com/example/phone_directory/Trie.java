package com.example.phone_directory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Trie {
    private class Node{
        private char value;
        private Map<Character, Node> children = new HashMap<>();
        private boolean endOfWord = false;
        public Node(char value){
            this.value = value;
        }
        public boolean hasChild(char ch){
            return children.containsKey(ch);
        }
        public boolean hasChildren(){
            return !children.isEmpty();
        }
        public void addChild(char ch){
            children.put(ch,new Node(ch));
        }
        public void removeChild(char ch){
            children.remove(ch);
        }
        public Node getChild(char ch){
            return children.get(ch);
        }
        public Node[] getChildren(){
            return children.values().toArray(new Node[0]);
        }
    }
    private Node root = new Node(' ');
    public void insert(String word) {
        if(word == null)
            return;
        Node current = root;
        for(char ch: word.toCharArray()){
            if(!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.endOfWord=true;
    }
    public boolean contains(String word) {
        if(word == null)
            return false;
        Node current = root;
        for(char ch: word.toCharArray()){
            if(!current.hasChild(ch))
                return false;
            current = current.getChild(ch);
        }
        return current.endOfWord;
    }
    public void remove(String word) {
        if(word == null)
            return;
        remove(root, word, 0);
    }
    private void remove(Node root, String word, int index){
        if(index == word.length()){
            root.endOfWord = false;
            return;
        }
        var ch = word.charAt(index);
        var child = root.getChild(ch);
        if(child == null)
            return;
        remove(child, word, index + 1);
        if(!child.hasChildren() && !child.endOfWord){
            root.removeChild(ch);
        }
    }
    public List<String> getSuggestions(String prefix){
        var lastNode = findLastNode(prefix);
        List<String> words = new ArrayList<>();
        getSuggestions(lastNode, prefix, words);
        return words;
    }
    private void getSuggestions(Node root, String prefix, List<String> word){
        if(root == null)
            return;
        if(root.endOfWord)
            word.add(prefix);
        for (var child: root.getChildren())
            getSuggestions(child, prefix + child.value, word);
    }
    private Node findLastNode(String prefix){
        if(prefix == null)
            return null;
        var current = root;

        for(char ch: prefix.toCharArray()){
            var child = current.getChild(ch);
            if(child == null)
                return null;
            current = child;
        }
        return current;
    }

}