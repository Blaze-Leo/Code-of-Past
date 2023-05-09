import java.util.*;
class Word_Math
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter expression :");
        String s=sc.nextLine();
        String exp[]=s.split(" ");
        String one[]={"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
        String eval[]={"Plus","Minus","Times","Divided-By"};
        int val[]=new int[2];
        int n=0;
        for(int i=0;i<10;i++)
          if(exp[0].compareTo(one[i])==0)
            val[0]=i;
        for(int i=0;i<10;i++)
          if(exp[2].compareTo(one[i])==0)
            val[1]=i;
        if(exp[1].compareTo(eval[0])==0)
          n=val[0]+val[1];
        else if(exp[1].compareTo(eval[1])==0)
          n=val[0]-val[1];
        else if(exp[1].compareTo(eval[2])==0)
          n=val[0]*val[1];
        else if(exp[1].compareTo(eval[3])==0)
          n=val[0]/val[1];
        System.out.println("Value = "+n);
    }
}