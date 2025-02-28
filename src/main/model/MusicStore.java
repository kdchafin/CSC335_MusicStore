package main.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class MusicStore {

    private static MusicStore instance;

    protected ArrayList<Album> albumList;
    protected ArrayList<Song> songList;

    public MusicStore() {
        this.albumList = new ArrayList<>();
        this.songList = new ArrayList<>();
    }

    public static MusicStore getInstance() {
        if(instance == null) {
            instance = new MusicStore();
        }
        return instance;
    }

    // generate data for both the albums and songs
    public void generateDataset() {
        Scanner fileScanner = null;
        File albumFile = new File("albums/albums.txt");
        try {
            fileScanner = new Scanner(albumFile);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: albums/albums.txt");
            System.exit(0);
        }
        
        ArrayList<String> albumFileNames = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            String currLine = fileScanner.nextLine();
            String[] albumName = currLine.split(",");
            String nameString = "albums/" + albumName[0] + "_" + albumName[1] + ".txt";
            albumFileNames.add(nameString);
        }
        fileScanner.close();

        for (String s : albumFileNames) {
            File nameFile = new File(s);
            Scanner albumScanner = null;
            try {
                albumScanner = new Scanner(nameFile);
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + s);
                System.exit(0);
            }
            String currLine = albumScanner.nextLine();

            String[] albumInfo = currLine.split(",");

            String albumName = albumInfo[0];
            String artistName = albumInfo[1];
            String genre = albumInfo[2];
            int year = Integer.parseInt(albumInfo[3]);

            // create the album object
            Album album = new Album(albumName, artistName, genre, year);
            addAlbumToList(album);

            // add songs to the album and song list
            while (albumScanner.hasNextLine()) {
                currLine = albumScanner.nextLine();
                Song song = new Song(currLine, album);
                album.addSong(song);
                addSongToList(song);
            }
            albumScanner.close();
        }
    }

// ----------------------  Datagen methods  ----------------------

    private void addAlbumToList(Album album) {
        albumList.add(album);
    }

    private void addSongToList(Song song) {
        songList.add(song);
    }


//----------------------   Referenced getter methods  ----------------------
// these return a reference to theobjects direct data

    protected Song getSong(Song song) {
        for (Song s : this.songList) {
            if (s.equals(song)) {
                return s;
            }
        }
        return null;
    }

    protected Album getAlbum(Album album) {
        for (Album a : this.albumList) {
            if (a.equals(album)) {
                return a;
            }
        }
        return null;
    }

//----------------------   All Dereferenced getter methods  ----------------------
// dereferenced object getters. these are memory safe to use

    public ArrayList<Song> getSongsByTitle(String title) {
        ArrayList<Song> temp = new ArrayList<>();

        for (Song s : songList) {
            if (s.getTitle().equalsIgnoreCase(title)) temp.add(new Song(s));
        }
        return temp;
    }

    public ArrayList<Song> getSongsByArtist(String artist) {
        ArrayList<Song> temp = new ArrayList<>();

        for (Song s : songList) {
            if (s.getArtist().equalsIgnoreCase(artist)) temp.add(new Song(s));
        }
        return temp;
    }

    public ArrayList<Album> getAlbumsByTitle(String title) {
        ArrayList<Album> temp = new ArrayList<>();

        for (Album a : albumList) {
            if (a.getTitle().equalsIgnoreCase(title)) temp.add(new Album(a));
        }
        return temp;
    }

    public ArrayList<Album> getAlbumsByArtist(String artist) {
        ArrayList<Album> temp = new ArrayList<>();

        for (Album a : albumList) {
            if (a.getArtist().equalsIgnoreCase(artist)) temp.add(new Album(a));
        }
        return temp;
    }
    
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> temp = new ArrayList<>();
        // duplicate = "lullaby"
        for (Song s : songList) {
            temp.add(new Song(s));
        }
        // System.out.println(titles.size());
        Collections.sort(temp, Comparator.comparing(Song::getTitle));
        return temp;
    }

    public ArrayList<Album> getAllAlbums() {
        ArrayList<Album> temp = new ArrayList<>();

        for (Album a : albumList) {
            temp.add(new Album(a));
        }

        Collections.sort(temp, Comparator.comparing(Album::getTitle));
        return temp;
    }

    public ArrayList<String> getAllArtists() {
        ArrayList<String> temp = new ArrayList<>();

        for (Album a : albumList) {
            temp.add(a.getArtist());
        }

        //remove duplicated using a set
        HashSet<String> temp2 = new HashSet<String>(temp); 
        temp = new ArrayList<>(temp2); 

        return temp;
    }
}
//   "bruh!"
//     /
// (^.^) 

// how is the cat still even here?