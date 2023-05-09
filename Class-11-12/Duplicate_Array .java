import java.util.*;
class Duplicate_Array
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter size of array :");
        int a[]=new int[sc.nextInt()];
        System.out.println("Enter elements of array :");
        for(int i=0;i<a.length;i++)
          a[i]=sc.nextInt();
		boolean z=false;
        for(int i=0;i<a.length;i++)
        {
			if(a[i]==0)
			  z=true;
        }
        for(int i=0;i<a.length;i++)
        {
            for(int j=i+1;j<a.length;j++)
            {
                if(a[j]==a[i])
                  a[j]=0;
            }
        }
		int s=0;
		for(int i=0;i<a.length;i++)
        {
			if(a[i]!=0)
			  s++;
        }
		if(z)
		  s++;
        System.out.println(s);
		int b[]=new int[s];
		if(z)
		  b[s-1]=0;
    
		for(int i=0,j=0;i<a.length;i++)
	    {
			if(a[i]!=0)
			{
			    b[j]=a[i];
				j++;
			}
	    }
        System.out.print("Sorted array :\n\n"+b[0]);
        for(int i=1;i<b.length;i++)
          System.out.print(","+b[i]);
    }
}