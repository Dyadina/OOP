// Абстрактний базовий клас для користувачів системи
public abstract class User {
    // Унікальний ідентифікатор користувача
    protected String id;
    
    // Ім'я користувача
    protected String name;
    
    // Конструктор для ініціалізації користувача
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Метод для отримання імені користувача
    public String getName() {
        System.out.println("Користувач: " + name);
        return name;
    }
    
    // Метод для отримання ID користувача
    public String getId() {
        return id;
    }
}
