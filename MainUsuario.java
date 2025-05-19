import java.util.Scanner;

public class MainUsuario extends FuncoesUsuario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String limpaTela = "\n\n\n\n";
        int resposta;
        boolean sair = false;
        while (sair != true) {
            System.out.println(limpaTela + "\t -=- FastMenu -=-");
            System.out.println("\nEscolha uma opção: ");
            System.out.println("1) Ver lista de restaurantes");
            System.out.println("2) Escolher um restaurante");
            System.out.println("3) Sair do programa");
            System.out.println("\nOpção: ");
            resposta = scanner.nextInt();
            scanner.nextLine();
            switch (resposta) {
                case 1: 
                    listarRestaurantes();
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println(limpaTela + "Escolha um cardápio (ID)\nResposta: ");
                    int cardapio_id = scanner.nextInt();
                    scanner.nextLine();
                    escolherRestaurante(cardapio_id);
                    contarVisita(cardapio_id);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("\n\n\tFinalizando sessão!...\n");
                    sair = true;
                    break;
                default:
                    System.out.println(limpaTela + "\tOpção inválida!\n");
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
            }
        }
        scanner.close();
    }
}