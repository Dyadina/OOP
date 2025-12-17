// Клас для представлення однієї тренувальної сесії
public class WorkoutSession {
    // Унікальний ідентифікатор тренування
    private String sessionId;
    
    // Дата тренування
    private String date;
    
    // Навантаження (в хвилинах)
    private int load;
    
    // Статус виконання
    private boolean isCompleted;
    
    // Оцінка самопочуття (1-10)
    private int wellbeingScore;
    
    // Конструктор ініціалізації тренувальної сесії
    public WorkoutSession(String sessionId, String date, int load) {
        this.sessionId = sessionId;
        this.date = date;
        this.load = load;
        this.isCompleted = false;
        this.wellbeingScore = 0;
    }
    
    // Отримання ID сесії
    public String getSessionId() {
        return sessionId;
    }
    
    // Отримання дати тренування
    public String getDate() {
        return date;
    }
    
    // Отримання навантаження
    public int getLoad() {
        return load;
    }
    
    // Встановлення навантаження (корегування тренером)
    public void setLoad(int newLoad) {
        this.load = newLoad;
        System.out.println("⚙️  Навантаження для тренування " + sessionId + " змінено на " + newLoad + " хвилин");
    }
    
    // Позначення тренування як виконаного
    public void markDone() {
        isCompleted = true;
        System.out.println("✅ Тренування " + sessionId + " позначено як виконане");
    }
    
    // Оцінка самопочуття після тренування
    public void rateWellbeing(int score) {
        if (score >= 1 && score <= 10) {
            this.wellbeingScore = score;
            System.out.println("😊 Самопочуття оцінено: " + score + "/10");
        }
    }
    
    // Отримання статусу виконання
    public boolean isCompleted() {
        return isCompleted;
    }
    
    // Отримання оцінки самопочуття
    public int getWellbeingScore() {
        return wellbeingScore;
    }
    
    // Висновок деталей тренування
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
