import java.util.concurrent.*;

class Bank {
    private int numTellers;
    private int numCustomers;
    private int servedCustomers = 1;

    public Bank(int numTellers, int numCustomers) {
        this.numTellers = numTellers;
        this.numCustomers = numCustomers+2;
        serveCustomers();
    }

    public void serveCustomers() {
        ExecutorService executor = Executors.newFixedThreadPool(numTellers);
        for (int i = 0; i < numCustomers; i++) {
            executor.execute(new Teller(i + 1));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }

    private class Teller implements Runnable {
        private int tellerId;

        public Teller(int tellerId) {
            this.tellerId = tellerId;
        }

        public void run() {
            long threadId = Thread.currentThread().getId() % numTellers + 1;
            int customerNumber = assignCustomer();
            System.out.println("Customer " + customerNumber + " served by teller " + threadId);
            servedCustomers++;
        }
    }

    private int assignCustomer() {
        return servedCustomers;
    }
}
