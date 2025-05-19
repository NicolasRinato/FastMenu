import java.sql.*;
import java.util.Scanner;

public class FuncoesSuperUsuario {
    private static final String URL = "jdbc:mysql://localhost:3306/fastmenu"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "";
    // Função 1
    protected static void listarRestaurantes() { 
        String sql = "SELECT c.id, r.cnpj, r.nome, r.telefone, r.descricao FROM cardapio AS c JOIN prato AS p ON c.id = p.Cardapio_ID JOIN restaurante AS r ON p.restaurante_cnpj = r.cnpj GROUP BY c.id, r.nome";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = conn.prepareStatement(sql)) {
        ResultSet rs = stmt.executeQuery();
        System.out.println("\n\n\t---- Lista de Restaurantes ----");
            while (rs.next()) {
                System.out.println("\nCardápio: " + rs.getInt("c.id"));
                System.out.println("CNPJ: " + rs.getString("r.cnpj"));
                System.out.println("Nome: " + rs.getString("r.nome"));
                System.out.println("Telefone: " + rs.getString("r.telefone"));
                System.out.println("Descrição: " + rs.getString("r.descricao"));
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            }
            System.out.println("\n\t---- Fim dos restaurantes ----\n");
        } catch (SQLException e) {
        System.out.println("Erro ao listar as informações: " + e.getMessage());}
    }
    //Função 2
    protected static void listarPratosTotal() { 
        String sql = "SELECT * FROM prato"; 
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {  
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n\n\t---- Lista de Pratos ----");
            while (rs.next()) {
                System.out.println("\nID do prato: " + rs.getInt("ID"));
                System.out.println("Restaurante CNPJ: " + rs.getString("Restaurante_CNPJ"));
                System.out.println("Cardápio: " + rs.getInt("Cardapio_ID"));
                System.out.println("Nome: " + rs.getString("Nome"));
                System.out.printf("Preço: R$ %.2f%n", rs.getDouble("Preco"));
                System.out.println("Descrição: " + rs.getString("Descricao"));
                System.out.println("\n--------------------------\n");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar as informações: " + e.getMessage());
        }
        System.out.println("\n\t--- Fim dos pratos ---\n\n");
    }
    //Função 3
    protected static void escolherRestaurante(int cardapio_id) {
        String sql = "SELECT * FROM prato WHERE cardapio_id = ?"; 
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cardapio_id); 
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n\n\t---- Lista de Pratos ----");
            if (!rs.isBeforeFirst()) {
                System.out.println("\nNenhum prato encontrado para este restaurante.");
            } else {
                while (rs.next()) {
                    System.out.println("\nID: " + rs.getInt("ID"));
                    System.out.println("Nome: " + rs.getString("Nome"));
                    System.out.printf("Preço: R$ %.2f%n", rs.getDouble("Preco"));
                    System.out.println("Descrição: " + rs.getString("Descricao"));
                    System.out.println("\n--------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar as informações: " + e.getMessage());
        }
        System.out.println("\n\t --- Fim dos pratos ---\n");
    }
    //Função 4
    protected static void listarAdministrador() { 
        String sql = "SELECT * FROM Administrador"; 
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {  
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n\n\t---- Lista de Administradores ----");
            if (!rs.isBeforeFirst()) {
                System.out.println("\nNenhum Administrador encontrado.");
            } else {
                while (rs.next()) {
                    System.out.println("\nCPF: " + rs.getString("cpf"));
                    System.out.println("Nome: " + rs.getString("nome"));
                    System.out.println("CNPJ do Restaurante: " + rs.getString("restaurante_cnpj"));
                    System.out.println("Senha: "+ rs.getString("senha"));
                    System.out.println("Cardapio: " + rs.getInt("cardapio_id"));
                    System.out.println("\n--------------------------\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar as informações: " + e.getMessage());
        }
        System.out.println("\n\t--- Fim dos Administradores ---\n\n");
    }
    // Função 5
    protected static void listarAdministradorRest(int id) { 
        String sql = "SELECT * FROM Administrador WHERE cardapio_id = ?"; 
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);  
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n\n\t---- Lista de Administradores ----");
            if (!rs.isBeforeFirst()) {
                System.out.println("\nNenhum Administrador encontrado para este restaurante.");
            } else {
                while (rs.next()) {
                    System.out.println("\nCPF: " + rs.getString("cpf"));
                    System.out.println("Nome: " + rs.getString("nome"));
                    System.out.println("CNPJ do Restaurante: " + rs.getString("restaurante_cnpj"));
                    System.out.println("Senha: " + rs.getString("senha"));
                    System.out.printf("ID do cardápio: " + rs.getInt("cardapio_id"));
                    System.out.println("\n--------------------------\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar as informações: " + e.getMessage());
        }
        System.out.println("\n\t--- Fim dos Administradores ---\n\n");
    }  
    // Função 6
    protected static void cadastrarRestaurante(Scanner scanner) {  
        System.out.println("\n\t---- Cadastrar Restaurante ----");
        System.out.println("\nModelo: 'XX.XXX.XXX/XXXX-XX'\nCNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.println("\nModelo: 'Panificadora Juliana'\nNome do Restaurante: ");
        String nome = scanner.nextLine();
        System.out.println("\nModelo: '(10)90000-0000'\nTelefone para contato:");
        String telefone = scanner.nextLine();
        System.out.println("\nModelo: 'Padaria do bairro'\nDescrição do restaurante: ");
        String descricao = scanner.nextLine();    
        
        System.out.println("\n\n\t--- Cadastrar Admin ---");
        System.out.println("\nModelo: 'XXX.XXX.XXX-XX' \nCPF: ");
        String cpf = scanner.nextLine();
        System.out.println("\nModelo: 'Eduardo Jess' \nNome: ");
        String nomeAdm = scanner.nextLine();
        System.out.println("\nModelo: '1234567890'\nSenha (max 10): ");
        String senhaAdm = scanner.nextLine();
        
        int idCardapio = 0;
        
        String sqlRestaurante = "INSERT INTO Restaurante (CNPJ, Nome, Telefone, Descricao) VALUES (?, ?, ?, ?)";
        String sqlCardapio = "INSERT INTO Cardapio (qnt_visitas) VALUES (?)";
        String sqlAdmin = "INSERT INTO Administrador (CPF, Nome, Restaurante_CNPJ, Senha, Cardapio_ID) VALUES (?, ?, ?, ?, ?)";
    
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmtRestaurante = conn.prepareStatement(sqlRestaurante);
             PreparedStatement stmtCardapio = conn.prepareStatement(sqlCardapio, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement stmtAdmin = conn.prepareStatement(sqlAdmin)) {
             
            stmtRestaurante.setString(1, cnpj);
            stmtRestaurante.setString(2, nome);
            stmtRestaurante.setString(3, telefone); 
            stmtRestaurante.setString(4, descricao);
            stmtRestaurante.executeUpdate(); // cria restaurante
            
            stmtCardapio.setInt(1, 0); 
            stmtCardapio.executeUpdate(); // cria cardapio
    
            try (ResultSet generatedKeys = stmtCardapio.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idCardapio = generatedKeys.getInt(1); // puxa o ultimo id gerado
                    System.out.println("ID do cardápio gerado: " + idCardapio);
                }
            }

            stmtAdmin.setString(1, cpf);
            stmtAdmin.setString(2, nomeAdm);
            stmtAdmin.setString(3, cnpj); 
            stmtAdmin.setString(4, senhaAdm);
            stmtAdmin.setInt(5, idCardapio); 
            stmtAdmin.executeUpdate(); 
    
            System.out.println("\nImportante: Anote o ID do cardápio!");
            System.out.println("\n\n\tRestaurante, cardápio e administrador adicionados com sucesso!\n\n");
        } catch (SQLException e) {
            System.out.println("\n\tErro ao adicionar o restaurante: " + e.getMessage() + "\n\n");
        }
    }
    // Função 7
    protected static void cadastrarAdministrador(Scanner scanner) { 
        System.out.println("\n\n\t---- Cadastro de Administrador ----\n");
        System.out.println("\nModelo: 'XX.XXX.XXX/XXXX-XX'\nCNPJ: ");
        String restauranteCnpj = scanner.nextLine();
        System.out.println("\nModelo: 'XXX.XXX.XXX-XX'\nCPF: ");
        String cpf = scanner.nextLine();
        System.out.println("\nModelo: 'Otavio Rinato'\nNome: ");
        String nome = scanner.nextLine();
        System.out.println("\nModelo: '1234567890'\nSenha (max 10): ");
        String senha = scanner.nextLine();
        System.out.println("\nModelo: '1' \nID do cardápio: ");
        int cardapioId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n\n\t ---- Fim do cadastro ----\n");
        String sql = "INSERT INTO Administrador (CPF, Nome, Restaurante_CNPJ, Senha, Cardapio_ID) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, restauranteCnpj); 
            stmt.setString(4, senha);
            stmt.setInt(5, cardapioId);
            stmt.executeUpdate();
            System.out.println("\n\n\tAdministrador cadastrado com sucesso!\n\n");
        } catch (SQLException e) {
            System.out.println("\n\tErro ao cadastrar um novo administrador: " + e.getMessage()+"\n\n");
        }
    }
    //Função 8
    protected static void alterarRestaurante(Scanner scanner) { 
        System.out.println("\nModelo: 'XX.XXX.XXX/XXXX-XX'\nDigite o CNPJ do Restaurante que deseja alterar: ");
        String cnpj = scanner.nextLine();
        System.out.print("\nModelo: 'Alex Panificadoras'\nNovo nome: ");
        String nome = scanner.nextLine();
        System.out.println("\nModelo: '(41)98991-0045'\nNovo telefone: ");
        String telefone = scanner.nextLine();
        System.out.println("\nModelo: 'Panificadora mais conhecida do bairro.'\nNova descrição: ");
        String descricao = scanner.nextLine();
        String sql = "UPDATE Restaurante SET nome = ?, telefone = ?, descricao = ?  WHERE CNPJ = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setString(3, descricao);
            stmt.setString(4, cnpj);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\n\n\tRestaurante atualizado com sucesso!\n\n");
            } else {
                System.out.println("\n\tRestaurante não encontrado.\n");
            }
        } catch (SQLException e) {
            System.out.println("\n\tErro ao editar o Restaurante: " + e.getMessage()+"\n\n");
        }
    }
    //Função 9
    protected static void alterarAdministrador(Scanner scanner) { 
        System.out.println("\nModelo: 'XXX.XXX.XXX-XX'\nDigite o CPF do Administrador que deseja alterar: ");
        String cpf = scanner.nextLine();
        System.out.print("\nModelo: 'Henrique Meira'\nNovo nome: ");
        String nome = scanner.nextLine();
        System.out.println("\nModelo: '0101010'\nNova senha: ");
        String senha = scanner.nextLine();
        String sql = "UPDATE Administrador SET nome = ?, senha = ?  WHERE CPF = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            stmt.setString(3, cpf);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\n\n\tAdministrador atualizado com sucesso!\n\n");
            } else {
                System.out.println("\n\tAdministrador não encontrado.\n");
            }
        } catch (SQLException e) {
            System.out.println("\n\tErro ao editar o Administrador: " + e.getMessage()+"\n\n");
        }
    }
    //Função 10
    protected static void removerRestaurante(Scanner scanner) { 
        System.out.print("\nModelo: 'XX.XXX.XXX/XXXX-XX'\nCNPJ do restaurante que será removido: ");
        String cnpj = scanner.nextLine();
        System.out.println("\nModelo: '1'\nDigite o ID do cardápio: ");
        int idCardapio = scanner.nextInt();
        scanner.nextLine();
        String sqlAdministrador = "DELETE FROM Administrador WHERE Restaurante_CNPJ = ? and Cardapio_ID = ?";
        String sqlPrato = "DELETE FROM Prato WHERE Restaurante_CNPJ = ? and Cardapio_ID = ?";
        String sqlCardapio = "DELETE FROM Cardapio WHERE ID = ?";
        String sqlRestaurante = "DELETE FROM Restaurante WHERE CNPJ = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmtAdmin = conn.prepareStatement(sqlAdministrador);
            PreparedStatement stmtPrato = conn.prepareStatement(sqlPrato);
            PreparedStatement stmtCardapio = conn.prepareStatement(sqlCardapio);
            PreparedStatement stmtRestaurante = conn.prepareStatement(sqlRestaurante)) {
            
            stmtAdmin.setString(1, cnpj);
            stmtAdmin.setInt(2, idCardapio);
            stmtAdmin.executeUpdate();
            
            stmtPrato.setString(1, cnpj);
            stmtPrato.setInt(2, idCardapio);
            stmtPrato.executeUpdate();
            
            stmtCardapio.setInt(1, idCardapio);
            stmtCardapio.executeUpdate();

            stmtRestaurante.setString(1, cnpj);
            stmtRestaurante.executeUpdate();
            System.out.println("\n\n\n\tRestaurante Removido com sucesso!\n\n");

        } catch (SQLException e) {
            System.out.println("\n\tErro ao remover o Restaurante: " + e.getMessage()+"\n\n");
        }
    }
    //Função 11
    protected static void removerAdministrador(Scanner scanner) { 
        System.out.print("\nModelo: 'XXX.XXX.XXX-XX'\nCPF do Administrador a ser removido: ");
        String cpf = scanner.nextLine();
        String sql = "DELETE FROM Administrador WHERE CPF = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\n\n\tAdministrador removido com sucesso!\n\n");
            } else {
                System.out.println("\n\nAdministrador não encontrado.\n\n");
            }
        } catch (SQLException e) {
            System.out.println("\n\tErro ao remover o Administrador: " + e.getMessage()+"\n\n");
        }
    }
}