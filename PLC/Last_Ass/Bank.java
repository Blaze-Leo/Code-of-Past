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
    private int totalCustomers;
    private AtomicInteger customersServedByTellers = new AtomicInteger(0);
    private AtomicInteger customersServedByManager = new AtomicInteger(0);

    public Bank(int nTellers) { 
        tellerQueue = new PriorityBlockingQueue<>(100, Comparator.comparingInt(c -> c.token));
        managerQueue = new PriorityBlockingQueue<>(100, Comparator.comparingInt(c -> c.token));
        tellerToken = new AtomicInteger(0);
        managerToken = new AtomicInteger(1000);

        for (int i = 0; i < nTellers; i++) {
            Teller teller = new Teller(i);
            new Thread(teller).start();
        }
        Manager manager = new Manager();
        new Thread(manager).start();
    }

    public void setLimit(int n) {
        totalCustomers = n;
    }

    public synchronized void enter(int id) {
        int token = tellerToken.getAndIncrement();
        Cust c = new Cust(id, token);
        tellerQueue.put(c);
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
                    Cust c = tellerQueue.poll(100, TimeUnit.MILLISECONDS);
                    if (c != null) {
                        synchronized (this) {
                            System.out.println("Customer " + c.id + " with token number " + c.token + " serviced by teller " + id);
                            int managerTokenInt = managerToken.getAndIncrement();
                            Cust newCust = new Cust(c.id, managerTokenInt);
                            managerQueue.put(newCust);
                            System.out.println("Customer " + c.id + " added to manager queue with token number " + managerTokenInt + " by teller " + id);
                            customersServedByTellers.incrementAndGet();
                        }
                    } else if (customersServedByTellers.get() >= totalCustomers && totalCustomers > 0) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Manager implements Runnable {
        public void run() {
            while (true) {
                try {
                    Cust c = managerQueue.poll(100, TimeUnit.MILLISECONDS);
                    if (c != null) {
                        synchronized (this) {
                            System.out.println("Customer " + c.id + " with token number " + c.token + " serviced by manager");
                            customersServedByManager.incrementAndGet();
                        }
                    } else if (customersServedByManager.get() >= totalCustomers && totalCustomers > 0) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}












