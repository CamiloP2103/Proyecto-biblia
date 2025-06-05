package Estructras;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * La clase List implementa una lista doblemente enlazada genérica.
 * Esta estructura de datos permite agregar elementos al final y eliminar
 * elementos desde el principio, además de recorrerlos en ambas direcciones.
 * 
 * <p>Una lista doblemente enlazada mantiene referencias tanto al siguiente
 * como al anterior nodo, permitiendo navegación bidireccional y operaciones
 * eficientes en ambos extremos.</p>
 * 
 * <p><strong>Características principales:</strong></p>
 * <ul>
 *   <li>Acceso aleatorio por índice en O(n)</li>
 *   <li>Inserción y eliminación en los extremos en O(1)</li>
 *   <li>Inserción y eliminación en posición específica en O(n)</li>
 *   <li>Soporte para elementos nulos</li>
 * </ul>
 * 
 * @author Rodrigo
 * @author Juan
 * @author Camilo
 * @param <Item> El tipo de elemento que contendrá esta lista
 * @version 1.0
 * @since 1.0
 */
public class List<Item> implements Iterable<Item> {
    
    /** Referencia al primer nodo de la lista */
    private Node first;
    
    /** Referencia al último nodo de la lista */
    private Node last;
    
    /** Contador de elementos en la lista */
    private int count;
    
    /**
     * Constructor que inicializa una lista vacía.
     * 
     * <p>Inicializa todos los campos a sus valores por defecto:
     * {@code first} y {@code last} como {@code null}, y {@code count} como 0.</p>
     */
    public List() {
        first = null;
        last = null;
        count = 0;
    }
    
    /**
     * Clase interna para representar un nodo de la lista doblemente enlazada.
     * 
     * <p>Cada nodo mantiene referencias al nodo anterior y siguiente,
     * permitiendo navegación bidireccional.</p>
     */
    private class Node {
        /** El elemento almacenado en este nodo */
        Item item;
        
        /** Referencia al siguiente nodo en la lista */
        Node next;
        
        /** Referencia al nodo anterior en la lista */
        Node prev;
    }
    
    /**
     * Añade un elemento al final de la lista.
     * 
     * <p>Esta operación se realiza en tiempo constante O(1).</p>
     * 
     * @param item Elemento a añadir. Puede ser {@code null}.
     */
    public void add(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
            last.prev = null;
        } else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        count++;
    }
    
    /**
     * Añade un elemento al inicio de la lista.
     * 
     * <p>Esta operación se realiza en tiempo constante O(1).</p>
     * 
     * @param item Elemento a añadir. Puede ser {@code null}.
     */
    public void addFirst(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        if (isEmpty()) {
            last = first;
            first.next = null;
        } else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        count++;
    }
    
    /**
     * Elimina y retorna el primer elemento de la lista.
     * 
     * <p>Esta operación se realiza en tiempo constante O(1).</p>
     * 
     * @return El primer elemento de la lista
     * @throws NoSuchElementException si la lista está vacía
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("La lista está vacía");
        Item item = first.item;
        first = first.next;
        count--;
        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }
        return item;
    }
    
    /**
     * Elimina y retorna el último elemento de la lista.
     * 
     * <p>Esta operación se realiza en tiempo constante O(1).</p>
     * 
     * @return El último elemento de la lista
     * @throws NoSuchElementException si la lista está vacía
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("La lista está vacía");
        Item item = last.item;
        last = last.prev;
        count--;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        return item;
    }
    
    /**
     * Obtiene el elemento en una posición específica.
     * 
     * <p>Esta operación se realiza en tiempo lineal O(n), donde n es el índice.</p>
     * 
     * @param index Índice del elemento (0 es el primero)
     * @return El elemento en la posición especificada
     * @throws IndexOutOfBoundsException si el índice es inválido (index < 0 || index >= size())
     */
    public Item get(int index) {
        if (index < 0 || index >= count) 
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }
    
    /**
     * Elimina el elemento en una posición específica.
     * 
     * <p>Esta operación se realiza en tiempo lineal O(n), donde n es el índice.</p>
     * 
     * @param index Índice del elemento a eliminar (0 es el primero)
     * @return El elemento eliminado
     * @throws IndexOutOfBoundsException si el índice es inválido (index < 0 || index >= size())
     */
    public Item remove(int index) {
        if (index < 0 || index >= count) 
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        if (index == 0) return removeFirst();
        if (index == count - 1) return removeLast();
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        Item item = current.item;
        count--;
        return item;
    }
    
    /**
     * Actualiza el elemento en una posición específica.
     * 
     * <p>Esta operación se realiza en tiempo lineal O(n), donde n es el índice.</p>
     * 
     * @param index Índice del elemento a actualizar
     * @param item Nuevo valor para el elemento. Puede ser {@code null}.
     * @throws IndexOutOfBoundsException si el índice es inválido (index < 0 || index >= size())
     */
    public void set(int index, Item item) {
        if (index < 0 || index >= count) 
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.item = item;
    }
    
    /**
     * Inserta un elemento en una posición específica.
     * 
     * <p>Los elementos existentes en la posición especificada y posteriores
     * se desplazan hacia la derecha (se incrementa su índice en 1).</p>
     * 
     * <p>Esta operación se realiza en tiempo lineal O(n), donde n es el índice.</p>
     * 
     * @param index Índice donde insertar el elemento (0 <= index <= size())
     * @param item Elemento a insertar. Puede ser {@code null}.
     * @throws IndexOutOfBoundsException si el índice es inválido (index < 0 || index > size())
     */
    public void insert(int index, Item item) {
        if (index < 0 || index > count) 
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        if (index == 0) {
            addFirst(item);
            return;
        }
        if (index == count) {
            add(item);
            return;
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
        count++;
    }
    
    /**
     * Busca un elemento en la lista y devuelve su índice.
     * 
     * <p>Esta operación se realiza en tiempo lineal O(n) en el peor caso.</p>
     * 
     * @param item Elemento a buscar. Puede ser {@code null}.
     * @return El índice del elemento, o -1 si no se encuentra
     */
    public int indexOf(Item item) {
        Node current = first;
        int index = 0;
        
        while (current != null) {
            if ((item == null && current.item == null) ||
                (item != null && item.equals(current.item))) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }
    
    /**
     * Verifica si la lista contiene un elemento específico.
     * 
     * <p>Esta operación se realiza en tiempo lineal O(n) en el peor caso.</p>
     * 
     * @param item Elemento a buscar. Puede ser {@code null}.
     * @return {@code true} si el elemento está en la lista, {@code false} en caso contrario
     */
    public boolean contains(Item item) {
        return indexOf(item) != -1;
    }
    
    /**
     * Verifica si la lista está vacía.
     * 
     * @return {@code true} si la lista está vacía, {@code false} en caso contrario
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * Obtiene el tamaño de la lista.
     * 
     * @return Número de elementos en la lista
     */
    public int size() {
        return count;
    }
    
    /**
     * Proporciona un iterador para recorrer los elementos de la lista.
     * 
     * <p>El iterador recorre la lista desde el primer elemento hasta el último,
     * en el orden en que fueron agregados.</p>
     * 
     * @return Un iterador para los elementos de la lista
     */
    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }
    
    /**
     * Clase interna que implementa el iterador para la lista.
     * 
     * <p>Permite recorrer los elementos de la lista de forma secuencial
     * desde el primer elemento hasta el último.</p>
     */
    private class LinkedListIterator implements Iterator<Item> {
        
        /** Nodo actual durante la iteración */
        private Node current = first;
        
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