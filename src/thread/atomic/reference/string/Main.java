package thread.atomic.reference.string;

import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) {
        String oldName = "old name";
        String newName = "new name";

        AtomicReference<String> atomicReference = new AtomicReference<>(oldName);

        if (atomicReference.compareAndSet(oldName, newName)) {
            System.out.println("New Value is " + atomicReference.get());
        } else {
            System.out.println("Nothig Changed");
        }
    }
}
