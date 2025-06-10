package util;

import java.text.Normalizer;

public class ProcesadorTexto {
    public static String normalizarPalabra(String palabra) {
        if (palabra == null || palabra.isEmpty()) return "";
        
        palabra = palabra.toLowerCase()
                      .replaceAll("[^\\p{IsAlphabetic}]", "");
        palabra = Normalizer.normalize(palabra, Normalizer.Form.NFD)
                      .replaceAll("\\p{M}", "");
        
        return palabra;
    }

    public static boolean esPalabraValida(String palabra) {
        return palabra != null && !palabra.isEmpty() && palabra.matches(".*[a-z].*");
    }
}