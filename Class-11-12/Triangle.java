
 

class Triangle
{
    

static void space(int n)
{
    if(n==0)
    return;

    System.out.print(" ");
    space(n-1);
}

static void printn(int n,int num,int s)
{
    // base case
    if (num == 0)
        return;
    
    System.out.print ("* ");

    // recursively calling printn()
    printn(n,num - 1,s);
}

 


static void pattern(int n,int f, int i)
{
    // base case
    if (n == 0)
        return;
    space(f-i);
    printn(n,i,i);
    System.out.println();

 

    // recursively calling pattern()
    pattern(n - 1,f, i + 1);
}

 


public static void main (String[] args)
{

 

    int n = 5;
    pattern(n,n, 1);
}
}