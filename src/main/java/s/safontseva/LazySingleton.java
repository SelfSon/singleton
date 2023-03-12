package s.safontseva;

public final class LazySingleton {
    private static final Object LOCK = new Object();
    // благодаря volatile запись в instance happens-before какой-то поток прочитает instance
    // то есть, если какой-то поток и получит instance, то инициализированный, а не просто not-null ссылку,
    // указывающую на выделенное место в памяти
    private static volatile LazySingleton instance;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        // Оптимизация
        // в случае если instance уже инициализированная, мы экономим ресурсы, так как возвращаем объект helper,
        // вместо того, чтобы еще раз читать значение volatile instance из главной памяти
        LazySingleton helper = instance;
        if (helper == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
            helper = instance;
        }
        return helper;
    }
}
