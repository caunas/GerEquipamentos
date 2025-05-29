package core;
import java.util.List;
import java.util.ArrayList;

/**
 * Autor: Robert
 * Essa classe é responsável por gerênciar a lista de equipamentos
 * */

public class EquipamentoDAO {
  static List<Equipamento> EquipamentoLista = new ArrayList<>();
  private static int indiceProximo = 1;

  public static List listarTodos(){
    return EquipamentoLista;
  }

  public static List buscarPorNome(String nome) {
    List<String> out = new ArrayList<>();
    for (int i = 0; i < EquipamentoLista.size(); i++) {
      if (EquipamentoLista.get(i).getNome() == nome) {
        out.add(EquipamentoLista.get(i).toString());
      }
    }
    return out;
  }

  public static String buscarPorId(int id){
    String out = "";
    for(int i = 0; i < EquipamentoLista.size(); i++){
      if(EquipamentoLista.get(i).getId() == id){
        out = EquipamentoLista.get(i).toString();
      } else{
        out = "n tem n, rs";
      }
    }
    return out;
  }

  public static List buscarPorCategoria(String categoria){
    List<String> out = new ArrayList<>();
    for (int i = 0; i < EquipamentoLista.size(); i++) {
      if (EquipamentoLista.get(i).getCategoria() == categoria) {
        out.add(EquipamentoLista.get(i).toString());
      }
    }
    return out;
  }

  public static void atualizar(int id, String filtro, String novo_dado){
    for(int i = 0; i < EquipamentoLista.size(); i++){
      if(EquipamentoLista.get(i).getId() == id){
        if(filtro == "nome"){
          // atualizar nome
          EquipamentoLista.get(i).setNome(novo_dado);
        } else if (filtro == "categoria") {
          // atualizar categoria
          EquipamentoLista.get(i).setDetalhes(novo_dado);
        } else if(filtro == "detalhes"){
          // atualizar detalhes
          EquipamentoLista.get(i).setDetalhes(novo_dado);
        }
      }
    }
  }

  public static void adicionar(String nome, String categoria, String detalhes) {
    Equipamento novoEquip = new Equipamento(indiceProximo,
            nome,
            categoria,
            detalhes);

    EquipamentoLista.add(novoEquip);

    indiceProximo++;
  }

  public static void remover(int id){
    for(int i = 0; i <= EquipamentoLista.size(); i++){
      if(EquipamentoLista.get(i).getId() == id){
        EquipamentoLista.remove(i);
      }
    }
  }

  @Override
  public String toString(){
    return "Data Acess Object is Online!";
  }

}
