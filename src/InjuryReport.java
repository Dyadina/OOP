// Клас для реєстрації та управління травмами
public class InjuryReport {
    // Тип травми
    private String type;
    
    // Опис травми
    private String description;
    
    // Дата звіту
    private String date;
    
    // Статус звіту (NEW, REVIEWED, RESOLVED)
    private String status;
    
    // Конструктор ініціалізації звіту про травму
    public InjuryReport(String type, String description, String date) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.status = "NEW";
    }
    
    // Отримання типу травми
    public String getType() {
        return type;
    }
    
    // Отримання опису
    public String getDescription() {
        return description;
    }
    
    // Отримання дати звіту
    public String getDate() {
        return date;
    }
    
    // Отримання статусу
    public String getStatus() {
        return status;
    }
    
    // Встановлення нового статусу
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Висновок деталей травми
    public void displayReport() {
        System.out.println("🏥 Звіт про травму:");
        System.out.println("   Тип: " + type);
        System.out.println("   Опис: " + description);
        System.out.println("   Дата: " + date);
        System.out.println("   Статус: " + status);
    }
}
