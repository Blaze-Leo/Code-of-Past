#include <stdio.h>
#include <stdlib.h>
#include "binary_trees.h"

int main() {
    // Create a binary tree
    BinaryTree* tree = create_tree();

    // Insert nodes into the tree
    insert_node(tree, 5);
    insert_node(tree, 3);
    insert_node(tree, 7);
    insert_node(tree, 1);
    insert_node(tree, 4);
    insert_node(tree, 6);
    insert_node(tree, 8);

    // Test various functions 
    printf("Inserted nodes 5 3 7 1 4 6 8\n");
    printf("Is the tree empty? %s\n", is_empty(tree) ? "Yes" : "No");
    printf("Minimum value in the tree: %.2f\n", tree_min(tree));
    printf("Maximum value in the tree: %.2f\n", tree_max(tree));
    printf("Median of all values in the tree: %.2f\n", tree_median(tree));
    printf("Sum of all values in the tree: %.2f\n", tree_sum(tree));
    printf("Average of all values in the tree: %.2f\n", tree_avg(tree));
    printf("Does the tree contain value 6? %s\n", contains(tree, 6) ? "Yes" : "No");
    printf("Height of the tree: %d\n", tree_height(tree));
    printf("Size of the tree: %d\n", tree_size(tree));
    printf("Width of the tree: %d\n", tree_width(tree));
    printf("\n");

    // Test inorder traversal
    int* inorder_values = NULL;
    int inorder_len = 0;
    inorder_traversal(tree, &inorder_values, &inorder_len);
    printf("Inorder traversal: ");
    for (int i = 0; i < inorder_len; i++) {
        printf("%d ", inorder_values[i]);
    }
    printf("\n");
    free(inorder_values);

    // Test preorder traversal
    int* preorder_values = NULL;
    int preorder_len = 0;
    preorder_traversal(tree, &preorder_values, &preorder_len);
    printf("Preorder traversal: ");
    for (int i = 0; i < preorder_len; i++) {
        printf("%d ", preorder_values[i]);
    }
    printf("\n");
    free(preorder_values);

    // Test postorder traversal
    int* postorder_values = NULL;
    int postorder_len = 0;
    postorder_traversal(tree, &postorder_values, &postorder_len);
    printf("Postorder traversal: ");
    for (int i = 0; i < postorder_len; i++) {
        printf("%d ", postorder_values[i]);
    }
    printf("\n");
    free(postorder_values);

    // Free the binary tree
    free_tree(tree);

    return 0;
}
