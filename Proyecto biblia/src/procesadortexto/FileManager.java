/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesadortexto;

import java.io.*;

/**
 *
 * @author priet
 */
public class FileManager {
    public static String leerArchivo(String nombreArchivo) throws IOException {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            throw new FileNotFoundException("No se encontró el archivo: " + archivo.getAbsolutePath());
        }
        
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        }
        return contenido.toString();
    }
    
    public static void guardarArchivoLimpio(String nombreOriginal, String contenido) throws IOException {
        File archivoOriginal = new File(nombreOriginal);
        String nuevoNombre = archivoOriginal.getParent() + File.separator + 
                           archivoOriginal.getName().replace(".txt", "") + ".limpio.txt";
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(nuevoNombre))) {
            pw.print(contenido);
        }
    }
}