import java.util.*;
class BS_mad
{
    int a[];
    int[] b={10,10,20,20,30};
    public static void main(String args[])
    {
        BS_mad bm=new BS_mad();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter size of int array :");
        bm.a=new int[sc.nextInt()];
        System.out.println("Enter the elements :");
        for (int i=0;i<bm.a.length;i++)
          bm.a[i]=sc.nextInt();
        bm.sort();
        bm.a=bm.selectionSort(bm.a);
        for (int i = 0; i < bm.a.length; ++i)
            System.out.print(bm.a[i] + " ");
    }
    int[] selectionSort(int[] arr){  
        for (int i = 0; i < arr.length - 1; i++)  
        {  
            int index = i;  
            for (int j = i + 1; j < arr.length; j++){  
                if (arr[j] > arr[index] ){  
                    index = j;//searching for lowest index  
                }  
            }  
            int smallerNumber = arr[index];   
            arr[index] = arr[i];  
            arr[i] = smallerNumber;  
        }  
        return arr;
    }  
    int[] sort()
    {
        int n = a.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1 && b[j]==b[j+1]; j++)
                if (a[j] > a[j + 1]) 
                  swap(j);
        return a;
    }
    void swap(int j)
    {
        int temp = a[j];
        a[j] = a[j + 1];
        a[j + 1] = temp;
    }
}
