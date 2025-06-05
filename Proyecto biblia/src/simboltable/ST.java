/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simboltable;

import java.util.Iterator;
/**
 *
 * @author priet
 */
public class ST<Key, Value> implements Iterable<Key>{
    private Node first;
    private int count;
    
    private class Node{
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    
    public Value get(Key key){
        for(Node x = first;x != null; x = x.next)
            if (key.equals(x.key))
                return x.value;
        return null;
    }
    
    public void put(Key key, Value value){
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)){
                x.value = value;
                return;
            }
        first = new Node(key , value, first);
        count++;
    }
    
    public void delete(Key key){
        put(key, null);
        count--;
    }
    
    public boolean contains(Key key){
        return get(key) != null;
    }
    
    public boolean isEmpty(){
        //return first == null;
        return size() == 0;
    }
    
    public int size(){
        return count;
    }
    
    @Override
    public Iterator<Key> iterator() {
        return new LinkedListIterator();
    }
    
    private class LinkedListIterator implements Iterator{
        
        private Node current;
        
        public LinkedListIterator() {
        current = first; // ← IMPORTANTE
        }
        
        @Override
        public boolean hasNext() {return current != null;}

        @Override
        public Object next() {
            Key key = current.key;
            current = current.next;
            return key;
        }
    }
}
