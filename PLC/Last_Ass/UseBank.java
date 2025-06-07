public class UseBank {
    Bank bank; 

    public UseBank() {
		int nTellers = 2; 
        int nCustomers = 10;
        Customer c; 
		bank = new Bank(nTellers);

        for (int i = 0; i < nCustomers; i++) {
            c = new Customer(i);
            new Thread(c).start();
        }
        bank.setLimit(nCustomers);
    }

    class Customer implements Runnable {
        int id;
        public Customer(int id) {
            this.id = id;
        }
        
        public void run() {
            bank.enter(id);
        }
    }
	public static void main(String[] args) {
        new UseBank();
    }

}
