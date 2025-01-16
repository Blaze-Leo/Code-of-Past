import java.util.*;
class BinSe {
    int a[];
    // Returns index of x if it is present in arr[l..
    // r], else return -1
    int search(int arr[], int l, int r, int x)
    {
        if (r >= l) {
            int mid = l + (r - l) / 2;
 
            // If the element is present at the
            // middle itself
            if (arr[mid] == x)
                return mid;
 
            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] > x)
                return search(arr, l, mid - 1, x);
 
            // Else the element can only be present
            // in right subarray
            return search(arr, mid + 1, r, x);
        }
 
        // We reach here when element is not present
        // in array
        return -1;
    }
 
    // Driver method to test above
    public static void main(String args[])
    {
        BinSe bm=new BinSe();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter size of int array :");
        bm.a=new int[sc.nextInt()];
        System.out.println("Enter the elements :");
        for (int i=0;i<bm.a.length;i++)
          bm.a[i]=sc.nextInt();
        System.out.println("Enter element to search :");
        int result = bm.search(bm.a, 0, bm.a.length - 1, sc.nextInt());
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at index "
                               + result);
    }
}