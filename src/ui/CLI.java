package ui;
import java.util.Scanner;
import core.EquipamentoDAO;

public class CLI{
  EquipamentoDAO object = new EquipamentoDAO();
  public static void show_menu(){
    System.out.println("Selecione uma opção");
    System.out.println("1 - EquipamentoDAO (Teste de importação)");;
    System.out.println("0 - Sair");
  }

  public static void catch_option(int escolha){
    switch(escolha){
      case 1:
        EquipamentoDAO.teste();
        break;
      default:
        System.out.println("Opção inválida.");
    }
  }

  public static void call(){
    Scanner scan = new Scanner(System.in);
    show_menu();
    catch_option(scan.nextInt());
  }
}