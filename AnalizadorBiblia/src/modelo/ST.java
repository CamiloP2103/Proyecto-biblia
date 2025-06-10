package modelo;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private final Lista<ParClaveValor> elementos;
    
    private class ParClaveValor {
        final Key clave;
        Value valor;
        
        ParClaveValor(Key clave, Value valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    public ST() {
        elementos = new Lista<>(16);
    }

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

    public Value get(Key clave) {
        if (clave == null) return null;
        int pos = rank(clave);
        if (pos < elementos.tamano() && elementos.obtenerDe(pos).clave.compareTo(clave) == 0) {
            return elementos.obtenerDe(pos).valor;
        }
        return null;
    }

    public void delete(Key clave) {
        if (clave == null) return;
        int pos = rank(clave);
        if (pos < elementos.tamano() && elementos.obtenerDe(pos).clave.compareTo(clave) == 0) {
            elementos.eliminarDe(pos);
        }
    }

    public boolean contains(Key clave) {
        return get(clave) != null;
    }

    public boolean isEmpty() {
        return elementos.estaVacia();
    }

    public int size() {
        return elementos.tamano();
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return elementos.obtenerDe(0).clave;
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return elementos.obtenerDe(elementos.tamano() - 1).clave;
    }

    public Key floor(Key clave) {
        int pos = rank(clave);
        if (pos < elementos.tamano() && elementos.obtenerDe(pos).clave.compareTo(clave) == 0) {
            return elementos.obtenerDe(pos).clave;
        }
        if (pos == 0) return null;
        return elementos.obtenerDe(pos - 1).clave;
    }

    public Key ceiling(Key clave) {
        int pos = rank(clave);
        if (pos == elementos.tamano()) return null;
        return elementos.obtenerDe(pos).clave;
    }

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

    public Key select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException();
        return elementos.obtenerDe(k).clave;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException();
        elementos.eliminarDe(0);
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException();
        elementos.eliminarDe(elementos.tamano() - 1);
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        return rank(hi) - rank(lo) + (contains(hi) ? 1 : 0);
    }

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

    @Override
    public Iterator<Key> iterator() {
        return keys(min(), max()).iterator();
    }
}