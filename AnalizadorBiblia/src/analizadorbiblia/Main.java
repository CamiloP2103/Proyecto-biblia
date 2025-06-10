package analizadorbiblia;

import modelo.AnalizadorBiblia;
import java.util.Scanner;
    
/**
 * Clase principal del sistema Analizador de Textos.
 * Proporciona una interfaz de usuario por consola para analizar
 * textos y obtener estadísticas sobre el uso de palabras.
 * 
 * <p>El programa permite:
 * <ul>
 *   <li>Cargar y procesar archivos de texto</li>
 *   <li>Obtener estadísticas completas del texto</li>
 *   <li>Buscar frecuencia de palabras específicas</li>
 *   <li>Filtrar palabras por letra inicial</li>
 *   <li>Mostrar todas las palabras ordenadas alfabéticamente</li>
 * </ul>
 * 
 * @author Camilo
 * @author Juan
 * @author Rodrigo
 */
public class Main {
    
     /**
     * Scanner para manejar la entrada del usuario desde la consola.
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que inicia la aplicación.
     * Carga un archivo inicial y presenta el menú principal al usuario.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
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
    
    /**
     * Carga y procesa un archivo de texto especificado por el usuario.
     * Muestra el progreso del procesamiento y mide el tiempo de ejecución.
     * 
     * @param analizador Instancia del analizador donde se procesará el texto
     * @throws RuntimeException Si ocurre un error al leer el archivo
     */
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
    
    /**
     * Muestra el menú principal de opciones disponibles para el usuario.
     */
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
    
    /**
     * Obtiene y valida la opción seleccionada por el usuario.
     * Maneja entradas inválidas y solicita una nueva entrada hasta obtener un número válido.
     * 
     * @return Número entero correspondiente a la opción seleccionada
     */
    private static int obtenerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Por favor ingrese un número: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return opcion;
    }
    
    /**
     * Muestra las estadísticas completas del texto procesado.
     * Incluye total de palabras, palabras únicas y la palabra más repetida.
     * 
     * @param analizador Instancia del analizador con el texto procesado
     */
    private static void mostrarEstadisticasCompletas(AnalizadorBiblia analizador) {
        System.out.println("\nESTADÍSTICAS DEL TEXTO");
        System.out.println("----------------------");
        System.out.println("- Total de palabras (con repeticiones): " + analizador.getTotalPalabras());
        System.out.println("- Palabras únicas (sin repeticiones): " + analizador.getPalabrasUnicas());
        System.out.println("- Palabra más repetida: " + analizador.getPalabraMasRepetida());
    }
    
    /**
     * Permite al usuario buscar la frecuencia de una palabra específica.
     * 
     * @param analizador Instancia del analizador donde buscar la palabra
     */
    private static void buscarPalabra(AnalizadorBiblia analizador) {
        System.out.print("\nIngrese la palabra a buscar: ");
        String palabra = scanner.nextLine();
        
        int frecuencia = analizador.obtenerRepeticiones(palabra);
        System.out.printf("\nLa palabra '%s' aparece %d veces en el texto.%n", 
                         palabra, frecuencia);
    }
    
    /**
     * Busca y muestra todas las palabras que comienzan con una letra específica.
     * Valida que la entrada sea una única letra válida.
     * 
     * @param analizador Instancia del analizador donde buscar las palabras
     */
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
    
    /**
     * Muestra todas las palabras del texto ordenadas alfabéticamente.
     * Solicita confirmación del usuario antes de mostrar la lista completa
     * debido a la posible cantidad de palabras.
     * 
     * @param analizador Instancia del analizador con las palabras a mostrar
     */
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
    
    /**
     * Permite al usuario procesar un nuevo archivo, reemplazando el actual.
     * Solicita confirmación antes de proceder con la operación.
     * 
     * @param analizador Instancia del analizador donde cargar el nuevo archivo
     */
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