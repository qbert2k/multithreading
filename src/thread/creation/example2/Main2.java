package thread.creation.example2;

public class Main2 {

    public static void main(String[] args) {
        Thread thread = new NewThread();

        thread.setName("My new thread");

        thread.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from: " + this.getName());
        }
    }
}
