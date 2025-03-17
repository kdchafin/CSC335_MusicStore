package main.model;

public class Song extends Album {
    private String title;
    private int rating;
    private String[] emojiRatings = {"🔥", "🔥🔥", "🔥🔥🔥", "🔥🔥🔥🔥", "🔥🔥🔥🔥🔥"};
    private Album album;
    private int plays;

    public Song(String title, Album album) {
        super(album.getTitle(),
              album.getArtist(),
              album.getGenre(), 
              album.getYear());
        this.title = title;
        this.album = album;
        this.plays = 0;
    }

    // Copy constructor
    public Song(Song song) {
        super(song);
        this.title = song.getTitle();
        this.album = song.album;
        this.rating = song.getRating();
        this.plays = song.plays;
    }

    public void play() {
        this.plays += 1;
    }

    public int getPlays() {
        return this.plays;
    }

    //WARNING: all getters in this class return references to the original objects
    public int getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public Album getAlbum() {
        return album;
    }

    public boolean isFavorite() {
        return rating == 5;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.title + " by " + 
                  this.getArtist() + ", \"" + 
                  album.getTitle() + "\" (" + 
                  album.getYear() + ")" + " [" + 
                  this.getGenre() + "]" +
                  " - " + this.plays + " plays");

        if(this.rating != 0) {
            sb.append(" [" + emojiRatings[this.rating-1] + "]");
        }
        return sb.toString(); 
    }

    public void setRating(int rating) {
        if(rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = rating;
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