package thread.joining.example1;

import java.math.BigInteger;

public class FactorialThread extends Thread {

    private final long inputNumber;
    private BigInteger result = BigInteger.ZERO;
    private boolean isFinished = false;

    public FactorialThread(long inputNumber) {
        this.inputNumber = inputNumber;
    }

    @Override
    public void run() {
        result = factorial(inputNumber);
        isFinished = true;
    }

    public BigInteger factorial(long n) {
        BigInteger temResult = BigInteger.ONE;

        for (long i = n; i > 0; i--) {
            temResult = temResult.multiply(new BigInteger(Long.toString(i)));
        }

        return temResult;
    }

    public BigInteger getResult() {
        return result;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
