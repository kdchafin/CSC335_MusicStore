package main.model;

public class Song extends Album {
    private String title;
    private int rating;
    private boolean isFavorite;

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
    
    @Override
    public String toString() {
        return this.title + " by " + this.getArtist() + " on the album \"" + this.getAlbumTitle() + "\"";
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if(rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        if(rating == 5) {
            this.isFavorite = true;
        }
        this.rating = rating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Song)) {
            return false;
        }
        Song other = (Song) obj;
        return this.getTitle().equals(other.getTitle()) 
            && this.getArtist().equals(other.getArtist()) 
            && this.getAlbumTitle().equals(other.getAlbumTitle())
            && this.getGenre().equals(other.getAlbumTitle());
    }
}