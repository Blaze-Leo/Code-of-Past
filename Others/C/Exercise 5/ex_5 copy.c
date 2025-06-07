#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define the structures
typedef struct Song {
    char* title;
    char* artist;
    int year;
    char* lyrics;
    int streams;
} Song;

typedef struct Playlist {
    char* name;
    Song** songs;
    int songsNum;
} Playlist;

// Function prototypes
void printMainMenu();
void printPlaylistMenu();
void printSortMenu();
void deleteSong(Song* song);
void playSong(Song* song);
void freeSong(Song* song);
void freePlaylist(Playlist* playlist);
void showPlaylist(Playlist* playlist);
void addSongToPlaylist(Playlist* playlist);
void deleteSongFromPlaylist(Playlist* playlist);
void sortPlaylist(Playlist* playlist);
void playPlaylist(Playlist* playlist);
void removePlaylist(Playlist** playlists, int* playlistsNum, int index);
Playlist* createPlaylist(const char* name);
Song* createSong();
char* readDynamicString();

// Main program
int main() {
    Playlist** playlists = NULL;
    int playlistsNum = 0;
    int choice;

    while (1) {
        printMainMenu();
        scanf("%d", &choice);
        getchar();  // Clear newline after choice input

        switch (choice) {
            case 1: // Watch playlists
                if (playlistsNum == 0) {
                    printf("No playlists available.\n");
                    break;
                }
                do{
                    printf("Choose a playlist:\n");
                    for (int i = 0; i < playlistsNum; i++) {
                        printf("\t%d. %s\n", i + 1, playlists[i]->name);
                    }
                    printf("\t%d. Back to main menu\n", playlistsNum + 1);
                    int playlistChoice;
                    scanf("%d", &playlistChoice);
                    getchar();
                    if (playlistChoice == playlistsNum + 1) break;
                    if (playlistChoice < 1 || playlistChoice > playlistsNum) {
                        printf("Invalid option.\n");
                        break;
                    }
                    Playlist* selectedPlaylist = playlists[playlistChoice - 1];
                    int playlistOption;
                    do {
                        printf("playlist %s:\n", selectedPlaylist->name);
                        printPlaylistMenu();
                        scanf("%d", &playlistOption);
                        getchar();

                        switch (playlistOption) {
                            case 1:
                                showPlaylist(selectedPlaylist);
                                break;
                            case 2:
                                addSongToPlaylist(selectedPlaylist);
                                break;
                            case 3:
                                deleteSongFromPlaylist(selectedPlaylist);
                                break;
                            case 4:
                                sortPlaylist(selectedPlaylist);
                                break;
                            case 5:
                                playPlaylist(selectedPlaylist);
                                break;
                            case 6:
                                break;
                            default:
                                printf("Invalid option.\n");
                        }
                    } while (playlistOption != 6);
                }while(1);
                break;

            case 2: // Add playlist
                printf("Enter playlist's name:\n");
                char* name = readDynamicString();
                playlists = realloc(playlists, (playlistsNum + 1) * sizeof(Playlist*));
                playlists[playlistsNum] = createPlaylist(name);
                playlistsNum++;
                free(name);
                break;

            case 3: // Remove playlist
                if (playlistsNum == 0) {
                    printf("No playlists available to remove.\n");
                    break;
                }
                printf("Choose a playlist:\n");
                for (int i = 0; i < playlistsNum; i++) {
                    printf("\t%d. %s\n", i + 1, playlists[i]->name);
                }
                printf("\t%d. Back to main menu\n", playlistsNum + 1);
                int removeChoice;
                scanf("%d", &removeChoice);
                getchar();
                if (removeChoice == playlistsNum + 1) break;
                if (removeChoice < 1 || removeChoice > playlistsNum) {
                    printf("Invalid option.\n");
                    break;
                }
                removePlaylist(playlists, &playlistsNum, removeChoice - 1);
                break;

            case 4: // Exit
                for (int i = 0; i < playlistsNum; i++) {
                    freePlaylist(playlists[i]);
                }
                free(playlists);
                printf("Goodbye!\n");
                return 0;

            default:
                printf("Invalid option.\n");
        }
    }
}

// Utility functions
void printMainMenu() {
    printf("Please Choose:\n");
    printf("\t1. Watch playlists\n\t2. Add playlist\n\t3. Remove playlist\n\t4. exit\n");
}

void printPlaylistMenu() {
    printf("\t1. Show Playlist\n\t2. Add Song\n\t3. Delete Song\n\t4. Sort\n\t5. Play\n\t6. exit\n");
}

void printSortMenu() {
    printf("Choose:\n");
    printf("1. Sort by year\n2. Sort by streams - ascending order\n");
    printf("3. Sort by streams - descending order\n4. Sort alphabetically\n");
}

Playlist* createPlaylist(const char* name) {
    Playlist* playlist = malloc(sizeof(Playlist));
    playlist->name = strdup(name);
    playlist->songs = NULL;
    playlist->songsNum = 0;
    return playlist;
}

