package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.repository.AlbumRepository;
import mk.finki.ukim.mk.lab.repository.ArtistRepository;
import mk.finki.ukim.mk.lab.repository.SongRepository;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public void addArtistToSong(Artist artist, Song song) {
        List<Artist> artists = song.getPerformers();
        artists.add(artist);
        song.setPerformers(artists);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId).orElse(null);
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    public void editSong(Long songId, String title, String trackId, String genre, int releaseYear, Long albumId) {
        Song song = songRepository.findById(songId).orElse(null);
        if (song == null) {
            throw new IllegalArgumentException("Song not found");
        }
        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(albumRepository.findById(albumId).orElse(null));
        songRepository.save(song);
    }

    @Override
    public void createSong(String title, String trackId, String genre, int releaseYear, Long albumId) {
        songRepository.save(new Song(trackId, title, genre, releaseYear, new ArrayList<>(), albumRepository.findById(albumId).orElse(null)));
    }

    @Override
    public void addPerformerToSong(String trackId, Long performerId) {
        Song song = songRepository.findByTrackId(trackId).orElse(null);
        if (song == null) {
            throw new IllegalArgumentException("Song not found");
        }
        Artist artist = artistRepository.findById(performerId).orElse(null);

        song.getPerformers().add(artist);
        songRepository.save(song);
    }
}
