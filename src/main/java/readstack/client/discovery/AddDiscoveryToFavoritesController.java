package readstack.client.discovery;

import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import readstack.domain.api.DiscoveryBasicInfo;
import readstack.domain.api.DiscoveryService;
import readstack.domain.api.FavDiscoveryInfo;
import readstack.domain.api.FavDiscoveryService;
import readstack.domain.favDiscovery.FavDiscoveriesDao;
import readstack.domain.user.UserDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint( value = "GET" , rolesAllowed = "USER"),
                @HttpMethodConstraint(value = "POST", rolesAllowed = "USER")
        }
)

@WebServlet("/discovery/addtofavorites")
public class AddDiscoveryToFavoritesController extends HttpServlet {

    private UserDao userDao = new UserDao();
    private FavDiscoveryService favDiscoveryService = new FavDiscoveryService();
    List<FavDiscoveryInfo> favList;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FavDiscoveryInfo favDiscoveryInfo = creatFavDisc(request);
        try {
            favDiscoveryService.addFavDisc(favDiscoveryInfo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect(request.getContextPath());

    }
//juz mamy metode ktora znajduje ulubionediscoveries teraz musimy tylko napisac controlller dodający jej do tabeli favDiscovery w MySql



    private FavDiscoveryInfo creatFavDisc(HttpServletRequest request){
        Integer userId = userDao.findByUsername(request.getUserPrincipal().getName()).orElseThrow().getId();
        Integer discoveryId = Integer.valueOf(request.getParameter("id"));
        return new FavDiscoveryInfo(discoveryId,userId);
    }

}