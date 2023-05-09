import java.util.*;
class Time
{
    public static void main(String args[])
    {
    String one[]={"","one","two","three","four","five","six","seven","eight","nine"};
    String teen[]={"","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
    String tens[]={"","ten","twenty"};
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter a time in between 1 to 12 :");
    int h=sc.nextInt();
    System.out.println("Enter 0 to 59 as the minute :");
    int m=sc.nextInt();
    String tim=m>30?" to":" past";
    h=m>30?h+1:h;
    if(h==13)
      h=1;
    tim=tim+" "+(h<10?one[h]:h==10?"ten":teen[h-10]);
    m=m>30?60-m:m;
    if(m==15)
      tim="quarter "+tim;
    else if(m==30)
      tim="half "+tim;
    else
      tim=(m>10&&m<20?teen[m-10]:(tens[m/10]+" "+one[m%10])) +tim;
    if(m==0)
      tim=(h<10?one[h]:h==10?"ten":teen[h-10])+" 'o' clock";
    System.out.println("Time : "+tim);
    sc.close();
    }
}
