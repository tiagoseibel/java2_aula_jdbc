package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Editora;
import util.ConnectionJDBC;

public class EditoraDAO {

    Connection connection;

    public EditoraDAO() throws Exception {
        // Obtem uma conexão
        connection = ConnectionJDBC.getConnection();
    }

    public void save(Editora editora) throws Exception {
        String SQL = "INSERT INTO EDITORA VALUES (?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, editora.getEditora_id());
            p.setString(2, editora.getNome());
            p.setString(3, editora.getMunicipio() );
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(Editora editora) throws Exception {
        String SQL = "UPDATE EDITORA SET NOME=?, MUNICIPIO=? WHERE EDITORA_ID=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, editora.getNome());
            p.setString(2, editora.getNome());
            p.setInt(3, editora.getEditora_id());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void delete(Editora editora) throws Exception {
        String SQL = "DELETE FROM EDITORA WHERE EDITORA_ID=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, editora.getEditora_id());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Editora findById(int id) {
        return new Editora();
    }

    public List<Editora> findAll() throws Exception {
        // Lista para manter os valores do ResultSet
        List<Editora> list = new ArrayList<>();
        Editora objeto;
        String SQL = "SELECT * FROM EDITORA";
        try {
            // Prepara a SQL
            PreparedStatement p = connection.prepareStatement(SQL);
            // Executa a SQL e mantem os valores no ResultSet rs
            ResultSet rs = p.executeQuery();
            // Navega pelos registros no rs
            while (rs.next()) {
                // Instancia a classe e informa os valores do BD
                objeto = new Editora();
                objeto.setEditora_id(rs.getInt("editora_id"));
                objeto.setNome(rs.getString("nome"));
                objeto.setMunicipio( rs.getString("municipio") );
                // Inclui na lista
                list.add(objeto);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        // Retorna a lista
        return list;
    }
}
