// Клас для представлення носимого фітнес-пристрою, який імплементує DataProvider
public class WearableDevice implements DataProvider {
    // Унікальний ID пристрою
    private String deviceId;
    
    // Модель пристрою
    private String model;
    
    // Останні дані про здоров'я з пристрою
    private HealthData lastHealthData;
    
    // Конструктор ініціалізації носимого пристрою
    public WearableDevice(String deviceId, String model) {
        this.deviceId = deviceId;
        this.model = model;
        this.lastHealthData = null;
    }
    
    // Отримання ID пристрою
    public String getDeviceId() {
        return deviceId;
    }
    
    // Отримання моделі пристрою
    public String getModel() {
        return model;
    }
    
    // Реалізація методу інтерфейсу DataProvider для отримання даних здоров'я
    @Override
    public HealthData fetchHealthData() {
        // Симуляція отримання даних з пристрою
        System.out.println("📱 Пристрій " + deviceId + " (" + model + ") передає дані...");
        
        // Генерація даних
        int pulse = 60 + (int)(Math.random() * 40);
        float sleep = 6 + (float)(Math.random() * 3);
        String date = java.time.LocalDate.now().toString();
        
        lastHealthData = new HealthData(pulse, sleep, date);
        return lastHealthData;
    }
    
    // Отримання останніх отриманих даних
    public HealthData getLastHealthData() {
        return lastHealthData;
    }
    
    // Синхронізація даних з приложенням
    public void syncData() {
        if (lastHealthData != null) {
            System.out.println("🔄 Синхронізація даних з пристрою " + deviceId);
            lastHealthData.displayHealthStatus();
        } else {
            System.out.println("⚠️  Немає даних для синхронізації");
        }
    }
}
