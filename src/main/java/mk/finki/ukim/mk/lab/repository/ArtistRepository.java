package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {

    public List<Artist> findAll() {
        return DataHolder.artists;
    }

    public Optional<Artist> findById(Long id) {
        return DataHolder.artists.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Artist> save(Long id, String firstName, String lastName, String bio, List<Song> songs) {
        Artist artist = new Artist(id, firstName, lastName, bio, songs);
        DataHolder.artists.removeIf(i -> i.getId().equals(id));
        DataHolder.artists.add(artist);
        return Optional.of(artist);
    }

    public Artist addSongToArtist(Artist artist, Song song) {
        artist.getSongs().add(song);
        save(artist.getId(), artist.getFirstName(), artist.getLastName(), artist.getBio(), artist.getSongs());
        return artist;
    }
}
