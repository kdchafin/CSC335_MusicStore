package main.model;

public class Song extends Album {
    private String title;
    private int rating;
    private boolean isFavorite = false;
    String[] emojiRatings = {"🔥", "🔥🔥", "🔥🔥🔥", "🔥🔥🔥🔥", "🔥🔥🔥🔥🔥"};
    private Album album;

    public Song(String title, Album album) {
        super(album.getTitle(),
              album.getArtist(),
              album.getGenre(), 
              album.getYear());
        this.title = title;
        this.album = album;
    }

    public Song(Song song) {
        super(song);
        this.title = song.getTitle();
        this.album = song.album;
        this.rating = song.getRating();
        this.isFavorite = song.isFavorite();
    }

    public String getTitle() {
        return title;
    }

    public Album getAlbum() {
        return new Album(album);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.title + " by " + this.getArtist() + " on the album \"" + album.getTitle() + "\"");
        if(this.rating != 0) {
            sb.append(" [" + emojiRatings[this.rating-1] + "]");
        }
        return sb.toString(); 
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
            && this.album.getTitle().equals(other.album.getTitle())
            && this.getGenre().equals(other.getGenre());
    }
}