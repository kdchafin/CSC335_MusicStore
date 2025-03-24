package main.view;

import main.model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        [8] View A Playlist
        [9] Delete Playlist
        [10] Search For Songs By Title
        [11] Search For Albums By Title
        [12] Search For Songs By Artist
        [13] Search For Albums By Artist
        [14] Search For Songs By Genre
        """.trim());
        
        this.library = Menu.getUser().getLibraryModel();
        defaultOption = () -> { previousMenu.executeMenu(); };
        colorizeBrackets();
        addOption(1, "get songs", () -> { getSongs(); executeMenu(); });
        addOption(2, "get albums", () -> { getAlbums(); executeMenu(); });
        addOption(3, "get artists", () -> { getArtists(); executeMenu(); });
        addOption(4, "get playlists", () -> { getPlaylists(); executeMenu(); });
        addOption(5, "get favorite songs", () -> { getFavoriteSongs(); executeMenu(); });
        addOption(6, "create a playlist", () -> { createPlaylist(); executeMenu(); });
        addOption(7, "remove a song from a playlist", () -> { editPlaylist(); executeMenu(); });
        addOption(8, "view a playlist", () -> { viewPlaylist(); executeMenu(); });
        addOption(9, "delete a playlist", () -> { deletePlaylist(); executeMenu(); });
        addOption(10, "search for songs by title", () -> { searchForSongsByTitle(); executeMenu();});
        addOption(11, "search for albums by title", () -> { searchForAlbumsByTitle(); executeMenu();});
        addOption(12, "search for songs by artist", () -> { searchForSongsByArtist(); executeMenu();});
        addOption(13, "search for albums by artist", () -> { searchForAlbumsByArtist(); executeMenu();}); 
        addOption(14, "search for songs by genre", () -> { searchForSongsByGenre(); executeMenu();});
    }

    private ArrayList<Song> sortOrShuffleSongs(ArrayList<Song> theSongs) {
        int sortCriteria = 4;
        while(true && theSongs.size() > 1) {
            Scanner in = Menu.getScanner();
            String tempMenu = """
                
                [1] Sort by Artist
                [2] Sort by Rating
                [3] Sort by Title
                [4] Shuffle Songs
                ----------------------------""";
            tempMenu= tempMenu.replaceAll("\\[(\\d+)\\]", GREEN + "[$1]" + RESET);
            System.out.println(tempMenu);
            try {
                sortCriteria = Integer.parseInt(in.nextLine().toLowerCase());
            } catch (NumberFormatException e) {
                printErrorMessage("Invalid option.");
                return theSongs;
            }
            if(sortCriteria >= 1 && sortCriteria <= 4) {
                break;
            }
            printErrorMessage("Invalid option.");
        }
        
        switch (sortCriteria) {
            case 1:
                theSongs.sort(Comparator.comparing(Song::getArtist));
                break;
            case 2:
                theSongs.sort(Comparator.comparing(Song::getRating));
                break;
            case 3:
                theSongs.sort(Comparator.comparing(Song::getTitle));
                break;
            case 4:
            default:
                Collections.shuffle(theSongs);
        }
        return theSongs;
    }

    private void getSongs() {
        ArrayList<Song> songs = library.getSongs();
        String plural = songs.size() == 1 ? "" : "s";
        if(songs.size() != 0) System.out.println("You have " + library.getSongs().size() + " song" + plural + " in your library:");
        sortOrShuffleSongs(songs);
        printEachElement(songs, "song");
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
        ArrayList<String> albums = library.getAlbums();
        String plural = albums.size() == 1 ? "" : "s";
        if(albums.size() == 0) {
            System.out.println("You have no albums in your library.");
        } else {
            System.out.println("You have " + albums.size() + " album" + plural + ":");
            for (String album : albums) {
                System.out.println("- " + album);
            }
        }
    }

    private void getPlaylists() {
        ArrayList<Playlist> playlists = library.getPlaylists();
        ArrayList<Playlist> nonEmptyPlaylists = new ArrayList<>(playlists.stream()
            .filter(playlist -> playlist.getSize() > 0)
            .collect(Collectors.toList()));
        printEachElement(nonEmptyPlaylists, "playlist");
    }


    private void getFavoriteSongs() {
        ArrayList<Song> favoriteSongs = library.getFavoriteSongs();
        String plural = favoriteSongs.size() == 1 ? "" : "s";
        if(favoriteSongs.size() == 0) {
            System.out.println("You have no favorite songs");
        } else {
            System.out.println("You have " + favoriteSongs.size() + " favorite song" + plural + ":");
            sortOrShuffleSongs(favoriteSongs);
            printEachElement(favoriteSongs, "song");
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

    private void viewPlaylist() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a playlist name: ");
        String name = in.nextLine();
        ArrayList<Playlist> playlists = library.getPlaylists();
        Playlist selectedPlaylist = null;
        boolean foundPlaylist = false;
        for(Playlist pl : playlists) {
            if(pl.getName().equalsIgnoreCase(name)) {
                selectedPlaylist = pl;
                foundPlaylist = true;
            }
        }
        if(!foundPlaylist) {
            System.out.println("There is no playlist named \"" + name + "\".");
            return;
        }
        if(name.equalsIgnoreCase("Recently Played") || name.equalsIgnoreCase("Most Played")) {
            System.out.println(ORANGE + "This playlist cannot be shuffled or sorted." + RESET);
            System.out.println(selectedPlaylist);
            return;
        }
        ArrayList<Song> rearrangedSongs = sortOrShuffleSongs(selectedPlaylist.getSongs());
        Playlist temp = new Playlist(name);
        for (Song s : rearrangedSongs) {
            temp.addSong(s);
        }
        System.out.println(temp);
        // System.out.println(selectedPlaylist);

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

    private void searchForSongsByTitle() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a song title: ");
        String title = in.nextLine();
        
        ArrayList<Song> songs = library.getSongsByTitle(title);

        if(songs.size() == 0) {
            System.out.println("Song not found in library");
        } else if (songs.size() == 1) {
            System.out.println(songs.get(0));
            SelectedSongMenu selectedSongMenu = new SelectedSongMenu(this, songs.get(0), true);
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
        ArrayList<Album> albums = library.getAlbumsByTitle(title);
    
        if(albums.size() == 0) {
            System.out.println("Album not found");
        }
        else if (albums.size() == 1) {
            System.out.println(albums.get(0));
            SelectedAlbumMenu selectedAlbumMenu = new SelectedAlbumMenu(this, albums.get(0), true);
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
        ArrayList<Song> songs = library.getSongsByArtist(artist);

        if(songs.size() == 0) {
            System.out.println("Artist not found");
        } else if(songs.size() == 1) {
            System.out.println(songs.get(0));
            SelectedSongMenu selectedSongMenu = new SelectedSongMenu(this, songs.get(0), true);
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

        ArrayList<Album> albums = library.getAlbumsByArtist(artist);
    
        if(albums.size() == 0) {
            System.out.println("Artist not found");
        }
        else if (albums.size() == 1) {
            System.out.println(albums.get(0));
            SelectedAlbumMenu selectedAlbumMenu = new SelectedAlbumMenu(this, albums.get(0), true);
            selectedAlbumMenu.executeMenu();
        }

        else {
            MultiAlbumsMenu multiAlbumMenu = new MultiAlbumsMenu(this, albums);
            multiAlbumMenu.executeMenu();
        }
    }

    private void searchForSongsByGenre() {
        Scanner in = Menu.getScanner();
        System.out.println("Enter a genre: ");
        String genre = in.nextLine();
        ArrayList<Song> songs = library.getSongsByGenre(genre);

        if(songs.size() == 0) {
            System.out.println("Genre not found");
        }
        else if (songs.size() == 1) {
            System.out.println(songs.get(0));
            SelectedSongMenu selectedSongMenu = new SelectedSongMenu(this, songs.get(0), true);
            selectedSongMenu.executeMenu();
        }
        else {
            MultiSongMenu multiSongMenu = new MultiSongMenu(this, songs);
            multiSongMenu.executeMenu();
        }
    }
}
