// Інтерфейс для забезпечення даних про здоров'я з пристроїв
public interface DataProvider {
    // Отримання даних про здоров'я від пристрою
    HealthData fetchHealthData();
}
