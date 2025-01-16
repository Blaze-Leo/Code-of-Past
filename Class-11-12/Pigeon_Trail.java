import java.util.*;
class Pigeon_Trail
{
	static int start(int[] x,int[] y)
	{
		int s=0;
		for(int i=0;i<y.length;i++)
		{
			if(y[i]>s)
			  s=y[i];
		}
		int c=0,a=0;
		for(int i=0;i<x.length;i++)
		{
			if(y[i]==s)
			{
				a+=x[i];
				c++;
			}
		}
		a=a/c;
		int d[]=new int[x.length];
		for(int i=0;i<x.length;i++)
		  d[i]=Math.abs(x[i]-a);
		s=d[0];
		c=0;
		for(int i=0;i<x.length;i++)
		{
			if(d[i]<s)
			{
				s=d[i];
				c=i;
			}
		}
		return c;
	}
	
	static void pigeon(int x[],int y[],int r[])
    {
		int c=0;
		int d=x[start(x,y)];
		for(int i=0;i<x.length;i++)
			c+=x[i]<=d?1:0;
		int xl[]=new int[c];
		int xr[]=new int[x.length-c];
		int yl[]=new int[c];
		int yr[]=new int[x.length-c];
		int rl[]=new int[c];
		int rr[]=new int[x.length-c];
		int le=0,ri=0;
		for(int i=0;i<x.length;i++)
		{
			if(x[i]<=d)
			{
				xl[le]=x[i];
				yl[le]=y[i];
				rl[le]=r[i];
				le++;
			}
			else
			{
				xr[ri]=x[i];
				yr[ri]=y[i];
				rr[ri]=r[i];
				ri++;
			}
		}
        for (int i = 0; i < xl.length - 1; i++)  
        {  
            int index = i;  
            for (int j = i + 1; j < xl.length; j++){  
                if (yl[j] > yl[index]){ 
					index = j; 
                }  
            }  
			xl=swap(index,i,xl);
			yl=swap(index,i,yl);
			rl=swap(index,i,rl); 
        }  
		for (int i = 0; i < xl.length - 1; i++)  
        {  
            int index = i;  
            for (int j = i + 1; j < xl.length; j++){  
                if (xl[j] < xl[index] && yl[i]==yl[j]){  
					index = j;
                }  
            }  
			xl=swap(index,i,xl);
			yl=swap(index,i,yl);
			rl=swap(index,i,rl); 
        }

        for (int i = 0; i < xr.length - 1; i++)  
        {  
            int index = i;  
            for (int j = i + 1; j < xr.length; j++){  
                if (yr[j] < yr[index]){  
					index = j;
                }  
            }  
			xr=swap(index,i,xr);
			yr=swap(index,i,yr);
			rr=swap(index,i,rr); 
        }  
		for (int i = 0; i < xr.length - 1; i++)  
        {  
            int index = i;  
            for (int j = i + 1; j < xr.length; j++){  
                if (xr[j] < xr[index] && yr[i]==yr[j]){  
					index = j;
                }  
            }  
			xr=swap(index,i,xr);
			yr=swap(index,i,yr);
			rr=swap(index,i,rr); 
        }

		System.out.println("\nOutput\n");

		for(int i=0;i<rl.length;i++)
			System.out.println(rl[i]);
		for(int i=0;i<rr.length;i++)
		    System.out.println(rr[i]);
		System.out.println(rl[0]);
    }
    static int[] swap(int i,int j,int a[])
    {
        int smallerNumber = a[i];   
            a[i]=a[j];  
            a[j] = smallerNumber; 
		return a;
    }
	public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of waypoints :");
		int n=sc.nextInt();
		int x[]=new int[n];
		int y[]=new int[n];
		int r[]=new int[n];
		System.out.println("Enter co-ordinates :");
		for(int i=0;i<n;i++)
		{
			x[i]=sc.nextInt();
			y[i]=sc.nextInt();
			r[i]=i+1;
		}
		pigeon(x,y,r);
		sc.close();
    }
}