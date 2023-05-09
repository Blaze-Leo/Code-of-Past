import java.util.*;
class Word_Number
{
    public static void main(String args[])
    {
        String one[]={"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
        String two[]={"","Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
        String teen[]={"","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
        String three[]={"","One Hundred","Two Hundred","Three Hundred","Four Hundred","Five Hundred","Six Hundred","Seven Hundred","Eight Hundred","Nine Hundred"};
        String four[]={"","One Thousand","Two Thousand","Three Thousand","Four Thousand","Five Thousand","Six Thousand","Seven Thousand","Eight Thousand","Nine Thousand"};
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number ::");
        int n=sc.nextInt();
        String s="";
        s+=four[(n-n%1000)/1000]+" ";
        n%=1000;
        s+=three[(n-n%100)/100]+" ";
        if (n%100 < 20 && n%100 > 10)
        {
            s+=teen[n%10];
        }
        else
        {
            s+=two[(n%100-n%10)/10]+" ";
            s+=one[n%10];
        }
        System.out.println(s);
    }
}