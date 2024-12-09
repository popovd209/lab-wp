package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import java.util.List;

@Data
public class Song {
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> performers;
    private Album album;

    public Song(String trackId, String title, String genre, int releaseYear, List<Artist> performers, Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = performers;
        this.album = album;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
