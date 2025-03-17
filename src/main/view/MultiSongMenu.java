package main.view;

import java.util.ArrayList;
import main.model.Song;

public class MultiSongMenu extends Menu {
    MultiSongMenu(Menu previousMenu, ArrayList<Song> songs) {
        super("");
        defaultOption = () -> { previousMenu.executeMenu(); };
        int index = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(songs.size() + " Songs Found:");
        for(Song s : songs) {
            index++;
            boolean inLibrary = previousMenu.getClass().getSimpleName().equals("LibraryMenu");
            addOption(index, s.getTitle(), () -> { System.out.println("You selected " + s.toString()); SelectedSongMenu selectedSongMenu = new SelectedSongMenu(this, s, inLibrary); selectedSongMenu.executeMenu(); });
            sb.append("\n[" + index + "] " + s.toString());
        }
        menu += sb.toString();
        colorizeBrackets();
    }
}
