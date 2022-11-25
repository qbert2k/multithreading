package thread.creation.example2;

public class Main {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("Intentional Exception");
        });

        thread.setName("Misbehaving thread");

        thread.setUncaughtExceptionHandler((t, e) ->
                System.out.println("A critical error happened in thread " + t.getName()
                        + " the error is " + e.getMessage()));

        thread.start();
    }
}
