package com.example.phone_directory;

import org.bson.Document;

import java.util.List;

public class Contacts {
    private Trie trie;
    private CAVLTree cavlTree;
    public Contacts(){
        trie = new Trie();
        cavlTree = new CAVLTree();
        for (int i = 0; i < Main.contacts.size(); i++) {
            String name = (String) Main.contacts.get(i).get("first_name")+" "+(String) Main.contacts.get(i).get("last_name");
            trie.insert(name);
            cavlTree.insert(Main.contacts.get(i));
        }
    }
    public List<String> getSuggestions(String prefix){
        return trie.getSuggestions(prefix);
    }
    public LinkedList getSortedContacts(){
        return cavlTree.InOrder();
    }
    public void update(Document doc){
        trie.insert((doc.get("first_name")+" "+(String)doc.get("last_name")));
        cavlTree.insert(doc);
    }
    public void delete(Document doc){
        trie.remove(doc.get("first_name")+" "+(String)doc.get("last_name"));
        cavlTree.delete(doc.get("first_name")+" "+(String)doc.get("last_name"));
    }
    public Document searchByName(String name){
        return cavlTree.search(name);
    }
}
