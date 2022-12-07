package com.example.phone_directory;

import org.bson.Document;

import java.util.List;

public class Contacts {
    private Trie trie;
    private NAVLTree navlTree;
    private CAVLTree cavlTree;
    public Contacts(){
        trie = new Trie();
//        navlTree = new NAVLTree();
        cavlTree = new CAVLTree();
        for (int i = 0; i < Main.contacts.size(); i++) {
            String name = (String) Main.contacts.get(i).get("first_name")+(String) Main.contacts.get(i).get("last_name");
            trie.insert(name);
//            navlTree.insert(Main.contacts.get(i));
            cavlTree.insert(Main.contacts.get(i));
        }
    }
    public LinkedList getSortedContacts(){
        return cavlTree.InOrder();
    }
    public List<String> getSuggestions(String prefix){
        return trie.getSuggestions(prefix);
    }
    public void update(Document doc){
        trie.insert(((String) doc.get("first_name")+(String)doc.get("last_name")));
//        navlTree.insert(doc);
        cavlTree.insert(doc);
    }
    public void delete(Document doc){
        trie.remove(((String) doc.get("first_name")+(String)doc.get("last_name")));
//        navlTree.delete(Long.parseLong((String)doc.get("phone_no")));
        cavlTree.delete(Long.parseLong((String)doc.get("phone_no")));
    }
    public Document searchByName(String name){
        return cavlTree.search(name);
    }
}
