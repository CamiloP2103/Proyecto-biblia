package modelo;

import util.ProcesadorTexto;

public class AnalizadorBiblia {
    private final ST<String, Integer> tablaPalabras;
    private int totalPalabras;
    private String palabraMasRepetida;
    private int maxRepeticiones;

    public AnalizadorBiblia() {
        tablaPalabras = new ST<>();
        totalPalabras = 0;
        palabraMasRepetida = null;
        maxRepeticiones = 0;
    }

    public void procesarTexto(String texto) {
        String[] palabras = texto.split("\\s+");
        for (String palabra : palabras) {
            agregarPalabra(palabra);
        }
    }

    private void agregarPalabra(String palabra) {
        palabra = ProcesadorTexto.normalizarPalabra(palabra);
        if (!ProcesadorTexto.esPalabraValida(palabra)) return;

        Integer contador = tablaPalabras.get(palabra);
        int nuevoContador = (contador == null) ? 1 : contador + 1;
        tablaPalabras.put(palabra, nuevoContador);
        totalPalabras++;

        if (nuevoContador > maxRepeticiones) {
            maxRepeticiones = nuevoContador;
            palabraMasRepetida = palabra;
        }
    }

    public int obtenerRepeticiones(String palabra) {
        palabra = ProcesadorTexto.normalizarPalabra(palabra);
        Integer contador = tablaPalabras.get(palabra);
        return contador != null ? contador : 0;
    }

    public int getTotalPalabras() {
        return totalPalabras;
    }

    public int getPalabrasUnicas() {
        return tablaPalabras.size();
    }

    public String getPalabraMasRepetida() {
        return palabraMasRepetida != null ? 
               String.format("%s (%d repeticiones)", palabraMasRepetida, maxRepeticiones) : 
               "No hay palabras procesadas";
    }

    public Lista<String> palabrasQueEmpiezanCon(char letra) {
        Lista<String> resultado = new Lista<>(10);
        letra = Character.toLowerCase(letra);
        
        for (String palabra : tablaPalabras) {
            if (!palabra.isEmpty() && Character.toLowerCase(palabra.charAt(0)) == letra) {
                resultado.agregarAlFinal(palabra);
            }
        }
        return resultado;
    }

    public Lista<String> getPalabrasOrdenadas() {
        Lista<String> resultado = new Lista<>(tablaPalabras.size());
        for (String palabra : tablaPalabras) {
            resultado.agregarAlFinal(palabra);
        }
        return resultado;
    }

    public String getEstadisticas() {
        return String.format(
            "=== ESTADÍSTICAS ===\n" +
            "Total palabras: %d\n" +
            "Palabras únicas: %d\n" +
            "Palabra más repetida: %s\n",
            getTotalPalabras(),
            getPalabrasUnicas(),
            getPalabraMasRepetida()
        );
    }
}