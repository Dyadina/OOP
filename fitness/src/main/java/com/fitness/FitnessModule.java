package com.fitness;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class FitnessModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(String.class)
                .annotatedWith(Names.named("JDBC URL"))
                .toInstance("jdbc:sqlite:target/fitness.db");

        bind(WorkoutSessionService.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    Connection provideConnection(@Named("JDBC URL") String url) {
        try {
            Connection connection = DriverManager.getConnection(url);
            createTablesIfNotExists(connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    private void createTablesIfNotExists(Connection connection) {
        String createWorkoutSessions =
                "CREATE TABLE IF NOT EXISTS workout_sessions (" +
                "id TEXT NOT NULL, " +
                "client_id TEXT NOT NULL, " +
                "date TEXT NOT NULL, " +
                "load INTEGER NOT NULL, " +
                "done INTEGER NOT NULL, " +
                "wellbeing INTEGER, " +
                "avg_pulse INTEGER, " +
                "sleep_hours REAL, " +
                "PRIMARY KEY (id, client_id)" +
                ")";

        try (Statement st = connection.createStatement()) {
            st.execute(createWorkoutSessions);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create tables", e);
        }
    }
}
