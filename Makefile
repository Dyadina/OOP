# Makefile для компіляції та запуску Java фітнес-застосунку

# Змінні компіляції
JAVAC = javac
JAVA = java
SRC_DIR = src
BIN_DIR = bin
MAIN_CLASS = Main

# Флаги компілятора
JAVAC_FLAGS = -d $(BIN_DIR) -sourcepath $(SRC_DIR)

# Отримати список всіх .java файлів
SOURCES = $(wildcard $(SRC_DIR)/*.java)
CLASSES = $(patsubst $(SRC_DIR)/%.java, $(BIN_DIR)/%.class, $(SOURCES))

# Основна мета - компіляція та запуск
all: compile run

# Мета для компіляції всіх файлів
compile: $(CLASSES)
	@echo "✅ Компіляція завершена!"

# Правило для компіляції окремих файлів
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@echo "🔨 Компілювання $<..."
	$(JAVAC) $(JAVAC_FLAGS) $<

# Мета для запуску програми
run: compile
	@echo "🚀 Запуск програми..."
	@cd $(BIN_DIR) && $(JAVA) $(MAIN_CLASS)

# Мета для очистки скомпільованих файлів
clean:
	@echo "🧹 Очистка скомпільованих файлів..."
	@rm -rf $(BIN_DIR)/*.class
	@echo "✅ Очистка завершена!"

# Мета для перекомпіляції (очистка та компіляція)
rebuild: clean compile

# Мета для відображення інформації про проєкт
info:
	@echo "📊 Інформація про проєкт:"
	@echo "   Директорія з кодом: $(SRC_DIR)"
	@echo "   Директорія для скомпільованих файлів: $(BIN_DIR)"
	@echo "   Головний клас: $(MAIN_CLASS)"
	@echo "   Кількість файлів: $(words $(SOURCES))"

# Мета для відображення доступних команд
help:
	@echo "📚 Доступні команди:"
	@echo "   make compile  - скомпілювати всі файли"
	@echo "   make run      - скомпілювати та запустити програму"
	@echo "   make clean    - видалити скомпільовані файли"
	@echo "   make rebuild  - перекомпілювати всі файли"
	@echo "   make info     - показати інформацію про проєкт"
	@echo "   make help     - показати цю довідку"

# Мета для запуску тільки без компіляції (якщо файли вже скомпільовані)
run-only:
	@echo "🚀 Запуск програми..."
	@cd $(BIN_DIR) && $(JAVA) $(MAIN_CLASS)

# Оголошення фіктивних цілей
.PHONY: all compile run clean rebuild info help run-only
