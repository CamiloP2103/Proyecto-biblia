package modelo;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de una Tabla de Símbolos (Symbol Table) ordenada
 * basada en un arreglo de pares clave-valor.
 * 
 * <p>Esta implementación mantiene las claves ordenadas y utiliza
 * búsqueda binaria para operaciones eficientes. Soporta todas las
 * operaciones estándar de una tabla de símbolos ordenada.
 * 
 * <p>Complejidades de tiempo:
 * <ul>
 *   <li>Búsqueda: O(log n)</li>
 *   <li>Inserción: O(n) en el peor caso</li>
 *   <li>Eliminación: O(n) en el peor caso</li>
 * </ul>
 * 
 * @param <Key> Tipo de las claves, debe implementar Comparable
 * @param <Value> Tipo de los valores asociados a las claves
 * 
 * @author Camilo
 * @author Juan
 * @author Rodrigo
 */
public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    /**
     * Lista que almacena los pares clave-valor ordenados por clave.
     */
    private final Lista<ParClaveValor> elementos;
    
    /**
     * Clase interna que representa un par clave-valor.
     */
    private class ParClaveValor {
        /**
         * Clave del par (inmutable una vez asignada).
         */
        final Key clave;
        /**
         * Valor asociado a la clave (puede ser modificado).
         */
        Value valor;
        
        /**
         * Constructor del par clave-valor.
         * 
         * @param clave Clave del par
         * @param valor Valor asociado a la clave
         */
        ParClaveValor(Key clave, Value valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    /**
     * Constructor que inicializa una tabla de símbolos vacía.
     */
    public ST() {
        elementos = new Lista<>(16);
    }

    /**
     * Inserta o actualiza un par clave-valor en la tabla.
     * Si la clave ya existe, actualiza su valor.
     * Si el valor es null, elimina la clave de la tabla.
     * 
     * @param clave Clave a insertar o actualizar
     * @param valor Valor a asociar con la clave
     * @throws IllegalArgumentException si la clave es null
     */
    public void put(Key clave, Value valor) {
        if (clave == null) throw new IllegalArgumentException("Clave no puede ser null");
        if (valor == null) {
            delete(clave);
            return;
        }
        
        int pos = rank(clave);
        
        if (pos < elementos.tamano() && elementos.obtenerDe(pos).clave.compareTo(clave) == 0) {
            elementos.obtenerDe(pos).valor = valor;
        } else {
            elementos.insertarEn(pos, new ParClaveValor(clave, valor));
        }
    }

    /**
     * Obtiene el valor asociado a una clave específica.
     * 
     * @param clave Clave a buscar
     * @return Valor asociado a la clave, o null si no existe
     */
    public Value get(Key clave) {
        if (clave == null) return null;
        int pos = rank(clave);
        if (pos < elementos.tamano() && elementos.obtenerDe(pos).clave.compareTo(clave) == 0) {
            return elementos.obtenerDe(pos).valor;
        }
        return null;
    }

    /**
     * Elimina una clave y su valor asociado de la tabla.
     * 
     * @param clave Clave a eliminar
     */
    public void delete(Key clave) {
        if (clave == null) return;
        int pos = rank(clave);
        if (pos < elementos.tamano() && elementos.obtenerDe(pos).clave.compareTo(clave) == 0) {
            elementos.eliminarDe(pos);
        }
    }

    /**
     * Verifica si la tabla contiene una clave específica.
     * 
     * @param clave Clave a verificar
     * @return true si la clave existe en la tabla, false en caso contrario
     */
    public boolean contains(Key clave) {
        return get(clave) != null;
    }

    /**
     * Verifica si la tabla está vacía.
     * 
     * @return true si no contiene elementos, false en caso contrario
     */
    public boolean isEmpty() {
        return elementos.estaVacia();
    }

    /**
     * Obtiene el número de pares clave-valor en la tabla.
     * 
     * @return Tamaño de la tabla
     */
    public int size() {
        return elementos.tamano();
    }

    /**
     * Obtiene la clave más pequeña en la tabla.
     * 
     * @return Clave mínima
     * @throws NoSuchElementException si la tabla está vacía
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return elementos.obtenerDe(0).clave;
    }

     /**
     * Obtiene la clave más grande en la tabla.
     * 
     * @return Clave máxima
     * @throws NoSuchElementException si la tabla está vacía
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return elementos.obtenerDe(elementos.tamano() - 1).clave;
    }

    /**
     * Encuentra la clave más grande que es menor o igual a la clave dada.
     * 
     * @param clave Clave de referencia
     * @return Clave floor o null si no existe
     */
    public Key floor(Key clave) {
        int pos = rank(clave);
        if (pos < elementos.tamano() && elementos.obtenerDe(pos).clave.compareTo(clave) == 0) {
            return elementos.obtenerDe(pos).clave;
        }
        if (pos == 0) return null;
        return elementos.obtenerDe(pos - 1).clave;
    }

    /**
     * Encuentra la clave más pequeña que es mayor o igual a la clave dada.
     * 
     * @param clave Clave de referencia
     * @return Clave ceiling o null si no existe
     */
    public Key ceiling(Key clave) {
        int pos = rank(clave);
        if (pos == elementos.tamano()) return null;
        return elementos.obtenerDe(pos).clave;
    }

    /**
     * Obtiene el número de claves menores que la clave dada.
     * Utiliza búsqueda binaria para eficiencia.
     * 
     * @param clave Clave de referencia
     * @return Número de claves menores que la clave dada
     */
    public int rank(Key clave) {
        int inicio = 0, fin = elementos.tamano() - 1;
        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            int cmp = clave.compareTo(elementos.obtenerDe(medio).clave);
            if (cmp < 0) fin = medio - 1;
            else if (cmp > 0) inicio = medio + 1;
            else return medio;
        }
        return inicio;
    }

    /**
     * Obtiene la clave en la posición k-ésima (0-indexada).
     * 
     * @param k Posición de la clave a obtener
     * @return Clave en la posición k
     * @throws IllegalArgumentException si k está fuera de rango
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException();
        return elementos.obtenerDe(k).clave;
    }

    /**
     * Elimina la clave más pequeña de la tabla.
     * 
     * @throws NoSuchElementException si la tabla está vacía
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException();
        elementos.eliminarDe(0);
    }

    /**
     * Elimina la clave más grande de la tabla.
     * 
     * @throws NoSuchElementException si la tabla está vacía
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException();
        elementos.eliminarDe(elementos.tamano() - 1);
    }

    /**
     * Cuenta el número de claves en el rango [lo, hi].
     * 
     * @param lo Límite inferior del rango
     * @param hi Límite superior del rango
     * @return Número de claves en el rango especificado
     */
    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        return rank(hi) - rank(lo) + (contains(hi) ? 1 : 0);
    }

    /**
     * Obtiene todas las claves en el rango [lo, hi].
     * 
     * @param lo Límite inferior del rango
     * @param hi Límite superior del rango
     * @return Iterable con las claves en el rango especificado
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        Lista<Key> lista = new Lista<>(size());
        int inicio = rank(lo);
        int fin = rank(hi);
        
        for (int i = inicio; i < fin; i++) {
            lista.agregarAlFinal(elementos.obtenerDe(i).clave);
        }
        if (contains(hi)) {
            lista.agregarAlFinal(elementos.obtenerDe(fin).clave);
        }
        return lista;
    }

    /**
     * Proporciona un iterador para recorrer todas las claves en orden.
     * 
     * @return Iterator para recorrer las claves ordenadamente
     */
    @Override
    public Iterator<Key> iterator() {
        return keys(min(), max()).iterator();
    }
}