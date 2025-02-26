package main.view;

import java.io.FileNotFoundException;

import main.model.*;

public class View {
    /* Users need to be able to do the following: 
     * Search for songs by title or artist
     * Search for albums by title or artist
     * When returning a song from search, print title, artist, and album.
     * Search from user library. 
     */
    public static void main(String[] args) throws FileNotFoundException {
        LibraryModel library = LibraryModel.getInstance();
        MusicStore musicStore = MusicStore.getInstance();
        musicStore.generateDataset();
        // Song rockYouLikeAHurricane = new Song("Rock You Like A Hurricane", new Album("Love At First Sting", "The Scorpions", "Rock", 1984));
        // rockYouLikeAHurricane.setRating(5);
        // Song noOneLikeYou = new Song("No One Like You", new Album("Blackout", "The Scorpions", "Rock", 1982));
        // library.addSong(rockYouLikeAHurricane);
        // library.addSong(noOneLikeYou);
        MainMenu mainMenu = new MainMenu();
        mainMenu.executeMenu();
    }
}
