package com.fitness;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.inject.Inject;

public class WorkoutSessionService {
    private final Connection connection;

    @Inject
    public WorkoutSessionService(Connection connection) {
        this.connection = connection;
    }

    public void save(String clientId, WorkoutSession session, boolean done, Integer wellbeing, Integer avgPulse, Double sleepHours) {
        String sql = "INSERT INTO workout_sessions " +
                "(id, client_id, date, load, done, wellbeing, avg_pulse, sleep_hours) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT(id, client_id) DO UPDATE SET " +
                "date=excluded.date, load=excluded.load, done=excluded.done, " +
                "wellbeing=excluded.wellbeing, avg_pulse=excluded.avg_pulse, sleep_hours=excluded.sleep_hours";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, session.getSessionId()); // ✅
            ps.setString(2, clientId);
            ps.setString(3, session.getDate());
            ps.setInt(4, session.getLoad());
            ps.setInt(5, done ? 1 : 0);

            if (wellbeing == null) ps.setNull(6, java.sql.Types.INTEGER);
            else ps.setInt(6, wellbeing);

            if (avgPulse == null) ps.setNull(7, java.sql.Types.INTEGER);
            else ps.setInt(7, avgPulse);

            if (sleepHours == null) ps.setNull(8, java.sql.Types.REAL);
            else ps.setDouble(8, sleepHours);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save workout session to DB", e);
        }
    }
}
