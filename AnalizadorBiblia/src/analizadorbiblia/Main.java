package analizadorbiblia;

import modelo.AnalizadorBiblia;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AnalizadorBiblia analizador = new AnalizadorBiblia();
        
        System.out.println("ANALIZADOR DE TEXTOS BÍBLICOS");
        System.out.println("=============================\n");
        
        // Paso 1: Cargar archivo
        cargarArchivo(analizador);
        
        // Menú principal
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = obtenerOpcion();
            
            switch (opcion) {
                case 1:
                    mostrarEstadisticasCompletas(analizador);
                    break;
                case 2:
                    buscarPalabra(analizador);
                    break;
                case 3:
                    buscarPorLetraInicial(analizador);
                    break;
                case 4:
                    mostrarPalabrasOrdenadas(analizador);
                    break;
                case 5:
                    procesarNuevoArchivo(analizador);
                    break;
                case 6:
                    System.out.println("\nSaliendo del programa...");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente nuevamente.");
            }
            
            if (opcion != 6) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcion != 6);
        
        scanner.close();
    }
    
    private static void cargarArchivo(AnalizadorBiblia analizador) {
        System.out.print("Ingrese la ruta del archivo a analizar: ");
        String rutaArchivo = scanner.nextLine();
        
        try {
            long startTime = System.currentTimeMillis();
            
            // Procesar archivo línea por línea
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(rutaArchivo))) {
                String linea;
                int numLinea = 1;
                
                System.out.println("\nProcesando archivo...");
                while ((linea = br.readLine()) != null) {
                    analizador.procesarTexto(linea);
                    System.out.print("."); // Mostrar progreso
                    if (numLinea % 50 == 0) System.out.println();
                    numLinea++;
                }
                
                long endTime = System.currentTimeMillis();
                System.out.println("\n\n¡Archivo procesado con éxito!");
                System.out.printf("Tiempo de procesamiento: %.2f segundos%n", (endTime - startTime) / 1000.0);
            }
        } catch (Exception e) {
            System.err.println("\nError al procesar el archivo: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private static void mostrarMenuPrincipal() {
        System.out.println("\nMENÚ PRINCIPAL");
        System.out.println("1. Mostrar estadísticas completas");
        System.out.println("2. Buscar frecuencia de una palabra");
        System.out.println("3. Buscar palabras por letra inicial");
        System.out.println("4. Mostrar todas las palabras ordenadas");
        System.out.println("5. Procesar un nuevo archivo");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private static int obtenerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Por favor ingrese un número: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return opcion;
    }
    
    private static void mostrarEstadisticasCompletas(AnalizadorBiblia analizador) {
        System.out.println("\nESTADÍSTICAS DEL TEXTO");
        System.out.println("----------------------");
        System.out.println("- Total de palabras (con repeticiones): " + analizador.getTotalPalabras());
        System.out.println("- Palabras únicas (sin repeticiones): " + analizador.getPalabrasUnicas());
        System.out.println("- Palabra más repetida: " + analizador.getPalabraMasRepetida());
    }
    
    private static void buscarPalabra(AnalizadorBiblia analizador) {
        System.out.print("\nIngrese la palabra a buscar: ");
        String palabra = scanner.nextLine();
        
        int frecuencia = analizador.obtenerRepeticiones(palabra);
        System.out.printf("\nLa palabra '%s' aparece %d veces en el texto.%n", 
                         palabra, frecuencia);
    }
    
    private static void buscarPorLetraInicial(AnalizadorBiblia analizador) {
        System.out.print("\nIngrese la letra inicial: ");
        String input = scanner.nextLine();
        
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.println("Debe ingresar una sola letra válida.");
            return;
        }
        
        char letra = input.toLowerCase().charAt(0);
        System.out.printf("\nPalabras que empiezan con '%c':%n", letra);
        
        int contador = 0;
        for (String palabra : analizador.getPalabrasOrdenadas()) {
            if (!palabra.isEmpty() && Character.toLowerCase(palabra.charAt(0)) == letra) {
                System.out.printf("- %s (%d repeticiones)%n", 
                                palabra, analizador.obtenerRepeticiones(palabra));
                contador++;
            }
        }
        
        if (contador == 0) {
            System.out.println("No se encontraron palabras con esta letra inicial.");
        } else {
            System.out.println("\nTotal encontradas: " + contador);
        }
    }
    
    private static void mostrarPalabrasOrdenadas(AnalizadorBiblia analizador) {
        System.out.print("\n¿Mostrar todas las palabras? (s/n): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("s")) {
            System.out.println("\nLISTA COMPLETA DE PALABRAS ORDENADAS:");
            System.out.println("-----------------------------------");
            
            int contador = 0;
            for (String palabra : analizador.getPalabrasOrdenadas()) {
                System.out.printf("%-15s (%d repeticiones)%n", 
                                palabra, analizador.obtenerRepeticiones(palabra));
                contador++;
                
                if (contador % 20 == 0) {
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
                }
            }
            System.out.println("\nTotal de palabras únicas: " + contador);
        }
    }
    
    private static void procesarNuevoArchivo(AnalizadorBiblia analizador) {
        System.out.print("\n¿Está seguro que desea procesar un nuevo archivo? (s/n): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("s")) {
            cargarArchivo(analizador);
        } else {
            System.out.println("Operación cancelada.");
        }
    }
}