package Estructras;

import java.util.Iterator;

/**
 * La clase Stack implementa una pila genérica basada en una lista enlazada.
 * Esta estructura de datos sigue el principio LIFO (Last-In-First-Out),
 * donde el último elemento agregado es el primero en ser eliminado.
 * 
 * @author Rodrigo
 * @author Juan
 * @author Camilo
 * @param <Item> El tipo de elemento que contendrá esta pila.
 */
public class Stack <Item> implements Iterable<Item>{
    private Node first;
    private int count;
    
    /**
     * Constructor que inicializa una pila vacía.
     */
    public Stack(){
        first = null;
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
     * Agrega un elemento al tope de la pila.
     * 
     * @param item El elemento a ser agregado.
     */
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        count++;
    }
    
    /**
     * Elimina y devuelve el elemento en el tope de la pila.
     * 
     * @return El elemento eliminado.
     */
    public Item pop(){
        Item item = first.item;
        first.item = null;
        first = first.next;
        count--;
        return item;
    }
    
    /**
     * Devuelve el elemento en el tope de la pila sin eliminarlo.
     * 
     * @return El elemento en el tope de la pila.
     */
    public Item peak(){
        return first.item;
    }
    
    /**
     * Verifica si la pila está vacía.
     * 
     * @return true si la pila está vacía, false en caso contrario.
     */
    public boolean isEmpty(){return first == null;}
    
    /**
     * Devuelve el número de elementos en la pila.
     * 
     * @return El número de elementos.
     */
    public int size(){return count;}
    
    /**
     * Proporciona un iterador para recorrer los elementos de la pila.
     * 
     * @return Un iterador para los elementos de la pila.
     */
    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }
    
    /**
     * Clase interna que implementa el iterador para la pila.
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