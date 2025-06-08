package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Autor: Robert
 * Essa classe é responsável por gerênciar a lista de equipamentos
 * Jeremias 29:11
 * */

public class EquipamentoDAO {
    private static List<Equipamento> equipamentoLista = new ArrayList<>();
    private static int indiceProximo = 1;

    public static List<Equipamento> listarTodos() {
        return equipamentoLista;
    }

    public static List<Equipamento> buscarPorNome(String nome) {
        List<Equipamento> out = new ArrayList<>();
        for (Equipamento eq : equipamentoLista) {
            if (eq.getNome().equalsIgnoreCase(nome)) {
                out.add(eq);
            }
        }
        return out;
    }

    public static Equipamento buscarPorId(int id) {
        for (Equipamento eq : equipamentoLista) {
            if (eq.getId() == id) {
                return eq;
            }
        }
        return null;
    }

    public static List<Equipamento> buscarPorCategoria(String categoria) {
        List<Equipamento> out = new ArrayList<>();
        for (Equipamento eq : equipamentoLista) {
            if (eq.getCategoria().equalsIgnoreCase(categoria)) {
                out.add(eq);
            }
        }
        return out;
    }

    public static void atualizar(int id, String filtro, String novoDado) {
        Equipamento eq = buscarPorId(id);
        if (eq != null) {
            switch (filtro.toLowerCase()) {
                case "nome":
                    eq.setNome(novoDado);
                    break;
                case "categoria":
                    eq.setCategoria(novoDado);
                    break;
                case "detalhes":
                    eq.setDetalhes(novoDado);
                    break;
            }
        }
    }

    public static void adicionar(String nome, String categoria, String detalhes) {
        Equipamento novoEquip = new Equipamento(indiceProximo++, nome, categoria, detalhes);
        equipamentoLista.add(novoEquip);
    }

    public static boolean remover(int id) {
        return equipamentoLista.removeIf(eq -> eq.getId() == id);
    }

    public static void limparTudo() {
        equipamentoLista.clear();
        indiceProximo = 1;
    }

    @Override
    public String toString() {
        return "DAO operacional com " + equipamentoLista.size() + " equipamentos.";
    }
}
