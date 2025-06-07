#include "ex6.h"
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

# define INT_BUFFER 128

// ================================================
// Basic struct definitions from ex6.h assumed:
//   PokemonData { int id; char *name; PokemonType TYPE; int hp; int attack; EvolutionStatus CAN_EVOLVE; }
//   PokemonNode { PokemonData* data; PokemonNode* left, *right; }
//   OwnerNode   { char* ownerName; PokemonNode* pokedexRoot; OwnerNode *next, *prev; }
//   OwnerNode* ownerHead;
//   const PokemonData pokedex[];
// ================================================

// --------------------------------------------------------------
// 1) Safe integer reading
// --------------------------------------------------------------

void trimWhitespace(char *str) {
    // Remove leading spaces/tabs/\r
    int start = 0;
    while (str[start] == ' ' || str[start] == '\t' || str[start] == '\r')
        start++;

    if (start > 0) {
        int idx = 0;
        while (str[start])
            str[idx++] = str[start++];
        str[idx] = '\0';
    }

    // Remove trailing spaces/tabs/\r and newline (\n) character
    int len = (int)strlen(str);
    while (len > 0 && (str[len - 1] == ' ' || str[len - 1] == '\t' || str[len - 1] == '\r' || str[len - 1] == '\n')) {
        str[--len] = '\0';
    }
}

char *myStrdup(const char *src)
{
    if (!src)
        return NULL;
    size_t len = strlen(src);
    char *dest = (char *)malloc(len + 1);
    if (!dest)
    {
        printf("Memory allocation failed in myStrdup.\n");
        return NULL;
    }
    strcpy(dest, src);
    return dest;
}

int readIntSafe(const char *prompt)
{
    char buffer[INT_BUFFER];
    int value;
    int success = 0;
 printf("%s", prompt);
    while (!success)
    {
       

        // If we fail to read, treat it as invalid
        if (!fgets(buffer, sizeof(buffer), stdin))
        {
            // printf("Invalid input.\n");
            clearerr(stdin);
            continue;
        }

        // 1) Strip any trailing \r or \n
        //    so "123\r\n" becomes "123"
        size_t len = strlen(buffer);
        if (len > 0 && (buffer[len - 1] == '\n' || buffer[len - 1] == '\r'))
            buffer[--len] = '\0';
        if (len > 0 && (buffer[len - 1] == '\r' || buffer[len - 1] == '\n'))
            buffer[--len] = '\0';

        // 2) Check if empty after stripping
        if (len == 0)
        {
            // printf("Invalid input.\n");
            continue;
        }

        // 3) Attempt to parse integer with strtol
        char *endptr;
        value = (int)strtol(buffer, &endptr, 10);

        // If endptr didn't point to the end => leftover chars => invalid
        // or if buffer was something non-numeric
        if (*endptr != '\0')
        {
            // printf("Invalid input.\n");
        }
        else
        {
            // We got a valid integer
            success = 1;
        }
    }
    return value;
}

// --------------------------------------------------------------
// 2) Utility: Get type name from enum
// --------------------------------------------------------------
const char *getTypeName(PokemonType type)
{
    switch (type)
    {
    case GRASS:
        return "GRASS";
    case FIRE:
        return "FIRE";
    case WATER:
        return "WATER";
    case BUG:
        return "BUG";
    case NORMAL:
        return "NORMAL";
    case POISON:
        return "POISON";
    case ELECTRIC:
        return "ELECTRIC";
    case GROUND:
        return "GROUND";
    case FAIRY:
        return "FAIRY";
    case FIGHTING:
        return "FIGHTING";
    case PSYCHIC:
        return "PSYCHIC";
    case ROCK:
        return "ROCK";
    case GHOST:
        return "GHOST";
    case DRAGON:
        return "DRAGON";
    case ICE:
        return "ICE";
    default:
        return "UNKNOWN";
    }
}

// --------------------------------------------------------------
// Utility: getDynamicInput (for reading a line into malloc'd memory)
// --------------------------------------------------------------
char *getDynamicInput()
{
    char *input = NULL;
    size_t size = 0, capacity = 1;
    input = (char *)malloc(capacity);
    if (!input)
    {
        printf("Memory allocation failed.\n");
        return NULL;
    }

    int c;
    while ((c = getchar()) != '\n' && c != EOF)
    {
        if (size + 1 >= capacity)
        {
            capacity *= 2;
            char *temp = (char *)realloc(input, capacity);
            if (!temp)
            {
                printf("Memory reallocation failed.\n");
                free(input);
                return NULL;
            }
            input = temp;
        }
        input[size++] = (char)c;
    }
    input[size] = '\0';

    // Trim any leading/trailing whitespace or carriage returns
    trimWhitespace(input);

    return input;
}

// Function to print a single Pokemon node
void printPokemonNode(PokemonNode *node)
{
    if (!node)
        return;
    printf("ID: %d, Name: %s, Type: %s, HP: %d, Attack: %d, Can Evolve: %s\n",
           node->data->id,
           node->data->name,
           getTypeName(node->data->TYPE),
           node->data->hp,
           node->data->attack,
           (node->data->CAN_EVOLVE == CAN_EVOLVE) ? "Yes" : "No");
}

// --------------------------------------------------------------
// Display Menu
// --------------------------------------------------------------


// --------------------------------------------------------------
// Left-Over Functions
// --------------------------------------------------------------

// Function to create a new PokemonNode
PokemonNode *createPokemonNode(const PokemonData *data) {
    if (!data) {
        printf("Error: NULL PokemonData provided.\n");
        return NULL;
    }

    // Allocate memory for the new node
    PokemonNode *newNode = (PokemonNode *)malloc(sizeof(PokemonNode));
    if (!newNode) {
        printf("Memory allocation failed for PokemonNode.\n");
        return NULL;
    }

    // Allocate memory for PokemonData and copy values
    newNode->data = (PokemonData *)malloc(sizeof(PokemonData));
    if (!newNode->data) {
        printf("Memory allocation failed for PokemonData.\n");
        free(newNode);
        return NULL;
    }

    // Copy data
    newNode->data->id = data->id;
    newNode->data->name = myStrdup(data->name); // Duplicate the name
    newNode->data->TYPE = data->TYPE;
    newNode->data->hp = data->hp;
    newNode->data->attack = data->attack;
    newNode->data->CAN_EVOLVE = data->CAN_EVOLVE;

    // Initialize left and right children to NULL
    newNode->left = NULL;
    newNode->right = NULL;

    return newNode;
}

