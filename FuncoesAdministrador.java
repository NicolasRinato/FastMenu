import java.sql.*;
import java.util.Scanner;

public abstract class FuncoesAdministrador {
    private static String restauranteCnpj;
    private static int cardapioId;
    private static final String URL = "jdbc:mysql://localhost:3306/fastmenu"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "";
    
    public static void realizarLogin(Scanner scanner){    
        System.out.println("\n\t --- Informações do Restaurante ---");
        System.out.println("\nModelo: 'XX.XXX.XXX/XXXX-XX'\nCNPJ: ");
        String digRestCnpj = scanner.nextLine();
        System.out.println("\nModelo: '2'\nID do cardápio: ");
        int digIdCardapio = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n\n\t --- Informações do Administrador ---");
        System.out.println("\nModelo: 'XXX.XXX.XXX-XX'\nCPF: ");
        String digCpf = scanner.nextLine();
        System.out.println("\nModelo: '1234567890'\nSenha (max 10): ");
        String digSenha = scanner.nextLine();
        String senha = "";
        String cpf = "";
        cardapioId = digIdCardapio;
        restauranteCnpj = digRestCnpj;
        String sql = "SELECT CPF, Senha FROM Administrador WHERE Restaurante_Cnpj = ?"; 
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, restauranteCnpj);  
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n\n\n");
            while (rs.next()) {
                cpf = rs.getString("CPF");
                senha = rs.getString("Senha");
                if (digCpf.equals(cpf) && digSenha.equals(senha)){
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar as informações: " + e.getMessage());
        }
        while (!digCpf.equals(cpf) || !digSenha.equals(senha)) {
            System.out.println("\n\n\tHouve um erro! \nCPF ou Senha estão incorretos...");
            System.out.println("Aperte 'Enter' para continuar: ");
            scanner.nextLine();
            System.out.println("\nModelo: 'XXX.XXX.XXX-XX'\nCPF: ");
            digCpf = scanner.nextLine();
            System.out.println("\nModelo: '1234567890'\nSenha (max 10): ");
            digSenha = scanner.nextLine();
        }
        System.out.println("Login bem-sucedido!");
    }
    // =============================== Gets importantes ===============================
    public static String getRestauranteCnpj() {
        return restauranteCnpj;
    }
    
    public static int getCardapioId() {  
        return cardapioId;
    }
    // =============================== Funções do Adm ===============================
    protected static void visualizarPratos(String cnpj) { 
        String sql = "SELECT * FROM prato WHERE Restaurante_CNPJ = ?"; 
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cnpj);  
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n\n\t---- Lista de Pratos ----");
            if (!rs.isBeforeFirst()) {
                System.out.println("\nNenhum prato encontrado para este restaurante.");
            } else {
                while (rs.next()) {
                    System.out.println("\nID do Prato: " + rs.getInt("ID"));
                    System.out.println("Restaurante CNPJ: " + rs.getString("Restaurante_CNPJ"));
                    System.out.println("ID do Cardápio: " + rs.getInt("Cardapio_ID"));
                    System.out.println("Nome: " + rs.getString("Nome"));
                    System.out.printf("Preço: R$ %.2f%n", rs.getDouble("Preco"));
                    System.out.println("Descrição: " + rs.getString("Descricao"));
                    System.out.println("\n--------------------------\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar as informações: " + e.getMessage());
        }
        System.out.println("\n\t--- Fim dos pratos ---\n\n");
    }
    protected static void cadastrarPrato(Scanner scanner) { 
        String restauranteCnpj = getRestauranteCnpj(); 
        System.out.println("\n\t --- Cadastrar prato ---");
        System.out.print("\nModelo: 'Bolo de Cenoura'\nNome: ");
        String nome = scanner.nextLine();
        System.out.print("\nModelo: '26,50' Utilize ','\nPreço: ");
        Double preco = scanner.nextDouble();
        scanner.nextLine();
        int cardapioId = getCardapioId();
        System.out.println("\nModelo: 'Especialidade da casa'\nDescrição: ");
        String descricao = scanner.nextLine();
        String sql = "INSERT INTO Prato (Restaurante_CNPJ, Nome, Preco, Cardapio_ID, Descricao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, restauranteCnpj);
            stmt.setString(2, nome);
            stmt.setDouble(3, preco); 
            stmt.setInt(4, cardapioId);
            stmt.setString(5, descricao);
            stmt.executeUpdate();
            System.out.println("\n\n\tPrato adicionado com sucesso!\n\n");
        } catch (SQLException e) {
            System.out.println("\n\tErro ao adicionar prato: " + e.getMessage()+"\n\n");
        }
    }
    protected static void alterarPrato(Scanner scanner) { 
        System.out.print("ID do prato a ser editado: ");
        int digId = scanner.nextInt();
        scanner.nextLine();
        String cnpjPrato = getRestauranteCnpj();
        int idCardapio = getCardapioId();
        String sqlVerificador = "SELECT ID FROM Prato WHERE Restaurante_CNPJ = ? AND Cardapio_ID = ? AND ID = ?";
        String sql = "UPDATE Prato SET Nome = ?, Preco = ?, Descricao = ? WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmtVerificador = conn.prepareStatement(sqlVerificador); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmtVerificador.setString(1, cnpjPrato);
            stmtVerificador.setInt(2, idCardapio);
            stmtVerificador.setInt(3, digId);
            ResultSet rs = stmtVerificador.executeQuery();
            if (rs.next()) {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Preço: ");
                Double preco = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Descrição: ");
                String descricao = scanner.nextLine();
    
                stmt.setString(1, nome);
                stmt.setDouble(2, preco);
                stmt.setString(3, descricao);
                stmt.setInt(4, digId);
    
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("\n\n\tPrato atualizado com sucesso!\n\n");
                } else {
                    System.out.println("\n\tErro ao atualizar o prato.\n");
                }
            } else {
                System.out.println("\n\n\tEsse prato não faz parte do seu restaurante!\n");
            }
        } catch (SQLException e) {
            System.out.println("\n\tErro ao editar o prato: " + e.getMessage() + "\n\n");
        }
    }
    protected static void removerPrato(Scanner scanner) { 
        System.out.print("ID do prato a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String cnpjPrato = getRestauranteCnpj();
        int idCardapio = getCardapioId();
        String sqlVerificador = "SELECT ID FROM Prato WHERE ID = ? AND Restaurante_CNPJ = ? AND Cardapio_ID = ?";
        String sqlRemocao = "DELETE FROM Prato WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmtVerificador = conn.prepareStatement(sqlVerificador); 
             PreparedStatement stmtRemocao = conn.prepareStatement(sqlRemocao)) {

            stmtVerificador.setInt(1, id);
            stmtVerificador.setString(2, cnpjPrato);
            stmtVerificador.setInt(3, idCardapio);
            ResultSet rs = stmtVerificador.executeQuery();
            if (rs.next()) {
                stmtRemocao.setInt(1, id);
                int rowsAffected = stmtRemocao.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("\n\n\tPrato removido com sucesso!\n\n");
                } else {
                    System.out.println("\n\tErro ao remover o prato.\n\n");
                }
            } else {
                System.out.println("\n\n\tEsse prato não faz parte do seu restaurante!\n");
            }
        } catch (SQLException e) {
            System.out.println("\n\tErro ao remover o prato: " + e.getMessage() + "\n\n");
        }
    }
}