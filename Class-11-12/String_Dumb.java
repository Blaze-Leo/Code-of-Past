import java.util.*;
class String_Dumb
{
    static void longest(String s)
    {
        s=s.replace(".","@");
        String n[]=s.split("@",0);
        int l=0;String p="";
        for(int i=0;i<n.length;i++)
        {
            String w[]=n[i].split(" ");
            for(int j=0;j<w.length;j++)
            {
                if(w[j].length()>=l)
                {
                    if(w[j].length()==l)
                        p+=" "+(i+1);
                    else
                      {l=w[j].length();
                      p=""+(i+1);}
                    if(p.length()>2)
                      if(p.charAt(p.length()-1) == p.charAt(p.length()-3))
                        p=p.substring(0,p.length()-2);
                }
            }
        }
        System.out.println("\nLine number with longest words : "+p);
    }
    static String wsort(String n)
    {
        int s[]=new int[n.length()];
        for(int i=0;i<s.length;i++)
          s[i]=n.charAt(i);
        int l = s.length;
        for (int i = 1; i < l; ++i) {
            int key = s[i];
            int j = i - 1;
            while (j >= 0 && s[j] > key) {
                s[j + 1] = s[j];
                j = j - 1;
            }
            s[j + 1] = key;
        }
        n="";
        for(int i=0;i<s.length;i++)
          n+=(char)(s[i]);
        return n;
    }
    static String[] psort(String s)
    {
        s=s.replace("."," ");
        String n[]=s.split(" ",0);
        for(int i=0;i<n.length;i++)
          n[i]=wsort(n[i]);
        int l = n.length;  
        String temp = "";  
        for(int i=0; i < l; i++)
        {  
            for(int j=1; j < (l-i); j++)
            {  
                if(n[j-1].compareTo(n[j])<0)
                {  
                    temp = n[j-1];  
                    n[j-1] = n[j];  
                    n[j] = temp;  
                }  
            }  
        }
        return n;
    }
    static void change(String s[])
    {
        System.out.println("\nVowels :");
        for(int i=0;i<s.length;i++)
        {
            
            char[] ch = s[i].toCharArray();
            for(int j=0;j<ch.length && ch[j] != ' ';j++)
            {
                char c=ch[j];
                c=Character.toLowerCase(c);
                if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u')
                {
                    if(j%2==0)
                    {
                    System.out.print(c+" ");
                    switch(c)
                    {
                        case 'a':
                        c='e';
                        break;
                        case 'e':
                        c='i';
                        break;
                        case 'i':
                        c='o';
                        break;
                        case 'o':
                        c='u';
                        break;
                        case 'u':
                        c='a';
                        break;
                    }
                    }
                }
                else 
                {
                    switch(c)
                    {
                        case 'b':
                        c='z';
                        break;
                        case 'f':
                        c='d';
                        break;
                        case 'j':
                        c='h';
                        break;
                        case 'p':
                        c='n';
                        break;
                        case 'v':
                        c='t';
                        break;
                        default:
                        c=(char)(c-1);
                    }
                }
                ch[j]=c;
            }
            s[i]=new String(ch);
        }
        System.out.println("\n\nModified paragraph :");
        for(int i=0;i<s.length;i++)
          System.out.print(s[i]+" ");
    }
    static void display(String n)
    {
        longest(n);
        change(psort(n));
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter paragraph :");
        String s=sc.nextLine();
        display(s);
    }
}