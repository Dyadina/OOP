package com.fitness;

public class Client extends User {
    // Вік клієнта
    private int age;
    
    // Поточна оцінка здоров'я клієнта
    private int healthScore;
    
    // Конструктор ініціалізації клієнта
    public Client(String id, String name, int age) {
        super(id, name);
        this.age = age;
        this.healthScore = 75;
    }
    
    // Отримання віку клієнта
    public int getAge() {
        return age;
    }
    
    // Отримання поточної оцінки здоров'я
    public int getHealthScore() {
        return healthScore;
    }
    
    // Позначення виконання тренування
    public void markWorkoutDone(WorkoutSession session) {
        session.markDone();
        System.out.println("✅ Клієнт " + name + " позначив виконання тренування");
    }
    
    // Оцінка самопочуття після тренування
    public void rateWellbeing(WorkoutSession session, int score) {
        session.rateWellbeing(score);
        // Оновлення загальної оцінки здоров'я на основі самопочуття
        if (score >= 7) {
            healthScore = Math.min(100, healthScore + 5);
        } else if (score <= 4) {
            healthScore = Math.max(0, healthScore - 10);
        }
        System.out.println("📊 Оцінка здоров'я клієнта " + name + " оновлена: " + healthScore);
    }
    
    // Оцінка плану тренувань
    public void viewPlan(MonthlyPlan plan) {
        System.out.println("\n👀 Клієнт " + name + " переглядає план:");
        plan.displayPlan();
    }
    
    // Повідомлення про травму
    public InjuryReport reportInjury(String type, String description, String date) {
        InjuryReport report = new InjuryReport(type, description, date);
        System.out.println("🚨 Клієнт " + name + " повідомив про травму!");
        report.displayReport();
        return report;
    }
}