OwnerNode *createOwner(char *ownerName, PokemonNode *pokedexRoot) {
    if (!ownerName) {
        printf("Error: NULL owner name provided.\n");
        return NULL;
    }

    // Allocate memory for the new OwnerNode
    OwnerNode *newOwner = (OwnerNode *)malloc(sizeof(OwnerNode));
    if (!newOwner) {
        printf("Memory allocation failed for OwnerNode.\n");
        return NULL;
    }

    // Duplicate the owner's name
    newOwner->ownerName = myStrdup(ownerName);
    if (!newOwner->ownerName) {
        printf("Memory allocation failed for ownerName.\n");
        free(newOwner);
        return NULL;
    }

    // Assign the pokedexRoot Pokemon to the owner's Pokedex
    newOwner->pokedexRoot = pokedexRoot;

    // Initialize the linked list pointers
    newOwner->next = NULL;
    newOwner->prev = NULL;

    return newOwner;
}


// Function to free a single PokemonNode


void freePokemonData(PokemonData *data) {
    if (!data) return;

    // Free the dynamically allocated name
    free(data->name);

    // Free the structure itself
    free(data);
}


void freePokemonTree(PokemonNode *root) {
    if (!root) {
        return;
    }
    freePokemonData(root->data);

    // Recursively free left and right subtrees
    freePokemonTree(root->left);
    freePokemonTree(root->right);


    // Free the current node
    free(root);
}
void freeAllOwners(void) {
    if (!ownerHead) return;  // If list is already empty, do nothing

    OwnerNode *current = ownerHead;
    OwnerNode *nextOwner;

    // Traverse and delete all nodes until the list is empty
    while (current) {
        nextOwner = current->next;  // Store the next owner
        freeOwnerNode(current);     // Free the current owner

        // If the list is empty now, stop
        if (ownerHead == NULL) break;

        current = nextOwner;  // Move to the next owner
    }
}


void freeOwnerNode(OwnerNode *owner) {
    if (!owner) {
        return;
    }

    // Free the owner's Pokedex (entire BST)
    freePokemonTree(owner->pokedexRoot);

    // If the owner is the only one in the circular list
    if (owner->next == owner && owner->prev == owner) {
        ownerHead = NULL;  // The list becomes empty
    } else {
        // Update the circular list to bypass the owner
        owner->prev->next = owner->next;
        owner->next->prev = owner->prev;

        // If this was the head, move the head to the next owner
        if (ownerHead == owner) {
            ownerHead = owner->next;
        }
    }

    // Free the dynamically allocated owner name
    free(owner->ownerName);

    // Free the OwnerNode itself
    free(owner);

}


/* ------------------------------------------------------------
   3) BST Insert, Search, Remove
   ------------------------------------------------------------ */

PokemonNode *insertPokemonNode(PokemonNode *root, PokemonNode *newNode) {
    if (!newNode) {
        return root;
    }
    if (!root) {
        return newNode;
    }
    if (newNode->data->id < root->data->id) {
        root->left = insertPokemonNode(root->left, newNode);
    } else if (newNode->data->id > root->data->id) {
        root->right = insertPokemonNode(root->right, newNode);
    } else {
        free(newNode->data->name);
        free(newNode->data);
        free(newNode);
    }
    return root;
}

PokemonNode *searchPokemonBFS(PokemonNode *root, int id) {
    if (!root) {
        return NULL;
    }
    // Level-order traversal (BFS)
    PokemonNode **queue = (PokemonNode **)malloc(sizeof(PokemonNode *) * 100); // simple queue
    int front = 0, rear = 0;
    queue[rear++] = root;

    while (front < rear) {
        PokemonNode *current = queue[front++];
        if (current->data->id == id) {
            free(queue);
            return current;
        }
        if (current->left) {
            queue[rear++] = current->left;
        }
        if (current->right) {
            queue[rear++] = current->right;
        }
    }
    free(queue);
    return NULL;
}

PokemonNode *removeNodeBST(PokemonNode *root, int id) {
    if (!root) {
        return NULL;
    }
    if (id < root->data->id) {
        root->left = removeNodeBST(root->left, id);
    } else if (id > root->data->id) {
        root->right = removeNodeBST(root->right, id);
    } else {
        // Node to be deleted found

        // Case 1: Node has no children (leaf node)
        if (!root->left && !root->right) {
            free(root->data->name);
            free(root->data);
            free(root);
            return NULL;
        }

        // Case 2: Node has one child
        if (!root->left) {
            PokemonNode *temp = root->right;
            free(root->data->name);
            free(root->data);
            free(root);
            return temp;
        }
        if (!root->right) {
            PokemonNode *temp = root->left;
            free(root->data->name);
            free(root->data);
            free(root);
            return temp;
        }

        // Case 3: Node has two children
        PokemonNode *minRight = root->right;
        while (minRight && minRight->left) {
            minRight = minRight->left;
        }
        root->data = minRight->data;
        root->right = removeNodeBST(root->right, minRight->data->id);
    }
    return root;
}

PokemonNode *removePokemonByID(PokemonNode *root, int id) {
    PokemonNode *nodeToRemove = searchPokemonBFS(root, id);
    if (nodeToRemove) {
        return removeNodeBST(root, id);
    }
    return root;
}

/* ------------------------------------------------------------
   4) Generic BST Traversals (Function Pointers)
   ------------------------------------------------------------ */

