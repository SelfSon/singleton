package s.safontseva;

public final class StaticLazySingleton {
    private StaticLazySingleton() {

    }

    //Ленивая инициализация
    // Объект StaticLazySingleton будет создан только при первом вызове getInstance()
    public static StaticLazySingleton getInstance() {
        return StaticLazySingletonHelper.INSTANCE;
    }

    private static class StaticLazySingletonHelper {
        private static final StaticLazySingleton INSTANCE = new StaticLazySingleton();
    }
}
