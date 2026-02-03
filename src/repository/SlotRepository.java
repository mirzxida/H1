package repository;

import model.Slot;
import model.Band;
import repository.interfaces.CrudRepository;
import utils.DatabaseConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class SlotRepository implements CrudRepository<Slot> {
    public boolean hasTimeCollision(
        int stageId,
        LocalDateTime start,
        LocalDateTime end) throws Exception{
        String sql = """
                SELECT COUNT(*) FROM slots
                WHERE stage_id = ?
                AND start_time < ?
                AND end_time > ?
                """;
        try (Connection c = DatabaseConnection.getConnection();PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, stageId);
            ps.setTimestamp(2,Timestamp.valueOf(end));
            ps.setTimestamp(3,Timestamp.valueOf(start));

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    public void bandOnStage() throws Exception {
        String sql = """
                SELECT s.stage_name,b.band_name
                FROM slots sl
                JOIN bands b ON sl.band_id = b.band_id
                JOIN stages s ON sl.stage_id = s.stage_id
                ORDER BY s.stage_name;
                """;
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            String currentStage = "";
            while (rs.next()) {
                String stageName = rs.getString("stage_name");
                String bandName = rs.getString("band_name");
                if (!stageName.equals(currentStage)) {
                    currentStage = stageName;
                    System.out.println("Stage: " + currentStage);
                }
                System.out.println("-" + bandName);
            }
        }
    }

    @Override
    public void create(Slot slot) throws Exception {
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM slots WHERE slot_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Slot findById(int id) throws Exception {
        String sql = """
            SELECT start_time, end_time
            FROM slots
            WHERE slot_id = ?
        """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return null;

            return new Slot(
                    null,   // Band можно подтянуть позже
                    null,   // Stage можно подтянуть позже
                    rs.getTimestamp("start_time").toLocalDateTime(),
                    rs.getTimestamp("end_time").toLocalDateTime()
            );
        }
    }

    @Override
    public void update(Slot slot) throws Exception {
        String sql = """
            UPDATE slots
            SET start_time = ?, end_time = ?
            WHERE band_id = ? AND stage_id = ?
        """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(slot.getStart()));
            ps.setTimestamp(2, Timestamp.valueOf(slot.getEnd()));
            ps.setInt(3, slot.getBand().getId());
            ps.setInt(4, slot.getStage().getId());

            ps.executeUpdate();
        }
    }

    @Override
    public List<Slot> findAll() throws Exception {
        List<Slot> slots = new ArrayList<>();

        String sql = """
        SELECT start_time, end_time
        FROM slots
    """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Slot slot = new Slot(
                        null, // Band подтягивается позже
                        null, // Stage подтягивается позже
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getTimestamp("end_time").toLocalDateTime()
                );
                slots.add(slot);
            }
        }
        return slots;
    }

    public List<Band> findAllBands() {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM bands";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Band band = new Band(
                        rs.getInt("band_id"),
                        rs.getString("band_name"),
                        rs.getString("country"),
                        rs.getString("genre")
                );
                bands.add(band);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bands;
    }


}
