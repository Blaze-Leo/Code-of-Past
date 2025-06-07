import java.util.*;
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
    int totalCustomers = 0;
    AtomicInteger tellerServed = new AtomicInteger(0);
    AtomicInteger managerServed = new AtomicInteger(0);
    int nTellers;

    public Bank(int nTellers) {
        this.nTellers = nTellers;

        tellerQueue = new PriorityBlockingQueue<>(100, Comparator.comparingInt(c -> c.token));
        managerQueue = new PriorityBlockingQueue<>(100, Comparator.comparingInt(c -> c.token));
        tellerToken = new AtomicInteger(0);
        managerToken = new AtomicInteger(1000);

        // Start manager immediately
        Manager manager = new Manager();
        new Thread(manager).start();
    }

    public void setLimit(int n) {
        totalCustomers = n;

        // Now start tellers only after all customers have entered
        for (int i = 0; i < nTellers; i++) {
            Teller teller = new Teller(i);
            new Thread(teller).start();
        }
    }

    public synchronized void enter(int id) {
        int token = tellerToken.getAndIncrement();
        Cust cust = new Cust(id, token);
        tellerQueue.put(cust);
        System.out.println("Customer " + id + " added to teller queue with token number " + token);
    }

    class Teller implements Runnable {
        int id;

        public Teller(int id) {
            this.id = id;
        }

        public void run() {
            while (true) {
                try {
                    Cust cust = tellerQueue.poll(1, TimeUnit.SECONDS);
                    if (cust == null) {
                        if (tellerServed.get() >= totalCustomers) {
                            break;
                        }
                        continue;
                    }

                    System.out.println("Customer " + cust.id + " with token number " + cust.token + " serviced by teller " + id);
                    tellerServed.incrementAndGet();

                    int mToken;
                    synchronized (Bank.this) {
                        mToken = managerToken.getAndIncrement();
                    }

                    Cust mCust = new Cust(cust.id, mToken);
                    managerQueue.put(mCust);
                    System.out.println("Customer " + cust.id + " added to manager queue with token number " + mToken + " by teller " + id);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    class Manager implements Runnable {
        public void run() {
            while (true) {
                try {
                    Cust cust = managerQueue.poll(1, TimeUnit.SECONDS);
                    if (cust == null) {
                        if (managerServed.get() >= totalCustomers) {
                            break;
                        }
                        continue;
                    }

                    System.out.println("Customer " + cust.id + " with token number " + cust.token + " serviced by manager");
                    managerServed.incrementAndGet();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}
