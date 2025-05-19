import java.util.Scanner;

public class MainAdministrador extends FuncoesAdministrador{
    public String restauranteCnpj;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String limpaTela = "\n\n\n\n";
        int opcao;
        int resposta;
        boolean sair = false;
        boolean logado = false;
        
        while (sair != true) {
            System.out.println(limpaTela + "\t -=- FastMenu(Admin) -=-");
            System.out.println("\nEscolha uma opção: ");
            System.out.println("1) Logar como Admin");
            System.out.println("2) Sair do programa");
            System.out.println("\nOpção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    realizarLogin(scanner);
                    sair = true;
                    logado = true;
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 2:
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
        String cnpj = getRestauranteCnpj(); // puxa o CNPJ do Adm
        while (logado != false){
            System.out.println(limpaTela + "\t -=- FastMenu(Admin) -=-");
            System.out.println("Escolha uma opção:");
            System.out.println("1) Visualizar pratos cadastrados");
            System.out.println("2) Cadastrar novo prato");
            System.out.println("3) Alterar dados de um prato");
            System.out.println("4) Remover um prato");
            System.out.println("5) Sair do programa");
            System.out.print("\nResposta: ");
            resposta = scanner.nextInt();
            scanner.nextLine();
            switch (resposta) {
                case 1:
                    visualizarPratos(cnpj);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 2: 
                    cadastrarPrato(scanner);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 3:   
                    alterarPrato(scanner);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 4: 
                    FuncoesAdministrador.removerPrato(scanner);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 5: 
                    System.out.println("\n\n\tFinalizando sessão!...\n");
                    logado = false;
                    break;
                default:
                System.out.println(limpaTela + "\tOpção inválida!\n");
                System.out.println("Aperte 'Enter' para continuar: ");
                scanner.nextLine();
            }
        }
    }
}