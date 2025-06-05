/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesadortexto;

/**
 * La clase ProcesadorTexto proporciona métodos para limpiar y analizar texto.
 * Incluye funciones para normalizar texto y contar palabras según diferentes criterios.
 * 
 * @author Rodrigo
 * @author Juan
 * @author Camilo
 */
public class ProcesadorTexto {
    
    /**
     * Normaliza un texto eliminando caracteres especiales, acentos y espacios redundantes.
     * 
     * @param texto El texto a limpiar.
     * @return El texto normalizado en minúsculas y sin caracteres especiales.
     */
    public String limpiarTexto(String texto) {
        String limpio = texto.toLowerCase()
            .replaceAll("[áàäâ]", "a")
            .replaceAll("[éèëê]", "e")
            .replaceAll("[íìïî]", "i")
            .replaceAll("[óòöô]", "o")
            .replaceAll("[úùüû]", "u")
            .replaceAll("[\\p{Punct}]", " ")
            .replaceAll("\\r?\\n", " ")
            .replaceAll("\\s+", " ")
            .trim();
        
        return limpio;
    }
    
    /**
     * Cuenta las palabras en un arreglo que comienzan con la letra especificada.
     * 
     * @param palabras Arreglo de palabras a analizar.
     * @param letra La letra inicial a buscar.
     * @return El número de palabras que comienzan con la letra.
     */
    public int contarPalabrasConLetra(String[] palabras, char letra) {
        int contador = 0;
        for (String palabra : palabras) {
            if (!palabra.isEmpty() && palabra.charAt(0) == letra) {
                contador++;
            }
        }
        return contador;
    }
    
    /**
     * Cuenta las palabras en un párrafo que comienzan con la letra especificada.
     * 
     * @param parrafo El párrafo a analizar.
     * @param letra La letra inicial a buscar.
     * @return El número de palabras que comienzan con la letra.
     */
    public int contarPalabrasConLetraEnParrafo(String parrafo, char letra) {
        String parrafoLimpio = limpiarTexto(parrafo);
        String[] palabras = parrafoLimpio.split(" ");
        return contarPalabrasConLetra(palabras, letra);
    }
}