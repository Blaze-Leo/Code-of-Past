import java.util.*;
class InPrePost
{
    String stack="",out="";
    String fix(String s,boolean f)
    {
        int l=s.length();
        for(int i=0;i<l;i++)
        {
            char c=s.charAt(i);
            switch(c)
            {
                case '+':
                case '-':
                case '*':
                case '/':
                case '%':
                case '^':
                case '(':
                case ')':
                stack+=c;
                if(f)
                  repre();
                else
                  repost();
                break;
                default:
                out+=c;
            }
        }
        return out;
    }
    void repre()
    {
        int l=stack.length();
        if(stack.charAt(l-1)==')')
          return;
        if(stack.charAt(l-1)=='(')
        {
            if(stack.charAt(l-2)==')')
              {stack=stack.substring(0,l-2);return;}
            out+=stack.charAt(l-2);
            stack=stack.substring(0,l-2)+'(';
            repre();
            return;
        }
        char c=stack.charAt(l-1);
        char p=stack.charAt(l-2);
        switch(c)
        {
            case '-':
            case '+':
            if(p=='^' || p=='/' || p=='*' || p=='%')
            {
                out+=p;
                stack=stack.substring(0,l-2)+c;
                repre();
            }
            break;
            case '/':
            case '*':
            case '%':
            if(p=='^')
            {
                out+=p;
                stack=stack.substring(0,l-2)+c;
                repre();
            }
            break;
        }
    }
    void repost()
    {
        int l=stack.length();
        if(stack.charAt(l-1)=='(')
          return;
        if(stack.charAt(l-1)==')')
        {
            if(stack.charAt(l-2)=='(')
              {stack=stack.substring(0,l-2);return;}
            out+=stack.charAt(l-2);
            stack=stack.substring(0,l-2)+')';
            repost();
            return;
        }
        char c=stack.charAt(l-1);
        char p=stack.charAt(l-2);
        switch(c)
        {
            case '-':
            case '+':
            if(p=='^' || p=='%' || p=='/' || p=='*' || p=='+' || p== '-')
            {
                out+=p;
                stack=stack.substring(0,l-2)+c;
                repost();
            }
            return;
            case '/':
            case '*':
            case '%':
            if(p=='^' || p=='*' || p=='/' || p=='%')
            {
                out+=p;
                stack=stack.substring(0,l-2)+c;
                repost();
            }
            return;
        }
    }
    String reverse(String s)
    {
        String n="";
        for(int i=s.length()-1;i>=0;i--)
          n+=s.charAt(i);
        return n;
    }
    void display(String n)
    {
        fix(n,false);
        System.out.println("\nPosfix Expression :  "+out);
        out="";stack="";
        n=reverse(n);
        fix(n,true);
        System.out.println("\nPrefix Expression :  "+reverse(out));
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        InPrePost ipp=new InPrePost();
        System.out.println("Enter expression :");
        String n=sc.nextLine();
        n=n.replace(' ','\u0000');
        n="("+n+")";
        ipp.display(n);
    }
}
