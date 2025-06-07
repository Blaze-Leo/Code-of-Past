#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_NAME_LEN 20
#define MAX_POKEMONS 151


typedef enum {
    BULBASAUR = 1,
    CHARMANDER = 4,
    SQUIRTLE = 7
} StarterPokemon;

typedef enum {
    CAN_EVOLVE = 1,
    CANNOT_EVOLVE = 0
} EvolutionStatus;

typedef enum { GRASS, FIRE, WATER, BUG, NORMAL, 
    POISON, ELECTRIC, GROUND, FAIRY, 
    FIGHTING, PSYCHIC, ROCK, GHOST, DRAGON,
    ICE } PokemonType;

typedef struct {
    int id;
    char name[40];
    PokemonType type;
    int baseHP;
    int baseAttack;
    EvolutionStatus evolve;  // Add this field to represent the evolution status
} PokemonData;

// Array of Pokemon data
static const PokemonData pokedex[] = {
    {1, "Bulbasaur", GRASS, 45, 49, CAN_EVOLVE},
    {2, "Ivysaur", GRASS, 60, 62, CAN_EVOLVE},
    {3, "Venusaur", GRASS, 80, 82, CANNOT_EVOLVE},
    {4, "Charmander", FIRE, 39, 52, CAN_EVOLVE},
    {5, "Charmeleon", FIRE, 58, 64, CAN_EVOLVE},
    {6, "Charizard", FIRE, 78, 84, CANNOT_EVOLVE},
    {7, "Squirtle", WATER, 44, 48, CAN_EVOLVE},
    {8, "Wartortle", WATER, 59, 63, CAN_EVOLVE},
    {9, "Blastoise", WATER, 79, 83, CANNOT_EVOLVE},
    {10, "Caterpie", BUG, 45, 30, CAN_EVOLVE},
    {11, "Metapod", BUG, 50, 20, CAN_EVOLVE},
    {12, "Butterfree", BUG, 60, 45, CANNOT_EVOLVE},
    {13, "Weedle", BUG, 40, 35, CAN_EVOLVE},
    {14, "Kakuna", BUG, 45, 25, CAN_EVOLVE},
    {15, "Beedrill", BUG, 65, 90, CANNOT_EVOLVE},
    {16, "Pidgey", NORMAL, 40, 45, CAN_EVOLVE},
    {17, "Pidgeotto", NORMAL, 63, 60, CAN_EVOLVE},
    {18, "Pidgeot", NORMAL, 83, 80, CANNOT_EVOLVE},
    {19, "Rattata", NORMAL, 30, 56, CAN_EVOLVE},
    {20, "Raticate", NORMAL, 55, 81, CANNOT_EVOLVE},
    {21, "Spearow", NORMAL, 40, 60, CAN_EVOLVE},
    {22, "Fearow", NORMAL, 65, 90, CANNOT_EVOLVE},
    {23, "Ekans", POISON, 35, 60, CAN_EVOLVE},
    {24, "Arbok", POISON, 60, 85, CANNOT_EVOLVE},
    {25, "Pikachu", ELECTRIC, 35, 55, CAN_EVOLVE},
    {26, "Raichu", ELECTRIC, 60, 90, CANNOT_EVOLVE},
    {27, "Sandshrew", GROUND, 50, 75, CAN_EVOLVE},
    {28, "Sandslash", GROUND, 75, 100, CANNOT_EVOLVE},
    {29, "NidoranF", POISON, 55, 47, CAN_EVOLVE},
    {30, "Nidorina", POISON, 70, 62, CAN_EVOLVE},
    {31, "Nidoqueen", POISON, 90, 92, CANNOT_EVOLVE},
    {32, "NidoranM", POISON, 46, 57, CAN_EVOLVE},
    {33, "Nidorino", POISON, 61, 72, CAN_EVOLVE},
    {34, "Nidoking", POISON, 81, 102, CANNOT_EVOLVE},
    {35, "Clefairy", FAIRY, 70, 45, CAN_EVOLVE},
    {36, "Clefable", FAIRY, 95, 70, CANNOT_EVOLVE},
    {37, "Vulpix", FIRE, 38, 41, CAN_EVOLVE},
    {38, "Ninetales", FIRE, 73, 76, CANNOT_EVOLVE},
    {39, "Jigglypuff", NORMAL, 115, 45, CAN_EVOLVE},
    {40, "Wigglytuff", NORMAL, 140, 70, CANNOT_EVOLVE},
    {41, "Zubat", POISON, 40, 45, CAN_EVOLVE},
    {42, "Golbat", POISON, 75, 80, CAN_EVOLVE},
    {43, "Oddish", GRASS, 45, 50, CAN_EVOLVE},
    {44, "Gloom", GRASS, 60, 65, CAN_EVOLVE},
    {45, "Vileplume", GRASS, 75, 80, CANNOT_EVOLVE},
    {46, "Paras", BUG, 35, 70, CAN_EVOLVE},
    {47, "Parasect", BUG, 60, 95, CANNOT_EVOLVE},
    {48, "Venonat", BUG, 60, 55, CAN_EVOLVE},
    {49, "Venomoth", BUG, 70, 65, CANNOT_EVOLVE},
    {50, "Diglett", GROUND, 10, 55, CAN_EVOLVE},
    {51, "Dugtrio", GROUND, 35, 80, CANNOT_EVOLVE},
    {52, "Meowth", NORMAL, 40, 45, CAN_EVOLVE},
    {53, "Persian", NORMAL, 65, 70, CANNOT_EVOLVE},
    {54, "Psyduck", WATER, 50, 52, CAN_EVOLVE},
    {55, "Golduck", WATER, 80, 82, CANNOT_EVOLVE},
    {56, "Mankey", FIGHTING, 40, 80, CAN_EVOLVE},
    {57, "Primeape", FIGHTING, 65, 105, CANNOT_EVOLVE},
    {58, "Growlithe", FIRE, 55, 70, CAN_EVOLVE},
    {59, "Arcanine", FIRE, 90, 110, CANNOT_EVOLVE},
    {60, "Poliwag", WATER, 40, 50, CAN_EVOLVE},
    {61, "Poliwhirl", WATER, 65, 65, CAN_EVOLVE},
    {62, "Poliwrath", WATER, 90, 95, CANNOT_EVOLVE},
    {63, "Abra", PSYCHIC, 25, 20, CAN_EVOLVE},
    {64, "Kadabra", PSYCHIC, 40, 35, CAN_EVOLVE},
    {65, "Alakazam", PSYCHIC, 55, 50, CANNOT_EVOLVE},
    {66, "Machop", FIGHTING, 70, 80, CAN_EVOLVE},
    {67, "Machoke", FIGHTING, 80, 100, CAN_EVOLVE},
    {68, "Machamp", FIGHTING, 90, 130, CANNOT_EVOLVE},
    {69, "Bellsprout", GRASS, 50, 75, CAN_EVOLVE},
    {70, "Weepinbell", GRASS, 65, 90, CAN_EVOLVE},
    {71, "Victreebel", GRASS, 80, 105, CANNOT_EVOLVE},
    {72, "Tentacool", WATER, 40, 40, CAN_EVOLVE},
    {73, "Tentacruel", WATER, 80, 70, CANNOT_EVOLVE},
    {74, "Geodude", ROCK, 40, 80, CAN_EVOLVE},
    {75, "Graveler", ROCK, 55, 95, CAN_EVOLVE},
    {76, "Golem", ROCK, 80, 120, CANNOT_EVOLVE},
    {77, "Ponyta", FIRE, 50, 85, CAN_EVOLVE},
    {78, "Rapidash", FIRE, 65, 100, CANNOT_EVOLVE},
    {79, "Slowpoke", WATER, 90, 65, CAN_EVOLVE},
    {80, "Slowbro", WATER, 95, 75, CANNOT_EVOLVE},
    {81, "Magnemite", ELECTRIC, 25, 35, CAN_EVOLVE},
    {82, "Magneton", ELECTRIC, 50, 60, CANNOT_EVOLVE},
    {83, "Farfetch'd", NORMAL, 52, 65, CANNOT_EVOLVE},
    {84, "Doduo", NORMAL, 35, 85, CAN_EVOLVE},
    {85, "Dodrio", NORMAL, 60, 110, CANNOT_EVOLVE},
    {86, "Seel", WATER, 65, 45, CAN_EVOLVE},
    {87, "Dewgong", WATER, 90, 70, CANNOT_EVOLVE},
    {88, "Grimer", POISON, 80, 80, CAN_EVOLVE},
    {89, "Muk", POISON, 105, 105, CANNOT_EVOLVE},
    {90, "Shellder", WATER, 30, 65, CAN_EVOLVE},
    {91, "Cloyster", WATER, 50, 95, CANNOT_EVOLVE},
    {92, "Gastly", GHOST, 30, 35, CAN_EVOLVE},
    {93, "Haunter", GHOST, 45, 50, CAN_EVOLVE},
    {94, "Gengar", GHOST, 60, 65, CANNOT_EVOLVE},
    {95, "Onix", ROCK, 35, 45, CANNOT_EVOLVE},
    {96, "Drowzee", PSYCHIC, 60, 48, CAN_EVOLVE},
    {97, "Hypno", PSYCHIC, 85, 73, CANNOT_EVOLVE},
    {98, "Krabby", WATER, 30, 105, CAN_EVOLVE},
    {99, "Kingler", WATER, 55, 130, CANNOT_EVOLVE},
    {100, "Voltorb", ELECTRIC, 40, 30, CAN_EVOLVE},
    {101, "Electrode", ELECTRIC, 60, 50, CANNOT_EVOLVE},
    {102, "Exeggcute", GRASS, 60, 40, CAN_EVOLVE},
    {103, "Exeggutor", GRASS, 95, 95, CANNOT_EVOLVE},
    {104, "Cubone", GROUND, 50, 50, CAN_EVOLVE},
    {105, "Marowak", GROUND, 60, 80, CANNOT_EVOLVE},
    {106, "Hitmonlee", FIGHTING, 50, 120, CANNOT_EVOLVE},
    {107, "Hitmonchan", FIGHTING, 50, 105, CANNOT_EVOLVE},
    {108, "Lickitung", NORMAL, 90, 55, CANNOT_EVOLVE},
    {109, "Koffing", POISON, 40, 65, CAN_EVOLVE},
    {110, "Weezing", POISON, 65, 90, CANNOT_EVOLVE},
    {111, "Rhyhorn", GROUND, 80, 85, CAN_EVOLVE},
    {112, "Rhydon", GROUND, 105, 130, CANNOT_EVOLVE},
    {113, "Chansey", NORMAL, 250, 5, CANNOT_EVOLVE},
    {114, "Tangela", GRASS, 65, 55, CANNOT_EVOLVE},
    {115, "Kangaskhan", NORMAL, 105, 95, CANNOT_EVOLVE},
    {116, "Horsea", WATER, 30, 40, CAN_EVOLVE},
    {117, "Seadra", WATER, 55, 65, CANNOT_EVOLVE},
    {118, "Goldeen", WATER, 45, 67, CAN_EVOLVE},
    {119, "Seaking", WATER, 80, 92, CANNOT_EVOLVE},
    {120, "Staryu", WATER, 30, 45, CAN_EVOLVE},
    {121, "Starmie", WATER, 60, 75, CANNOT_EVOLVE},
    {122, "Mr. Mime", PSYCHIC, 40, 45, CANNOT_EVOLVE},
    {123, "Scyther", BUG, 70, 110, CANNOT_EVOLVE},
    {124, "Jynx", ICE, 65, 50, CANNOT_EVOLVE},
    {125, "Electabuzz", ELECTRIC, 65, 83, CANNOT_EVOLVE},
    {126, "Magmar", FIRE, 65, 95, CANNOT_EVOLVE},
    {127, "Pinsir", BUG, 65, 125, CANNOT_EVOLVE},
    {128, "Tauros", NORMAL, 75, 100, CANNOT_EVOLVE},
    {129, "Magikarp", WATER, 20, 10, CAN_EVOLVE},
    {130, "Gyarados", WATER, 95, 125, CANNOT_EVOLVE},
    {131, "Lapras", WATER, 130, 85, CANNOT_EVOLVE},
    {132, "Ditto", NORMAL, 48, 48, CANNOT_EVOLVE},
    {133, "Eevee", NORMAL, 55, 55, CAN_EVOLVE},
    {134, "Vaporeon", WATER, 130, 65, CANNOT_EVOLVE},
    {135, "Jolteon", ELECTRIC, 65, 65, CANNOT_EVOLVE},
    {136, "Flareon", FIRE, 65, 130, CANNOT_EVOLVE},
    {137, "Porygon", NORMAL, 65, 60, CANNOT_EVOLVE},
    {138, "Omanyte", ROCK, 35, 40, CAN_EVOLVE},
    {139, "Omastar", ROCK, 70, 60, CANNOT_EVOLVE},
    {140, "Kabuto", ROCK, 30, 80, CAN_EVOLVE},
    {141, "Kabutops", ROCK, 60, 115, CANNOT_EVOLVE},
    {142, "Aerodactyl", ROCK, 80, 105, CANNOT_EVOLVE},
    {143, "Snorlax", NORMAL, 160, 110, CANNOT_EVOLVE},
    {144, "Articuno", ICE, 90, 85, CANNOT_EVOLVE},
    {145, "Zapdos", ELECTRIC, 90, 90, CANNOT_EVOLVE},
    {146, "Moltres", FIRE, 90, 100, CANNOT_EVOLVE},
    {147, "Dratini", DRAGON, 41, 64, CAN_EVOLVE},
    {148, "Dragonair", DRAGON, 61, 84, CAN_EVOLVE},
    {149, "Dragonite", DRAGON, 91, 134, CANNOT_EVOLVE},
    {150, "Mewtwo", PSYCHIC, 106, 110, CANNOT_EVOLVE},
    {151, "Mew", PSYCHIC, 100, 100, CANNOT_EVOLVE}};





