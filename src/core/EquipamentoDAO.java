package core;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * Autor: Robert
 * Essa classe é responsável por gerênciar a lista de equipamentos
 * Jeremias 29:11
 * */

public class EquipamentoDAO {
    private static List<Equipamento> equipamentoLista = new ArrayList<>();
    private static int indiceProximo = 1;
    private static final String ARQUIVO = "equipamentos.csv";

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

    public static void salvarEmArquivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Equipamento eq : equipamentoLista) {
                writer.println(eq.getId() + "," + eq.getNome() + "," + eq.getCategoria() + "," + eq.getDetalhes());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static void carregarDoArquivo() {
        equipamentoLista.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length >= 4) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    String categoria = partes[2];
                    String detalhes = partes[3];
                    equipamentoLista.add(new Equipamento(id, nome, categoria, detalhes));
                    if (id >= indiceProximo) {
                        indiceProximo = id + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado ou erro ao carregar: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "DAO operacional com " + equipamentoLista.size() + " equipamentos.";
    }
}
