import java.util.*;
class Data_Validity
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your date of birth in dd mm yyyy format :");
        int d=sc.nextInt();
        int m=sc.nextInt();
        int y=sc.nextInt();
        int day=0;
        switch(m)
        {
            case 4:
            case 6:
            case 9:
            case 11:
            day=30;
            break;
            case 2:
            day=28;
            day+=(y%4==0)&&(y%400!=0)?1:0;
            break;
            default:
            day=31;
        }
        boolean valid=true;
        valid&=m>0&&m<13;
        valid&=d>0&&d<=day;
        if(valid)
        System.out.println("Valid Date");
        else
        System.out.println("Invalid Date");
    }
}