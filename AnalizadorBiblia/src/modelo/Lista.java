
package modelo;

import java.util.Iterator;

/**
 * Implementación de una lista genérica dinámica en Java.
 * Permite agregar, insertar, eliminar y consultar elementos de forma eficiente.
 * La lista se redimensiona automáticamente según sea necesario.
 * 
 * <p>Características principales:
 * <ul>
 *   <li>Capacidad dinámica con redimensionamiento automático</li>
 *   <li>Soporte para tipos genéricos</li>
 *   <li>Implementa la interfaz Iterable</li>
 *   <li>Operaciones de inserción y eliminación en cualquier posición</li>
 * </ul>
 * 
 * @param <Item> Tipo de elementos que almacenará la lista
 * 
 * @author Camilo
 * @author Juan
 * @author Rodrigo
 */

public class Lista<Item> implements Iterable<Item> {
    /**
     * Arreglo interno que almacena los elementos de la lista.
     */
    private Item[] elementos;
    /**
     * Número actual de elementos en la lista.
     */
    private int cantidad;
    /**
     * Capacidad actual del arreglo interno.
     */
    private int capacidad;

    /**
     * Constructor que inicializa la lista con una capacidad inicial específica.
     * 
     * @param capacidadInicial Capacidad inicial del arreglo interno.
     *                        Si es menor o igual a 0, se establece en 0.
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
     * Redimensiona el arreglo interno a una nueva capacidad.
     * Copia todos los elementos existentes al nuevo arreglo.
     * 
     * @param nuevaCapacidad Nueva capacidad del arreglo
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
     * Si la capacidad es insuficiente, redimensiona automáticamente.
     * 
     * @param elemento Elemento a agregar
     */
    public void agregarAlFinal(Item elemento) {
        if (cantidad == capacidad) {
            redimensionar(capacidad * 2);
        }
        elementos[cantidad++] = elemento;
    }

    /**
     * Agrega un elemento al inicio de la lista.
     * Desplaza todos los elementos existentes una posición hacia la derecha.
     * 
     * @param elemento Elemento a agregar
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
     * Desplaza los elementos existentes según sea necesario.
     * 
     * @param posicion Posición donde insertar el elemento (0-indexada)
     * @param elemento Elemento a insertar
     * @return true si la inserción fue exitosa, false si la posición es inválida
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
     * Desplaza los elementos restantes para llenar el espacio vacío.
     * 
     * @param posicion Posición del elemento a eliminar (0-indexada)
     * @return Elemento eliminado o null si la posición es inválida
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
     * 
     * @param posicion Posición del elemento a obtener (0-indexada)
     * @return Elemento en la posición o null si la posición es inválida
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
     * 
     * @return true si la lista no contiene elementos, false en caso contrario
     */
    public boolean estaVacia() {
        return cantidad == 0;
    }

    /**
     * Obtiene la cantidad de elementos almacenados en la lista.
     * 
     * @return Número de elementos en la lista
     */
    public int tamano() {
        return cantidad;
    }
    
     /**
     * Obtiene la capacidad actual del arreglo interno.
     * 
     * @return Capacidad actual del arreglo interno
     */
    public int capacidadActual() {
        return capacidad;
    }

    /**
     * Muestra el contenido completo de la lista en la consola.
     * Enumera cada elemento con su posición correspondiente.
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

    /**
     * Proporciona un iterador para recorrer los elementos de la lista.
     * 
     * @return Iterator para recorrer la lista
     */
    @Override
    public Iterator<Item> iterator() {
        return new IteradorLista();
    }

    /**
     * Clase interna que implementa el patrón Iterator para la lista.
     * Permite recorrer los elementos de forma secuencial y segura.
     */
    private class IteradorLista implements Iterator<Item> {
        /**
         * Posición actual del iterador en la lista.
         */
        private int posicionActual = 0;

        /**
         * Verifica si existen más elementos por recorrer.
         * 
         * @return true si hay más elementos, false en caso contrario
         */
        @Override
        public boolean hasNext() {
            return posicionActual < cantidad;
        }

        /**
         * Obtiene el siguiente elemento en la iteración.
         * 
         * @return Siguiente elemento o null si no hay más elementos
         */
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