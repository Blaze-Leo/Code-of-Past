#include "tree_hash_set.h"
#include "binary_trees.h"
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

// Function to create a new TreeHashSet
TreeHashSet *create_set(int size, int prime)
{
    TreeHashSet *set = (TreeHashSet *)malloc(sizeof(TreeHashSet));
    assert(set != NULL);
    set->prime = prime;
    set->size = size;
    set->array = (BinaryTree **)malloc(size * sizeof(BinaryTree *));
    assert(set->array != NULL);
    for (int i = 0; i < size; ++i)
    {
        set->array[i] = create_tree();
    }
    return set;
}

// Function to free the TreeHashSet
void free_set(TreeHashSet *s)
{
    if (s == NULL)
        return;
    for (int i = 0; i < s->size; ++i)
    {
        free_tree(s->array[i]);
    }
    free(s->array);
    free(s);
}

// Function to check if the TreeHashSet is empty
bool is_empty_set(TreeHashSet *s)
{
    for (int i = 0; i < s->size; ++i)
    {
        if (!is_empty(s->array[i]))
            return false;
    }
    return true;
}

// Function to calculate the index using the hash formula
int set_hash(TreeHashSet *s, int value)
{

    int length = snprintf(NULL, 0, "%d", value);
    char *val = malloc(length + 1);
    snprintf(val, length + 1, "%d", value);
    int sum = 0;
    for (int i = 0; i < length; i++)
    {
        sum += (int)val[i];
    }
    free(val);
    return ((sum % s->prime) % s->size);
}

// Function to remove a value from the TreeHashSet
void remove_by_key(TreeHashSet *s, int value)
{
    int index = set_hash(s, value);
    free_tree(s->array[index]);
    s->array[index] = create_tree();
}

// Function to get the size of the TreeHashSet
int set_size(TreeHashSet *s)
{
    int size = 0;
    for (int i = 0; i < s->size; ++i)
    {
        size += tree_size(s->array[i]);
    }
    return size;
}

int* set_usage(TreeHashSet *s)
{
    int *usage = (int *)malloc(s->size * sizeof(int));
    if (usage == NULL)
    {
        fprintf(stderr, "Memory allocation failed\n");
        exit(EXIT_FAILURE);
    }
    for (int i = 0; i < s->size; ++i)
    {
        usage[i] = tree_size(s->array[i]);
    }
    return usage;
}

bool is_member(TreeHashSet *s, int value)
{
    int index = set_hash(s, value);
    return contains(s->array[index], value);
}

void insert(TreeHashSet *s, int value)
{
    // Calculate hash index for the value
    int index = set_hash(s, value);

    // Check if the value already exists in the binary tree
    if (!is_member(s, value))
    {
        // If not, insert the value into the corresponding binary tree
        insert_node(s->array[index], value);
    }
}

// Function to get the minimum value in the TreeHashSet
int get_min(TreeHashSet *s)
{
    int min = 2147483647;
    for (int i = 1; i < s->size; ++i)
    {
        int temp_min = tree_min(s->array[i]);
        if (temp_min < min)
        {
            min = temp_min;
        }
    }
    return min;
}

// Function to get the maximum value in the TreeHashSet
int get_max(TreeHashSet *s)
{
    int max = -2147483648;
    for (int i = 1; i < s->size; ++i)
    {
        int temp_max = tree_max(s->array[i]);
        if (temp_max > max)
        {
            max = temp_max;
        }
    }
    return max;
}


// Compare function for sorting integers in ascending order
int compare_integers(const void *a, const void *b)
{
    return (*(int *)a - *(int *)b);
}

double get_median(TreeHashSet *s)
{

    int *values = NULL;
    int len = 0;
    sorted_values(s, &values, &len);

    double median;
    if (len % 2 == 1)
    {
        median = values[len / 2];
    }
    else
    {
        median = (values[len / 2 - 1] + values[len / 2]) / 2.0;
    }

    free(values);
    return median;
}

// Function to get the sum of all values in the TreeHashSet
int get_sum(TreeHashSet *s)
{
    int sum = 0;
    for (int i = 0; i < s->size; ++i)
    {
        sum += tree_sum(s->array[i]);
    }
    return sum;
}

// Function to get the average of all values in the TreeHashSet
double get_avg(TreeHashSet *s)
{
    int size = set_size(s);
    if (size == 0)
        return 0;
    return (((double)get_sum(s)) / size);
}

