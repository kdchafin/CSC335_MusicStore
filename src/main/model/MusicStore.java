import java.util.HashMap;

public class MusicStore {

    private HashMap<String, Album> albumMap;
    private HashMap<String, Song> songMap;

    public MusicStore() {
        
        this.albumMap = new HashMap<>();
        this.songMap = new HashMap<>();
    }
}