import java.util.Scanner;
import simboltable.ST;

public class SimbolTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ST<String, Integer> st = new ST<>();

        System.out.println("=== Ingrese palabras (Ctrl+D para terminar) ===");

        int i = 0;
        for (; scanner.hasNext(); i++) {
            String key = scanner.next();
            st.put(key, i);
        }

        System.out.println("\n=== Contenido de la tabla de símbolos ===");
        for (String s : st) {
            System.out.println("Clave: \"" + s + "\" → Valor: " + st.get(s));
        }

        System.out.println("Total de claves: " + st.size());

        scanner.close();
    }
}