typedef struct PokemonNode {
    PokemonData *data;
    struct PokemonNode *next;
} PokemonNode;

typedef struct OwnerNode {
    char ownerName[MAX_NAME_LEN];
    PokemonNode *pokedexRoot;
    struct OwnerNode *next;
    struct OwnerNode *prev;
} OwnerNode;

OwnerNode *ownerListHead = NULL;

// void freePokemon(PokemonNode *node);
// void freeAllPokemon(PokemonNode *node) {
//     if (node == NULL) return;
//     freeAllPokemon(node->next);
//     free(node->data);
//     free(node);
// }

// PokemonNode *insertPokemonNode(PokemonNode *root, PokemonNode *newNode);
// PokemonNode *findPokemonByID(PokemonNode *root, int id);
// PokemonNode *createPokemon( int id);
// void deletePokemonByID(PokemonNode *root, int id);
// void printPokemon(PokemonNode *node);
// void evolvePokemon(PokemonNode **root);
// void deleteOwner(OwnerNode *owner);
// void mergePokedex(OwnerNode *owner1, OwnerNode *owner2);
// void sortOwnersByName();
void printOwners(int times, int forward);
void enterExistingPokedexMenu(OwnerNode *owner);
void freePokemonList(PokemonNode *head);



// Placeholder function prototypes for undefined functions
void sortOwnersMenu();
void deletePokedexMenu();
void mergePokedexMenu();
void printOwnersMenu();
void exitProgram();

