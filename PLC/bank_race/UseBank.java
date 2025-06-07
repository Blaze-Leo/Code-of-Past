
import java.util.Random;
public class UseBank {
    public static void main(String[] args) {
        int nTellers = 0; 
        int nCustomers = 0;
        Random r = new Random();
                
        // Generate a random number of tellers, between 2 and 5
        // while (nTellers < 2){
        //    nTellers = r.nextInt(6);
        // }
        nTellers = 3;

        // Generate a random number of customers, between 100 and 200
        while (nCustomers < 10){
            nCustomers = r.nextInt(16);
        }
                
        System.out.println("Number of tellers is: " + nTellers + "\n");
        System.out.println("Number of customers is: " + nCustomers + "\n");

        new Bank(nTellers, nCustomers);
    }
}