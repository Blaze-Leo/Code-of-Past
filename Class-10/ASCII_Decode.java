import java.util.*;
class Boomer_Code
{
    char encode(char x)
    {
        if (x<58)
          return (char)(x-48+210);
        int y;
        x=(char)((int)(x) - 64);
        if (x<16)
          y=32+x;
        else if (x<23)
          y=57+x-15;
        else
          y=90+x-22;
        return (char)(y);
    }
    char decode(char x)
    {
        if (x>209)
          return (char)(x-210+48);
        int y;
        if (x<48)
          y=x-32;
        else if (x<65)
          y=x-57+15;
        else 
          y=x-90+22;
        y=y+64;
        return (char)(y);
    }
    public static void main(String args[])
    {
        Boomer_Code bc=new Boomer_Code();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string");
        String s=sc.nextLine();
        s=s.toUpperCase();
        String m="";
        System.out.println("Enter 1 for Encode and 2 for Decode");
        int i=sc.nextInt();
        switch (i)
        {
            case 1:
            for (int j=0;j<s.length();j++)
            {
                if ((!(Character.isAlphabetic(s.charAt(j)))) && (!(Character.isDigit(s.charAt(j)))))
                {
                    m+=s.charAt(j);
                    continue;
                }
                m+=bc.encode(s.charAt(j));
            }
            break;
            case 2:
            for (int j=0;j<s.length();j++)
            {
                if (s.charAt(j) == ' ')
                {
                    m+=' ';
                    continue;
                }
                m+=bc.decode(s.charAt(j));
            }
            break;
        }
        System.out.println("Output : " + m);
    }
}