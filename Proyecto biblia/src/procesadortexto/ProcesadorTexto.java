/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesadortexto;

/**
 *
 * @author priet
 */
public class ProcesadorTexto {
    
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
    
    public int contarPalabrasConLetra(String[] palabras, char letra) {
        int contador = 0;
        for (String palabra : palabras) {
            if (!palabra.isEmpty() && palabra.charAt(0) == letra) {
                contador++;
            }
        }
        return contador;
    }
    
    public int contarPalabrasConLetraEnParrafo(String parrafo, char letra) {
        String parrafoLimpio = limpiarTexto(parrafo);
        String[] palabras = parrafoLimpio.split(" ");
        return contarPalabrasConLetra(palabras, letra);
    }
}