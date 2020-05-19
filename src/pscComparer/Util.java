
package pscComparer;

import java.util.Arrays;
import java.util.List;

public class Util {
    public static void mostrarLista(List l) {
        for (Object o : l) {
            System.out.println(o);
        }
    }
    
    public static void mostrarLista(Object[] l) {
        mostrarLista(Arrays.asList(l));
    }
    
    public static void compararListas(List l1, List l2) {
        for (int i = 0; i < l1.size(); i++) {
            System.out.println(l1.get(i) + " || " + l2.get(i));
        }
    }
    
    public static boolean existeStringEnLista(String cad, List<String> lista) {
        boolean existe = false;
        int i = 0;
        while (i < lista.size() && !lista.get(i).equalsIgnoreCase(cad)) {
            i++;
        }
        if (i < lista.size()) {
            existe = true;
        }
        return existe;
    }
    
    public static boolean hayItemDeListaEnCadena(String cad, String[] lista) {
        boolean existe = false;
        int i = 0;
        while (i < lista.length && !cad.contains(lista[i])) {
            i++;
        }
        if (i < lista.length) {
            existe = true;
        }
        return existe;
    }
    
    public static String[] cadComasToArray(String cadComas) {
        String[] result = cadComas.split(",");
        return result.length == 1 && result[0].isEmpty() ? null : result;
    }
}
