package main.view;

import main.model.*;
import java.util.Scanner;
import java.util.ArrayList;

public class MusicStoreMenu extends Menu {
    public MusicStoreMenu(Menu previousMenu) {
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
        addOption(3, "search for songs by artist", () -> { System.out.println("Search for Songs by Artist"); executeMenu();});
        addOption(4, "search for albums by artist", () -> { System.out.println("Search for Albums by Artist"); executeMenu();});
        addOption(5, "list all songs", () -> { listAllSongs(); executeMenu(); });
        addOption(6, "list all albums", () -> { listAllAlbums(); executeMenu(); });
        addOption(7, "list all artists", () -> { listAllArtists(); executeMenu(); });
    }

    private void searchForSongsByTitle() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a song title: ");
        String title = in.nextLine();
        MusicStore musicStore = MusicStore.getInstance();
        ArrayList<Song> song = musicStore.getSongByTitle(title);
        if(song != null) {
            System.out.println(song.get(0));
            SelectedSongMenu selectedSongMenu = new SelectedSongMenu(this, song); 
            selectedSongMenu.executeMenu();
        } else {
            System.out.println("Song not found");
        }
    }

    private void searchForAlbumsByTitle() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter an album title: ");
        String title = in.nextLine();
        MusicStore musicStore = MusicStore.getInstance();

        ArrayList<Album> album = musicStore.getAlbumByTitle(title);
        if(album.size() == 0) {
            //TODO: print song not found
        }
        else if (album.size() == 1) {
            //TODO: print the song
        }
        else {
            //TODO: prompt the user which song they are refering to
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
