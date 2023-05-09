import java.util.*;
class Digit_to_Words
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any digit ::");
        String m=sc.nextLine();
        char c;
        for(int i=0;i<m.length();i++)
        {
          c=m.charAt(i);
        String one[]={"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
        if((int)(c) <= 57 && (int)(c) >= 48)
          System.out.print(one[c-48]+" ");
        else
          {System.out.println("Not Valid Input");System.exit(0);}
        }
        int n=Integer.valueOf(m);
        System.out.println("\nDenominations ::\n");
        String g=n>=1000?("1000 x "+n/1000+" = "+n/1000+"000 \n"):"";n%=1000;
        g+=n>=500?"500 x "+n/500+" = "+(n/500)*5+"00 \n":"";n%=500;
        g+=n>=100?"100 x "+n/100+" = "+n/100+"00 \n":"";n%=100;
        g+=n>=50?"50 x "+n/50+" = "+(n/50)*5+"0 \n":"";n%=50;
        g+=n>=20?"20 x "+n/20+" = "+(n/20)*2+"0 \n":"";n%=20;
        g+=n>=10?"10 x "+n/10+" = "+n/10+"0 \n":"";n%=10;
        g+=n>=5?"5 x "+n/5+" = "+(n/5)*5+" \n":"";n%=5;
        g+=n>=2?"2 x "+n/2+" = "+(n/2)*2+" \n":"";n%=2;
        g+=n==1?"1 x 1 = 1 \n":"";
        System.out.println(g);
    }
}