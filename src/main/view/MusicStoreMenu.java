package main.view;

import main.model.*;
import java.util.Scanner;
import java.util.ArrayList;

public class MusicStoreMenu extends Menu {
    public MusicStoreMenu(Menu previousMenu) {
        //TODO: add a menu option to search for an artist and add them to the library? the spec is confusing on this. 
        super("""
        [1] Search for Songs By Title
        [2] Search for Albums By Title
        [3] Search for Songs By Artist
        [4] Search for Albums By Artist
        [5] List All Songs
        [6] List All Albums
        [7] List All Artists
        """.trim());
        defaultOption = () -> { previousMenu.executeMenu(); };
        addOption(1, "search for songs by title", () -> { searchForSongsByTitle(); executeMenu();});
        addOption(2, "search for albums by title", () -> { searchForAlbumsByTitle(); executeMenu();});
        addOption(3, "search for songs by artist", () -> { System.out.println("Search for Songs by Artist"); executeMenu();}); //TODO: search for songs by artist in MusicStoreMenu
        addOption(4, "search for albums by artist", () -> { System.out.println("Search for Albums by Artist"); executeMenu();}); //TODO: search for albums by artist in MusicStoreMenu
        addOption(5, "list all songs", () -> { listAllSongs(); executeMenu(); });
        addOption(6, "list all albums", () -> { listAllAlbums(); executeMenu(); });
        addOption(7, "list all artists", () -> { listAllArtists(); executeMenu(); });
    }

    private void searchForSongsByTitle() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a song title: ");
        String title = in.nextLine();
        MusicStore musicStore = MusicStore.getInstance();
        ArrayList<Song> songs = musicStore.getSongByTitle(title);

        if(songs.size() == 0) {
            System.out.println("Song not found");
        } else if (songs.size() == 1) {
            System.out.println(songs.get(0));
            SelectedSongMenu selectedSongMenu = new SelectedSongMenu(this, songs.get(0));
            selectedSongMenu.executeMenu();
        } else if (songs.size() > 1) {
            MultiSongMenu multiSongMenu = new MultiSongMenu(this, songs);
            multiSongMenu.executeMenu();
        }
    }

    private void searchForAlbumsByTitle() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter an album title: ");
        String title = in.nextLine();
        MusicStore musicStore = MusicStore.getInstance();

        ArrayList<Album> album = musicStore.getAlbumByTitle(title);
        //TODO: implement library + album functionality; the only thing we need is to add the album to the library, no rating or anything else. 
        if(album.size() == 0) {
            //TODO: print song not found
        }
        else if (album.size() == 1) {
            //TODO: print the album and ask if the user wants to add it to the library
        }
        else {
            //TODO: get the user input to select an album and add it to the library 
            //IMPORTANT: use nextLine() to get the input, not nextInt()! refer to LibraryMenu option 7 if confused
        }
    }

    private void listAllSongs() {
        ArrayList<Song> songs = MusicStore.getInstance().getAllSongs();
        System.out.println(songs.size() + " Songs Found:");
        printEachElement(songs);
    }

    private void listAllAlbums() {
        ArrayList<Album> albums = MusicStore.getInstance().getAllAlbums();
        System.out.println(albums.size() + " Albums Found:");
        printEachElement(albums);
    }

    private void listAllArtists() {
        ArrayList<String> artists = MusicStore.getInstance().getAllArtists();
        System.out.println(artists.size() + " Artists Found:");
        printEachElement(artists);
    }

    private <T> void printEachElement(ArrayList<T> elements) {
        for(T element : elements) {
            System.out.println("- " + element);
        }
    }
    
}