// Function to get the sorted values of the TreeHashSet
void sorted_values(TreeHashSet *s, int **values_ptr, int *len_ptr)
{
    int size = set_size(s);
    if (size == 0)
    {
        *len_ptr = 0;
        return;
    }

    *values_ptr = (int *)malloc(size * sizeof(int));
    if (*values_ptr == NULL)
    {
        fprintf(stderr, "Memory allocation failed\n");
        exit(EXIT_FAILURE);
    }

    int index = 0;
    for (int i = 0; i < s->size; ++i)
    {
        int *tree_values;
        int tree_size;
        inorder_traversal(s->array[i], &tree_values, &tree_size);
        for (int j = 0; j < tree_size; ++j)
        {
            (*values_ptr)[index++] = tree_values[j];
        }
    }

    qsort(*values_ptr, size, sizeof(int), compare_integers);

    *len_ptr = size;
}

// Function to check if two TreeHashSets are equal
bool is_equal(TreeHashSet *s1, TreeHashSet *s2)
{

    if (set_size(s1) != set_size(s2))
        return false;

    int *arr1 = NULL;
    int len1 = 0;
    sorted_values(s1, &arr1, &len1);

    int *arr2 = NULL;
    int len2 = 0;
    sorted_values(s2, &arr2, &len2);

    for (int i = 0; i < len1; ++i)
    {
        if (arr1[i] != arr2[i])
        {
            return false;
        }
    }

    return true;
}

TreeHashSet *set_union(TreeHashSet *s1, TreeHashSet *s2)
{
    int new_prime = s1->prime > s2->prime ? s1->prime : s2->prime;
    int new_size = s1->size > s2->size ? s1->size : s2->size;
    TreeHashSet *result_set = create_set(new_size, new_prime);

    int *arr1 = NULL;
    int len1 = 0;
    sorted_values(s1, &arr1, &len1);

    int *arr2 = NULL;
    int len2 = 0;
    sorted_values(s2, &arr2, &len2);

    for (int i = 0; i < len1; ++i)
        insert(result_set, arr1[i]);

    for (int i = 0; i < len2; ++i)
        insert(result_set, arr2[i]);

    return result_set;
}

// Function to perform the intersection of two TreeHashSets
TreeHashSet *set_intersection(TreeHashSet *s1, TreeHashSet *s2)
{

    int new_prime = s1->prime > s2->prime ? s1->prime : s2->prime;
    int new_size = s1->size > s2->size ? s1->size : s2->size;
    TreeHashSet *result_set = create_set(new_size, new_prime);

    int *arr1 = NULL;
    int len1 = 0;
    sorted_values(s1, &arr1, &len1);

    int *arr2 = NULL;
    int len2 = 0;
    sorted_values(s2, &arr2, &len2);

    int inter[len1 + len2];

    int i = 0, j = 0, index = 0;
    while (i < len1 && j < len2)
    {
        if (arr1[i] < arr2[j])
        {
            i++;
        }
        else if (arr2[j] < arr1[i])
        {
            j++;
        }
        else
        {
            inter[index] = arr1[i++];
            index++;
            j++;
        }
    }

    for (int i = 0; i < index; ++i)
        insert(result_set, inter[i]);

    return result_set;
}

// Function to perform the difference of two TreeHashSets
TreeHashSet *set_difference(TreeHashSet *s1, TreeHashSet *s2)
{
    int new_prime = s1->prime > s2->prime ? s1->prime : s2->prime;
    int new_size = s1->size > s2->size ? s1->size : s2->size;
    TreeHashSet *result_set = create_set(new_size, new_prime);

    int *arr1 = NULL;
    int len1 = 0;
    sorted_values(s1, &arr1, &len1);

    int *arr2 = NULL;
    int len2 = 0;
    sorted_values(s2, &arr2, &len2);

    int diff[len1 + len2];

    int i = 0, j = 0, index = 0;
    while (i < len1 && j < len2)
    {
        if (arr1[i] < arr2[j])
        {
            diff[index] = arr1[i++];
            index++;
            i++;
        }
        else if (arr2[j] < arr1[i])
        {
            diff[index] = arr1[i++];
            index++;
            j++;
        }
        else
        {
            i++;
            j++;
        }
    }

    for (int i = 0; i < index; ++i)
        insert(result_set, diff[i]);

    return result_set;
}
