#ifndef BINARY_TREES_H
#define BINARY_TREES_H

#include <stdbool.h>

// Define TreeNode structure
typedef struct TreeNode
{
    struct TreeNode *left;
    struct TreeNode *right;
    int value;
} TreeNode;

// Define BinaryTree structure
typedef struct BinaryTree
{
    TreeNode *head;
} BinaryTree;

// Function prototypes
BinaryTree *create_tree();
void free_tree(BinaryTree *t);
bool is_empty(BinaryTree *t);
double tree_min(BinaryTree *t);
double tree_max(BinaryTree *t);
double tree_median(BinaryTree *t);
double tree_sum(BinaryTree *t);
double tree_avg(BinaryTree *t);
bool contains(BinaryTree *t, int value);
void insert_node(BinaryTree *t, int value);
int tree_height(BinaryTree *t);
int tree_size(BinaryTree *t);
int tree_width(BinaryTree *t);
void inorder_traversal(BinaryTree *t, int **values_ptr, int *len_ptr);
void preorder_traversal(BinaryTree *t, int **values_ptr, int *len_ptr);
void postorder_traversal(BinaryTree *t, int **values_ptr, int *len_ptr);

#endif /* BINARY_TREES_H */
