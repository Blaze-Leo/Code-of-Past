import java.util.*;
class Word_Frequency
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of sentences :");
        int sen=sc.nextInt();
        if(sen>4){System.out.println("Invalid Input");}
        System.out.println("Enter sentence :");
        String s= sc.nextLine();
        s= sc.nextLine();
        for(int i=0;i<s.length();i++)
          s=s.replace(s.charAt(i),s.charAt(i)>90||s.charAt(i)<65?' ':s.charAt(i));
        String[] word=s.split(" ");
        int[] c=new int[word.length];
        for(int i=0;i<c.length;i++)
          c[i]=0;
        for(int i=0;i<word.length;i++)
        {
            for(int j=i+1;j<word.length;j++)
               if(word[i].equals(word[j]))
               {
                   c[i]++;
                   word[j]=" ";
               }
        }
        for(int i=0;i<word.length;i++)
        {
            for(int j=0;j<word.length;j++)
            {
                if(c[j]==i && !(word[j].equals(" ")))
                System.out.println(word[j]+"--"+(i+1));
            }
        }
        sc.close();
    }
}
        
