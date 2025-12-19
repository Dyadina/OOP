package com.fitness;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {

        // ✅ Guice injector
        Injector injector = Guice.createInjector(new FitnessModule());

        // ✅ отримуємо об'єкти через DI
        FitnessApp app = injector.getInstance(FitnessApp.class);
        WorkoutSessionService workoutSessionService = injector.getInstance(WorkoutSessionService.class);

        System.out.println("✅ Guice працює. FitnessApp та WorkoutSessionService створені через injector.getInstance()");

        // Далі твоя логіка демо/тесту (за наявності класів Client/Trainer/Plan і т.д.)
    }
}
