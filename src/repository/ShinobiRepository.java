package repository;

import exception.DatabaseOperationException;
import model.Shinobi;
import model.WarriorNinja;
import model.MedicNinja;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShinobiRepository {

    public void create(Shinobi shinobi) {
        String sql = "INSERT INTO shinobi (name, chakra_level, dtype) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, shinobi.getName());
            pstmt.setInt(2, shinobi.getChakraLevel());
            pstmt.setString(3, shinobi.getClass().getSimpleName()); // Сохраняем тип (Warrior или Medic)

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Ошибка при сохранении шиноbi: " + e.getMessage(), e);
        }
    }

    public List<Shinobi> getAll() {
        List<Shinobi> list = new ArrayList<>();
        String sql = "SELECT * FROM shinobi";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToShinobi(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Ошибка при получении списка: " + e.getMessage(), e);
        }
        return list;
    }

    public Shinobi getById(int id) {
        String sql = "SELECT * FROM shinobi WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToShinobi(rs);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Ошибка при поиске по ID: " + e.getMessage(), e);
        }
        return null;
    }

    public void update(int id, Shinobi shinobi) {
        String sql = "UPDATE shinobi SET name = ?, chakra_level = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, shinobi.getName());
            pstmt.setInt(2, shinobi.getChakraLevel());
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Ошибка при обновлении: " + e.getMessage(), e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM shinobi WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Ошибка при удалении: " + e.getMessage(), e);
        }
    }

    // Вспомогательный метод для преобразования строки БД в объект Java (Маппинг)
    private Shinobi mapResultSetToShinobi(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int chakra = rs.getInt("chakra_level");
        String type = rs.getString("dtype");

        if ("MedicNinja".equals(type)) {
            return new MedicNinja(id, name, chakra);
        } else {
            return new WarriorNinja(id, name, chakra);
        }
    }
}