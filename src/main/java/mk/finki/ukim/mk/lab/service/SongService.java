package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;

import java.util.List;

public interface SongService {

    List<Song> listSongs();

    void addArtistToSong(Artist artist, Song song);

    Song findByTrackId(String trackId);

    void deleteSong(Long id);

    Song findById(Long id);

    void editSong(Long songId, String title, String trackId, String genre, int releaseYear, Long albumId, List<Long> performerIds);

    void saveSong(String title, String trackId, String genre, int releaseYear, Long albumId, List<Long> performerIds);
}
