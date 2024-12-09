package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import java.util.List;

@Data
public class Artist {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private List<Song> songs;

    public Artist(Long id, String firstName, String lastName, String bio, List<Song> songs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.songs = songs;
    }
}
