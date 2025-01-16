import java.util.*;
class Change
{
    String str,newstr;
    int len;
    Scanner sc=new Scanner(System.in);
    Change()
    {
        str=newstr="";
        len=0;
    }
    void inputword()
    {
        System.out.println("Enter any word :");
        str=sc.nextLine();
        len=str.length();
    }
    char caseconvert(char ch)
    {
        if(Character.isUpperCase(ch))
          ch=Character.toLowerCase(ch);
        else if(Character.isLowerCase(ch))
          ch=Character.toUpperCase(ch);
        return ch;
    }
    String recchange(int n)
    {
        String ne="";
        if(n==len-1)
          return Character.toString(caseconvert(str.charAt(n)));
        ne+=Character.toString(caseconvert(str.charAt(n)));
        return ne+recchange(n+1);
    }
    void display()
    {
        newstr=recchange(0);
        System.out.println("\nOriginal word : "+str);
        System.out.println("Changed word : "+newstr);
    }
    public static void main(String args[])
    {
        Change c=new Change();
        c.inputword();
        c.display();
    }
}
         