package thread.joining.example2;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ComplexCalculation complexCalculation = new ComplexCalculation();

        BigInteger result = complexCalculation.calculateResult(
                new BigInteger("13"),
                new BigInteger("45600"),
                new BigInteger("21"),
                new BigInteger("78900"));

        System.out.println("Complex calculation result is " + result);
    }
}
