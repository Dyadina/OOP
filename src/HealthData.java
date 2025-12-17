// Клас для представлення даних про здоров'я клієнта
public class HealthData {
    // Середній пульс
    private int averagePulse;
    
    // Годин сну
    private float sleepHours;
    
    // Дата отримання даних
    private String date;
    
    // Конструктор ініціалізації даних здоров'я
    public HealthData(int averagePulse, float sleepHours, String date) {
        this.averagePulse = averagePulse;
        this.sleepHours = sleepHours;
        this.date = date;
    }
    
    // Отримання середнього пульсу
    public int getAveragePulse() {
        return averagePulse;
    }
    
    // Отримання годин сну
    public float getSleepHours() {
        return sleepHours;
    }
    
    // Отримання дати
    public String getDate() {
        return date;
    }
    
    // Висновок про здоров'я
    public void displayHealthStatus() {
        System.out.println("📊 Дані здоров'я за " + date + ":");
        System.out.println("   Середній пульс: " + averagePulse + " bpm");
        System.out.println("   Сон: " + sleepHours + " годин");
    }
}
