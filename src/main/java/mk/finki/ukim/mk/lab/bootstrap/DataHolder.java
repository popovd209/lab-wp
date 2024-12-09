package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Song> songs = null;
    public static List<Artist> artists = null;
    public static List<Album> albums = null;

    @PostConstruct
    public void init() {
        albums = new ArrayList<>();
        albums.add(new Album(1L, "A Night at the Opera", "Rock", "1975", new ArrayList<>()));
        albums.add(new Album(2L, "Thriller", "Pop", "1982", new ArrayList<>()));
        albums.add(new Album(3L, "Nevermind", "Grunge", "1991", new ArrayList<>()));
        albums.add(new Album(4L, "Imagine", "Pop", "1971", new ArrayList<>()));
        albums.add(new Album(5L, "Hotel California", "Rock", "1992", new ArrayList<>()));

        artists = new ArrayList<>();
        artists.add(new Artist(1L, "Freddie", "Mercury", "Lead vocalist of Queen.", new ArrayList<>()));
        artists.add(new Artist(2L, "Michael", "Jackson", "The 'King of Pop'.", new ArrayList<>()));
        artists.add(new Artist(3L, "Kurt", "Cobain", "Lead singer and guitarist of Nirvana.", new ArrayList<>()));
        artists.add(new Artist(4L, "John", "Lennon", "Co-founder of The Beatles.", new ArrayList<>()));
        artists.add(new Artist(5L, "Whitney", "Houston", "An iconic singer with one of the greatest voices.", new ArrayList<>()));

        songs = new ArrayList<>();
        songs.add(new Song("T01", "Bohemian Rhapsody", "Rock", 1975, new ArrayList<>(), albums.get(0)));
        songs.add(new Song("T02", "Billie Jean", "Pop", 1982, new ArrayList<>(), albums.get(1)));
        songs.add(new Song("T03", "Smells Like Teen Spirit", "Grunge", 1991, new ArrayList<>(), albums.get(2)));
        songs.add(new Song("T05", "Imagine", "Pop", 1971, new ArrayList<>(), albums.get(3)));
        songs.add(new Song("T04", "Hotel California", "Rock", 1976, new ArrayList<>(), albums.get(4)));
    }
}

