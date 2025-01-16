import java.math.BigInteger;
import java.util.*;
class Add
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter first number :");
        String s1=sc.next();s1="0"+s1;
        System.out.println("Enter second number :");
        String s2=sc.next();s2="0"+s2;
        if(s1.indexOf(".")==-1)
          s1+=".0";
        if(s2.indexOf(".")==-1)
          s2+=".0";
        String L1=s1.substring(0,s1.indexOf("."));
        String L2=s2.substring(0,s2.indexOf("."));
        String R1=s1.substring(s1.indexOf(".")+1);
        String R2=s2.substring(s2.indexOf(".")+1);
        int max=Math.max(R1.length(),R2.length());
        while(R1.length()<max)
          R1=R1+"0";
        while(R2.length()<max)
          R2=R2+"0";
        BigInteger l1=new BigInteger("0");
        BigInteger l2=new BigInteger("0");
        BigInteger r1=new BigInteger("0");
        BigInteger r2=new BigInteger("0");   
        
        try
        {
            l1=new BigInteger(L1);
            r1=new BigInteger(R1);
            l2=new BigInteger(L2);
            r2=new BigInteger(R2);
        }
        catch(Exception e)
        {
            System.out.println("Wrong Input");
            System.exit(0);
        }
        BigInteger suml,sumr;
        suml=l1.add(l2);
        sumr=r1.add(r2);
        String sr=sumr.toString();
        while(sr.length()<max)
          sr="0"+sr;
        String out=sumr.intValue()==0?"\n"+suml:"\n"+suml+"."+sr;
        System.out.println(out);
    }
}