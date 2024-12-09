package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {
    public List<Song> findAll() {
        return DataHolder.songs;
    }

    public Optional<Song> findByTrackId(String trackId) {
        return DataHolder.songs.stream().filter(s -> s.getTrackId().equals(trackId)).findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        save(song.getTrackId(), song.getTitle(), song.getGenre(), song.getReleaseYear(), song.getPerformers());
        return artist;
    }

    public Optional<Song> save(String trackId, String title, String genre, int releaseYear, List<Artist> performers) {
        Song song = new Song(trackId, title, genre, releaseYear, performers);
        DataHolder.songs.removeIf(p -> p.getTrackId().equals(song.getTrackId()));
        DataHolder.songs.add(song);
        return Optional.of(song);
    }
}
