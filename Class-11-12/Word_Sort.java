import java.util.*;
class Word_Sort
{
    public static void main(String args[]) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any word :");
        String str=sc.next();
        sc.close();
        char[] array = new char[str.length()];
        for (int l = 0; l < str.length(); l++) {
            array[l] = str.charAt(l);
        }
        int n = array.length;
        int key,i;
        System.out.println("Array before Sort :");
        for (int k=0;k<n;k++)
          System.out.print(array[k]);
        System.out.println();
        for (int j = 1; j < n; j++) {
            key = array[j];
            i = j-1;
            while ( (i > -1) && ( array [i] > key ) ) {
                array [i+1] = array [i];
                i--;
            }
            array[i+1] = (char)(key);
        }
        System.out.println("Array after Sort :");
        for (int k=0;k<n;k++)
          System.out.print(array[k]);
    }
}