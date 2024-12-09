package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.AlbumService;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping()
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("songs", songService.listSongs());

        return "listSongs";
    }

    @GetMapping("/add")
    public String getAddSongPage(Model model) {
        model.addAttribute("albums", albumService.findAll());

        return "addSong";
    }

    @PostMapping("/add")
    public String saveSong(@RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId) {
        songService.saveSong(title, trackId, genre, releaseYear, albumId);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId, Model model) {
        Song song = songService.findById(songId);
        if (song == null) {
            return "redirect:/songs?error=SongNotFound";
        }

        model.addAttribute("song", song);
        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("artists", artistService.listArtists());

        return "editSong";
    }

    @PostMapping("/edit/{songId}")
    public String saveEditedSong(@PathVariable Long songId,
                                 @RequestParam String title,
                                 @RequestParam String trackId,
                                 @RequestParam String genre,
                                 @RequestParam int releaseYear,
                                 @RequestParam Long albumId) {
        songService.editSong(songId, title, trackId, genre, releaseYear, albumId);

        return "redirect:/songs";
    }

    @PostMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return "redirect:/songs";
    }

}
