// Клас для представлення тренера в системі
public class Trainer extends User {
    // Спеціалізація тренера
    private String specialization;
    
    // Конструктор ініціалізації тренера
    public Trainer(String id, String name, String specialization) {
        super(id, name);
        this.specialization = specialization;
    }
    
    // Отримання спеціалізації тренера
    public String getSpecialization() {
        return specialization;
    }
    
    // Створення місячного плану для клієнта
    public MonthlyPlan createMonthlyPlan(String clientId, String month, String goal) {
        System.out.println("📋 Тренер " + name + " створює план на " + month + " для клієнта " + clientId);
        return new MonthlyPlan(month, goal, clientId);
    }
    
    // Коригування навантаження при травмі або потребі
    public final void adjustLoad(WorkoutSession session, int newLoad) {
        // Фінальний метод - не може бути перевизначений
        System.out.println("🔧 Тренер " + name + " коригує навантаження:");
        session.setLoad(newLoad);
    }
    
    // Аналіз та відповідь на звіт про травму
    public void analyzeInjury(InjuryReport report) {
        System.out.println("🏥 Тренер " + name + " аналізує травму:");
        report.displayReport();
        report.setStatus("REVIEWED");
        System.out.println("   Статус оновлено на: REVIEWED");
    }
}
