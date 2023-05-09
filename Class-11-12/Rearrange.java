import java.util.*;
class Rearrange
{
    
    Scanner sc=new Scanner(System.in);
    String txt,cxt;
    int len;
    Rearrange()
    {
        txt="";
        cxt="";
        len=0;
    }
    void readword()
    {
        System.out.println("Enter any word :");
        txt=sc.next();
        txt=txt.toUpperCase();
        len=txt.length();
    }
    void convert()
    {
        int p=-1;
        char a='\u0000';
        for(int i=0;i<len;i++)
        {
            a=txt.charAt(i);
            if(a=='A' || a=='E' || a=='I' || a=='O' || a=='U')
            {
                p=i;
                break;
            }
        }
        if(p==0)
          cxt=txt+"Y";
        else if(p==-1)
          cxt=txt+"N";
        else
        {
            cxt=txt.substring(p)+txt.substring(0,p)+"C";
        }
    }
    void display()
    {
        System.out.println("Rearranged word : "+cxt);
    }
    public static void main(String args[])
    {
        Rearrange r =new Rearrange();
        r.readword();
        r.convert();
        r.display();
    }
}