// Main menu function
void printMainMenu() {
    printf("=== Main Menu ===\n");
    printf("1. New Pokedex\n");
    printf("2. Existing Pokedex\n");
    printf("3. Delete a Pokedex\n");
    printf("4. Merge Pokedexes\n");
    printf("5. Sort Owners by Name\n");
    printf("6. Print Owners in a direction X times\n");
    printf("7. Exit\n");
    printf("Your choice: ");
}
void printSortChoice() {
    printf("Display\n");
    printf("1. BFS (Level-Order)\n");
    printf("2. Pre-Order\n");
    printf("3. In-Order\n");
    printf("4. Post-Order\n");
    printf("5. Alphabetical (by name)\n");
    printf("Your choice: ");
}
int getValidatedInteger() {
    char input[20];  // Buffer to store input
    // printf("Enter a number: ");
    scanf("%19s", input);  // Read input as a string (avoid buffer overflow)

    // Validate if the input contains only digits
    for (int i = 0; i < strlen(input); i++) {
        if (!isdigit(input[i])) {
            // printf("Invalid input! Not a number.\n");
            return -1;  // Return -1 (or NULL if you prefer a pointer)
        }
    }

    // Convert valid string to integer
    return atoi(input);
}
// Existing Pokedex menu
void printExistingPokedexMenu() {
    if (ownerListHead == NULL) {
        printf("No existing pokedexes.\n");
        return;
    }

    printf("Existing Pokedexes:\n");

    OwnerNode *temp = ownerListHead;
    int index = 1;

    // Print the list of existing pokedexes
    do {
        printf("%d. %s\n", index++, temp->ownerName);
        temp = temp->next;
    } while (temp != ownerListHead);  // Stop when we come back to the head node

    printf("Choose a Pokedex by number: ");
    int owner_index;
    owner_index=getValidatedInteger();
    // scanf("%d",&owner_index);
    owner_index--;
    int counter=0;
    bool done=false;

    OwnerNode *temp2 = ownerListHead;
    do {
        if (owner_index==counter)
        {done=true;break;}
        counter++;
        temp2 = temp2->next;
    } while (temp2 != ownerListHead);

    if (done)
    {printf("Entering %s's Pokedex..",temp2->ownerName);
        enterExistingPokedexMenu(temp2);}
    else{
        printf("Invalid Choice.\n");
    }

}

