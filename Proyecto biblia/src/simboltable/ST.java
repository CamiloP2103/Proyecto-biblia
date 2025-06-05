/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simboltable;

import java.util.Iterator;
/**
 * La clase ST implementa una tabla de símbolos genérica basada en una lista enlazada.
 * Permite almacenar pares clave-valor y realizar operaciones básicas de búsqueda e inserción.
 * 
 * @author Rodrigo
 * @author Juan
 * @author Camilo
 * @param <Key> El tipo de dato para las claves.
 * @param <Value> El tipo de dato para los valores.
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
    
    /**
     * Obtiene el valor asociado a una clave.
     * 
     * @param key La clave a buscar.
     * @return El valor asociado o null si no existe.
     */
    public Value get(Key key){
        for(Node x = first;x != null; x = x.next)
            if (key.equals(x.key))
                return x.value;
        return null;
    }
    
    /**
     * Inserta o actualiza un par clave-valor en la tabla.
     * 
     * @param key La clave a insertar/actualizar.
     * @param value El valor a asociar.
     */
    public void put(Key key, Value value){
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)){
                x.value = value;
                return;
            }
        first = new Node(key , value, first);
        count++;
    }
    
    /**
     * Elimina una clave y su valor asociado de la tabla.
     * 
     * @param key La clave a eliminar.
     */
    public void delete(Key key){
        put(key, null);
        count--;
    }
    
    /**
     * Verifica si una clave existe en la tabla.
     * 
     * @param key La clave a buscar.
     * @return true si la clave existe, false en caso contrario.
     */
    public boolean contains(Key key){
        return get(key) != null;
    }
    
    /**
     * Verifica si la tabla está vacía.
     * 
     * @return true si la tabla está vacía, false en caso contrario.
     */
    public boolean isEmpty(){
        //return first == null;
        return size() == 0;
    }
    
    /**
     * Devuelve el número de elementos en la tabla.
     * 
     * @return El número de elementos.
     */
    public int size(){
        return count;
    }
    
    /**
     * Proporciona un iterador para recorrer las claves de la tabla.
     * 
     * @return Un iterador para las claves.
     */
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
