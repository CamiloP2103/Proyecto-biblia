package util;

import java.text.Normalizer;

/**
 * Clase utilitaria para el procesamiento y normalización de texto.
 * Proporciona métodos estáticos para limpiar, normalizar y validar palabras
 * antes de su análisis.
 * 
 * <p>Las funcionalidades incluyen:
 * <ul>
 *   <li>Normalización de palabras (eliminación de acentos, caracteres especiales)</li>
 *   <li>Conversión a minúsculas</li>
 *   <li>Validación de palabras según criterios específicos</li>
 * </ul>
 * 
 * @author Camilo
 * @author Juan
 * @author Rodrigo
 */
public class ProcesadorTexto {
    
     /**
     * Normaliza una palabra eliminando caracteres especiales, acentos
     * y convirtiéndola a minúsculas.
     * 
     * <p>El proceso de normalización incluye:
     * <ul>
     *   <li>Conversión a minúsculas</li>
     *   <li>Eliminación de caracteres no alfabéticos</li>
     *   <li>Eliminación de acentos y diacríticos</li>
     * </ul>
     * 
     * @param palabra Palabra a normalizar
     * @return Palabra normalizada, cadena vacía si la entrada es null o vacía
     */
    public static String normalizarPalabra(String palabra) {
        if (palabra == null || palabra.isEmpty()) return "";
        
        palabra = palabra.toLowerCase()
                      .replaceAll("[^\\p{IsAlphabetic}]", "");
        palabra = Normalizer.normalize(palabra, Normalizer.Form.NFD)
                      .replaceAll("\\p{M}", "");
        
        return palabra;
    }

    /**
     * Valida si una palabra es considerada válida para el análisis.
     * Una palabra es válida si no es null, no está vacía y contiene
     * al menos una letra del alfabeto.
     * 
     * @param palabra Palabra a validar
     * @return true si la palabra es válida, false en caso contrario
     */
    public static boolean esPalabraValida(String palabra) {
        return palabra != null && !palabra.isEmpty() && palabra.matches(".*[a-z].*");
    }
}