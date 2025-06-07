
import java.util.concurrent.*;

class Bank {
    private int nTellers;
    private int nCustomers;
    private int currentCustomer=0;

    public Bank(int nTellers, int nCustomers) {
        this.nTellers = nTellers;
        this.nCustomers = nCustomers;
        runBank();
    }

    private int assignCustomer() {
        return ++currentCustomer;
    }

    public void runBank() {
        ExecutorService executor = Executors.newFixedThreadPool(nTellers);
        while (currentCustomer<nCustomers) {
            assignCustomer();
            executor.execute(new Customer(currentCustomer));
        }
        executor.shutdown();
    }

    private class Customer implements Runnable {
        private int customerNumber;

        public Customer(int customerNumber) {
            this.customerNumber = customerNumber;
        }

        @Override
        public void run() {
            int tellerNumber = (int) (Thread.currentThread().getId() % nTellers) + 1;
            System.out.println("Customer " + customerNumber + " served by teller " + tellerNumber);
        }
    }
}

