package com.fitness;

public class WorkoutLog {
    private final String clientId;
    private final String date;          // YYYY-MM-DD
    private final boolean completed;
    private final int wellbeing;        // 1..10

    public WorkoutLog(String clientId, String date, boolean completed, int wellbeing) {
        this.clientId = clientId;
        this.date = date;
        this.completed = completed;
        this.wellbeing = wellbeing;
    }

    public String getClientId() { return clientId; }
    public String getDate() { return date; }
    public boolean isCompleted() { return completed; }
    public int getWellbeing() { return wellbeing; }
}
