import java.util.*;
class String_Sort
{
    String[] sort(String a[])
    {
        int l = a.length;  
        String temp = "";  
        for(int i=0; i < l; i++)
        {  
            for(int j=1; j < (l-i); j++)
            {  
                if(a[j-1].compareTo(a[j])>0)
                {  
                    temp = a[j-1];  
                    a[j-1] = a[j];  
                    a[j] = temp;  
                }  
            }  
        }
        return a;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any sentence  :");
        String line=sc.nextLine();
        line=line.substring(0,line.length()-1);
        line = line.toUpperCase();
        String[] s=line.split(" ",0);
        String_Sort ss=new String_Sort();
        s=ss.sort(s);
        int len =0,p=0;
        System.out.println("\nSorted String :\n");
        for(int i=0;i<s.length;i++)
        {
            if(s[i].length()>len)
            {
                len=s[i].length();
                p=i;
            }
            if(!(s[i].equals("")))
              System.out.print(s[i]+" ");
        }
        System.out.print("\n\nLargest Word(s) : "+s[p]);
        for(int i=p+1;i<s.length;i++)
        {
            if(s[i].length()==len)
              System.out.print(" , "+s[i]);
        }
    }
}
