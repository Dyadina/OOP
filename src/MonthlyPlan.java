import java.util.ArrayList;
import java.util.List;

// Клас для представлення місячного тренувального плану
public class MonthlyPlan {
    // Назва місяця
    private String month;
    
    // Мета навантаження
    private String goal;
    
    // Список тренувань на місяць
    private List<WorkoutSession> workouts;
    
    // ID клієнта, якому належить план
    private String clientId;
    
    // Конструктор ініціалізації місячного плану
    public MonthlyPlan(String month, String goal, String clientId) {
        this.month = month;
        this.goal = goal;
        this.clientId = clientId;
        this.workouts = new ArrayList<>();
    }
    
    // Отримання назви місяця
    public String getMonth() {
        return month;
    }
    
    // Отримання мети плану
    public String getGoal() {
        return goal;
    }
    
    // Додавання тренування до плану
    public void addWorkout(WorkoutSession session) {
        workouts.add(session);
        System.out.println("📝 Тренування додано до плану на " + month);
    }
    
    // Отримання тренування за ID
    public WorkoutSession getSession(String sessionId) {
        for (WorkoutSession ws : workouts) {
            if (ws.getSessionId().equals(sessionId)) {
                return ws;
            }
        }
        return null;
    }
    
    // Розрахунок відсотка виконання плану
    public float calculateAdherence() {
        if (workouts.isEmpty()) {
            return 0;
        }
        int completed = 0;
        for (WorkoutSession ws : workouts) {
            if (ws.isCompleted()) {
                completed++;
            }
        }
        return (completed * 100.0f) / workouts.size();
    }
    
    // Отримання списку всіх тренувань
    public List<WorkoutSession> getAllWorkouts() {
        return workouts;
    }
    
    // Висновок плану з усіма тренуваннями
    public void displayPlan() {
        System.out.println("\n📅 План на " + month + ":");
        System.out.println("   Мета: " + goal);
        System.out.println("   Тренувань у плані: " + workouts.size());
        System.out.println("   Дотримання плану: " + String.format("%.1f", calculateAdherence()) + "%");
        System.out.println("   Тренування:");
        for (WorkoutSession ws : workouts) {
            System.out.println("     - " + ws.getSessionId() + " (" + ws.getDate() + ") - " + ws.getLoad() + " хв");
        }
    }
}
