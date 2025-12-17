import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Головний клас додатку для управління фітнес-системою
public class FitnessApp {
    // Список зареєстрованих клієнтів
    private List<Client> clients;
    
    // Список тренерів
    private List<Trainer> trainers;
    
    // Список підключених пристроїв
    private List<DataProvider> wearableDevices;
    
    // Знову збережені плани для кожного клієнта
    private Map<String, MonthlyPlan> clientPlans;
    
    // Список всіх звітів про травми
    private List<InjuryReport> injuryReports;
    
    // Конструктор ініціалізації додатку
    public FitnessApp() {
        this.clients = new ArrayList<>();
        this.trainers = new ArrayList<>();
        this.wearableDevices = new ArrayList<>();
        this.clientPlans = new HashMap<>();
        this.injuryReports = new ArrayList<>();
    }
    
    // Реєстрація нового клієнта
    public void registerClient(Client client) {
        clients.add(client);
        System.out.println("✅ Клієнт " + client.getName() + " зареєстрований в системі");
    }
    
    // Додавання тренера до системи
    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
        System.out.println("✅ Тренер " + trainer.getName() + " додан до системи");
    }
    
    // Підключення носимого пристрою (залежність через інтерфейс)
    public void connectDevice(DataProvider device) {
        wearableDevices.add(device);
        System.out.println("✅ Пристрій підключено до системи");
    }
    
    // Імпорт даних здоров'я від усіх пристроїв
    public void importHealthData() {
        System.out.println("\n📥 Імпорт даних здоров'я від пристроїв:");
        for (DataProvider device : wearableDevices) {
            HealthData data = device.fetchHealthData();
            data.displayHealthStatus();
        }
    }
    
    // Збереження місячного плану для клієнта
    public void savePlan(String clientId, MonthlyPlan plan) {
        clientPlans.put(clientId, plan);
        System.out.println("💾 План на " + plan.getMonth() + " збережено для клієнта " + clientId);
    }
    
    // Отримання збереженого плану для клієнта
    public MonthlyPlan getPlan(String clientId) {
        return clientPlans.get(clientId);
    }
    
    // Застосування корегування навантаження на основі травми
    public void applyLoadAdjustment(String trainerId, String clientId, int reductionPercent) {
        System.out.println("\n⚙️  Застосування корегування навантаження:");
        System.out.println("   Результат травми - зменшення навантаження на " + reductionPercent + "%");
        
        MonthlyPlan plan = clientPlans.get(clientId);
        if (plan != null) {
            for (WorkoutSession ws : plan.getAllWorkouts()) {
                int newLoad = (int)(ws.getLoad() * (100 - reductionPercent) / 100.0);
                ws.setLoad(newLoad);
            }
            System.out.println("   Навантаження в плані скориговано");
        }
    }
    
    // Реєстрація звіту про травму
    public void reportInjury(InjuryReport report) {
        injuryReports.add(report);
        System.out.println("📝 Звіт про травму зареєстрований у системі");
    }
    
    // Отримання списку всіх клієнтів
    public List<Client> getAllClients() {
        return clients;
    }
    
    // Отримання списку всіх тренерів
    public List<Trainer> getAllTrainers() {
        return trainers;
    }
    
    // Висновок статусу системи
    public void displaySystemStatus() {
        System.out.println("\n==================================================");
        System.out.println("📊 СТАТУС ФІТНЕС-СИСТЕМИ");
        System.out.println("==================================================");
        System.out.println("Зареєстровано клієнтів: " + clients.size());
        System.out.println("Активних тренерів: " + trainers.size());
        System.out.println("Підключених пристроїв: " + wearableDevices.size());
        System.out.println("Звітів про травми: " + injuryReports.size());
        System.out.println("Активних планів: " + clientPlans.size());
    }
}
