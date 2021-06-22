package readstack.client.home;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import readstack.domain.api.*;
import readstack.domain.discovery.DiscoveryDao;

import java.util.Collections;
import java.util.Comparator;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("")
public class HomeController extends HttpServlet {
    private static DiscoveryService discoveryService = new DiscoveryService();
    private CategoryService categoryService = new CategoryService();



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<DiscoveryBasicInfo> discoveries = discoveryService.findAll();
        Collections.sort(discoveries, ((o1, o2) -> o2.getDateAdded().compareTo(o1.getDateAdded())));//sortowanie od najnowszego
        request.setAttribute("discoveries", discoveries);
        List<CategoryName> categories = categoryService.findAllCategoryNames();
        request.setAttribute("categories", categories);


        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request,response);
    }
//    Jedyne co się tutaj dzieje, to stworzenie obiektu DiscoveryService,
//    pobranie wszystkich znalezisk, zapisanie ich jako atrybut żądania i przekazanie sterowania do strony widoku, czyli index.jsp

}