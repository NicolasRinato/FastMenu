import java.sql.*;

public abstract class  FuncoesUsuario {    
    private static final String URL = "jdbc:mysql://localhost:3306/fastmenu"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "";

    protected static void listarRestaurantes() {
        String sql = "SELECT c.id, r.nome, r.descricao FROM cardapio AS c JOIN prato AS p ON c.id = p.Cardapio_ID JOIN restaurante AS r ON p.restaurante_cnpj = r.cnpj GROUP BY c.id, r.nome";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = conn.prepareStatement(sql)) {
        ResultSet rs = stmt.executeQuery();
        System.out.println("\n\n\t---- Lista de Restaurantes ----");
            while (rs.next()) {
                System.out.println("\nID: " + rs.getInt("c.ID") + " | Nome: " + rs.getString("r.Nome") + " | Descrição: " + rs.getString("r.Descricao"));
            }
            System.out.println("\n\t---- Fim dos restaurantes ----\n");
        } catch (SQLException e) {
        System.out.println("Erro ao listar as informações: " + e.getMessage());
        }
    }

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

    protected static void contarVisita(int id) { 
        String sql = "SELECT qnt_visitas FROM cardapio WHERE id = ?"; 
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);  
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { 
                int novaVisita = rs.getInt("qnt_visitas") + 1;
                String sql1 = "UPDATE cardapio SET qnt_visitas = ? WHERE ID = ?";
                try (PreparedStatement stmt1 = conn.prepareStatement(sql1)) {
                    stmt1.setInt(1, novaVisita);
                    stmt1.setInt(2, id);
                    int rowsAffected = stmt1.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("");
                    } else {
                        System.out.println("\n\tCardapio não encontrado.\n");
                    }
                } catch (SQLException e) {
                    System.out.println("\n\tErro ao editar o Cardapio: " + e.getMessage()+"\n\n");
                }
            } else { // faz referencia ao primeiro if --> "if (rs.next()) "
                System.out.println("\n\tNenhum registro encontrado.\n");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar as informações: " + e.getMessage());
        }
    }
}