import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Cust {
    int id;
    int token;

    public Cust(int id, int token) {
        this.id = id;
        this.token = token;
    }
}

public class Bank {
    PriorityBlockingQueue<Cust> tellerQueue, managerQueue;
    AtomicInteger tellerToken, managerToken;
    int nCustomers = 0;
    volatile int customersProcessedByTellers = 0;
    volatile int customersProcessedByManager = 0;

    public Bank(int nTellers) {
        tellerQueue = new PriorityBlockingQueue<>(100, Comparator.comparingInt(c -> c.token));
        managerQueue = new PriorityBlockingQueue<>(100, Comparator.comparingInt(c -> c.token));
        tellerToken = new AtomicInteger(0);
        managerToken = new AtomicInteger(1000);

        for (int i = 0; i < nTellers; i++) {
            new Thread(new Teller(i)).start();
        }
        new Thread(new Manager()).start();
    }

    public void setLimit(int n) {
        this.nCustomers = n;
    }

    public synchronized void enter(int id) {
        int token = tellerToken.getAndIncrement();
        Cust cust = new Cust(id, token);
        System.out.println("Customer " + id + " added to teller queue with token number " + token);
        tellerQueue.put(cust);
    }

    class Teller implements Runnable {
        int id;

        public Teller(int id) {
            this.id = id;
        }

        public void run() {
            while (true) {
                try {
                    Cust cust = tellerQueue.poll(100, TimeUnit.MILLISECONDS);
                    if (cust == null) {
                        if (customersProcessedByTellers >= nCustomers) break;
                        continue;
                    }

                    System.out.println("Customer " + cust.id + " with token number " + cust.token + " serviced by teller " + id);

                    int mToken = managerToken.getAndIncrement();
                    Cust mCust = new Cust(cust.id, mToken);
                    System.out.println("Customer " + cust.id + " added to manager queue with token number " + mToken + " by teller " + id);
                    managerQueue.put(mCust);

                    synchronized (Bank.this) {
                        customersProcessedByTellers++;
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    class Manager implements Runnable {
        public void run() {
            while (true) {
                try {
                    Cust cust = managerQueue.poll(100, TimeUnit.MILLISECONDS);
                    if (cust == null) {
                        if (customersProcessedByManager >= nCustomers) break;
                        continue;
                    }

                    System.out.println("Customer " + cust.id + " with token number " + cust.token + " serviced by manager");

                    synchronized (Bank.this) {
                        customersProcessedByManager++;
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