// Pokedex management options
void printOwnerPokedexMenu(char *ownerName) {
    printf("-- %s's Pokedex Menu --\n", ownerName);
    printf("1. Add Pokemon\n");
    printf("2. Display Pokedex\n");
    printf("3. Release Pokemon (by ID)\n");
    printf("4. Pokemon Fight!\n");
    printf("5. Evolve Pokemon\n");
    printf("6. Back to Main\n");
    printf("Your choice: ");
}

// Create a new Pokemon node
PokemonNode *createPokemon( int id) {
    PokemonNode *newPokemon = (PokemonNode *)malloc(sizeof(PokemonNode));
    newPokemon->data = (PokemonData *)malloc(sizeof(PokemonData));
    
    newPokemon->data=&pokedex[id - 1];
    newPokemon->next = NULL;
    return newPokemon;
}
// Function to create a new Pokedex and assign a starter Pokemon
OwnerNode *createOwnerNode(char *name, int starter) {
    OwnerNode *newOwner = (OwnerNode *)malloc(sizeof(OwnerNode));
    if (newOwner == NULL) {
        printf("Memory allocation failed!\n");
        return NULL;
    }
    strncpy(newOwner->ownerName, name, MAX_NAME_LEN);
    newOwner->pokedexRoot = createPokemon(starter);  // Create starter Pokémon
    newOwner->next = newOwner;
    newOwner->prev = newOwner;

    if (ownerListHead == NULL) {
        ownerListHead = newOwner;
    } else {
        OwnerNode *lastOwner = ownerListHead->prev;
        lastOwner->next = newOwner;
        newOwner->prev = lastOwner;
        newOwner->next = ownerListHead;
        ownerListHead->prev = newOwner;
    }

    return newOwner;
}




const char* getPokemonNameById(int id) {
    // Size of the pokedex array
    int size = sizeof(pokedex) / sizeof(pokedex[0]);
    
    // Loop through the array to find the Pokémon with the matching id
    for (int i = 0; i < size; i++) {
        if (pokedex[i].id == id) {
            return pokedex[i].name;  // Return the name of the Pokémon
        }
    }
    
    return "Unknown Pokémon";  // Return a default string if id is not found
}

EvolutionStatus getEvolutionStatusById(int id) {
    // Size of the pokedex array
    int size = sizeof(pokedex) / sizeof(pokedex[0]);
    
    // Loop through the array to find the Pokémon with the matching id
    for (int i = 0; i < size; i++) {
        if (pokedex[i].id == id) {
            return pokedex[i].evolve;  // Return the evolution status
        }
    }
    
    // Return a default value (this can be changed based on the behavior you want)
    return CANNOT_EVOLVE;  // Default to CANNOT_EVOLVE if Pokémon ID is not found
}