void BFSGeneric(PokemonNode *root, VisitNodeFunc visit) {
    if (!root) {
        return;
    }
    PokemonNode **queue = (PokemonNode **)malloc(sizeof(PokemonNode *) * 100); // simple queue
    int front = 0, rear = 0;
    queue[rear++] = root;

    while (front < rear) {
        PokemonNode *current = queue[front++];
        visit(current);
        if (current->left) {
            queue[rear++] = current->left;
        }
        if (current->right) {
            queue[rear++] = current->right;
        }
    }
    free(queue);
}

void preOrderGeneric(PokemonNode *root, VisitNodeFunc visit) {
    if (!root) {
        return;
    }
    visit(root);
    preOrderGeneric(root->left, visit);
    preOrderGeneric(root->right, visit);
}

void inOrderGeneric(PokemonNode *root, VisitNodeFunc visit) {
    if (!root) {
        return;
    }
    inOrderGeneric(root->left, visit);
    visit(root);
    inOrderGeneric(root->right, visit);
}

void postOrderGeneric(PokemonNode *root, VisitNodeFunc visit) {
    if (!root) {
        return;
    }
    postOrderGeneric(root->left, visit);
    postOrderGeneric(root->right, visit);
    visit(root);
}



/**
 * @brief Initialize a NodeArray with given capacity.
 * @param na pointer to NodeArray
 * @param cap initial capacity
 * Why we made it: We store pointers to PokemonNodes for alphabetical sorting.
 */
void initNodeArray(NodeArray *na, int cap) {
    na->nodes = (PokemonNode **)malloc(cap * sizeof(PokemonNode *));
    na->size = 0;
    na->capacity = cap;
}

/**
 * @brief Add a PokemonNode pointer to NodeArray, realloc if needed.
 * @param na pointer to NodeArray
 * @param node pointer to the node
 * Why we made it: We want a dynamic list of BST nodes for sorting.
 */
void addNode(NodeArray *na, PokemonNode *node) {
    if (na->size >= na->capacity) {
        na->capacity *= 2;
        na->nodes = (PokemonNode **)realloc(na->nodes, na->capacity * sizeof(PokemonNode *));
    }
    na->nodes[na->size++] = node;
}

/**
 * @brief Recursively collect all nodes from the BST into a NodeArray.
 * @param root BST root
 * @param na pointer to NodeArray
 * Why we made it: We gather everything for qsort.
 */
void collectAll(PokemonNode *root, NodeArray *na) {
    if (!root) {
        return;
    }
    collectAll(root->left, na);
    addNode(na, root);
    collectAll(root->right, na);
}

/**
 * @brief Compare function for qsort (alphabetical by node->data->name).
 * @param a pointer to a pointer to PokemonNode
 * @param b pointer to a pointer to PokemonNode
 * @return -1, 0, or +1
 * Why we made it: Sorting by name for alphabetical display.
 */
int compareByNameNode(const void *a, const void *b) {
    PokemonNode *nodeA = *(PokemonNode **)a;
    PokemonNode *nodeB = *(PokemonNode **)b;
    return strcmp(nodeA->data->name, nodeB->data->name);
}

/**
 * @brief BFS is nice, but alphabetical means we gather all nodes, sort by name, then print.
 * @param root BST root
 * Why we made it: Provide user the option to see Pokemon sorted by name.
 */
void displayAlphabetical(PokemonNode *root) {
    NodeArray na;
    initNodeArray(&na, 10);
    collectAll(root, &na);
    qsort(na.nodes, na.size, sizeof(PokemonNode *), compareByNameNode);

    for (int i = 0; i < na.size; i++) {
        printPokemonNode(na.nodes[i]);
    }

    free(na.nodes);
}

/**
 * @brief BFS user-friendly display (level-order).
 * @param root BST root
 * Why we made it: Quick listing in BFS order.
 */
void displayBFS(PokemonNode *root) {
    if (!root) {
        return;
    }
    PokemonNode **queue = (PokemonNode **)malloc(sizeof(PokemonNode *) * 100); // simple queue
    int front = 0, rear = 0;
    queue[rear++] = root;

    while (front < rear) {
        PokemonNode *current = queue[front++];
        printPokemonNode(current);
        if (current->left) {
            queue[rear++] = current->left;
        }
        if (current->right) {
            queue[rear++] = current->right;
        }
    }
    free(queue);
}

/**
 * @brief Pre-order user-friendly display (Root->Left->Right).
 * @param root BST root
 * Why we made it: Another standard traversal for demonstration.
 */
void preOrderTraversal(PokemonNode *root) {
    if (!root) {
        return;
    }
    printPokemonNode(root);
    preOrderTraversal(root->left);
    preOrderTraversal(root->right);
}

/**
 * @brief In-order user-friendly display (Left->Root->Right).
 * @param root BST root
 * Why we made it: Good for sorted output by ID if the tree is a BST.
 */
void inOrderTraversal(PokemonNode *root) {
    if (!root) {
        return;
    }
    inOrderTraversal(root->left);
    printPokemonNode(root);
    inOrderTraversal(root->right);
}

/**
 * @brief Post-order user-friendly display (Left->Right->Root).
 * @param root BST root
 * Why we made it: Another standard traversal pattern.
 */
void postOrderTraversal(PokemonNode *root) {
    if (!root) {
        return;
    }
    postOrderTraversal(root->left);
    postOrderTraversal(root->right);
    printPokemonNode(root);
}

/**
 * @brief Let user pick two Pokemon by ID in the same Pokedex to fight.
 * @param owner pointer to the Owner
 * Why we made it: Fun demonstration of BFS and custom formula for battles.
 */
