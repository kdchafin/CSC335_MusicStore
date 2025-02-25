package main.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class MusicStore {

    private static MusicStore instance;
    private HashMap<String, Album> titleToAlbumMap;
    private HashMap<String, Album> artistToAlbumMap;
    private HashMap<String, Song> titleToSongMap;
    private HashMap<String, Song> artistToSongMap;

    private MusicStore() {
        this.titleToAlbumMap = new HashMap<>();
        this.artistToAlbumMap = new HashMap<>();
        this.titleToSongMap = new HashMap<>();
        this.artistToSongMap = new HashMap<>();
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
<<<<<<< HEAD
    //TODO: Might make a seperate class and move this as it is very invase of MusicStores actual purpose.
    private void generateDataset() throws FileNotFoundException{
=======
    private void generateDataset() {
        Scanner fileScanner = null;
>>>>>>> 87fcd23bedf42239a2755054b414a0a88b552fac
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

            Album album = new Album(albumName, artistName, genre, year);
            addAlbumTitle(albumName, album);

            while (albumScanner.hasNextLine()) {
                currLine = albumScanner.nextLine();
                Song song = new Song(currLine, album);
                album.addSong(song);
                titleToSongMap.put(currLine, song);
            }
            albumScanner.close();
        }
    }

    public void addAlbumTitle(String title, Album album) {
        this.titleToAlbumMap.put(title, album);
        this.artistToAlbumMap.put(album.getArtist(), album);
    }

    public void addAlbumArtist(String artist, Album album) {
        this.artistToAlbumMap.put(artist, album);
        this.titleToAlbumMap.put(album.getAlbumTitle(), album);
    }

    // getters for all 4 HashMaps
    public HashMap<String, Album> getTitleToAlbumMap() {
        return titleToAlbumMap;
    }
    
    public HashMap<String, Song> getTitleToSongMap() {
        return titleToSongMap;
    }

    public HashMap<String, Album> getArtistToAlbumMap() {
        return artistToAlbumMap;
    }

<<<<<<< HEAD
    //TODO: impliment rest of search features (made this one to test)
    public Song getSongByName(String name) {
        if (songMap.containsKey(name)) {
            return songMap.get(name);
=======
    public HashMap<String, Song> getArtistToSongMap() {
        return artistToSongMap;
    }

    public void addSong(String title, Song song) {
        this.titleToSongMap.put(title, song);
        this.artistToSongMap.put(song.getArtist(), song);
    }

    public Song getSongByTitle(String title) {
        if (titleToSongMap.containsKey(title)) {
            return titleToSongMap.get(title);
>>>>>>> 87fcd23bedf42239a2755054b414a0a88b552fac
        }
        else {
            return null;
        }
    }

    public Album getAlbumByTitle(String title) {
        if (titleToAlbumMap.containsKey(title)) {
            return titleToAlbumMap.get(title);
        }
        else {
            return null;
        }
    }

    public Song getSongByArtist(String artist) {
        if(artistToSongMap.containsKey(artist)) {
            return artistToSongMap.get(artist);
        }
        else {
            return null;
        }
    }

    public Album getAlbumByArtist(String artist) {
        if(artistToAlbumMap.containsKey(artist)) {
            return artistToAlbumMap.get(artist);
        }
        else {
            return null;
        }
    }

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
}


// >.< bruh!