int getHPById(int id) {
    // Size of the pokedex array
    int size = sizeof(pokedex) / sizeof(pokedex[0]);
    
    // Loop through the array to find the Pokémon with the matching id
    for (int i = 0; i < size; i++) {
        if (pokedex[i].id == id) {
            return pokedex[i].baseHP;  // Return the evolution status
        }
    }
    
    // Return a default value (this can be changed based on the behavior you want)
    return 0;  // Default to CANNOT_EVOLVE if Pokémon ID is not found
}

int getAttackById(int id) {
    // Size of the pokedex array
    int size = sizeof(pokedex) / sizeof(pokedex[0]);
    
    // Loop through the array to find the Pokémon with the matching id
    for (int i = 0; i < size; i++) {
        if (pokedex[i].id == id) {
            return pokedex[i].baseAttack;  // Return the evolution status
        }
    }
    
    // Return a default value (this can be changed based on the behavior you want)
    return 0;  // Default to CANNOT_EVOLVE if Pokémon ID is not found
}


// Add Pokemon to the Pokedex
void addPokemonToPokedex(PokemonNode **root,int temp_index) {
    
    int id;

    if (temp_index==0){
    
    printf("Enter ID to add: ");
    id=getValidatedInteger();}
    // scanf("%d", &id);}

    else{id=temp_index;}

    char name[MAX_NAME_LEN];
    strcpy(name, getPokemonNameById(id));
    EvolutionStatus status = getEvolutionStatusById(id);

    // Check if ID already exists in the Pokedex
    PokemonNode *temp = *root;
    while (temp != NULL) {
        if (temp->data->id == id) { // Check ID field in PokemonData
            printf("Pokemon with ID %d is already in the Pokedex. No changes made..\n", id);
            return;
        }
        temp = temp->next;
    }

    // Allocate memory for new PokemonNode
    PokemonNode *newPokemon = (PokemonNode *)malloc(sizeof(PokemonNode));
    if (!newPokemon) {
        printf("Memory allocation failed!\n");
        return;
    }

    // Allocate memory for PokemonData
    newPokemon->data = (PokemonData *)malloc(sizeof(PokemonData));
    if (!newPokemon->data) {
        printf("Memory allocation failed!\n");
        free(newPokemon);
        return;
    }


    // Assign values
    newPokemon->data = &pokedex[id-1];
    newPokemon->next = NULL;

    // Insert into the linked list
    if (*root == NULL) {
        *root = newPokemon;
    } else {
        temp = *root;
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = newPokemon;
    }

    printf("Pokemon %s (ID: %d) added to Pokedex.\n", name, id);
}



// Display the Pokedex
void displayPokedex(PokemonNode *root) {
    if (root == NULL) {
        printf("Pokedex is empty.\n");
        return;
    }

    PokemonNode *temp = root;
    while (temp != NULL) {
        printf("Pokemon ID: %d, Name: %s\n", temp->data->id, temp->data->name);
        temp = temp->next;
    }
}

// Release Pokemon by ID
void releasePokemonByID(PokemonNode **root, int id,bool display) {


    if (*root == NULL) {
        printf("No Pokemon to release.\n");
        return;
    }

    if (id==-1)
    {
        
        printf("Enter Pokemon ID to release: ");
        id=getValidatedInteger();
    }
    PokemonNode *temp = *root, *prev = NULL;

    // If the Pokemon to be deleted is the first node
    if (temp != NULL && temp->data->id == id) {
        *root = temp->next;  // Move the head to the next node
        free(temp);  // Only free the node itself, not the data
        if (display)
        {printf("Pokemon with ID %d has been released.\n", id);}
        return;
    }

    // Traverse the linked list
    while (temp != NULL && temp->data->id != id) {
        prev = temp;
        temp = temp->next;
    }

    // If the Pokemon was not found
    if (temp == NULL) {
        printf("Pokemon with ID %d not found.\n", id);
        return;
    }

    prev->next = temp->next;  // Remove the node from the list
    free(temp);  // Only free the node itself, not the data
    if (display)
    {printf("Pokemon with ID %d has been released.\n", id);}
}


// Simulate a Pokemon Fight
void pokemonFight(PokemonNode *root) {
    if (root == NULL) {
        printf("Pokedex is empty.\n");
        return;
    }

    int id1,id2;

    printf("\nEnter first ID:");
    id1=getValidatedInteger();
    // scanf("%d", &id1);

    printf("\nEnter second ID:");
    id2=getValidatedInteger();
    // scanf("%d", &id2);
    // printf("Fight between %s and %s!\n", root->data->name, root->next->data->name);
    bool is_there1=false;
    bool is_there2=false;
    while (root->next!=NULL)
    {
        //printf("%d",root->data->id);
        if (root->data->id==id1){is_there1=true;}
        if (root->data->id==id2){is_there2=true;}
        root=root->next;
    }
    if (root->data->id==id1){is_there1=true;}
    if (root->data->id==id2){is_there2=true;}

    if (!is_there1 || !is_there2){printf("One or more ids not found");return;}
    double value;

    int score1=1.5*getAttackById(id1)+1.2*getHPById(id1);
    int score2=1.5*getAttackById(id2)+1.2*getHPById(id2);

    printf("Pokemon 1 %s: Score (%d)",getPokemonNameById(id1),score1);
    printf("\nPokemon 2 %s: Score (%d)",getPokemonNameById(id2),score2);

    value=score1-score2;
    // Simple fight simulation: winner is the first one

    if(value==0){printf("\nIt's a tie!\n");return;}
    if (value>=0)
    {
        printf("\n%s wins!\n", getPokemonNameById(id1));
        return;
    }
    printf("\n%s wins!\n", getPokemonNameById(id2));
}




