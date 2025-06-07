#ifndef TREE_HASH_SET_H
#define TREE_HASH_SET_H

#include "binary_trees.h"
#include <stdbool.h>

// Define TreeHashSet structure
typedef struct TreeHashSet {
    int prime;
    int size;
    BinaryTree** array;
} TreeHashSet;

// Function prototypes
TreeHashSet* create_set(int size, int prime);
void free_set(TreeHashSet* s);
bool is_empty_set(TreeHashSet* s);
int set_hash(TreeHashSet* s, int value);
void remove_by_key(TreeHashSet* s, int value);
int set_size(TreeHashSet* s);
int* set_usage(TreeHashSet* s);
bool is_member(TreeHashSet* s, int value);
void insert(TreeHashSet* s, int value);
int get_min(TreeHashSet* s);
int get_max(TreeHashSet* s);
double get_median(TreeHashSet* s);
int get_sum(TreeHashSet* s);
double get_avg(TreeHashSet* s);
void sorted_values(TreeHashSet* s, int** values_ptr, int* len_ptr);
bool is_equal(TreeHashSet* s1, TreeHashSet* s2);
TreeHashSet* set_union(TreeHashSet* s1, TreeHashSet* s2);
TreeHashSet* set_intersection(TreeHashSet* s1, TreeHashSet* s2);
TreeHashSet* set_difference(TreeHashSet* s1, TreeHashSet* s2);

#endif /* TREE_HASH_SET_H */
