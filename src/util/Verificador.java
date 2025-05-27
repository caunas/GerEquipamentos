package util;

/**
* Autor: Vinicius
**/

public class Verificador {
    public static boolean validarNome(String nome) {
        return nome != null && nome.trim().length() > 1;
    }
    public static boolean validarCategoria(String categoria) {
        return categoria != null && categoria.equalsIgnoreCase("Construção") ||
                categoria.equalsIgnoreCase("Manutenção") ||
                categoria.equalsIgnoreCase("Informática");
    }
}
