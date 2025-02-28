package main.view;

import java.util.ArrayList;
import main.model.Album;

public class MultiAlbumsMenu extends Menu {
    MultiAlbumsMenu(Menu previousMenu, ArrayList<Album> album) {
        super("");
        defaultOption = () -> { previousMenu.executeMenu(); };
        int index = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(album.size() + " Albums Found:");
        for(Album a : album) {
            index++;
            addOption(index, a.getTitle(), () -> { System.out.println("You selected " + a.getTitle()); SelectedAlbumMenu selectedAlbumMenu = new SelectedAlbumMenu(this, a); selectedAlbumMenu.executeMenu(); });
            sb.append("\n[" + index + "] " + a.getTitle());
        }
        menu += sb.toString();
        colorizeBrackets();
    }
}