void pokemonFight(OwnerNode *owner) {
    if (!owner || !owner->pokedexRoot) {
        printf("Pokedex is empty.\n");
        return;
    }

    // Step 1: Perform BFS to collect all the Pokemon IDs
    PokemonNode *root = owner->pokedexRoot;
    int id1, id2;


    // Step 2: Let user pick two Pokemon by their IDs
    printf("Enter ID of the first Pokemon: ");
    scanf("%d", &id1);
    printf("Enter ID of the second Pokemon: ");
    scanf("%d", &id2);

    // Step 3: Search for the selected Pokemon by ID
    PokemonNode *pokemon1 = searchPokemonBFS(root, id1);
    PokemonNode *pokemon2 = searchPokemonBFS(root, id2);

    if (!pokemon1 || !pokemon2) {
        printf("One or both Pokemon not found.\n");
        return;
    }

    // Step 4: Calculate the battle results using the custom formula
    double result1 = (pokemon1->data->attack * 1.5) + (pokemon1->data->hp * 1.2);
    double result2 = (pokemon2->data->attack * 1.5) + (pokemon2->data->hp * 1.2);

    printf("Pokemon 1: %s (Score = %.2f)",pokedex[id1-1].name,result1);
    printf("\nPokemon 2: %s (Score = %.2f)",pokedex[id2-1].name,result2);
    // Step 5: Determine and print the winner (or tie)
    if (result1 > result2) {
        printf("\n%s wins!\n", pokemon1->data->name);
    } else if (result1 < result2) {
        printf("\n%s wins!\n", pokemon2->data->name);
    } else {
        printf("\nIt's a tie!\n");
    }
}

/**
 * @brief Evolve a Pokemon (ID -> ID+1) if allowed.
 * @param owner pointer to the Owner
 * Why we made it: Demonstrates removing an old ID, inserting the next ID.
 */
void evolvePokemon(OwnerNode *owner) {
    if (!owner || !owner->pokedexRoot) {
        printf("Cannot evolve. Pokedex empty.\n");
        return;
    }

    // Step 1: Let the user choose the Pokemon to evolve by ID
    int id;
    printf("Enter ID of Pokemon to evolve: ");
    scanf("%d", &id);

    // Step 2: Find the Pokemon by ID
    PokemonNode *pokemon = searchPokemonBFS(owner->pokedexRoot, id);

    if (!pokemon) {
        printf("Pokemon with ID %d not found.\n", id);
        return;
    }

    // Step 3: Check if the Pokemon can evolve
    if (pokemon->data->CAN_EVOLVE == CAN_EVOLVE) {

        printf("Removing Pokemon %s (ID %d).\n",pokedex[id-1].name,id);

        owner->pokedexRoot = removeNodeBST(owner->pokedexRoot, id);

        PokemonNode *pokemon2 = searchPokemonBFS(owner->pokedexRoot, (id+1));

        if (pokemon2) {
        printf("Pokemon with ID %d already exists, not evolved.\n", (id+1));
        return;
        }

        PokemonData data = pokedex[id];
        PokemonNode *newNode = createPokemonNode(&data);

        // Remove the old node (with ID) from the tree
        

 

        // Insert the new evolved Pokemon node into the BST
        owner->pokedexRoot = insertPokemonNode(owner->pokedexRoot, newNode);

        printf("Pokemon evolved from %s (ID %d) to %s (ID %d).\n",pokedex[id-1].name, id,pokedex[id].name, (id+1));
    } else {
        printf("Pokemon with ID %d cannot evolve.\n", id);
    }
}

/**
 * @brief Prompt for an ID, BFS-check duplicates, then insert into BST.
 * @param owner pointer to the Owner
 * Why we made it: Primary user function for adding new Pokemon to an owner’s Pokedex.
 */
void addPokemon(OwnerNode *owner) {
    if (!owner) {
        printf("Invalid owner.\n");
        return;
    }

    // Step 1: Prompt user for Pokemon ID
    int id;
    printf("Enter ID to add: ");
    scanf("%d", &id);

    // Step 2: Check if the Pokemon already exists in the BST
    PokemonNode *existingPokemon = searchPokemonBFS(owner->pokedexRoot, id);

    if (existingPokemon) {
        printf("Pokemon with ID %d is already in the Pokedex. No changes made.\n", id);
        return;
    }

    // Step 3: Copy the Pokemon details from the pokedex array
    if (id < 0 || id > (int)(sizeof(pokedex) / sizeof(pokedex[0]))) {
        printf("Invalid Pokemon ID.\n");
        return;
    }

    // Get the Pokemon details from the global pokedex array
    PokemonData newPokemonData = pokedex[id-1];
    
    // Step 4: Create a new Pokemon node and copy the values individually
    PokemonNode *newNode = (PokemonNode *)malloc(sizeof(PokemonNode));
    if (!newNode) {
        printf("Memory allocation failed for new Pokemon node.\n");
        return;
    }

    newNode->data = (PokemonData *)malloc(sizeof(PokemonData));
    if (!newNode->data) {
        printf("Memory allocation failed for Pokemon data.\n");
        free(newNode);
        return;
    }

    // Copying Pokemon details
    newNode->data->id = newPokemonData.id;
    newNode->data->name = myStrdup(newPokemonData.name);  // Assuming `myStrdup` is available to duplicate strings
    newNode->data->TYPE = newPokemonData.TYPE;
    newNode->data->hp = newPokemonData.hp;
    newNode->data->attack = newPokemonData.attack;
    newNode->data->CAN_EVOLVE = newPokemonData.CAN_EVOLVE;

    newNode->left = NULL;
    newNode->right = NULL;

    // Step 5: Insert the new Pokemon node into the BST
    owner->pokedexRoot = insertPokemonNode(owner->pokedexRoot, newNode);
    printf("Pokemon %s (ID %d) added.\n",newPokemonData.name, id);
}

/**
 * @brief Prompt for ID, remove that Pokemon from BST by ID.
 * @param owner pointer to the Owner
 * Why we made it: Another user function for releasing a Pokemon.
 */