// Main evolvePokemon function
void evolvePokemon(PokemonNode **root) {
        PokemonNode *currentPokemon = *root;
        if (currentPokemon == NULL) {
        printf("No pokemon to evolve.\n");
        return;
    }
    int pokemonID;
    printf("Enter ID of Pokemon to evolve: ");
    pokemonID=getValidatedInteger();
    // scanf("%d", &pokemonID);


        // Traverse the owner's Pokedex

        //printf("hello");
        while (currentPokemon != NULL) {
            if (currentPokemon->data->id == pokemonID) {
                EvolutionStatus status=getEvolutionStatusById(pokemonID);
                if (status == CAN_EVOLVE) {
                    int evolvedID = pokemonID+1;
                    bool is_dup=false;
                    PokemonNode *temp = *root;
                    while (temp != NULL) {
                        if (temp->data->id == evolvedID) { // Check ID field in PokemonData
                            printf("Error: A Pokemon with ID %d is duplicate", evolvedID);
                            is_dup=true;
                            break;
                        }
                        temp = temp->next;
                    }
                    if (!is_dup)
                    {addPokemonToPokedex(root,evolvedID);}

                    releasePokemonByID(root,pokemonID,false);

                    

                } else {
                    printf("This Pokemon cannot evolve.\n");
                }
                return; // Exit after evolving the pokemon
            }
            currentPokemon = currentPokemon->next;
        }


    printf("No Pokemon with ID %d found.\n", pokemonID);
}






