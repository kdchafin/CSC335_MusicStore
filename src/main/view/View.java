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
        MusicStore musicStore = MusicStore.getInstance();
        musicStore.generateDataset();

        AccountMenu accountMenu = new AccountMenu();
        accountMenu.executeMenu();
    }
}