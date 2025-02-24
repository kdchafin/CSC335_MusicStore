package main.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MusicStore {

    private HashMap<String, Album> albumMap;
    private HashMap<String, Song> songMap;

    public MusicStore() throws FileNotFoundException{
        this.albumMap = new HashMap<>();
        this.songMap = new HashMap<>();
        generateDataset();
    }

    /*
     * Generate both hashmaps of album and song objects for reference in code. Might make a controller class and move this as it
     * is very invase of MusicStores actual purpose.
     */
    private void generateDataset() throws FileNotFoundException{
        File albumFile = new File("albums.txt");
        Scanner fileScanner = new Scanner(albumFile);
        
        ArrayList<String> albumFileNames = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            String currLine = fileScanner.nextLine();
            String[] albumName = currLine.split(",");
            String nameString = albumName[0] + "_" + albumName[1];
            albumFileNames.add(nameString);
        }
        fileScanner.close();

        for (String s : albumFileNames) {
            File nameFile = new File(s);
            Scanner albumScanner  = new Scanner(nameFile);
            String currLine = albumScanner.nextLine();

            String[] albumInfo = currLine.split(",");

            String albumName = albumInfo[0];
            String artistName = albumInfo[1];
            String genre = albumInfo[2];
            int year = Integer.parseInt(albumInfo[3]);

            Album album = new Album(albumName, artistName, genre, year);
            albumMap.put(albumName, album);

            while (albumScanner.hasNextLine()) {
                currLine = albumScanner.nextLine();

                Song song = new Song(currLine, album);
                songMap.put(currLine, song);
            }
            albumScanner.close();
        }
    }

    public void addAlbum(String key, Album value) {
        this.albumMap.put(key, value);
    }

    // Getters for albumMap and songMap
    public HashMap<String, Album> getAlbumMap() {
        return albumMap;
    }

    public void addSong(String key, Song value) {
        this.songMap.put(key, value);
    }

    public HashMap<String, Song> getSongMap() {
        return songMap;
    }
}


// >.< bruh!