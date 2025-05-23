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
        colorizeBrackets();
        defaultOption = () -> { previousMenu.executeMenu(); };
        addOption(1, "search for songs by title", () -> { searchForSongsByTitle(); executeMenu();});
        addOption(2, "search for albums by title", () -> { searchForAlbumsByTitle(); executeMenu();});
        addOption(3, "search for songs by artist", () -> { searchForSongsByArtist(); executeMenu();}); 
        addOption(4, "search for albums by artist", () -> { searchForAlbumsByArtist(); executeMenu();}); 
        addOption(5, "list all songs", () -> { listAllSongs(); executeMenu(); });
        addOption(6, "list all albums", () -> { listAllAlbums(); executeMenu(); });
        addOption(7, "list all artists", () -> { listAllArtists(); executeMenu(); });
    }

    private void searchForSongsByTitle() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a song title: ");
        String title = in.nextLine();
        MusicStore musicStore = MusicStore.getInstance();
        ArrayList<Song> songs = musicStore.getSongsByTitle(title);

        if(songs.size() == 0) {
            System.out.println("Song not found");

        } else if (songs.size() == 1) {
            System.out.println(songs.get(0));
            SelectedSongMenu selectedSongMenu = new SelectedSongMenu(this, songs.get(0), false);
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

        ArrayList<Album> albums = musicStore.getAlbumsByTitle(title);
    
        if(albums.size() == 0) {
            System.out.println("Album not found");
        }
        else if (albums.size() == 1) {
            System.out.println(albums.get(0));
            SelectedAlbumMenu selectedAlbumMenu = new SelectedAlbumMenu(this, albums.get(0), false);
            selectedAlbumMenu.executeMenu();
        }
        else {
            MultiAlbumsMenu multiAlbumMenu = new MultiAlbumsMenu(this, albums);
            multiAlbumMenu.executeMenu();
        }
    }

    private void searchForSongsByArtist() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter an artist name: ");
        String artist = in.nextLine();
        MusicStore musicStore = MusicStore.getInstance();
        ArrayList<Song> songs = musicStore.getSongsByArtist(artist);

        if(songs.size() == 0) {
            System.out.println("Artist not found");
        } else if(songs.size() == 1) {
            System.out.println(songs.get(0));
            SelectedSongMenu selectedSongMenu = new SelectedSongMenu(this, songs.get(0), false);
            selectedSongMenu.executeMenu();
        } else {
            MultiSongMenu multiSongMenu = new MultiSongMenu(this, songs);
            multiSongMenu.executeMenu();
        }
    }

    private void searchForAlbumsByArtist() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter an artist name: ");
        String artist = in.nextLine();
        MusicStore musicStore = MusicStore.getInstance();

        ArrayList<Album> albums = musicStore.getAlbumsByArtist(artist);
    
        if(albums.size() == 0) {
            System.out.println("Artist not found");
        }
        else if (albums.size() == 1) {
            System.out.println(albums.get(0));
            SelectedAlbumMenu selectedAlbumMenu = new SelectedAlbumMenu(this, albums.get(0), false);
            selectedAlbumMenu.executeMenu();
        }

        else {
            MultiAlbumsMenu multiAlbumMenu = new MultiAlbumsMenu(this, albums);
            multiAlbumMenu.executeMenu();
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
        for (Album album : albums) {
            System.out.println("- " + album.getTitle() + " by " + album.getArtist() + ", " + album.getAlbumSongs().size() + " songs");
        }
        //printEachElement(albums);
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
