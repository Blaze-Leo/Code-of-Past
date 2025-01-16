/*Write a complete class using overloaded constructor to initialize the value of length, breadth, 
height (all integer). The write a method int volume() to calculate the volume and return the same. 
The default value of length, breadth and height will be 15. Write the main method to show the call 
of over loaded constructors and display the volume.*/
import java.util.*;
class Overload
{
    int l,b,h;
    Overload()
    {l=b=h=0;}
    Overload(int ll,int bb,int hh)
    {l=ll;b=bb;h=hh;}
    int volume()
    {
        return (l*b*h);
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Overload o1=new Overload();
        System.out.println("Enter Length");
        int l=sc.nextInt();
        System.out.println("Enter Breadth");
        int b=sc.nextInt();
        System.out.println("Enter Height");
        int h=sc.nextInt();
        Overload o2=new Overload(l,b,h);
        System.out.println("Default Volume = "+o1.volume());
        System.out.println("Calculated Volume = "+o2.volume());
    }
}