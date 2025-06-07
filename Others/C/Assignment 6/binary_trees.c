#include "binary_trees.h"
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

// Function to create a new TreeNode
TreeNode *create_node(int value)
{
    TreeNode *node = (TreeNode *)malloc(sizeof(TreeNode));
    assert(node != NULL);
    node->value = value;
    node->left = NULL;
    node->right = NULL;
    return node;
}

// Function to insert a new node into the tree
void insert_node_recursive(TreeNode **node, int value)
{
    if (*node == NULL)
    {
        *node = create_node(value);
    }
    else
    {
        if (value < (*node)->value)
        {
            insert_node_recursive(&(*node)->left, value);
        }
        else if (value > (*node)->value)
        {
            insert_node_recursive(&(*node)->right, value);
        }
    }
}

// Function to free all nodes of the tree
void free_nodes(TreeNode *node)
{
    if (node == NULL)
        return;
    free_nodes(node->left);
    free_nodes(node->right);
    free(node);
}

double node_min(TreeNode *node)
{
    if (node->left == NULL)
        return node->value;
    return node_min(node->left);
}

double node_max(TreeNode *node)
{
    if (node->right == NULL)
        return node->value;
    return node_max(node->right);
}

double node_sum(TreeNode *node)
{
    if (node == NULL)
        return 0;
    return node->value + node_sum(node->left) + node_sum(node->right);
}

bool node_contains(TreeNode *node, int value)
{
    if (node == NULL)
        return false;
    if (node->value == value)
        return true;
    if (value < node->value)
        return node_contains(node->left, value);
    return node_contains(node->right, value);
}

// Function to calculate the size of the tree
int calculate_size(TreeNode *node)
{
    if (node == NULL)
        return 0;
    return 1 + calculate_size(node->left) + calculate_size(node->right);
}

// Function to calculate the height of the tree
int calculate_height(TreeNode *node)
{
    if (node == NULL)
        return -1;
    int left_height = calculate_height(node->left);
    int right_height = calculate_height(node->right);
    return 1 + ((left_height > right_height) ? left_height : right_height);
}

int max(int a, int b)
{
    return (a > b) ? a : b;
}

// Function to find the width of a specific level in the binary tree
int get_level_width(TreeNode *node, int level)
{
    if (node == NULL)
        return 0;
    if (level == 1)
        return 1;
    else if (level > 1)
        return get_level_width(node->left, level - 1) + get_level_width(node->right, level - 1);
    return 0;
}

// Function to traverse the tree inorder and store values
void inorder_traverse(TreeNode *node, int **values_ptr, int *index)
{
    if (node == NULL)
        return;
    inorder_traverse(node->left, values_ptr, index);
    (*values_ptr)[(*index)++] = node->value;
    inorder_traverse(node->right, values_ptr, index);
}

// Function to traverse the tree preorder and store values
void preorder_traverse(TreeNode *node, int **values_ptr, int *index)
{
    if (node == NULL)
        return;
    (*values_ptr)[(*index)++] = node->value;
    preorder_traverse(node->left, values_ptr, index);
    preorder_traverse(node->right, values_ptr, index);
}

// Function to traverse the tree postorder and store values
void postorder_traverse(TreeNode *node, int **values_ptr, int *index)
{
    if (node == NULL)
        return;
    postorder_traverse(node->left, values_ptr, index);
    postorder_traverse(node->right, values_ptr, index);
    (*values_ptr)[(*index)++] = node->value;
}

// Function to create a new binary tree
BinaryTree *create_tree()
{
    BinaryTree *tree = (BinaryTree *)malloc(sizeof(BinaryTree));
    assert(tree != NULL);
    tree->head = NULL;
    return tree;
}

// Function to free the binary tree
void free_tree(BinaryTree *t)
{
    if (t == NULL)
        return;
    free_nodes(t->head);
    free(t);
}

// Function to check if the binary tree is empty
bool is_empty(BinaryTree *t)
{
    return t->head == NULL;
}

// Function to find the minimum value in the binary tree
double tree_min(BinaryTree *t)
{
    if (t->head == NULL)
        return 2147483647;
    return node_min(t->head);
}

// Function to find the maximum value in the binary tree
double tree_max(BinaryTree *t)
{
    if (t->head == NULL)
        return -2147483648;
    return node_max(t->head);
}

// Function to calculate the median value in the binary tree
double tree_median(BinaryTree *t)
{

    int *values = NULL;
    int len = 0;
    inorder_traversal(t, &values, &len);

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

// Function to calculate the sum of all values in the binary tree
double tree_sum(BinaryTree *t)
{
    if (t->head == NULL)
        return 0;
    return node_sum(t->head);
}

// Function to calculate the average of all values in the binary tree
double tree_avg(BinaryTree *t)
{
    int size = tree_size(t);
    if (size == 0)
        return 0;
    return (tree_sum(t) / size);
}

// Function to check if a value is present in the binary tree
bool contains(BinaryTree *t, int value)
{
    if (t->head == NULL)
        return false;
    return node_contains(t->head, value);
}

// Function to insert a value into the binary tree
void insert_node(BinaryTree *t, int value)
{
    insert_node_recursive(&t->head, value);
}

// Function to calculate the height of the binary tree
int tree_height(BinaryTree *t)
{
    return calculate_height(t->head);
}

// Function to calculate the size of the binary tree
int tree_size(BinaryTree *t)
{
    return calculate_size(t->head);
}

// Function to find the maximum width of the binary tree
int tree_width(BinaryTree *t)
{
    if (t == NULL || t->head == NULL)
        return 0;

    int max_width = 0;
    int height = tree_height(t);

    for (int i = 1; i <= height + 1; i++)
    {
        int width = get_level_width(t->head, i);
        max_width = max(max_width, width);
    }
    return max_width;
}

// Function to perform inorder traversal and store values
void inorder_traversal(BinaryTree *t, int **values_ptr, int *len_ptr)
{
    int size = tree_size(t);
    if (size == 0)
    {
        *len_ptr = 0;
        return;
    }
    *values_ptr = (int *)malloc(size * sizeof(int));
    assert(*values_ptr != NULL);
    int index = 0;
    inorder_traverse(t->head, values_ptr, &index);
    *len_ptr = size;
}

// Function to perform preorder traversal and store values
void preorder_traversal(BinaryTree *t, int **values_ptr, int *len_ptr)
{
    int size = tree_size(t);
    if (size == 0)
    {
        *len_ptr = 0;
        return;
    }
    *values_ptr = (int *)malloc(size * sizeof(int));
    assert(*values_ptr != NULL);
    int index = 0;
    preorder_traverse(t->head, values_ptr, &index);
    *len_ptr = size;
}

// Function to perform postorder traversal and store values
void postorder_traversal(BinaryTree *t, int **values_ptr, int *len_ptr)
{
    int size = tree_size(t);
    if (size == 0)
    {
        *len_ptr = 0;
        return;
    }
    *values_ptr = (int *)malloc(size * sizeof(int));
    assert(*values_ptr != NULL);
    int index = 0;
    postorder_traverse(t->head, values_ptr, &index);
    *len_ptr = size;
}
