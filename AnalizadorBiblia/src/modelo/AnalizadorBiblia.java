package modelo;

import util.ProcesadorTexto;

/**
 * Clase principal para el análisis de textos.
 * Utiliza una tabla de símbolos (ST) para almacenar y gestionar
 * las palabras del texto junto con sus frecuencias de aparición.
 * 
 * <p>Esta clase proporciona funcionalidades para:
 * <ul>
 *   <li>Procesar texto y contar frecuencias de palabras</li>
 *   <li>Obtener estadísticas del texto analizado</li>
 *   <li>Buscar palabras específicas y sus frecuencias</li>
 *   <li>Filtrar palabras por criterios específicos</li>
 * </ul>
 * 
 * @author Camilo
 * @author Juan
 * @author Rodrigo
 */
public class AnalizadorBiblia {
    
    /**
     * Tabla de símbolos que almacena las palabras como claves
     * y sus frecuencias como valores.
     */
    private final ST<String, Integer> tablaPalabras;
    
    /**
     * Contador total de palabras procesadas (incluyendo repeticiones).
     */
    private int totalPalabras;
    
    /**
     * Palabra que aparece con mayor frecuencia en el texto.
     */
    private String palabraMasRepetida;
    
    /**
     * Número máximo de repeticiones encontradas para una palabra.
     */
    private int maxRepeticiones;

    /**
     * Constructor que inicializa el analizador con estructuras de datos vacías.
     */
    public AnalizadorBiblia() {
        tablaPalabras = new ST<>();
        totalPalabras = 0;
        palabraMasRepetida = null;
        maxRepeticiones = 0;
    }

    /**
     * Procesa una línea de texto dividiéndola en palabras individuales
     * y agregándolas al análisis.
     * 
     * @param texto Línea de texto a procesar
     */
    public void procesarTexto(String texto) {
        String[] palabras = texto.split("\\s+");
        for (String palabra : palabras) {
            agregarPalabra(palabra);
        }
    }

    /**
     * Agrega una palabra individual al análisis.
     * La palabra es normalizada antes de ser agregada y se actualizan
     * las estadísticas correspondientes.
     * 
     * @param palabra Palabra a agregar al análisis
     */
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

    /**
     * Obtiene el número de repeticiones de una palabra específica.
     * 
     * @param palabra Palabra a buscar
     * @return Número de veces que aparece la palabra, 0 si no existe
     */
    public int obtenerRepeticiones(String palabra) {
        palabra = ProcesadorTexto.normalizarPalabra(palabra);
        Integer contador = tablaPalabras.get(palabra);
        return contador != null ? contador : 0;
    }

    /**
     * Obtiene el total de palabras procesadas incluyendo repeticiones.
     * 
     * @return Número total de palabras procesadas
     */
    public int getTotalPalabras() {
        return totalPalabras;
    }

     /**
     * Obtiene el número de palabras únicas (sin repeticiones).
     * 
     * @return Número de palabras únicas en el texto
     */
    public int getPalabrasUnicas() {
        return tablaPalabras.size();
    }

    /**
     * Obtiene información sobre la palabra más repetida en el texto.
     * 
     * @return String con la palabra más repetida y su frecuencia,
     *         o mensaje indicando que no hay palabras procesadas
     */
    public String getPalabraMasRepetida() {
        return palabraMasRepetida != null ? 
               String.format("%s (%d repeticiones)", palabraMasRepetida, maxRepeticiones) : 
               "No hay palabras procesadas";
    }

    /**
     * Busca todas las palabras que comienzan con una letra específica.
     * 
     * @param letra Letra inicial a buscar (no sensible a mayúsculas)
     * @return Lista de palabras que comienzan con la letra especificada
     */
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

    /**
     * Obtiene todas las palabras del texto ordenadas alfabéticamente.
     * 
     * @return Lista de todas las palabras únicas ordenadas alfabéticamente
     */
    public Lista<String> getPalabrasOrdenadas() {
        Lista<String> resultado = new Lista<>(tablaPalabras.size());
        for (String palabra : tablaPalabras) {
            resultado.agregarAlFinal(palabra);
        }
        return resultado;
    }

    /**
     * Genera un resumen con las estadísticas principales del texto analizado.
     * 
     * @return String formateado con las estadísticas del texto
     */
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