Song* createSong() {
    Song* song = malloc(sizeof(Song));
    printf("Enter song's details:\nTitle:\n");
    song->title = readDynamicString();
    printf("Artist:\n");
    song->artist = readDynamicString();
    printf("Year of release:\n");
    scanf("%d", &song->year);
    getchar();
    printf("Lyrics:\n");
    song->lyrics = readDynamicString();
    song->streams = 0;
    return song;
}

void deleteSong(Song* song) {
    free(song->title);
    free(song->artist);
    free(song->lyrics);
    free(song);
}

void playSong(Song* song) {
    printf("Now playing %s:\n$ %s $\n\n", song->title, song->lyrics);
    song->streams++;
}

void freeSong(Song* song) {
    deleteSong(song);
}

void freePlaylist(Playlist* playlist) {
    for (int i = 0; i < playlist->songsNum; i++) {
        freeSong(playlist->songs[i]);
    }
    free(playlist->songs);
    free(playlist->name);
    free(playlist);
}

void showPlaylist(Playlist* playlist) {
    if (playlist->songsNum == 0) {
        printf("Playlist is empty.\n");
        return;
    }
    for (int i = 0; i < playlist->songsNum; i++) {
        Song* song = playlist->songs[i];
        printf("%d. Title: %s\n   Artist: %s\n   Released: %d\n   Streams: %d\n\n",
               i + 1, song->title, song->artist, song->year, song->streams);
    }

    while (1) {
        printf("choose a song to play, or 0 to quit:\n");
        int songChoice;
        scanf("%d", &songChoice);
        getchar();  // Clear newline after input

        if (songChoice == 0) break;  // Exit the loop
        if (songChoice < 1 || songChoice > playlist->songsNum) {
            printf("Invalid option.\n");
        } else {
            playSong(playlist->songs[songChoice - 1]);
        }
    }
}

void addSongToPlaylist(Playlist* playlist) {
    Song* newSong = createSong();
    playlist->songs = realloc(playlist->songs, (playlist->songsNum + 1) * sizeof(Song*));
    playlist->songs[playlist->songsNum] = newSong;
    playlist->songsNum++;
}

void deleteSongFromPlaylist(Playlist* playlist) {
    if (playlist->songsNum == 0) {
        printf("Playlist is empty.\n");
        return;
    }
    for (int i = 0; i < playlist->songsNum; i++) {
        Song* song = playlist->songs[i];
        printf("%d. Title: %s\n   Artist: %s\n   Released: %d\n   Streams: %d\n\n",
               i + 1, song->title, song->artist, song->year, song->streams);
    }
    printf("Choose a song to delete, or 0 to quit:\n");
    int songChoice;
    scanf("%d", &songChoice);
    getchar();
    if (songChoice == 0) return;
    if (songChoice < 1 || songChoice > playlist->songsNum) {
        printf("Invalid option.\n");
        return;
    }
    deleteSong(playlist->songs[songChoice - 1]);
    for (int i = songChoice; i < playlist->songsNum; i++) {
        playlist->songs[i - 1] = playlist->songs[i];
    }
    playlist->songsNum--;
    playlist->songs = realloc(playlist->songs, playlist->songsNum * sizeof(Song*));
    printf("Song deleted successfully.\n");
}

void sortPlaylist(Playlist* playlist) {
    if (playlist->songsNum <= 1) return;
    printSortMenu();
    int sortChoice;
    scanf("%d", &sortChoice);
    getchar();
    if (sortChoice < 1 || sortChoice > 4) sortChoice = 4;

    for (int i = 0; i < playlist->songsNum - 1; i++) {
        for (int j = 0; j < playlist->songsNum - i - 1; j++) {
            int swap = 0;
            if (sortChoice == 1 && playlist->songs[j]->year > playlist->songs[j + 1]->year) {
                swap = 1;
            } else if (sortChoice == 2 && playlist->songs[j]->streams > playlist->songs[j + 1]->streams) {
                swap = 1;
            } else if (sortChoice == 3 && playlist->songs[j]->streams < playlist->songs[j + 1]->streams) {
                swap = 1;
            } else if (sortChoice == 4 && strcmp(playlist->songs[j]->title, playlist->songs[j + 1]->title) > 0) {
                swap = 1;
            }
            if (swap) {
                Song* temp = playlist->songs[j];
                playlist->songs[j] = playlist->songs[j + 1];
                playlist->songs[j + 1] = temp;
            }
        }
    }
    printf("sorted\n");
}

void playPlaylist(Playlist* playlist) {
    for (int i = 0; i < playlist->songsNum; i++) {
        playSong(playlist->songs[i]);
    }
}

void removePlaylist(Playlist** playlists, int* playlistsNum, int index) {
    freePlaylist(playlists[index]);
    for (int i = index; i < *playlistsNum - 1; i++) {
        playlists[i] = playlists[i + 1];
    }
    (*playlistsNum)--;
    playlists = realloc(playlists, (*playlistsNum) * sizeof(Playlist*));
    printf("Playlist removed successfully.\n");
}

char* readDynamicString() {
    char* str = NULL;
    size_t len = 0;
    getline(&str, &len, stdin);
    // Remove the newline character
    size_t strLen = strlen(str);
    if (strLen > 0 && str[strLen - 1] == '\n') {
        str[strLen - 1] = '\0';
    }
    return str;
}
