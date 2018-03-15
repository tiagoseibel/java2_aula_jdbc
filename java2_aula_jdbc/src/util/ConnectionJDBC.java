package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectionJDBC {

    private static Connection connection;

    public static Connection getConnection() throws Exception {

        if (connection == null) {
            // Carregar o Driver JDBC
            try {
                Class.forName("org.firebirdsql.jdbc.FBDriver");
            } catch (ClassNotFoundException ex) {
                throw new Exception(ex);
            }

            /**
             * Conectar ao Banco de Dados URL:
             * jdbc:nomeDoProduto:servidor:porta:nomeDoBanco
             */
            String ip = "192.168.56.101";
            String database = "/databases/aula07.fdb";
            String url = "jdbc:firebirdsql:" + ip + "/3050:" + database;
            String usuario = "SYSDBA";
            String senha = "masterkey";

            try {
                // Obter uma conexao
                connection = DriverManager.getConnection(url, usuario, senha);
            } catch (SQLException ex) {
                throw new Exception(ex);
            }
        }

        return connection;
    }

    public static void main(String[] args) {
        System.out.println("Teste!");
        try {
            ConnectionJDBC.getConnection();
        } catch (Exception ex) {
            System.out.println(""+ex.getMessage());
        }
    }
}
