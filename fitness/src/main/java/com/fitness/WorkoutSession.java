package com.fitness;

// Клас для представлення однієї тренувальної сесії
public class WorkoutSession {
    private String sessionId;
    private String date;
    private int load;

    private boolean isCompleted;
    private int wellbeingScore;

    public WorkoutSession(String sessionId, String date, int load) {
        this.sessionId = sessionId;
        this.date = date;
        this.load = load;
        this.isCompleted = false;
        this.wellbeingScore = 0;
    }

    public String getSessionId() {
        return sessionId;
    }

    // ✅ Псевдонім для сумісності зі старим кодом (якщо десь викликається getId())
    public String getId() {
        return sessionId;
    }

    public String getDate() {
        return date;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int newLoad) {
        this.load = newLoad;
        System.out.println("⚙️  Навантаження для тренування " + sessionId + " змінено на " + newLoad + " хвилин");
    }

    public void markDone() {
        isCompleted = true;
        System.out.println("✅ Тренування " + sessionId + " позначено як виконане");
    }

    public void rateWellbeing(int score) {
        if (score >= 1 && score <= 10) {
            this.wellbeingScore = score;
            System.out.println("😊 Самопочуття оцінено: " + score + "/10");
        }
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getWellbeingScore() {
        return wellbeingScore;
    }

    public void displayDetails() {
        String status = isCompleted ? "Виконано" : "Не виконано";
        System.out.println("💪 Тренування " + sessionId + ":");
        System.out.println("   Дата: " + date);
        System.out.println("   Навантаження: " + load + " хвилин");
        System.out.println("   Статус: " + status);
        if (wellbeingScore > 0) {
            System.out.println("   Самопочуття: " + wellbeingScore + "/10");
        }
    }
}
