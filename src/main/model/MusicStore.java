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

    private ArrayList<Album> albumList;
    private ArrayList<Song> songList;

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

    /*
     * Generate both lists of album and song objects for reference in code. 
     */
    public void generateDataset() {
        Scanner fileScanner = null;
        File albumFile = new File("albums/albums.txt");
        try {
            fileScanner = new Scanner(albumFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: albums/albums.txt");
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
                System.out.println("File not found: " + s);
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

            //
            while (albumScanner.hasNextLine()) {
                currLine = albumScanner.nextLine();
                Song song = new Song(currLine, album);
                album.addSong(song);
                addSongToList(song);
            }
            albumScanner.close();
        }
    }

    public void addAlbumToList(Album album) {
        albumList.add(album);
    }

    public void addSongToList(Song song) {
        songList.add(song);
    }

    public ArrayList<Song> getSongByTitle(String title) {
        ArrayList<Song> temp = new ArrayList<>();

        for (Song s : songList) {
            if (s.getTitle().equalsIgnoreCase(title)) temp.add(new Song(s));
        }
        return temp;
    }

    /* getSongByArtist: returns all songs of a given artist
     * returns all songs in a ArrayList<Song> 
     */
    public ArrayList<Song> getSongByArtist(String artist) {
        ArrayList<Song> temp = new ArrayList<>();

        for (Song s : songList) {
            if (s.getArtist().equalsIgnoreCase(artist)) temp.add(new Song(s));
        }
        return temp;
    }

    /* getAlbumByTitle: returns all albums of a given title
     * returns albums in a ArrayList<Album>
     */
    public ArrayList<Album> getAlbumByTitle(String title) {
        ArrayList<Album> temp = new ArrayList<>();

        for (Album a : albumList) {
            if (a.getTitle().equalsIgnoreCase(title)) temp.add(new Album(a));
        }
        return temp;
    }

    /* getAlbumByArtist: returns all albums by a given artist
     * returns albums in a ArrayList<Album>
     */
    public ArrayList<Album> getAlbumByArtist(String artist) {
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