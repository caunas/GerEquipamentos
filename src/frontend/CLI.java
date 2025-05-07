package frontend;
import java.util.Scanner;
import core.EquipamentoDAO;

public class CLI{
  public static void show_menu(){
    System.out.println("Selecione uma opção");
    System.out.println("1 - Cadastrar Equipamento");
    System.out.println("2 - Buscar Equipamento");
    System.out.println("3 - Listar todos os Equipamentos");
    System.out.println("4 - Atualizar Equipamento");
    System.out.println("5 - Remover Equipamento");
    System.out.println("6 - Gerenciar Backups");
    System.out.println("0 - Sair");
  }
}