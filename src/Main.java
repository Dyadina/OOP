// Головна програма для демонстрації роботи фітнес-застосунку
public class Main {
    public static void main(String[] args) {
        System.out.println("🏋️  ============================================");
        System.out.println("🏋️  Система управління фітнес-планами");
        System.out.println("🏋️  ============================================\n");
        
        // Створення екземпляру додатку
        FitnessApp app = new FitnessApp();
        
        // Крок 1: Реєстрація користувачів
        System.out.println("📋 КРОК 1: Реєстрація користувачів");
        System.out.println("─────────────────────────────────────────────\n");
        
        // Створення тренера
        Trainer trainer = new Trainer("T001", "Іван Петренко", "Кардіо та силові вправи");
        app.addTrainer(trainer);
        
        // Створення клієнтів
        Client client1 = new Client("C001", "Марія Сидоренко", 28);
        Client client2 = new Client("C002", "Петро Коваленко", 35);
        app.registerClient(client1);
        app.registerClient(client2);
        
        // Крок 2: Підключення пристроїв
        System.out.println("\n📋 КРОК 2: Підключення пристроїв");
        System.out.println("─────────────────────────────────────────────\n");
        
        // Створення та підключення носимих пристроїв
        WearableDevice device1 = new WearableDevice("D001", "FitBand Pro");
        WearableDevice device2 = new WearableDevice("D002", "SmartWatch X");
        app.connectDevice(device1);
        app.connectDevice(device2);
        
        // Крок 3: Імпорт даних здоров'я
        System.out.println("\n📋 КРОК 3: Імпорт даних здоров'я від пристроїв");
        System.out.println("─────────────────────────────────────────────");
        app.importHealthData();
        
        // Крок 4: Тренер створює план
        System.out.println("\n📋 КРОК 4: Тренер створює місячний план");
        System.out.println("─────────────────────────────────────────────\n");
        
        MonthlyPlan plan = trainer.createMonthlyPlan("C001", "Січень 2025", "Підвищення витривалості");
        
        // Додавання тренувань до плану
        WorkoutSession session1 = new WorkoutSession("WS001", "2025-01-06", 45);
        WorkoutSession session2 = new WorkoutSession("WS002", "2025-01-08", 50);
        WorkoutSession session3 = new WorkoutSession("WS003", "2025-01-10", 45);
        
        plan.addWorkout(session1);
        plan.addWorkout(session2);
        plan.addWorkout(session3);
        
        // Збереження плану
        app.savePlan("C001", plan);
        
        // Крок 5: Клієнт переглядає план
        System.out.println("\n📋 КРОК 5: Клієнт переглядає план");
        System.out.println("─────────────────────────────────────────────");
        client1.viewPlan(plan);
        
        // Крок 6: Клієнт виконує тренування
        System.out.println("\n📋 КРОК 6: Клієнт виконує тренування");
        System.out.println("─────────────────────────────────────────────\n");
        
        client1.markWorkoutDone(session1);
        client1.rateWellbeing(session1, 8);
        
        client1.markWorkoutDone(session2);
        client1.rateWellbeing(session2, 7);
        
        // Крок 7: Клієнт повідомляє про травму
        System.out.println("\n📋 КРОК 7: Клієнт повідомляє про травму");
        System.out.println("─────────────────────────────────────────────\n");
        
        InjuryReport injury = client1.reportInjury("Розтягнення м'яза", 
                                                     "Розтягнення в лівій ніці під час тренування", 
                                                     "2025-01-10");
        app.reportInjury(injury);
        
        // Крок 8: Тренер аналізує травму та коригує навантаження
        System.out.println("\n📋 КРОК 8: Тренер аналізує травму та коригує навантаження");
        System.out.println("─────────────────────────────────────────────\n");
        
        trainer.analyzeInjury(injury);
        
        // Тренер коригує окремі тренування
        System.out.println();
        trainer.adjustLoad(session3, 30);
        
        // Застосування корегування на весь план
        app.applyLoadAdjustment("T001", "C001", 25);
        
        // Крок 9: Чотири дні тому - третя тренування виконана
        System.out.println("\n📋 КРОК 9: Клієнт виконує відкориговане тренування");
        System.out.println("─────────────────────────────────────────────\n");
        
        client1.markWorkoutDone(session3);
        client1.rateWellbeing(session3, 6);
        
        // Крок 10: Перегляд фінального стану плану
        System.out.println("\n📋 КРОК 10: Перегляд фінального стану плану");
        System.out.println("─────────────────────────────────────────────");
        plan.displayPlan();
        
        // Крок 11: Висновок статусу системи
        System.out.println("\n📋 КРОК 11: Статус системи");
        System.out.println("─────────────────────────────────────────────");
        app.displaySystemStatus();
        
        // Завершення
        System.out.println("\n🎉 Демонстрація завершена!");
        System.out.println("==================================================\n");
    }
}
