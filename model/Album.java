import java.util.ArrayList;

public class Album {
    
    private int yearReleased;
    private String author;
    private String albumTitle;
    private String genre;
    private ArrayList<Song> songsOnAlbum = new ArrayList<>();
    
    public Album(String albumTitle, String author, String genre, int yearReleased) {
        this.albumTitle = albumTitle;
        this.author = author;
        this.genre = genre;
        this.yearReleased = yearReleased;
    }

    public addSong(String songTitle, String author) {
        
    }

    public addSong(Song song) {
        
    }
}