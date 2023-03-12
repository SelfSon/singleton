package s.safontseva;

public final class JobInterviewSingleton {
    private static final Object LOCK = new Object();
    //так как instance не volatile есть возможность получить лишь частично инициализированный объект
    private static JobInterviewSingleton instance;

    private JobInterviewSingleton() {

    }

    public static JobInterviewSingleton getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new JobInterviewSingleton();
                }
            }
        }
        return instance;
    }
}
