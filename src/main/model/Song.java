package main.model;

public class Song extends Album {
    private String title;

    public Song(String title, Album album) {
        super(album.getAlbumTitle(),
              album.getArtist(),
              album.getGenre(), 
              album.getYear());
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}