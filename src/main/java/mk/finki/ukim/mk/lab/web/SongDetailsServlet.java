package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "song-details-servlet", urlPatterns = "/songDetails")
public class SongDetailsServlet extends HttpServlet {
    private final SongService songService;
    private final ArtistService artistService;
    private final SpringTemplateEngine templateEngine;

    public SongDetailsServlet(SongService songService, ArtistService artistService, SpringTemplateEngine templateEngine) {
        this.songService = songService;
        this.artistService = artistService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String trackId = req.getParameter("trackId");
        long artistId = Long.parseLong(req.getParameter("artistId"));

        Song song = songService.findByTrackId(trackId);
        Artist artist = artistService.findById(artistId);

        songService.addArtistToSong(artist, song);

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("song", song);

        templateEngine.process("songDetails.html", context, resp.getWriter());
    }
}