void freePokemon(OwnerNode *owner) {

    if (!owner || !owner->pokedexRoot) {
        printf("No Pokemon to release.\n");
        return;
    }
    if (!owner) {
        printf("Invalid owner.\n");
        return;
    }

    // Step 1: Prompt user for Pokemon ID to remove
    int id;
    printf("Enter Pokemon ID to release: ");
    scanf("%d", &id);

    // Step 2: Search for the Pokemon in the BST using BFS
    PokemonNode *pokemonToRemove = searchPokemonBFS(owner->pokedexRoot, id);

    if (!pokemonToRemove) {
        printf("No Pokemon with ID %d found.\n", id);
        return;
    }
    printf("Removing Pokemon %s (ID %d).\n",pokedex[id-1].name, id);

    // Step 3: Remove the Pokemon node from the BST
    owner->pokedexRoot = removeNodeBST(owner->pokedexRoot, id);

    // Step 4: Free the memory allocated for the Pokemon
    // if (pokemonToRemove->data) {
    //     free(pokemonToRemove->data->name); // Freeing the dynamically allocated name string
    //     free(pokemonToRemove->data);       // Free the data structure itself
    // }
    // free(pokemonToRemove);               // Free the Pokemon node

    // printf("\nRemoving Pokemon %s (ID %d).\n", pokedex[id-1].name,id);
}
/**
 * @brief Show sub-menu to let user pick BFS, Pre, In, Post, or alphabetical.
 * @param owner pointer to Owner
 * Why we made it: We want a simple menu that picks from various traversals.
 */
void displayMenu(OwnerNode *owner) {
    if (!owner || !owner->pokedexRoot) {
        printf("Pokedex is empty.\n");
        return;
    }

    // Display traversal options
    printf("Display:\n");
    printf("1. BFS (Level-Order)\n");
    printf("2. Pre-Order\n");
    printf("3. In-Order\n");
    printf("4. Post-Order\n");
    printf("5. Alphabetical (by name)");

    // Prompt user to select a choice
    int choice = readIntSafe("\nYour choice: ");
    // printf("\n");

    // Perform the corresponding action based on user input
    switch (choice) {
        case 1:
            displayBFS(owner->pokedexRoot);  // Perform BFS traversal and display
            break;
        case 2:
            preOrderTraversal(owner->pokedexRoot);  // Perform Pre-order traversal and display
            break;
        case 3:
            inOrderTraversal(owner->pokedexRoot);  // Perform In-order traversal and display
            break;
        case 4:
            postOrderTraversal(owner->pokedexRoot);  // Perform Post-order traversal and display
            break;
        case 5:
            displayAlphabetical(owner->pokedexRoot);  // Display Pokemon sorted alphabetically by name
            break;
        default:
            printf("Invalid choice. Please try again.\n");
    }
}
/**
 * @brief Sort the circular owners list by name.
 * Why we made it: Another demonstration of pointer manipulation + sorting logic.
 */
// void sortOwners(void) {
//     if (ownerHead == NULL || ownerHead->next == ownerHead) {
//         // If the list is empty or has only one owner, no need to sort
//         return;
//     }

//     OwnerNode *current, *nextNode;
//     int swapped;

//     // Perform Bubble Sort on the circular linked list
//     do {
//         swapped = 0;
//         current = ownerHead;
//         do {
//             nextNode = current->next;
//             if (strcmp(current->ownerName, nextNode->ownerName) > 0) {
//                 // Swap the owner names if they are in the wrong order
//                 char *temp = current->ownerName;
//                 current->ownerName = nextNode->ownerName;
//                 nextNode->ownerName = temp;

//                 swapped = 1;
//             }
//             current = nextNode;
//         } while (current != ownerHead);  // Go around the circular list once
//     } while (swapped);  // Repeat if a swap was made in the previous pass
// }

void swapOwners(OwnerNode *a, OwnerNode *b) {
    char tempName[50];
    PokemonNode *tempPokedex;

    // Swap owner names
    strcpy(tempName, a->ownerName);
    strcpy(a->ownerName, b->ownerName);
    strcpy(b->ownerName, tempName);

    // Swap Pokedex pointers
    tempPokedex = a->pokedexRoot;
    a->pokedexRoot = b->pokedexRoot;
    b->pokedexRoot = tempPokedex;
}

void sortOwners(void) {
    if (ownerHead == NULL || ownerHead->next == ownerHead) {
        printf("0 or 1 owners only => no need to sort.\n");
        return;
    }

    int swapped;
    OwnerNode *ptr;
    OwnerNode *last = NULL; // Marks the last sorted node

    // printf("Sorting owners by name...\n");

    do {
        swapped = 0;
        ptr = ownerHead;

        // Traverse the circular linked list
        while (ptr->next != last && ptr->next != ownerHead) {
            if (strcmp(ptr->ownerName, ptr->next->ownerName) > 0) {
                swapOwners(ptr, ptr->next);
                swapped = 1;
            }
            ptr = ptr->next;
        }
        last = ptr; // Reduce the sorting range in each iteration
    } while (swapped);

    printf("Owners sorted by name.\n");
}
/**
 * @brief Helper to swap name & pokedexRoot in two OwnerNode.
 * @param a pointer to first owner
 * @param b pointer to second owner
 * Why we made it: Used internally by bubble sort to swap data.
 */
void swapOwnerData(OwnerNode *a, OwnerNode *b) {
    if (a == NULL || b == NULL) {
        return; // Ensure pointers are not NULL
    }

    // Swap the owner names
    char *tempName = a->ownerName;
    a->ownerName = b->ownerName;
    b->ownerName = tempName;

    // Swap the pokedexRoot pointers
    PokemonNode *tempPokedex = a->pokedexRoot;
    a->pokedexRoot = b->pokedexRoot;
    b->pokedexRoot = tempPokedex;
}
/**
 * @brief Insert a new owner into the circular list. If none exist, it's alone.
 * @param newOwner pointer to newly created OwnerNode
 * Why we made it: We need a standard approach to keep the list circular.
 */
