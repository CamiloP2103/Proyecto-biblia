package Estructras;

import java.util.Iterator;

/**
 * La clase Bag implementa una colección tipo bolsa genérica que permite
 * almacenar elementos de cualquier tipo. Esta estructura de datos
 * permite agregar elementos y recorrerlos, sin un orden específico.
 * 
 * @author Rodrigo
 * @author Juan
 * @author Camilo
 * @param <Item> El tipo de elemento que contendrá esta bolsa.
 */
public class Bag <Item> implements Iterable<Item>{
    private Node first;
    private Node last;
    private int count;
    
    /**
     * Constructor que inicializa una bolsa vacía.
     */
    public Bag(){
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
     * Agrega un elemento a la bolsa.
     * 
     * @param item El elemento a ser agregado.
     */
    public void add(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        oldlast.next = last;
        if(isEmpty())
            first = last;
        count++;
    }
    
    /**
     * Elimina y devuelve el primer elemento de la bolsa.
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
     * Verifica si la bolsa está vacía.
     * 
     * @return true si la bolsa está vacía, false en caso contrario.
     */
    public boolean isEmpty(){return first == null;}
    
    /**
     * Devuelve el número de elementos en la bolsa.
     * 
     * @return El número de elementos.
     */
    public int size(){return count;}
    
    /**
     * Proporciona un iterador para recorrer los elementos de la bolsa.
     * 
     * @return Un iterador para los elementos de la bolsa.
     */
    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }
    
    /**
     * Clase interna que implementa el iterador para la bolsa.
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