package main.view;

import main.model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryMenu extends Menu {
    private LibraryModel library;
    public LibraryMenu(Menu previousMenu) {
        super("""
        [1] Get Songs
        [2] Get Albums
        [3] Get Artists
        [4] Get Playlists
        [5] Get Favorite Songs
        [6] Create Playlist
        [7] Remove A Song From A Playlist
        [8] Delete Playlist
        """.trim());
        library = LibraryModel.getInstance();
        defaultOption = () -> { previousMenu.executeMenu(); };

        addOption(1, "get songs", () -> { getSongs(); executeMenu(); });
        addOption(2, "get albums", () -> { getAlbums(); executeMenu(); });
        addOption(3, "get artists", () -> { getArtists(); executeMenu(); });
        addOption(4, "get playlists", () -> { printEachElement(library.getPlaylists(), "playlist"); executeMenu(); });
        addOption(5, "get favorite songs", () -> { getFavoriteSongs(); executeMenu(); });
        addOption(6, "create a playlist", () -> { createPlaylist(); executeMenu(); });
        addOption(7, "remove a song from a playlist", () -> { editPlaylist(); executeMenu(); });
        addOption(8, "delete a playlist", () -> { deletePlaylist(); executeMenu(); });
    }

    private void getArtists() {
        ArrayList<String> artists = library.getArtists();
        String plural = artists.size() == 1 ? "" : "s";
        if(artists.size() == 0) {
            System.out.println("You have no artists in your library.");
        } else {
            System.out.println("You have " + artists.size() + " artist" + plural + ":");
            printEachElement(artists, "artist");
        }
    }

    private void getAlbums() {
        ArrayList<Album> albums = library.getAlbums();
        String plural = albums.size() == 1 ? "" : "s";
        if(albums.size() == 0) {
            System.out.println("You have no albums in your library.");
        } else {
            System.out.println("You have " + albums.size() + " album" + plural + ":");
            for (Album album : albums) {
                System.out.println("- " + album.getTitle() + " by " + album.getArtist());
            }
        }
    }

    private void getSongs() {
        String plural = library.getSongs().size() == 1 ? "" : "s";
        System.out.println("You have " + library.getSongs().size() + " song" + plural + " in your library:");
        printEachElement(library.getSongs(), "song");
    }

    //TODO: this doesnt work properly. If a song is favortied in the store, the song object here doesnt reference the stores song object.
    private void getFavoriteSongs() {
        ArrayList<Song> favoriteSongs = library.getFavoriteSongs();
        String plural = favoriteSongs.size() == 1 ? "" : "s";
        if(favoriteSongs.size() == 0) {
            System.out.println("You have no favorite songs");
        } else {
            System.out.println("You have " + favoriteSongs.size() + " favorite song" + plural + ":");
            printEachElement(library.getFavoriteSongs(), "favorite song"); 
        } 
    }

    private void createPlaylist() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a playlist name: ");
        String name = in.nextLine();
        String result = library.createPlaylist(name);
        System.out.println(result);
    }

    private void editPlaylist() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a playlist name: ");
        String name = in.nextLine();

        ArrayList<Playlist> playlists = library.getPlaylists();
        Playlist playlist = null;
        boolean foundPlaylist = false;

        for(Playlist pl : playlists) {
            if(pl.getName().equals(name)) {
                foundPlaylist = true;
                playlist = pl;
                System.out.println("- " + pl);
                break;
            }
        }
        if(!foundPlaylist) {
            System.out.println("There is no playlist with that name.");
            return;
        }
        System.out.println("What number song would you like to remove from the playlist?");
        int index = Integer.parseInt(in.nextLine());

        try { 
            Song song = playlist.removeSong(index-1); 
            library.removeSongFromPlaylist(song, name);
            System.out.println(song.getTitle() + " removed from " + playlist.getName());
        } catch (IndexOutOfBoundsException e) { 
            System.out.println("There is no song at that index"); 
        }
    }

    private void deletePlaylist() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a playlist name: ");
        String name = in.nextLine();
        library.deletePlaylist(name);
    }

    private <T> void printEachElement(ArrayList<T> elements, String type) {
        if(elements.size() == 0) {
            System.out.println("There are no " + type + "s in the library.");
        }
        for(T element : elements) {
            System.out.println("- " + element);
        }
    }
}
