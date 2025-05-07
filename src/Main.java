package Main;
import ui.CLI;

public class Main {
    public static void main(String[] args) {
        CLI object = new CLI();
        CLI.call(); // chama a interface de linha de comando
    }
}
