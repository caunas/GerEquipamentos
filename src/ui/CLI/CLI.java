/**
 *Autor: Vinicius
 * A classe CLI é responsável pela interface de linha de comando
 * CLASSE DEPRECIADA
 **/
package ui.CLI;
import java.util.Scanner;
import core.EquipamentoDAO;

public class CLI{
  // Gerenciador de equipamentos
  EquipamentoDAO object = new EquipamentoDAO();

    public static void call() {
      Scanner scanner = new Scanner(System.in);

      boolean saida = false;

      do {
        switch(menu(scanner)) {
          case 1:
            // Cadastrar equipamento, chamar func cadastrar do EquipamentoDAO
            EquipamentoDAO.adicionar(
                    "teste",
                    "categoria teste",
                    "detalhes teste"
            );
            break;
          case 2:
            System.out.println(EquipamentoDAO.listarTodos());
            break;
          case 3:
            EquipamentoDAO.atualizar(
                    2,
                    "nome",
                    "Atualizar Funciona");
            break;
          case 4:
            EquipamentoDAO.remover(3);
            break;
          case 5:
            saida = true;
            System.out.println("\nSaindo...");
            break;
        }
      }while(!saida);

    }

    public static int menu(Scanner scanner) {

      System.out.println("\n****************************");
      System.out.println("| CADASTRO DE EQUIPAMENTOS |");
      System.out.println("****************************\n");

      System.out.println("Tipos disponíveis de categorias para cadastro:\n");
      System.out.println(".Manutenção");
      System.out.println(".Construção");
      System.out.println(".Informática");


      System.out.println("\n");
      System.out.println("**************************");
      System.out.println("*          Menu          *");
      System.out.println("**************************");
      System.out.println("*   1. Cadastrar         *");
      System.out.println("*   2. Exibir cadastro   *");
      System.out.println("*   3. Editar cadastro   *");
      System.out.println("*   4. Excluir cadastro  *");
      System.out.println("*   5. Sair              *");
      System.out.println("**************************");

      System.out.println("\nEscolha uma opção:");
      int opcao = scanner.nextInt();

      return opcao;
    }
  }