void linkOwnerInCircularList(OwnerNode *newOwner) {
    if (newOwner == NULL) {
        return; // Ensure the new owner is not NULL
    }

    // Case 1: If the list is empty, newOwner is the only owner
    if (ownerHead == NULL) {
        ownerHead = newOwner;
        newOwner->next = newOwner; // Points to itself (circular)
        newOwner->prev = newOwner; // Points to itself (circular)
    } else {
        // Case 2: If the list already has owners
        OwnerNode *lastOwner = ownerHead->prev;

        // Insert newOwner after the last owner and before the head
        newOwner->next = ownerHead;
        newOwner->prev = lastOwner;
        lastOwner->next = newOwner;
        ownerHead->prev = newOwner;
    }
}
/**
 * @brief Remove a specific OwnerNode from the circular list, possibly updating head.
 * @param target pointer to the OwnerNode
 * Why we made it: Deleting or merging owners requires removing them from the ring.
 */
void removeOwnerFromCircularList(OwnerNode *target) {
    if (target == NULL || ownerHead == NULL) {
        return; // Nothing to remove if target or the list is empty
    }

    // Case 1: Only one owner in the circular list
    if (ownerHead == target && ownerHead->next == ownerHead) {
        ownerHead = NULL; // List becomes empty
        free(target);     // Free the target node
        return;
    }

    // Case 2: More than one owner in the circular list
    // If target is the head, move the head to the next owner
    if (ownerHead == target) {
        ownerHead = target->next;
    }

    // Update the previous owner's next and the next owner's prev pointers
    target->prev->next = target->next;
    target->next->prev = target->prev;

    // Free the target node
    freeOwnerNode(target);
}
/**
 * @brief Find an owner by name in the circular list.
 * @param name string to match
 * @return pointer to the matching OwnerNode or NULL
 * Why we made it: We often need to locate an owner quickly.
 */
OwnerNode *findOwnerByName(const char *name) {
    if (ownerHead == NULL) {
        return NULL; // No owners in the list
    }

    OwnerNode *current = ownerHead;
    do {
        // Compare the current owner's name with the given name
        if (strcmp(current->ownerName, name) == 0) {
            return current; // Owner found
        }
        current = current->next; // Move to the next owner
    } while (current != ownerHead); // Loop until we reach the head again

    return NULL; // No owner found with the given name
}
/**
 * @brief Creates a new Pokedex (prompt for name, check uniqueness, choose pokedexRoot).
 * Why we made it: The main entry for building a brand-new Pokedex.
 */
void openPokedexMenu(void) {
    char ownerName[100];

    // Prompt user for the new owner's name
    printf("Your name: ");
    // scanf("%s", ownerName);
    fgets(ownerName, sizeof(ownerName), stdin);
    
    // Trim any leading or trailing spaces from the name
    trimWhitespace(ownerName);
    
    // Check for uniqueness (i.e., the name must not already exist in the list of owners)
    OwnerNode *current = ownerHead;
    while (current != NULL) {
        // printf("start");
        if (strcmp(current->ownerName, ownerName) == 0) {
            printf("Owner '%s' already exists. Not creating a new Pokedex.\n",ownerName);
            return;
        }
        current = current->next;
        if (current == ownerHead)
        {
            break;
        }
    }

    printf("Choose Starter:\n");
    printf("1. Bulbasaur\n");
    printf("2. Charmander\n");
    printf("3. Squirtle");
    // printf("Your choice: ");
    int starterChoice=readIntSafe("\nYour choice: ");
    int send_choice=0;
    if (starterChoice==1){send_choice=1;}
    if (starterChoice==2){send_choice=4;}
    if (starterChoice==3){send_choice=7;}

    PokemonData data = pokedex[send_choice-1];

    PokemonNode *pokedexRoot = createPokemonNode(&data);

    OwnerNode *newOwner=createOwner(ownerName,pokedexRoot);

    // Add the pokedexRoot Pokemon to the new owner's Pokedex
    // newOwner->pokedexRoot = insertPokemonNode(newOwner->pokedexRoot, pokedexRoot);
    
    // Link the new owner in the circular list
    linkOwnerInCircularList(newOwner);
    printf("New Pokedex created for %s with starter %s.\n", ownerName, (starterChoice == 1) ? "Bulbasaur" : (starterChoice == 2) ? "Charmander" : "Squirtle");


  }
/**
 * @brief Delete an entire Pokedex (owner) from the list.
 * Why we made it: Let user pick which Pokedex to remove and free everything.
 */
void deletePokedex(void) {

        OwnerNode *cur = ownerHead;
    int index = 1;
    int choice;
    if (cur == NULL) {
        printf("No existing Pokedexes to delete.\n");
        return;
    }
    // List all owners with numbers
    printf("\n=== Delete a Pokedex ===");
   

    OwnerNode *temp = cur;
    do {
        printf("\n%d. %s", index++, temp->ownerName);
        temp = temp->next;
    } while (temp != ownerHead); // Stop when we circle back to the head

    // Ask for the owner to select
    printf("\nChoose a Pokedex to delete by number: ");
    choice = readIntSafe(""); // Read integer input safely


        cur = ownerHead;
    index = 1;
    while (cur != NULL) {
        if (index == choice) {
            break;  // We found the matching owner
        }
        cur = cur->next;
        index++;
    }

    // If the choice was invalid, return
    if (cur == NULL) {
        printf("Invalid choice. Returning to main menu.\n");
        return;
        }
    printf("Deleting %s's entire Pokedex...", cur->ownerName);
    printf("\nPokedex deleted.\n");

    freeOwnerNode(cur);

}


void collectPokemonIDs(PokemonNode *root, NodeArray *nodeArray) {
    if (!root) {
        return;
    }

    // Traverse left subtree
    collectPokemonIDs(root->left, nodeArray);

    // If array is full, resize it (doubling capacity)
    if (nodeArray->size == nodeArray->capacity) {
        nodeArray->capacity *= 2;
        nodeArray->nodes = (PokemonNode **)realloc(nodeArray->nodes, sizeof(PokemonNode *) * nodeArray->capacity);
    }

    // Add the current node's Pokemon ID to the array
    nodeArray->nodes[nodeArray->size++] = root;

    // Traverse right subtree
    collectPokemonIDs(root->right, nodeArray);
}

