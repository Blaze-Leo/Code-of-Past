public interface Sortable {
    int cmp(Sortable s);

    
    default void sort(Sortable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].cmp(arr[j + 1]) > 0) { 
                    Sortable temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
