package Estructras;

import java.util.Iterator;

/**
 * La clase Bag implementa una colección tipo bolsa genérica que permite
 * almacenar elementos de cualquier tipo. Esta estructura de datos
 * permite agregar elementos y recorrerlos, sin un orden específico.
 * 
 * <p>Una bolsa es una estructura de datos que permite duplicados y no
 * mantiene un orden específico de los elementos. Es útil para recopilar
 * elementos cuando el orden no importa.</p>
 * 
 * <p><strong>Nota:</strong> Esta implementación tiene un error en el método
 * {@code add} que debe ser corregido.</p>
 * 
 * @author Rodrigo
 * @author Juan
 * @author Camilo
 * @param <Item> El tipo de elemento que contendrá esta bolsa.
 * @version 1.0
 * @since 1.0
 */
public class Bag <Item> implements Iterable<Item>{
    
    /** Referencia al primer nodo de la lista enlazada */
    private Node first;
    
    /** Referencia al último nodo de la lista enlazada */
    private Node last;
    
    /** Contador de elementos en la bolsa */
    private int count;
    
    /**
     * Constructor que inicializa una bolsa vacía.
     * 
     * <p>Inicializa todos los campos a sus valores por defecto:
     * {@code first} y {@code last} como {@code null}, y {@code count} como 0.</p>
     */
    public Bag(){
        first = null;
        last = null;
        count = 0;
    }
    
    /**
     * Clase interna que representa un nodo en la lista enlazada.
     * 
     * <p>Cada nodo contiene un elemento de tipo {@code Item} y una
     * referencia al siguiente nodo.</p>
     */
    private class Node{
        /** El elemento almacenado en este nodo */
        Item item;
        
        /** Referencia al siguiente nodo en la lista */
        Node next;
    }
    
    /**
     * Agrega un elemento a la bolsa.
     * 
     * <p><strong>NOTA:</strong> Este método contiene un error. Cuando la bolsa
     * está vacía, {@code oldlast} será {@code null} y se producirá una
     * {@code NullPointerException} al intentar acceder a {@code oldlast.next}.</p>
     * 
     * @param item El elemento a ser agregado. Puede ser {@code null}.
     * @throws NullPointerException si la bolsa está vacía (debido al error mencionado)
     */
    public void add(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        oldlast.next = last; // ERROR: oldlast puede ser null
        if(isEmpty())
            first = last;
        count++;
    }
    
    /**
     * Elimina y devuelve el primer elemento de la bolsa.
     * 
     * <p>Este método implementa el comportamiento de una cola FIFO
     * (First-In-First-Out), eliminando el primer elemento agregado.</p>
     * 
     * @return El elemento eliminado
     * @throws NullPointerException si la bolsa está vacía
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
     * @return {@code true} si la bolsa está vacía, {@code false} en caso contrario
     */
    public boolean isEmpty(){
        return first == null;
    }
    
    /**
     * Devuelve el número de elementos en la bolsa.
     * 
     * @return El número de elementos en la bolsa
     */
    public int size(){
        return count;
    }
    
    /**
     * Proporciona un iterador para recorrer los elementos de la bolsa.
     * 
     * <p>El iterador permite recorrer todos los elementos de la bolsa
     * en el orden en que fueron agregados (FIFO).</p>
     * 
     * @return Un iterador para los elementos de la bolsa
     */
    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }
    
    /**
     * Clase interna que implementa el iterador para la bolsa.
     * 
     * <p><strong>NOTA:</strong> Esta implementación tiene un error ya que
     * {@code current} no se inicializa en el constructor.</p>
     */
    private class LinkedListIterator implements Iterator<Item>{
        
        /** Nodo actual durante la iteración */
        private Node current; // ERROR: No se inicializa
        
        /**
         * Verifica si hay más elementos por recorrer.
         * 
         * @return {@code true} si hay más elementos, {@code false} en caso contrario
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Devuelve el siguiente elemento en la iteración.
         * 
         * @return El siguiente elemento en la iteración
         * @throws java.util.NoSuchElementException si no hay más elementos
         */
        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}