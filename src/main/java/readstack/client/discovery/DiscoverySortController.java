package readstack.client.discovery;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import readstack.domain.api.CategoryName;
import readstack.domain.api.CategoryService;
import readstack.domain.api.DiscoveryBasicInfo;
import readstack.domain.api.DiscoveryService;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/sort")
public class DiscoverySortController extends HttpServlet {
    DiscoveryService discoveryService = new DiscoveryService();
    CategoryService categoryService = new CategoryService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DiscoveryBasicInfo> discoveries = discoveryService.findAll();
        String sort = request.getParameter("sort");
        if (sort.equals("votesup")) {
            Collections.sort(discoveries, Comparator.comparing(DiscoveryBasicInfo::getVoteCount).reversed());
        } else if (sort.equals("votesdown")) {
            Collections.sort(discoveries, Comparator.comparing(DiscoveryBasicInfo::getVoteCount));
        } else if (sort.equals("data")) {
        Collections.sort(discoveries, Comparator.comparing(DiscoveryBasicInfo::getDateAdded).reversed());
    }

        List<CategoryName> categories = categoryService.findAllCategoryNames();
        request.setAttribute("categories", categories);
        request.setAttribute("discoveries", discoveries);

        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);


    }
}

