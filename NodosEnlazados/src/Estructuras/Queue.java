package Estructras;

import java.util.Iterator;

/**
 * La clase Queue implementa una cola genérica basada en una lista enlazada.
 * Esta estructura de datos sigue el principio FIFO (First-In-First-Out),
 * donde el primer elemento agregado es el primero en ser eliminado.
 * 
 * @author Rodrigo
 * @author Juan
 * @author Camilo
 * @param <Item> El tipo de elemento que contendrá esta cola.
 */
public class Queue <Item> implements Iterable<Item>{
    private Node first;
    private Node last;
    private int count;
    
    /**
     * Constructor que inicializa una cola vacía.
     */
    public Queue(){
        first = null;
        last = null;
        count = 0;
    }
    
    /**
     * Clase interna que representa un nodo en la lista enlazada.
     */
    private class Node{
        Item item;
        Node next;
    }
    
    /**
     * Agrega un elemento al final de la cola.
     * 
     * @param item El elemento a ser agregado.
     */
    public void enqueue(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        oldlast.next = last;
        if(isEmpty())
            first = last;
        count++;
    }
    
    /**
     * Elimina y devuelve el primer elemento de la cola.
     * 
     * @return El elemento eliminado.
     */
    public Item dequeue(){
        Item item = first.item;
        first.item = null;
        first = first.next;
        count--;
        if(isEmpty())
            last = null;
        return item;
    }
    
    /**
     * Verifica si la cola está vacía.
     * 
     * @return true si la cola está vacía, false en caso contrario.
     */
    public boolean isEmpty(){return first == null;}
    
    /**
     * Devuelve el número de elementos en la cola.
     * 
     * @return El número de elementos.
     */
    public int size(){return count;}
    
    /**
     * Proporciona un iterador para recorrer los elementos de la cola.
     * 
     * @return Un iterador para los elementos de la cola.
     */
    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }
    
    /**
     * Clase interna que implementa el iterador para la cola.
     */
    private class LinkedListIterator implements Iterator{
        
        private Node current;
        
        /**
         * Verifica si hay más elementos por recorrer.
         * 
         * @return true si hay más elementos, false en caso contrario.
         */
        @Override
        public boolean hasNext() {return current != null;}

        /**
         * Devuelve el siguiente elemento en la iteración.
         * 
         * @return El siguiente elemento.
         */
        @Override
        public Object next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}