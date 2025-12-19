package com.fitness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;

// Головний клас додатку для управління фітнес-системою
public class FitnessApp {

    // DI: залежність від сервісу збереження в БД
    private final WorkoutSessionService workoutSessionService;

    private List<Client> clients;
    private List<Trainer> trainers;
    private List<DataProvider> wearableDevices;
    private Map<String, MonthlyPlan> clientPlans;
    private List<InjuryReport> injuryReports;

    private void initCollections() {
        this.clients = new ArrayList<>();
        this.trainers = new ArrayList<>();
        this.wearableDevices = new ArrayList<>();
        this.clientPlans = new HashMap<>();
        this.injuryReports = new ArrayList<>();
    }

    // ✅ Guice constructor
    @Inject
    public FitnessApp(WorkoutSessionService workoutSessionService) {
        this.workoutSessionService = workoutSessionService;
        initCollections();
    }

    // Якщо десь створюєш вручну — можна лишити, але save/DB тоді викликати НЕ МОЖНА
    public FitnessApp() {
        this.workoutSessionService = null;
        initCollections();
    }

    // ✅ Правильний метод збереження тренування в БД
    public void saveWorkoutSessionToDb(
            String clientId,
            String sessionId,
            String date,
            boolean done,
            Integer wellbeing,
            Integer avgPulse,
            Double sleepHours,
            int load
    ) {
        if (workoutSessionService == null) {
            throw new IllegalStateException("WorkoutSessionService не ініціалізовано. Створюй FitnessApp через Guice Injector.");
        }

        WorkoutSession ws = new WorkoutSession(sessionId, date, load);
        workoutSessionService.save(clientId, ws, done, wellbeing, avgPulse, sleepHours);

        System.out.println("💾 Тренування збережено в БД для клієнта " + clientId + " (" + date + ")");
    }

    // Зручний варіант без sessionId (генеруємо самі)
    public final void markWorkoutAndSave(
            String clientId,
            String date,
            boolean done,
            Integer wellbeing,
            Integer avgPulse,
            Double sleepHours,
            int load
    ) {
        String sessionId = clientId + "_" + date;
        saveWorkoutSessionToDb(clientId, sessionId, date, done, wellbeing, avgPulse, sleepHours, load);
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

    // Підключення носимого пристрою (через інтерфейс)
    public void connectDevice(DataProvider device) {
        wearableDevices.add(device);
        System.out.println("✅ Пристрій підключено до системи");
    }

    // ✅ ВАЖЛИВО: тут має бути fetchHealthData(), а не provideHealthData()
    public void importHealthData() {
        System.out.println("\n📥 Імпорт даних здоров'я від пристроїв:");
        for (DataProvider device : wearableDevices) {
            HealthData data = device.fetchHealthData();
            data.displayHealthStatus();
        }
    }

    public void savePlan(String clientId, MonthlyPlan plan) {
        clientPlans.put(clientId, plan);
        System.out.println("💾 План на " + plan.getMonth() + " збережено для клієнта " + clientId);
    }

    public MonthlyPlan getPlan(String clientId) {
        return clientPlans.get(clientId);
    }

    public void applyLoadAdjustment(String trainerId, String clientId, int reductionPercent) {
        System.out.println("\n⚙️  Застосування корегування навантаження:");
        System.out.println("   Результат травми - зменшення навантаження на " + reductionPercent + "%");

        MonthlyPlan plan = clientPlans.get(clientId);
        if (plan != null) {
            for (WorkoutSession ws : plan.getAllWorkouts()) {
                int newLoad = (int) (ws.getLoad() * (100 - reductionPercent) / 100.0);
                ws.setLoad(newLoad);
            }
            System.out.println("   Навантаження в плані скориговано");
        }
    }

    public void reportInjury(InjuryReport report) {
        injuryReports.add(report);
        System.out.println("📝 Звіт про травму зареєстрований у системі");
    }

    public List<Client> getAllClients() { return clients; }
    public List<Trainer> getAllTrainers() { return trainers; }

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