int *getPokemonIDsForOwner(OwnerNode *owner, int *numIDs) {
    if (!owner || !owner->pokedexRoot) {
        *numIDs = 0;
        return NULL;
    }

    // Initialize NodeArray
    NodeArray nodeArray;
    nodeArray.nodes = (PokemonNode **)malloc(sizeof(PokemonNode *) * 10);  // initial capacity of 10
    nodeArray.size = 0;
    nodeArray.capacity = 10;

    // Collect all Pokemon nodes in the owner's Pokedex
    collectPokemonIDs(owner->pokedexRoot, &nodeArray);

    // Allocate an array of IDs to return
    int *pokemonIDs = (int *)malloc(sizeof(int) * nodeArray.size);

    // Fill the pokemonIDs array with Pokemon IDs
    for (int i = 0; i < nodeArray.size; i++) {
        pokemonIDs[i] = nodeArray.nodes[i]->data->id;
    }

    // Set the number of IDs
    *numIDs = nodeArray.size;

    // Free temporary nodeArray (not needed anymore)
    free(nodeArray.nodes);

    return pokemonIDs;
}



void mergePokemon(OwnerNode *owner,int id) {
    if (!owner) {
        printf("Invalid owner.\n");
        return;
    }

    // Step 2: Check if the Pokemon already exists in the BST
    PokemonNode *existingPokemon = searchPokemonBFS(owner->pokedexRoot, id);

    if (existingPokemon) {
        // printf("Pokemon with ID %d already exists in your Pokedex.\n", id);
        return;
    }

    // Step 3: Copy the Pokemon details from the pokedex array
    if (id < 0 || id > (int)(sizeof(pokedex) / sizeof(pokedex[0]))) {
        // printf("Invalid Pokemon ID.\n");
        return;
    }

    // Get the Pokemon details from the global pokedex array
    PokemonData newPokemonData = pokedex[id-1];
    
    // Step 4: Create a new Pokemon node and copy the values individually
    PokemonNode *newNode = (PokemonNode *)malloc(sizeof(PokemonNode));
    if (!newNode) {
        // printf("Memory allocation failed for new Pokemon node.\n");
        return;
    }

    newNode->data = (PokemonData *)malloc(sizeof(PokemonData));
    if (!newNode->data) {
        printf("Memory allocation failed for Pokemon data.\n");
        free(newNode);
        return;
    }

    // Copying Pokemon details
    newNode->data->id = newPokemonData.id;
    newNode->data->name = myStrdup(newPokemonData.name);  // Assuming `myStrdup` is available to duplicate strings
    newNode->data->TYPE = newPokemonData.TYPE;
    newNode->data->hp = newPokemonData.hp;
    newNode->data->attack = newPokemonData.attack;
    newNode->data->CAN_EVOLVE = newPokemonData.CAN_EVOLVE;

    newNode->left = NULL;
    newNode->right = NULL;

    // Step 5: Insert the new Pokemon node into the BST
    owner->pokedexRoot = insertPokemonNode(owner->pokedexRoot, newNode);
    // printf("Pokemon with ID %d has been added to your Pokedex!\n", id);
}
/**
 * @brief Merge two Pokedexes: copy Pokemon from the second owner's Pokedex to the first.
 * @param owner1 pointer to the first owner
 * @param owner2 pointer to the second owner
 * Why we made it: Combines the Pokedexes into one.
 */

 void mergePokedex(OwnerNode *owner1, OwnerNode *owner2) {
    if (!owner1 || !owner2) {
        printf("Error: One or both owners are invalid.\n");
        return;
    }

    // Get the list of Pokemon IDs from owner2's Pokedex
    int numPokemonsOwner2 = 0;
    int *pokemonIDsOwner2 = getPokemonIDsForOwner(owner2, &numPokemonsOwner2);

    if (!pokemonIDsOwner2) {
        // printf("No Pokemon found in %s's Pokedex.\n", owner2->ownerName);
        return;
    }

    // Step 1: Loop through each Pokemon ID from owner2
    for (int i = 0; i < numPokemonsOwner2; i++) {
        int pokemonID = pokemonIDsOwner2[i];

        // Step 2: Add the Pokemon from owner2 to owner1 (if not already present)
        mergePokemon(owner1, pokemonID);
    }
    printf("Merging %s and %s...", owner1->ownerName, owner2->ownerName);
    printf("\nMerge completed.");
    printf("\nOwner '%s' has been removed after merging.\n", owner2->ownerName);

    // Free the memory for the Pokemon IDs array
    free(pokemonIDsOwner2);
}

/**
 * @brief Merge the second owner's Pokedex into the first, then remove the second owner.
 * Why we made it: BFS copy demonstration plus removing an owner.
 */
void mergePokedexMenu(void) {

    OwnerNode *cur = ownerHead;
    if (cur == NULL) {
        printf("Not enough owners to merge.\n");
        return;
    }
    char ownerName1[100], ownerName2[100];
    OwnerNode *owner1 = NULL, *owner2 = NULL;
    OwnerNode *current = ownerHead;
    printf("\n=== Merge Pokedexes ===\n");

    // Prompt user for the two owners' names to merge their Pokedexes
    printf("Enter name of first owner: ");
    // scanf("%s", ownerName1);
    fgets(ownerName1, sizeof(ownerName1), stdin);
    trimWhitespace(ownerName1);
    printf("Enter name of second owner: ");
    fgets(ownerName2, sizeof(ownerName2), stdin);
    trimWhitespace(ownerName2);
    // scanf("%s", ownerName2);

    // Search for the first owner
    while (current != NULL) {
        if (strcmp(current->ownerName, ownerName1) == 0) {
            owner1 = current;
            break;
        }
        current = current->next;
        if (current==ownerHead)
        {break;}
    }

    // Search for the second owner
    current = ownerHead;
    while (current != NULL) {
        if (strcmp(current->ownerName, ownerName2) == 0) {
            owner2 = current;
            break;
        }
        current = current->next;
        if (current==ownerHead)
        {break;}
    }

        // Prevent merging an owner with themselves
    if (owner1 == owner2) {
        printf("Error: You cannot merge a Pokedex with itself!\n");
        return;
    }

    // Check if both owners were found
    if (owner1 == NULL) {
        printf("Error: No owner found with the name '%s'!\n", ownerName1);
        return;
    }
    if (owner2 == NULL) {
        printf("Error: No owner found with the name '%s'!\n", ownerName2);
        return;
    }

    // Merge owner2's Pokedex into owner1's Pokedex
    mergePokedex(owner1, owner2);

    // Remove owner2 from the circular list
    freeOwnerNode(owner2);


}

