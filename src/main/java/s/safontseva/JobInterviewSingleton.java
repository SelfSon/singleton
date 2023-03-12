package s.safontseva;

public final class JobInterviewSingleton {
    private static final Object LOCK = new Object();
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