// Sort Owners alphabetically by name
// Function to swap two owner nodes' data
void swapOwners(OwnerNode *a, OwnerNode *b) {
    char tempName[MAX_NAME_LEN];
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

// Function to sort owners alphabetically
void sortOwnersMenu() {
    if (ownerListHead == NULL || ownerListHead->next == ownerListHead) {
        printf("Not enough owners to sort.\n");
        return;
    }

    int swapped;
    OwnerNode *ptr;
    OwnerNode *last = NULL; // Marks the last sorted node

    printf("Sorting owners by name...\n");

    do {
        swapped = 0;
        ptr = ownerListHead;

        // Traverse the circular linked list
        while (ptr->next != last && ptr->next != ownerListHead) {
            if (strcmp(ptr->ownerName, ptr->next->ownerName) > 0) {
                swapOwners(ptr, ptr->next);
                swapped = 1;
            }
            ptr = ptr->next;
        }
        last = ptr; // Reduce the sorting range in each iteration
    } while (swapped);

    printf("Owners sorted successfully!\n");
}

void deleteMerged(char *name) {


    OwnerNode *current = ownerListHead;

    // Search for the owner in the circular list
    do {
        if (strcmp(current->ownerName, name) == 0) {
            // If there's only one owner in the list
            if (current->next == current) {
                freePokemonList(current->pokedexRoot);  // Free all Pokémon in this Pokedex
                free(current);
                ownerListHead = NULL;
                // printf("Pokedex for %s has been deleted.\n", name);
                return;
            }

            // Adjust pointers for deletion
            current->prev->next = current->next;
            current->next->prev = current->prev;

            // Update head if needed
            if (current == ownerListHead) {
                ownerListHead = current->next;
            }

            freePokemonList(current->pokedexRoot);  // Free Pokémon linked list
            free(current);
            // printf("Pokedex for %s has been deleted.\n", name);
            return;
        }

        current = current->next;
    } while (current != ownerListHead);

    // If owner is not found
    printf("Owner %s not found in the Pokedex list.\n", name);
}

// Delete a Pokedex by owner name
void deletePokedexMenu() {

        if (ownerListHead == NULL) {
        printf("No existing pokedexes.\n");
        return;
    }

    printf("Deleting Pokedexes:\n");

    OwnerNode *temp = ownerListHead;
    int index = 1;

    // Print the list of existing pokedexes
    do {
        printf("%d. %s\n", index++, temp->ownerName);
        temp = temp->next;
    } while (temp != ownerListHead);  // Stop when we come back to the head node

    printf("Choose a Pokedex to delete by number: ");
    int owner_index;
    owner_index=getValidatedInteger();
    // scanf("%d",&owner_index);
    owner_index--;
    int counter=0;
    bool done=false;

    OwnerNode *current = ownerListHead;
    do {
        if (owner_index==counter)
        {done=true;break;}
        counter++;
        current = current->next;
    } while (current != ownerListHead);

    if (!done)
    {printf("Invalid Choice.\n");return;}


    // Search for the owner in the circular list

    // If there's only one owner in the list
    if (current->next == current) {
        printf("Deleting %s's entire Pokedex...\nPokedex Deleted.\n", current->ownerName);
        freePokemonList(current->pokedexRoot);  // Free all Pokémon in this Pokedex
        free(current);
        ownerListHead = NULL;
        
        return;
    }
    printf("Deleting %s's entire Pokedex...\nPokedex Deleted.\n", current->ownerName);
    // Adjust pointers for deletion
    current->prev->next = current->next;
    current->next->prev = current->prev;

    // Update head if needed
    if (current == ownerListHead) {
        ownerListHead = current->next;
    }

    freePokemonList(current->pokedexRoot);  // Free Pokémon linked list
    free(current);
    
    return;


    // If owner is not found
    
}

// Function to free all Pokémon in a given Pokedex
void freePokemonList(PokemonNode *head) {
    PokemonNode *temp;
    while (head != NULL) {
        temp = head;
        head = head->next;
        free(temp);
    }
}


// add pokemontodex for merge
void addPokemonToPokedex_merge(PokemonNode **root, char *name, int id) {
    // Check if the Pokemon ID already exists
    PokemonNode *temp = *root;
    while (temp != NULL) {
        if (temp->data->id == id) {
            // printf("Pokemon with ID %d already exists in the Pokedex.\n", id);
            return;
        }
        temp = temp->next;
    }

    // Create new Pokemon
    PokemonNode *newPokemon = (PokemonNode *)malloc(sizeof(PokemonNode));
    if (!newPokemon) {
        printf("Memory allocation failed.\n");
        return;
    }
    newPokemon->data = (PokemonData *)malloc(sizeof(PokemonData));
    if (!newPokemon->data) {
        free(newPokemon);
        printf("Memory allocation failed.\n");
        return;
    }
    newPokemon->data->id = id;
    strcpy(newPokemon->data->name, name);
    newPokemon->data->evolve = CANNOT_EVOLVE;
    newPokemon->next = NULL;

    // Insert at the end of the Pokedex list
    if (*root == NULL) {
        *root = newPokemon;
    } else {
        temp = *root;
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = newPokemon;
    }

    // printf("Pokemon %s added to Pokedex.\n", name);
}

// void trim(char *str) {
//     int start = 0, end = strlen(str) - 1;

//     // Trim leading spaces
//     while (isspace((unsigned char)str[start])) {
//         start++;
//     }

//     // Trim trailing spaces
//     while (end > start && isspace((unsigned char)str[end])) {
//         end--;
//     }

//     // Shift the trimmed string to the beginning
//     memmove(str, str + start, end - start + 1);
//     str[end - start + 1] = '\0';  // Null-terminate
// }
// // Function to compare two strings ignoring leading/trailing spaces
// int compareIgnoringSpaces(const char *str1, const char *str2) {
//     char temp1[100], temp2[100];  // Adjust size as needed
//     strncpy(temp1, str1, sizeof(temp1) - 1);
//     strncpy(temp2, str2, sizeof(temp2) - 1);
//     temp1[sizeof(temp1) - 1] = '\0';
//     temp2[sizeof(temp2) - 1] = '\0';

//     trim(temp1);
//     trim(temp2);

//     return strcmp(temp1, temp2);  // 0 if equal, non-zero if different
// }
// merge two pokedexes
void mergePokedexMenu() {
    char tt[MAX_NAME_LEN];
    if (ownerListHead == NULL || ownerListHead->next == ownerListHead) { 
        printf("Not enough owners to merge.\n");
        return;
    }


    char firstOwner[MAX_NAME_LEN], secondOwner[MAX_NAME_LEN];
    printf("\n=== Merge Pokedexes ===\n");
    printf("Enter name of first owner: ");
    // fgets(tt, sizeof(tt), stdin);
    // fgets(firstOwner, sizeof(firstOwner), stdin);
    scanf("%s", firstOwner);
    printf("Enter name of second owner: ");
    // fgets(tt, sizeof(tt), stdin);
    // fgets(secondOwner, sizeof(secondOwner), stdin);
    scanf("%s", secondOwner);

    // Find first and second owners
    OwnerNode *owner_list = ownerListHead;
    OwnerNode *first = NULL;
    OwnerNode *second = NULL;
    OwnerNode *prevSecond = NULL;
    bool foundFirst = false, foundSecond = false;

    while (owner_list != NULL ) {
        // printf("%s",firstOwner);
        // printf("%s",secondOwner);
        // printf("%s",owner_list->ownerName);
        if (strcmp(owner_list->ownerName, firstOwner) == 0) {
            foundFirst = true;
            first=owner_list;
        }
        else if (strcmp(owner_list->ownerName, secondOwner) == 0) {
            foundSecond = true;
            second=owner_list;
        }
        if (!foundSecond)
        {prevSecond = owner_list;}
        owner_list = owner_list->next;
        if (owner_list == ownerListHead) break;
    } 

    if (!foundFirst || !foundSecond) {
        printf("Error: Invalid owner names provided.\n");
        return;
    }

    printf("Merging %s and %s...\n", firstOwner, secondOwner);

    // Merge second owner's Pokedex into first owner's
    PokemonNode *current = second->pokedexRoot;
    while (current) {
        addPokemonToPokedex_merge(&first->pokedexRoot, current->data->name, current->data->id);
        current = current->next;
    }

    // // Free second owner's Pokedex
    // freePokemonList(second->pokedexRoot);
    // second->pokedexRoot = NULL;

    // // Remove second owner from the circular list
    //     prevSecond->next = second->next;
    
    // if (second == ownerListHead) {
    //     ownerListHead = ownerListHead->next;
    // }

    // free(second);


    deleteMerged(secondOwner);
    printf("Merge completed.\nOwner '%s' has been removed after merging.\n", secondOwner);
}

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

// Print Owners in a specified direction X times
void printOwnersMenu() {
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

    printf("Enter number of times to print owners: ");
    times=getValidatedInteger();
    // scanf("%d", &times);

    // scanf("%d", &forward);
    printOwners(times, forward);
}

// Print Owners function (circular traversal)
void printOwners(int times, int forward) {
    if (ownerListHead == NULL) {
        printf("No owners available.\n");
        return;
    }

    OwnerNode *temp = ownerListHead;
    // if (!forward) {
    //     temp = ownerListHead->prev; // Start from the last owner if reverse
    // }

    for (int i = 0; i < times; ++i) {
        printf("[%d] %s\n",(i+1) ,temp->ownerName);
        temp = (forward) ? temp->next : temp->prev;
    }
}

// Exit Program
void exitProgram() {
    printf("Goodbye!\n");
    // You can free any dynamically allocated memory here if needed
    exit(0);
}


// Function to enter the existing Pokedex menu
void enterExistingPokedexMenu(OwnerNode *owner) {
    if (owner == NULL) {
        printf("No owner found.\n");
        return;
    }

    //printf("-- %s's Existing Pokedex Menu --\n", owner->ownerName);
    int choice;
    do {
        
        printOwnerPokedexMenu(owner->ownerName);
        choice=getValidatedInteger();
        // scanf("%d", &choice);
        switch (choice) {
            case 1:
                addPokemonToPokedex(&(owner->pokedexRoot),0);
                break;
            case 2:
                displayPokedex(owner->pokedexRoot);
                break;
            case 3: {

                // scanf("%d", &id);
                releasePokemonByID(&(owner->pokedexRoot), -1,true);
                break;
            }
            case 4:
                pokemonFight(owner->pokedexRoot);
                break;
            case 5: {
                evolvePokemon(&(owner->pokedexRoot));
                break;
            }
            case 6:
                printf("Back to main menu\n");
                break;
            default:
                printf("Invalid choice.\n");
                break;
        }
    } while (choice != 6);
}



// Add starter Pokémon after name entry
void createNewPokedex() {
    char name[MAX_NAME_LEN];
    char tt[MAX_NAME_LEN];
    int starterChoice;

    printf("Your name: ");
    fgets(tt, sizeof(tt), stdin);

    fgets(name, sizeof(name), stdin);

// Remove the newline character if it exists
    name[strcspn(name, "\n")] = '\0';
    //printf("%s",name);

    // Check for duplicate names
    OwnerNode *temp = ownerListHead;
    while (temp != NULL) {
        if (strcmp(temp->ownerName, name) == 0) {
            printf("Owner with name %s already exists.\n", name);
            return;
        }
        temp = temp->next;
        if (temp == ownerListHead) break;  // Circular list check
    }

    printf("Choose Starter:\n");
    printf("1. Bulbasaur\n");
    printf("2. Charmander\n");
    printf("3. Squirtle\n");
    printf("Your choice: ");
    starterChoice=getValidatedInteger();
    // scanf("%d", &starterChoice);

    if (starterChoice < 1 || starterChoice > 3) {
        printf("Invalid choice. Please try again.\n");
        return;
    }
    int send_choice=0;
    if (starterChoice==1){send_choice=1;}
    if (starterChoice==2){send_choice=4;}
    if (starterChoice==3){send_choice=7;}
    // Create the new owner and assign starter Pokémon
    OwnerNode *newOwner = createOwnerNode(name, send_choice);
    printf("New Pokedex created for %s with starter %s.\n", name, (starterChoice == 1) ? "Bulbasaur" : (starterChoice == 2) ? "Charmander" : "Squirtle");

    //enterExistingPokedexMenu(newOwner);  // Go to the Pokedex menu
}


// Main program loop
int main() {
    int choice;

    do {
        printMainMenu();
        choice=getValidatedInteger();
        // scanf("%d", &choice);
        switch (choice) {
            case 1:
                createNewPokedex();  // Call the function to create a new Pokedex
                break;
            case 2:
                printExistingPokedexMenu();
                break;
            case 3:
                deletePokedexMenu();
                break;
            case 4:
                mergePokedexMenu();
                break;
            case 5:
                sortOwnersMenu();
                break;
            case 6:
                printOwnersMenu();
                break;
            case 7:
                exitProgram();
                break;
            default:
                printf("Invalid choice.\n");
        }
    } while (choice != 7);
    return 0;
}