/**
 * @brief Print owners left or right from head, repeating as many times as user wants.
 * Why we made it: Demonstrates stepping through a circular list in a chosen direction.
 */

 int checkForwBack(const char* input) {
    // Check if input is "f" or "F"
    if (strcmp(input, "f") == 0 || strcmp(input, "F") == 0) {
        return 1;
    }
    // Check if input is "b" or "B"
    else if (strcmp(input, "b") == 0 || strcmp(input, "B") == 0) {
        return 0;
    }
    // Otherwise, return -1
    else {
        return -1;
    }
}
void printOwners(int times, int forward) {
    if (ownerHead == NULL) {
        printf("No owners available.\n");
        return;
    }

    OwnerNode *temp = ownerHead;
    // if (!forward) {
    //     temp = ownerListHead->prev; // Start from the last owner if reverse
    // }

    for (int i = 0; i < times; ++i) {
        printf("[%d] %s\n",(i+1) ,temp->ownerName);
        temp = (forward) ? temp->next : temp->prev;
    }
}

 void printOwnersCircular(void) {

    OwnerNode *cur = ownerHead;
    if (cur == NULL) {
        printf("No owners.\n");
        return;
    }
    int times, forward;
    printf("Enter direction (F or B): ");

    char input[10];
//     char tt[10];
// //   fgets(tt, sizeof(tt), stdin);

//     fgets(input, sizeof(input), stdin);
    scanf("%s", input);


    // fgets(input, sizeof(input), stdin);
    // input[strcspn(input, "\n")] = '\0';
    forward = checkForwBack(input);

    // scanf("%d", &forward);

    if (forward==-1){printf("Invalid Choice");return;}

    // printf("Enter number of times to print owners: ");
    times=readIntSafe("How many prints? ");
    // scanf("%d", &times);

    // scanf("%d", &forward);
    printOwners(times, forward);
}


/**
 * @brief Frees every remaining owner in the circular list, setting ownerHead = NULL.
 * Why we made it: Ensures a squeaky-clean exit with no leftover memory.
 */




// --------------------------------------------------------------
// Sub-menu for existing Pokedex
// --------------------------------------------------------------
void enterExistingPokedexMenu() {
    OwnerNode *cur = ownerHead;
    int index = 1;
    int choice;
    if (cur == NULL) {
        printf("No existing Pokedexes.\n");
        return;
    }
    // List all owners with numbers
    printf("\nExisting Pokedexes:");
    
    
    // Iterate over the circular list and print the owners with numbers
    OwnerNode *temp = cur;
    do {
        printf("\n%d. %s", index++, temp->ownerName);
        temp = temp->next;
    } while (temp != ownerHead); // Stop when we circle back to the head

    // Ask for the owner to select
    printf("\nChoose a Pokedex by number: ");
    choice = readIntSafe(""); // Read integer input safely

    // Find the corresponding owner based on the choice
    cur = ownerHead;
    index = 1;
    while (cur != NULL) {
        if (index == choice) {
            break;  // We found the matching owner
        }
        cur = cur->next;
        index++;
    }

    // If the choice was invalid, return
    if (cur == NULL) {
        printf("Invalid choice. Returning to main menu.\n");
        return;
    }

    // Now, enter the selected owner's Pokedex menu
    printf("\nEntering %s's Pokedex...\n", cur->ownerName);
    

    int subChoice;
    do {
        printf("-- %s's Pokedex Menu --\n", cur->ownerName);
        printf("1. Add Pokemon\n");
        printf("2. Display Pokedex\n");
        printf("3. Release Pokemon (by ID)\n");
        printf("4. Pokemon Fight!\n");
        printf("5. Evolve Pokemon\n");
        printf("6. Back to Main");

        subChoice = readIntSafe("\nYour choice: ");

        switch (subChoice) {
        case 1:
            addPokemon(cur);
            break;
        case 2:
            displayMenu(cur);
            break;
        case 3:
            freePokemon(cur);
            break;
        case 4:
            pokemonFight(cur);
            break;
        case 5:
            evolvePokemon(cur);
            break;
        case 6:
            printf("Back to Main Menu.\n");
            break;
        default:
            printf("Invalid choice.\n");
        }
    } while (subChoice != 6);
}
void exit_program()
{
 printf("Goodbye!");
freeAllOwners();
}

// --------------------------------------------------------------
// Main Menu
// --------------------------------------------------------------
void mainMenu()
{
    int choice;
    do
    {
        printf("=== Main Menu ===\n");
        printf("1. New Pokedex\n");
        printf("2. Existing Pokedex\n");
        printf("3. Delete a Pokedex\n");
        printf("4. Merge Pokedexes\n");
        printf("5. Sort Owners by Name\n");
        printf("6. Print Owners in a direction X times\n");
        printf("7. Exit");
        choice = readIntSafe("\nYour choice: ");

        switch (choice)
        {
        case 1:
            openPokedexMenu();
            break;
        case 2:
            enterExistingPokedexMenu();
            break;
        case 3:
            deletePokedex();
            break;
        case 4:
            mergePokedexMenu();
            break;
        case 5:
            sortOwners();
            break;
        case 6:
            // printf("\n");
            printOwnersCircular();
            break;
        case 7:
            exit_program();
           
            break;
        default:
            printf("Invalid.\n");
        }
    } while (choice != 7);
}

int main()
{
    mainMenu();
    // freeAllOwners();
    return 0;
}

