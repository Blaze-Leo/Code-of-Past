#include <stdio.h>
#include <stdlib.h>
#include "tree_hash_set.h"

int main()
{
    // Create two sets for testing
    TreeHashSet *set1 = create_set(10, 17);
    TreeHashSet *set2 = create_set(20, 19);

    // Insert some values into set1 and set2
    insert(set1, 5);
    insert(set1, 10);
    insert(set1, 15);
    insert(set1, 26);
    insert(set1, 7);

    insert(set2, 10);
    insert(set2, 20);
    insert(set2, 30);
    insert(set2, 5);
    insert(set2, 19);


    printf("Inserted values 5 10 15 26 17 in Set1\n");
    printf("Inserted values 10 20 30 5 19 in Set2\n");

    // Test functions
    printf("Is Set1 empty? %s\n", (is_empty_set(set1)) ? "Yes" : "No");
    printf("Is Set2 empty? %s\n", is_empty_set(set2) ? "Yes" : "No");

    printf("Set 1 hash value for 5: %d\n", set_hash(set1, 5));
    printf("Set 2 hash value for 20: %d\n", set_hash(set2, 20));

    printf("Removing key 19 from Set 2\n");
    remove_by_key(set2, 19);

    printf("Set 1 size: %d\n", set_size(set1));
    printf("Set 2 size: %d\n", set_size(set2));

    printf("Is 15 a member of set 1? %s\n", is_member(set1, 15) ? "Yes" : "No");
    printf("Is 19 a member of set 2? %s\n", is_member(set2, 25) ? "Yes" : "No");

    printf("Minimum value of Set1: %d\n", get_min(set1));
    printf("Maximum value of Set2: %d\n", get_max(set2));

    printf("Median value of Set1: %f\n", get_median(set1));
    printf("Average value of Set2: %f\n", get_avg(set2));

    printf("Sum value of Set1: %d\n", get_sum(set1));

    printf("Are Set1 and Set2 equal? %s\n", is_equal(set1, set2) ? "Yes" : "No");

    int *arr1 = NULL;
    int len1 = 0;
    sorted_values(set1, &arr1, &len1);

    printf("Sorted Values of Set1: ");
    for (int i = 0; i < len1; i++)
    {
        printf("%d ", arr1[i]);
    }
    printf("\n");

    int *arr2 = NULL;
    int len2 = 0;
    sorted_values(set2, &arr2, &len2);

    printf("Sorted Values of Set1: ");
    for (int i = 0; i < len2; i++)
    {
        printf("%d ", arr2[i]);
    }
    printf("\n");

    printf("Union of set 1 and set 2 is calculated:\n");
    TreeHashSet *union_set = set_union(set1, set2);
    printf("Is 15 a member of Union Set? %s\n", is_member(union_set, 15) ? "Yes" : "No");
    printf("Is 30 a member of Union Set? %s\n", is_member(union_set, 30) ? "Yes" : "No");

    printf("Intersection of set 1 and set 2 is calculated:\n");
    TreeHashSet *inter_set = set_intersection(set1, set2);
    printf("Is 5 a member of Intersection Set? %s\n", is_member(inter_set, 5) ? "Yes" : "No");
    printf("Is 15 a member of Intersection Set? %s\n", is_member(inter_set, 15) ? "Yes" : "No");

    printf("Difference of set 1 and set 2 is calculated:\n");
    TreeHashSet *diff_set = set_difference(set1, set2);
    printf("Is 26 a member of Difference Set? %s\n", is_member(diff_set, 26) ? "Yes" : "No");
    printf("Is 10 a member of Difference Set? %s\n", is_member(diff_set, 10) ? "Yes" : "No");

    return 0;
}
