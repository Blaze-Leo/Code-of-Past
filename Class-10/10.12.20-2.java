/*
A dice rolling game! 2 dice will be rolled and 2 random numbers between 1-6 will be generated. The sum will be taken from the 2 numbers 
and used to decide what is next. If user's sum is 2,3,12 then they lose. If the sum is 7,11 then they win. If sum is 4,5,6,8,9,10 then the program 
automatically rolls the dice again until the user wins or loses. Also, after every sum displayed, underneath, display the amount of games they have won/lost.
*/
import java.util.*;
class Dice
{
    int calc_sum()
    {
        int d1=0,d2=0;
        d1=(int)(Math.random()*5)+1;
        d2=(int)(Math.random()*5)+1;
        return d1+d2;
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        Dice dd=new Dice();
        System.out.println("Enter number of Players :");
        int n=sc.nextInt();
        int s=0;
        System.out.println("\n\nPress Enter for Player 1 :");
        try{System.in.read();}catch(Exception e){}
        for(int i=1;i<=n;i++)
        {
            s=dd.calc_sum();
            System.out.println("Roll Output = "+s);
            if (s==2 || s==3 || s==12)
               System.out.println("Player "+i+" Looses");
            else if (s==7 || s==11)
              System.out.println("Player "+i+" Wins");
            else
              {i--;continue;}
            if(i==n)break;
            System.out.println("\n\nPress Enter for Player "+(i+1)+" :");
            try{System.in.read();}catch(Exception e){}
        }
    }
}