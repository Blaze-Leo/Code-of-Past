import java.util.*;
class Anagram
{
    String[] full;
    int c=0;
String swap(String a, int i, int j) {
  char t;
  char[] c = a.toCharArray();
  t = c[i];
  c[i] = c[j];
  c[j] = t;
  return String.valueOf(c);
}
void revert(String s, int idx, int N) {
  if (idx == N)
  {
    full[c]=s;
    c++;
  }
  else {
    for (int i = idx; i <= N; i++) {
      s = swap(s, idx, i);
      revert(s, idx + 1, N);
      s = swap(s, idx, i);
    }
  }
}
int fact(int n)
{
    if(n==1)
      return n;
    return n*fact(n-1);
}
public static void main(String args[]) {
    Scanner sc=new Scanner(System.in);
    Anagram an=new Anagram();
    System.out.println("Enter String :");
    String s = sc.next();
    System.out.println("\n");
    int N = s.length();
    an.full=new String[an.fact(N)];
    an.revert(s, 0, N - 1);
    N=an.full.length;
    for(int i=0;i<N;i++)
    {
        for(int j=i+1;j<N;j++)
            if(an.full[j].compareTo(an.full[i])==0 || an.full[j].compareTo(s)==0)
              an.full[j]="";
    }
    for(int i=0;i<N;i++)
    {
        if(an.full[i]!="")
          System.out.println(an.full[i]);
        }
    }
}
