package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artists")
public class ArtistController {
    private final SongService songService;
    private final ArtistService artistService;

    public ArtistController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping("/add-performer/{trackId}")
    public String getAddPerformerForm(@PathVariable String trackId, Model model) {
        Song song = songService.findByTrackId(trackId);
        if (song == null) {
            return "redirect:/songs?error=Song with the given track ID does not exist.";
        }

        model.addAttribute("song", song);
        model.addAttribute("artists", artistService.listArtists());
        return "artistsList";
    }

    @PostMapping("/add-performer")
    public String addPerformerToSong(@RequestParam String trackId, @RequestParam Long artistId) {
        Song song = songService.findByTrackId(trackId);
        Artist artist = artistService.findById(artistId);

        if (song == null || artist == null) {
            return "redirect:/songs?error=Invalid track ID or artist ID.";
        }

        songService.addPerformerToSong(trackId, artistId);
        return "redirect:/songs/details/" + song.getId();
    }
}
