// Define que a classe pertence ao pacote 'core'  

package core; 

// Importa as bibliotecas necessárias  

import java.util.ArrayList; // Usada para criar listas dinâmicas  

import java.util.List; // Interface que representa uma lista  

import java.io.*; // Importa todas as classes de entrada e saída (leitura e escrita de arquivos) 

/** 

* Autor: Robert 

* Essa classe é responsável por gerênciar a lista de equipamentos 

* Jeremias 29:11 

*             */ 

// Classe EquipamentoDAO faz operações de CRUD e manipulação de arquivos com os equipamentos  

public class EquipamentoDAO { 

// Lista estática para armazenar todos os equipamentos em memória 
private static List<Equipamento> equipamentoLista = new ArrayList<>(); 
 
// Variável estática que armazena o próximo ID a ser usado 
private static int indiceProximo = 1; 
 
// Caminho do arquivo CSV usado para salvar os dados dos equipamentos 
private static final String ARQUIVO = "equipamentos.csv"; 
 
// Retorna todos os equipamentos cadastrados 
public static List<Equipamento> listarTodos() { 
    return equipamentoLista; 
} 
 
// Busca equipamentos pelo nome (sem diferenciar maiúsculas de minúsculas) 
public static List<Equipamento> buscarPorNome(String nome) { 
    List<Equipamento> out = new ArrayList<>(); 
    for (Equipamento eq : equipamentoLista) { 
        if (eq.getNome().equalsIgnoreCase(nome)) { 
            out.add(eq); // Adiciona à lista de resultado se o nome for igual 
        } 
    } 
    return out; 
} 
 
// Busca um equipamento pelo seu ID 
public static Equipamento buscarPorId(int id) { 
    for (Equipamento eq : equipamentoLista) { 
        if (eq.getId() == id) { 
            return eq; 
        } 
    } 
    return null; // Retorna null se não encontrar 
} 
 
// Busca equipamentos por categoria 
public static List<Equipamento> buscarPorCategoria(String categoria) { 
    List<Equipamento> out = new ArrayList<>(); 
    for (Equipamento eq : equipamentoLista) { 
        if (eq.getCategoria().equalsIgnoreCase(categoria)) { 
            out.add(eq); 
        } 
    } 
    return out; 
} 
 
// Atualiza os dados de um equipamento com base no ID e no campo informado 
public static void atualizar(int id, String filtro, String novoDado) { 
    Equipamento eq = buscarPorId(id); // Busca o equipamento com o ID 
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
 
// Adiciona um novo equipamento à lista com ID automático 
public static void adicionar(String nome, String categoria, String detalhes) { 
    Equipamento novoEquip = new Equipamento(indiceProximo++, nome, categoria, detalhes); 
    equipamentoLista.add(novoEquip); 
} 
 
// Remove um equipamento da lista pelo ID. Retorna true se conseguiu remover 
public static boolean remover(int id) { 
    return equipamentoLista.removeIf(eq -> eq.getId() == id); 
} 
 
// Limpa todos os dados da lista de equipamentos e reseta o ID 
public static void limparTudo() { 
    equipamentoLista.clear(); 
    indiceProximo = 1; 
} 
 
// Salva os dados da lista em um arquivo CSV 
public static void salvarEmArquivo() { 
    try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) { 
        for (Equipamento eq : equipamentoLista) { 
            // Escreve os dados de cada equipamento separados por vírgula 
            writer.println(eq.getId() + "," + eq.getNome() + "," + eq.getCategoria() + "," + eq.getDetalhes()); 
        } 
    } catch (IOException e) { 
        // Mostra mensagem de erro se não conseguir salvar 
        System.out.println("Erro ao salvar dados: " + e.getMessage()); 
    } 
} 
 
// Exporta os dados da lista para um arquivo CSV especificado 
public static void exportarEmArquivo(File arquivoExportar) { 
    try (PrintWriter writer = new PrintWriter(new FileWriter(arquivoExportar))) { 
        for (Equipamento eq : equipamentoLista) { 
            // Escreve cada equipamento no arquivo no formato CSV 
            writer.println(eq.getId() + "," + eq.getNome() + "," + eq.getCategoria() + "," + eq.getDetalhes()); 
        } 
    } catch (IOException e) { 
        System.out.println("Erro ao exportar dados: " + e.getMessage()); 
    } 
} 
 
// Carrega os dados salvos no arquivo CSV para a lista de equipamentos 
public static void carregarDoArquivo() { 
    equipamentoLista.clear(); // Limpa antes de carregar 
    try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) { 
        String linha; 
        while ((linha = reader.readLine()) != null) { 
            // Divide a linha por vírgula 
            String[] partes = linha.split(","); 
            if (partes.length >= 4) { 
                int id = Integer.parseInt(partes[0]); 
                String nome = partes[1]; 
                String categoria = partes[2]; 
                String detalhes = partes[3]; 
                // Cria novo objeto e adiciona à lista 
                equipamentoLista.add(new Equipamento(id, nome, categoria, detalhes)); 
                // Atualiza o próximo ID 
                if (id >= indiceProximo) { 
                    indiceProximo = id + 1; 
                } 
            } 
        } 
    } catch (IOException e) { 
        System.out.println("Arquivo não encontrado ou erro ao carregar: " + e.getMessage()); 
    } 
} 
 
// Carrega os dados de um arquivo CSV externo (backup) 
public static void carregarBackup(File arquivoBackup) { 
    equipamentoLista.clear(); 
    try (BufferedReader reader = new BufferedReader(new FileReader(arquivoBackup))) { 
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
        System.out.println("Erro ao carregar o backup: " + e.getMessage()); 
    } 
} 
 
// Método toString sobrescrito para exibir quantidade de equipamentos cadastrados 
@Override 
public String toString() { 
    return "DAO operacional com " + equipamentoLista.size() + " equipamentos."; 
} 
  

} 

 
