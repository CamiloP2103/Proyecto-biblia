package Estructras;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * La clase Queue implementa una cola genérica basada en una lista enlazada.
 * Esta estructura de datos sigue el principio FIFO (First-In-First-Out),
 * donde el primer elemento agregado es el primero en ser eliminado.
 * 
 * <p>Una cola es una estructura de datos lineal que permite agregar elementos
 * en un extremo (rear/back) y eliminarlos del otro extremo (front). Es ideal
 * para situaciones donde se necesita procesar elementos en el orden en que
 * llegaron.</p>
 * 
 * <p><strong>Operaciones principales:</strong></p>
 * <ul>
 *   <li>{@code enqueue}: Agrega un elemento al final de la cola - O(1)</li>
 *   <li>{@code dequeue}: Elimina y devuelve el primer elemento - O(1)</li>
 *   <li>{@code isEmpty}: Verifica si la cola está vacía - O(1)</li>
 *   <li>{@code size}: Devuelve el número de elementos - O(1)</li>
 * </ul>
 * 
 * <p><strong>Nota:</strong> Esta implementación tiene un error en el método
 * {@code enqueue} que debe ser corregido.</p>
 * 
 * @author Rodrigo
 * @author Juan
 * @author Camilo
 * @param <Item> El tipo de elemento que contendrá esta cola
 * @version 1.0
 * @since 1.0
 */
public class Queue <Item> implements Iterable<Item>{
    
    /** Referencia al primer nodo de la cola (frente) */
    private Node first;
    
    /** Referencia al último nodo de la cola (final) */
    private Node last;
    
    /** Contador de elementos en la cola */
    private int count;
    
    /**
     * Constructor que inicializa una cola vacía.
     * 
     * <p>Inicializa todos los campos a sus valores por defecto:
     * {@code first} y {@code last} como {@code null}, y {@code count} como 0.</p>
     */
    public Queue(){
        first = null;
        last = null;
        count = 0;
    }
    
    /**
     * Clase interna que representa un nodo en la lista enlazada.
     * 
     * <p>Cada nodo contiene un elemento de tipo {@code Item} y una
     * referencia al siguiente nodo en la cola.</p>
     */
    private class Node{
        /** El elemento almacenado en este nodo */
        Item item;
        
        /** Referencia al siguiente nodo en la cola */
        Node next;
    }
    
    /**
     * Agrega un elemento al final de la cola (operación enqueue).
     * 
     * <p>Esta operación se realiza en tiempo constante O(1).</p>
     * 
     * <p><strong>NOTA:</strong> Este método contiene un error. Cuando la cola
     * está vacía, {@code oldlast} será {@code null} y se producirá una
     * {@code NullPointerException} al intentar acceder a {@code oldlast.next}.</p>
     * 
     * @param item El elemento a ser agregado. Puede ser {@code null}.
     * @throws NullPointerException si la cola está vacía (debido al error mencionado)
     */
    public void enqueue(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        oldlast.next = last; // ERROR: oldlast puede ser null
        if(isEmpty())
            first = last;
        count++;
    }
    
    /**
     * Elimina y devuelve el primer elemento de la cola (operación dequeue).
     * 
     * <p>Esta operación se realiza en tiempo constante O(1).</p>
     * 
     * @return El elemento eliminado del frente de la cola
     * @throws NullPointerException si la cola está vacía
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
     * @return {@code true} si la cola está vacía, {@code false} en caso contrario
     */
    public boolean isEmpty(){
        return first == null;
    }
    
    /**
     * Devuelve el número de elementos en la cola.
     * 
     * @return El número de elementos en la cola
     */
    public int size(){
        return count;
    }
    
    /**
     * Proporciona un iterador para recorrer los elementos de la cola.
     * 
     * <p>El iterador permite recorrer todos los elementos de la cola
     * desde el frente hasta el final, en el orden FIFO.</p>
     * 
     * @return Un iterador para los elementos de la cola
     */
    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }
    
    /**
     * Clase interna que implementa el iterador para la cola.
     * 
     * <p><strong>NOTA:</strong> Esta implementación tiene un error ya que
     * {@code current} no se inicializa en el constructor.</p>
     */
    private class LinkedListIterator implements Iterator<Item>{
        
        /** Nodo actual durante la iteración */
        private Node current; // ERROR: No se inicializa
        
        /**
         * Constructor del iterador.
         * 
         * <p><strong>NOTA:</strong> Falta inicializar {@code current = first}.</p>
         */
        public LinkedListIterator() {
            // current = first; // ← Línea faltante
        }
        
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
         * @throws NoSuchElementException si no hay más elementos
         */
        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}