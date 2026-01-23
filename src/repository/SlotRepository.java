package repository;

import utils.DatabaseConnection;
import java.sql.*;
import java.time.LocalDateTime;

public class SlotRepository {
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
}
