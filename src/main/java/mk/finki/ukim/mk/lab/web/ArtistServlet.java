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
import java.util.List;

@WebServlet(name = "artist-servlet", urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {
    private final SongService songService;
    private final ArtistService artistService;
    private final SpringTemplateEngine templateEngine;

    public ArtistServlet(SongService songService, ArtistService artistService, SpringTemplateEngine templateEngine) {
        this.songService = songService;
        this.artistService = artistService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String trackId = req.getParameter("trackId");

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        Song song = songService.findByTrackId(trackId);
        List<Artist> artists = artistService.listArtists();
        context.setVariable("song", song);
        context.setVariable("artists", artists);

        templateEngine.process("artistsList.html", context, resp.getWriter());
    }
}
