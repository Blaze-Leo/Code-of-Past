import java.util.*;
class QQ_Style
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter encoded text :");
        String n=sc.nextLine();
        System.out.println("Enter shift value :");
        int s=sc.nextInt()-1;
        n=n.replace(" ","");
        n=n.replace("QQ"," ");
        for(int i=0;i<n.length();i++)
        {
            if(n.charAt(i)!=' ')
            {
                int c=(int)(n.charAt(i));
                c+=s;
                c=c>90?c%91+65:c;
                n=n.substring(0,i)+(char)(c)+n.substring(i+1);
            }
        }
        System.out.println("Decode text : "+n);
    }
}