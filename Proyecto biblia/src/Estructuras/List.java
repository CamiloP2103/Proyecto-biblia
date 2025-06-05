package Estructras;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * La clase List implementa una lista doblemente enlazada genérica.
 * Esta estructura de datos permite agregar elementos al final y eliminar
 * elementos desde el principio, además de recorrerlos en ambas direcciones.
 * 
 * @author Rodrigo
 * @author Juan
 * @author Camilo
 * @param <Item> El tipo de elemento que contendrá esta lista.
 */
public class List<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int count;
    
    /**
     * Constructor: inicializa una lista vacía
     */
    public List() {
        first = null;
        last = null;
        count = 0;
    }
    
    /**
     * Clase interna para representar un nodo de la lista
     */
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    
    /**
     * Añade un elemento al final de la lista
     * @param item Elemento a añadir
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
     * Añade un elemento al inicio de la lista
     * @param item Elemento a añadir
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
     * Elimina y retorna el primer elemento de la lista
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
     * Elimina y retorna el último elemento de la lista
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
     * Obtiene el elemento en una posición específica
     * @param index Índice del elemento (0 es el primero)
     * @return El elemento en la posición especificada
     * @throws IndexOutOfBoundsException si el índice es inválido
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
     * Elimina el elemento en una posición específica
     * @param index Índice del elemento a eliminar (0 es el primero)
     * @return El elemento eliminado
     * @throws IndexOutOfBoundsException si el índice es inválido
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
     * Actualiza el elemento en una posición específica
     * @param index Índice del elemento a actualizar
     * @param item Nuevo valor para el elemento
     * @throws IndexOutOfBoundsException si el índice es inválido
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
     * Inserta un elemento en una posición específica
     * @param index Índice donde insertar el elemento
     * @param item Elemento a insertar
     * @throws IndexOutOfBoundsException si el índice es inválido
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
     * Busca un elemento en la lista
     * @param item Elemento a buscar
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
     * Verifica si la lista contiene un elemento específico
     * @param item Elemento a buscar
     * @return true si el elemento está en la lista, false en caso contrario
     */
    public boolean contains(Item item) {
        return indexOf(item) != -1;
    }
    
    /**
     * Verifica si la lista está vacía
     * @return true si la lista está vacía, false en caso contrario
     */
    public boolean isEmpty() {return count == 0;}
    
    /**
     * Obtiene el tamaño de la lista
     * @return Número de elementos en la lista
     */
    public int size() {return count;}
    
    /**
     * Implementación del Iterador para recorrer la lista
     */
    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }
    
    private class LinkedListIterator implements Iterator<Item> {
        private Node current = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}