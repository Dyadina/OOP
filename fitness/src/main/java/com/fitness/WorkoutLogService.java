package com.fitness;

import com.google.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkoutLogService {

    private final Connection connection;

    /**
     * Конструктор із впровадженням залежності (SQLite Connection) через Guice.
     */
    @Inject
    public WorkoutLogService(Connection connection) {
        this.connection = connection;
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS workout_logs (" +
                "client_id TEXT NOT NULL, " +
                "date TEXT NOT NULL, " +
                "completed INTEGER NOT NULL, " +
                "wellbeing INTEGER NOT NULL, " +
                "PRIMARY KEY (client_id, date)" +
                ")";
        try (Statement st = connection.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create table workout_logs", e);
        }
    }

    public void save(WorkoutLog log) {
        String sql = "INSERT OR REPLACE INTO workout_logs(client_id, date, completed, wellbeing) " +
                     "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, log.getClientId());
            ps.setString(2, log.getDate());
            ps.setInt(3, log.isCompleted() ? 1 : 0);
            ps.setInt(4, log.getWellbeing());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save workout log", e);
        }
    }
}
