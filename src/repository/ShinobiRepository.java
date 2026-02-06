package repository;

import exception.DatabaseOperationException;
import model.Shinobi;
import model.WarriorNinja;
import model.MedicNinja;
import utils.DatabaseConnection;
import repository.interfaces.CrudRepository;
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

        public Shinobi findById(Integer id) {
            return getById(id);
        }


        public List<Shinobi> findAll() {
            return getAll();
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
    // 1. Вывести вообще всех (то, что ты уже видел)
    public void printAll() {
        String sql = "SELECT * FROM Shinobi";
        // код выполнения...
    }

    // 2. Вывести только сильных (у кого чакра > 500)
    public void printPowerful() {
        String sql = "SELECT * FROM Shinobi WHERE chakra_level > 500";
        // код выполнения...
    }

    // 3. Вывести тех, кто использует конкретную технику
    public void printByTechnique(String tech) {
        String sql = "SELECT * FROM Shinobi WHERE technique = ?";
        // код выполнения с pstmt.setString(1, tech)...
    }

    public void printTop3Strongest() {
        System.out.println("--- Топ-3 Шиноби по уровню чакры ---");
        String sql = "SELECT name, chakra_level FROM Shinobi ORDER BY chakra_level DESC LIMIT 3";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("name") + " — Чакра: " + rs.getInt("chakra_level"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void printAverageChakra() {
        String sql = "SELECT AVG(chakra_level) as average FROM shinobi";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                System.out.println("Средний уровень чакры в базе: " + rs.getDouble("average"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void searchByTechniquePart(String part) {
        System.out.println("--- Поиск техники: " + part + " ---");
        String sql = "SELECT name, technique FROM shinobi WHERE technique LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + part + "%"); // % означает любой текст до и после
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name") + " использует: " + rs.getString("technique"));
            }
        } catch (SQLException e) { e.printStackTrace(); }


    }
    public void Printthemostchakralevel() {
        System.out.println("the most chakra level");
        String sql = "SELECT name, chakra_level FROM Shinobi ORDER BY chakra_level DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("name") + " — Чакра: " + rs.getInt("chakra_level"));
            }
        } catch (SQLException e) { e.printStackTrace();
        }

    }
}