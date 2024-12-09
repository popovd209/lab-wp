package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.List;

@Data
public class Album {
    private Long id;
    private String name;
    private String genre;
    private String releaseYear;
    private List<Song> songs;

    public Album(Long id, String name, String genre, String releaseYear, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.songs = songs;
    }
}
