package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.enumerations.Role;
import mk.finki.ukim.mk.lab.repository.AlbumRepository;
import mk.finki.ukim.mk.lab.repository.ArtistRepository;
import mk.finki.ukim.mk.lab.repository.SongRepository;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(AlbumRepository albumRepository, ArtistRepository artistRepository, SongRepository songRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        List<User> users = new ArrayList<>();
        if (this.userRepository.count() == 0) {
            users.add(new User("elena.atanasoska", passwordEncoder.encode("ea"), "Elena", "Atanasoska", Role.ROLE_USER));
            users.add(new User("darko.sasanski", passwordEncoder.encode("ds"), "Darko", "Sasanski", Role.ROLE_USER));
            users.add(new User("ana.todorovska", passwordEncoder.encode("at"), "Ana", "Todorovska", Role.ROLE_USER));
            users.add(new User("admin", passwordEncoder.encode("admin"), "admin", "admin", Role.ROLE_ADMIN));
            this.userRepository.saveAll(users);
        }

        List<Album> albums = new ArrayList<>();
        if (this.albumRepository.count() == 0) {
            albums.add(new Album(1L, "A Night at the Opera", "Rock", "1975", new ArrayList<>()));
            albums.add(new Album(2L, "Thriller", "Pop", "1982", new ArrayList<>()));
            albums.add(new Album(3L, "Nevermind", "Grunge", "1991", new ArrayList<>()));
            albums.add(new Album(4L, "Imagine", "Pop", "1971", new ArrayList<>()));
            albums.add(new Album(5L, "Hotel California", "Rock", "1992", new ArrayList<>()));
            this.albumRepository.saveAll(albums);
        }

        List<Artist> artists = new ArrayList<>();
        if (this.artistRepository.count() == 0) {
            artists.add(new Artist(1L, "Freddie", "Mercury", "Lead vocalist of Queen.", new ArrayList<>()));
            artists.add(new Artist(2L, "Michael", "Jackson", "The 'King of Pop'.", new ArrayList<>()));
            artists.add(new Artist(3L, "Kurt", "Cobain", "Lead singer and guitarist of Nirvana.", new ArrayList<>()));
            artists.add(new Artist(4L, "John", "Lennon", "Co-founder of The Beatles.", new ArrayList<>()));
            artists.add(new Artist(5L, "Whitney", "Houston", "An iconic singer with one of the greatest voices.", new ArrayList<>()));
            this.artistRepository.saveAll(artists);
        }

        if (this.songRepository.count() == 0) {
            List<Song> songs = new ArrayList<>();
            songs.add(new Song("T01", "Bohemian Rhapsody", "Rock", 1975, List.of(artists.get(0)), albums.get(0)));
            songs.add(new Song("T02", "Billie Jean", "Pop", 1982, List.of(artists.get(1)), albums.get(1)));
            songs.add(new Song("T03", "Smells Like Teen Spirit", "Grunge", 1991, List.of(artists.get(2)), albums.get(2)));
            songs.add(new Song("T05", "Imagine", "Pop", 1971, List.of(artists.get(3)), albums.get(3)));
            songs.add(new Song("T04", "Hotel California", "Rock", 1976, List.of(artists.get(4)), albums.get(4)));
            songRepository.saveAll(songs);
        }
    }
}

