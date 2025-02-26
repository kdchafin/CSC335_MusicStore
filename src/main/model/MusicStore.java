package main.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MusicStore {

    private static MusicStore instance;

    private ArrayList<Album> albumList;
    private ArrayList<Song> songList;

    private MusicStore() {

        this.albumList = new ArrayList<>();
        this.songList = new ArrayList<>();
        generateDataset();
    }

    public static MusicStore getInstance() {
        if(instance == null) {
            instance = new MusicStore();
        }
        return instance;
    }

    /*
     * Generate both hashmaps of album and song objects for reference in code. 
     */
    private void generateDataset() {
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
            albumList.add(album);

            //
            while (albumScanner.hasNextLine()) {
                currLine = albumScanner.nextLine();
                Song song = new Song(currLine, album);
                album.addSong(song);
                songList.add(song);
            }
            albumScanner.close();
        }
    }

    // getters for all 4 HashMaps
    public ArrayList<Album> getAlbumList() {
        return albumList;
    }
    
    public ArrayList<Song> getSongList() {
        return songList;
    }

    //TODO: the next 4 functions have escaping references. please fix
    /* getSongByTitle: returns all songs of a given title
     * returns all songs in a ArrayList<Song> 
     * 
     * *PLEASE READ: if multiple songs are found, please prompt the user in the view!!!
     */
    public ArrayList<Song> getSongByTitle(String title) {
        ArrayList<Song> temp = new ArrayList<>();

        for (Song s : songList) {
            if (s.getTitle() == title) temp.add(s);
        }
        return temp;
    }

    /* getSongByArtist: returns all songs of a given artist
     * returns all songs in a ArrayList<Song> 
     */
    public ArrayList<Song> getSongByArtist(String artist) {
        ArrayList<Song> temp = new ArrayList<>();

        for (Song s : songList) {
            if (s.getArtist() == artist) temp.add(s);
        }
        return temp;
    }

    /* getAlbumByTitle: returns all albums of a given title
     * returns albums in a ArrayList<Album>
     */
    public ArrayList<Album> getAlbumByTitle(String title) {
        ArrayList<Album> temp = new ArrayList<>();

        for (Album a : albumList) {
            if (a.getTitle() == title) temp.add(a);
        }
        return temp;
    }

    /* getAlbumByArtist: returns all albums by a given artist
     * returns albums in a ArrayList<Album>
     */
    public ArrayList<Album> getAlbumByArtist(String artist) {
        ArrayList<Album> temp = new ArrayList<>();

        for (Album a : albumList) {
            if (a.getArtist() == artist) temp.add(a);
        }
        return temp;
    }

    public ArrayList getAllSongs() {
        ArrayList<Song> temp = new ArrayList<>();

        for (Song s : songList) {
            temp.add(s);
        }

        return temp;
    }

    public ArrayList<Album> getAllAlbums() {
        ArrayList<Album> temp = new ArrayList<>();

        for (Album a : albumList) {
            temp.add(a);
        }

        return temp;
    }

    public ArrayList<String> getAllArtists

    /*
    public List<String> getAllSongs() {
        List<String> sortedKeys = new ArrayList<>();
        titleToSongMap.values().forEach(song -> sortedKeys.add(song.getTitle() + " by " + song.getArtist() + " on the album \"" + song.getAlbumTitle() + "\""));
        Collections.sort(sortedKeys);
        return sortedKeys;
    }

    public List<String> getAllAlbums() {
        List<String> sortedKeys = new ArrayList<>();
        titleToAlbumMap.values().forEach(album -> sortedKeys.add(album.getAlbumTitle() + " : " + album.getArtist()));
        Collections.sort(sortedKeys);
        return sortedKeys;
    }

    public List<String> getAllArtists() {
        List<String> sortedKeys = new ArrayList<>(artistToAlbumMap.keySet());
        Collections.sort(sortedKeys);
        return sortedKeys;
    }
    */
}


// >.< bruh!