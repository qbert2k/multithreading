package thread.race.condition.atomic.operations;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        AvgMetrics avgMetrics = new AvgMetrics();
        MinMaxMetrics minMaxMetrics = new MinMaxMetrics();

        BusinessLogic businessLogicThread1 = new BusinessLogic(avgMetrics, minMaxMetrics);
        BusinessLogic businessLogicThread2 = new BusinessLogic(avgMetrics, minMaxMetrics);
        MetricsPrinter metricsPrinter = new MetricsPrinter(avgMetrics, minMaxMetrics);

        businessLogicThread1.start();
        businessLogicThread2.start();
        metricsPrinter.start();
    }

    public static class MetricsPrinter extends Thread {

        private final AvgMetrics avgMetrics;
        private final MinMaxMetrics minMaxMetrics;

        public MetricsPrinter(AvgMetrics avgMetrics, MinMaxMetrics minMaxMetrics) {
            this.avgMetrics = avgMetrics;
            this.minMaxMetrics = minMaxMetrics;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }

                double currentAverage = avgMetrics.getAverage();
                long min = minMaxMetrics.getMin();
                long max = minMaxMetrics.getMax();

                System.out.println("Current Average is " + currentAverage + " min is " + min + " max is " + max);
            }
        }
    }

    public static class BusinessLogic extends Thread {

        private final AvgMetrics avgMetrics;
        private final MinMaxMetrics minMaxMetrics;
        private final Random random = new Random();

        public BusinessLogic(AvgMetrics avgMetrics, MinMaxMetrics minMaxMetrics) {
            this.avgMetrics = avgMetrics;
            this.minMaxMetrics = minMaxMetrics;
        }

        @Override
        public void run() {
            while (true) {
                long start = System.currentTimeMillis();

                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException ignored) {
                }

                long end = System.currentTimeMillis();

                long diff = end - start;
                avgMetrics.addSample(diff);
                minMaxMetrics.addSample(diff);
            }
        }
    }

    public static class AvgMetrics {

        private long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }

        public double getAverage() {
            return average;
        }
    }

    public static class MinMaxMetrics {
        private volatile long min;
        private volatile long max;

        public MinMaxMetrics() {
            min = Long.MAX_VALUE;
            max = Long.MIN_VALUE;
        }

        public void addSample(long newSample) {
            synchronized (this) {
                this.min = Math.min(newSample, this.min);
                this.max = Math.max(newSample, this.max);
            }
        }

        public long getMin() {
            return min;
        }

        public long getMax() {
            return max;
        }
    }
}
