/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Paquete que contiene la implementación de la estructura de datos Lista.
 */
package modelo;

import java.util.Iterator;

/**
 * Implementación de una lista genérica en Java
 * Permite agregar, insertar, eliminar y consultar elementos.
 *
 * @param <Item> Tipo de elementos que almacenará la lista.
 * 
 * @author juan
 * @author Camilo
 * @author Rodrigo
 */

public class Lista<Item> implements Iterable<Item> {
    private Item[] elementos;
    private int cantidad;
    private int capacidad;

    /**
     * Constructor que inicializa la lista con capacidad inicial.
     * @param capacidadInicial Capacidad inicial del arreglo interno.
     */
    public Lista(int capacidadInicial) {
        if (capacidadInicial <= 0) {
            System.out.println("Advertencia: Capacidad invalida");
            capacidadInicial = 0;
        }
        this.capacidad = capacidadInicial;
        this.elementos = (Item[]) new Object[capacidad];
        this.cantidad = 0;
    }

    /**
     * Redimensiona el arreglo interno cuando es necesario.
     * @param nuevaCapacidad Nueva capacidad del arreglo.
     */
    private void redimensionar(int nuevaCapacidad) {
        Item[] nuevoArreglo = (Item[]) new Object[nuevaCapacidad];
        for (int i = 0; i < cantidad; i++) {
            nuevoArreglo[i] = elementos[i];
        }
        elementos = nuevoArreglo;
        capacidad = nuevaCapacidad;
    }

    /**
     * Agrega un elemento al final de la lista.
     * @param elemento Elemento a agregar.
     */
    public void agregarAlFinal(Item elemento) {
        if (cantidad == capacidad) {
            redimensionar(capacidad * 2);
        }
        elementos[cantidad++] = elemento;
    }

    /**
     * Agrega un elemento al inicio de la lista.
     * @param elemento Elemento a agregar.
     */
    public void agregarAlInicio(Item elemento) {
        if (cantidad == capacidad) {
            redimensionar(capacidad * 2);
        }
        
        for (int i = cantidad; i > 0; i--) {
            elementos[i] = elementos[i - 1];
        }
        
        elementos[0] = elemento;
        cantidad++;
    }

    /**
     * Inserta un elemento en la posición especificada.
     * @param posicion Posición donde insertar el elemento.
     * @param elemento Elemento a insertar.
     * @return true si la inserción fue exitosa, false si la posición es inválida.
     */
    public boolean insertarEn(int posicion, Item elemento) {
        if (posicion < 0 || posicion > cantidad) {
            System.out.println("Error: Posición inválida.");
            return false;
        }
        
        if (cantidad == capacidad) {
            redimensionar(capacidad * 2);
        }
        
        if (posicion == cantidad) {
            agregarAlFinal(elemento);
        } else {
            for (int i = cantidad; i > posicion; i--) {
                elementos[i] = elementos[i - 1];
            }
            elementos[posicion] = elemento;
            cantidad++;
        }
        return true;
    }

    /**
     * Elimina y retorna el elemento en la posición especificada.
     * @param posicion Posición del elemento a eliminar.
     * @return Elemento eliminado o null si la posición es inválida.
     */
    public Item eliminarDe(int posicion) {
        if (posicion < 0 || posicion >= cantidad) {
            System.out.println("Error: Posición inválida.");
            return null;
        }
        
        Item elemento = elementos[posicion];
        
        for (int i = posicion; i < cantidad - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        
        elementos[--cantidad] = null;
        
        if (cantidad > 0 && cantidad == capacidad / 4) {
            redimensionar(capacidad / 2);
        }
        
        return elemento;
    }

    /**
     * Obtiene el elemento en la posición especificada sin eliminarlo.
     * @param posicion Posición del elemento a obtener.
     * @return Elemento en la posición o null si es inválida.
     */
    public Item obtenerDe(int posicion) {
        if (posicion < 0 || posicion >= cantidad) {
            System.out.println("Error: Posicion invalida.");
            return null;
        }
        return elementos[posicion];
    }

    /**
     * Verifica si la lista está vacía.
     * @return true si la lista está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return cantidad == 0;
    }

    /**
     * Obtiene la cantidad de elementos en la lista.
     * @return Número de elementos en la lista.
     */
    public int tamano() {
        return cantidad;
    }
    
     /**
     * Devuelve la capacidad actual de la Lista.
     *
     * @return Capacidad actual del arreglo interno
     */
    public int capacidadActual() {
        return capacidad;
    }

    /**
     * Muestra el contenido de la lista en la consola.
     */
    public void mostrarContenido() {
        System.out.println("\nContenido de la lista:");
        for (int i = 0; i < cantidad; i++) {
            System.out.println((i + 1) + ". " + elementos[i]);
        }
        if (cantidad == 0) {
            System.out.println("La lista esta vacia.");
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new IteradorLista();
    }

    /**
     * Clase interna que implementa el iterador para la lista.
     */
    private class IteradorLista implements Iterator<Item> {
        private int posicionActual = 0;

        @Override
        public boolean hasNext() {
            return posicionActual < cantidad;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                System.out.println("Advertencia: No hay más elementos.");
                return null;
            }
            return elementos[posicionActual++];
        }
    }
}