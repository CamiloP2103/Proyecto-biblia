/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesadortexto;

import java.io.*;

/**
 * La clase FileManager proporciona métodos estáticos para leer y guardar archivos de texto.
 * 
 * @author Rodrigo
 * @author Juan
 * @author Camilo
 */
public class FileManager {

    /**
     * Lee el contenido de un archivo de texto y lo devuelve como una cadena.
     * 
     * @param nombreArchivo La ruta del archivo a leer.
     * @return El contenido del archivo como cadena.
     * @throws IOException Si ocurre un error al leer el archivo.
     * @throws FileNotFoundException Si el archivo no existe.
     */
    public static String leerArchivo(String nombreArchivo) throws IOException {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            throw new FileNotFoundException("No se encontro el archivo: " + archivo.getAbsolutePath());
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
    
    /**
     * Guarda el contenido proporcionado en un nuevo archivo con extensión .limpio.txt.
     * 
     * @param nombreOriginal La ruta del archivo original.
     * @param contenido El contenido a guardar.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public static void guardarArchivoLimpio(String nombreOriginal, String contenido) throws IOException {
        File archivoOriginal = new File(nombreOriginal);
        String nuevoNombre = archivoOriginal.getParent() + File.separator + archivoOriginal.getName().replace(".txt", "") + ".limpio.txt";
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(nuevoNombre))) {
            pw.print(contenido);
        }
    }
}