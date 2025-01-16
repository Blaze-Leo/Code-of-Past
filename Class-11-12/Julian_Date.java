import java.util.*;
class Julian_Date
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Date ::");
        String d=sc.nextLine();
        d=d.replace('.', '/');
        String w[]=d.split("/");
        int j=Integer.parseInt(w[0]);
        int m=Integer.parseInt(w[1]);
        int y=Integer.parseInt(w[2]);
        switch(m)
        {
            case 12:
            j+=30;
            case 11:
            j+=31;
            case 10:
            j+=30;
            case 9:
            j+=31;
            case 8:
            j+=31;
            case 7:
            j+=30;
            case 6:
            j+=31;
            case 5:
            j+=30;
            case 4:
            j+=31;
            case 3:
            j+=28;
            case 2:
            j+=31;
        }
        j+=((y%4==0 && y%100!=0) || y%400==0)?1:0;
        j+=y>=3?0:-1;
        int f=(y+j+(y-1)/4-(y-1)/100+(y-1)/400)%7;
        String a[]={"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
        System.out.println(a[f]);
    }
}