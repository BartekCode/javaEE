package readstack.client.vote;

import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import readstack.domain.api.DiscoveryVote;
import readstack.domain.api.DiscoveryVoteService;
import readstack.domain.user.User;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
//Głosowanie będzie się odbywało poprzez przejście na wskazany adres URL. Przykładowo jeżeli ktoś przejdzie do strony
// ocalhost:8080/readstack/discovery/vote?id=2&type=UP to odda głos "na tak" na znalezisko z identyfikatorem 2.
// Analogicznie w celu zagłosowania "na nie" należy zmienić wartość parametru type na DOWN. Głosować będą mogli tylko użytkownicy zalogowani,
// dlatego należy także zadbać o konfigurację zabezpieczeń w kontrolerze.

@WebServlet("/discovery/vote")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint( value = "GET" , rolesAllowed = "USER")
        }
)
public class DiscoveryVoteController extends HttpServlet {
    private DiscoveryVoteService voteService = new DiscoveryVoteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DiscoveryVote discoveryVote = createDiscoveryVote(request);
        voteService.addVote(discoveryVote);
        response.sendRedirect(request.getContextPath()); //i powrot do strony glownej

    }

    private DiscoveryVote createDiscoveryVote(HttpServletRequest request) {
        Integer discoveryId = Integer.valueOf(request.getParameter("id"));
        String voteType = request.getParameter("type");
        String username = request.getUserPrincipal().getName();
        DiscoveryVote discoveryVote = new DiscoveryVote(username,discoveryId, voteType);
        return discoveryVote;
    }

}