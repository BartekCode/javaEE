package readstack.client.profile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import readstack.domain.api.*;
import readstack.domain.discovery.Discovery;
import readstack.domain.discovery.DiscoveryDao;
import readstack.domain.user.User;
import readstack.domain.user.UserDao;
import readstack.domain.vote.Vote;
import readstack.domain.vote.VoteDao;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/profil")
public class ProfileController extends HttpServlet {
    private final static UserDao userDao = new UserDao();
    private final static VoteDao voteDao = new VoteDao();
    private final static DiscoveryDao discoverDao = new DiscoveryDao();
    private final static DiscoveryVoteService voteService= new DiscoveryVoteService();
    private final static DiscoveryService discoveryService = new DiscoveryService();
    private final static FavDiscoveryService favDiscovery= new FavDiscoveryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loggedEmail = userDao.findByUsername(request.getUserPrincipal().getName()).orElseThrow().getEmail();
        Integer userId = userDao.findByUsername(request.getUserPrincipal().getName()).orElseThrow().getId();
        List<DiscoveryBasicInfo> looggedUserDiscoveries = discoveryService.findAllbyUserId(userDao.findByUsername(request.getUserPrincipal().getName()).orElseThrow().getId());
        List<DiscoveryBasicInfo> allFavsDiscByUserId = favDiscovery.findFavUserDisc(userId);


        request.setAttribute("userfavdisc", allFavsDiscByUserId);
        request.setAttribute("loggedusersdiscoveries", looggedUserDiscoveries);
        request.setAttribute("logedemail", loggedEmail);
        request.getRequestDispatcher("/WEB-INF/views/profil.jsp").forward(request, response);


    }}

