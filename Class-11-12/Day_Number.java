import java.util.*;
class Day_Number
{
    static String date(int n,int y)
    {
        String s="";
        boolean leap=(y%4==0)&&(y%400!=0);
        String month[]={"","January","February","March","April","May","June","July","August","September","October","November","December"};
        if((n>365 && !leap) || (n>366 && leap))
        {y++;n-=leap?366:365;}
        leap=(y%4==0)&&(y%400!=0);
        int c=31;String m="";int k=0;
        k=n;
        if(n<c && k>0)
        {s+=k;m=month[1];}
        k=n-c;c+=28;c+=leap?1:0;
        if(n<c && k>0)
        {s+=k;m=month[2];}
        k=n-c;c+=31;
        if(n<c && k>0)
        {s+=k;m=month[3];}
        k=n-c;c+=30;
        if(n<c && k>0)
        {s+=k;m=month[4];}
        k=n-c;c+=31;
        if(n<c && k>0)
        {s+=k;m=month[5];}
        k=n-c;c+=30;
        if(n<c && k>0)
        {s+=k;m=month[6];}
        k=n-c;c+=31;
        if(n<c && k>0)
        {s+=k;m=month[7];}
        k=n-c;c+=31;
        if(n<c && k>0)
        {s+=k;m=month[8];}
        k=n-c;c+=30;
        if(n<c && k>0)
        {s+=k;m=month[9];}
        k=n-c;c+=31;
        if(n<c && k>0)
        {s+=k;m=month[10];}
        k=n-c;c+=30;
        if(n<c && k>0)
        {s+=k;m=month[11];}
        k=n-c;c+=31;
        if(n<c && k>0)
        {s+=k;m=month[12];}
        if(s.charAt(s.length()-1)=='1')
        s+="st";
        else if(s.charAt(s.length()-1)=='2')
        s+="nd";
        else if(s.charAt(s.length()-1)=='3')
        s+="rd";
        else
        s+="th";
        s+=" "+m+" "+y;
        return s;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Day Number : ");
        int n=sc.nextInt();
        System.out.print(" Year : ");
        int y=sc.nextInt();
        System.out.print(" Date After (N) : ");
        int d=sc.nextInt();
        System.out.println("\nDate : "+date(n,y));
        System.out.println("Date after "+d+" days : "+date(n+d,y));
    }
}