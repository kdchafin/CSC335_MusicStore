package main.view;

import java.io.FileNotFoundException;

import main.model.*;

public class View {
    public static void main(String[] args) throws FileNotFoundException {
        MusicStore musicStore = MusicStore.getInstance();
        musicStore.generateDataset();

        AccountMenu accountMenu = new AccountMenu();
        accountMenu.executeMenu();
    }
}