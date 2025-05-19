import java.util.Scanner;

public class MainSuperUsuario extends FuncoesSuperUsuario {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String limpaTela = "\n\n\n\n";
        int answer;
        boolean sair = false;
        while (sair != true) {
            System.out.println(limpaTela + "\tFastMenu(SuperMain)");
            System.out.println("\nEscolha uma opção: ");
            System.out.println("1) Visualizar a lista de Restaurantes");
            System.out.println("2) Listar todos os Pratos cadastrados");
            System.out.println("3) Listar Pratos de um Restaurante especifico");
            System.out.println("4) Listar todos os Administradores");
            System.out.println("5) Listar Administradores especificos");
            System.out.println("6) Cadastrar novo Restaurante");
            System.out.println("7) Cadastrar novo Administrador");
            System.out.println("8) Alterar Restaurante");
            System.out.println("9) Alterar Administrador");
            System.out.println("10) Apagar Restaurante");
            System.out.println("11) Apagar Administrador");
            System.out.println("12) Sair do programa");
            System.out.println("\nOpção: ");
            answer = scanner.nextInt();
            scanner.nextLine();
            switch (answer) {
                case 1:
                    listarRestaurantes();
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 2:
                    listarPratosTotal();
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println(limpaTela+"\tDigite a identificação");
                    System.out.println("\nModelo: '1'\nID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    escolherRestaurante(id);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 4:
                    listarAdministrador();
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 5:
                    System.out.println(limpaTela+"\tDigite a identificação");
                    System.out.println("\nModelo: '1'\nID: ");
                    int identificação = scanner.nextInt();
                    scanner.nextLine();
                    listarAdministradorRest(identificação);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 6:
                    cadastrarRestaurante(scanner);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 7:
                    cadastrarAdministrador(scanner);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 8:
                    alterarRestaurante(scanner);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 9:
                    alterarAdministrador(scanner);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 10:
                    removerRestaurante(scanner);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 11:
                    removerAdministrador(scanner);
                    System.out.println("Aperte 'Enter' para continuar: ");
                    scanner.nextLine();
                    break;
                case 12:
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
    }
}