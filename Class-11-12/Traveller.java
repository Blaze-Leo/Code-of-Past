import java.util.*;
class Traveller
{
    static boolean search(int x,int y,int[][] t)
    {
        for(int i=0;i<t[0].length;i++)
        {
            if(t[0][i]==x && t[1][i]==y)
                return true;
        }
        return false;
    }
    static int[] move(String t,int m,int dis)
    {
        int end=0;
        int[][] d=new int[2][dis];
        for(int i=0;i<dis;i++)
            {d[0][i]=0;d[1][i]=0;}
        int x=0,y=0,s=0;
        int lx=x,ly=y;
        int k=0;
        while(k<=m)
        {
        for(int i=0;i<t.length();i++)
        {
            s+=4;
            char c=t.charAt(i);
            if(c=='L')
                s--;
            else if(c=='R')
                s++;
            s%=4;
            if(c=='F' && k<=m)
            {
            if(s==0)
                y+=1;
            else if(s==1)
                x+=1;
            else if(s==2)
                y-=1;
            else
                x-=1;
            
            if(end>=5)
            {
                int a[]={x,y};
                return a;
            }
            if(search(x,y,d))
            {
                x=lx;
                y=ly;
                s+=1;
                s%=4;
                i-=1;
                end+=1;
            }
            else
            {
                shift(x,y,d);
                lx=x;
                ly=y;
                k+=1;
                end=0;
            }
            }
        }
        }
        int a[]={x,y};
        return a;
    }
    static int[][] shift(int x,int y,int[][] t)
    {
        for(int i=t[0].length-1;i>1;i--)
        {
            t[0][i]=t[0][i-1];
            t[1][i]=t[1][i-1];
        }
        t[0][0]=x;
        t[1][0]=y;
        return t;
    }
    
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter trail disappearance :");
        int d=sc.nextInt();
        System.out.println("Enter trail :");
        String t= sc.next();
        t=t.replaceAll("L","LF");
        t=t.replaceAll("R","RF");
        System.out.println("Enter number of moves :");
        int m=sc.nextInt();
        int ord[]=new int[2];
        ord=move(t,m-1,d-1);
        System.out.println("( "+ord[0]+" , "+ord[1]+" )");
    }
}