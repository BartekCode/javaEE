package readstack.client.comments;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import readstack.domain.api.*;

import java.io.IOException;

@WebServlet("/discovery/comment")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint( value = "GET" , rolesAllowed = "USER"),
                @HttpMethodConstraint(value = "POST", rolesAllowed = "USER")
        }
)
public class AddCommentController extends HttpServlet {
CommentService commentService = new CommentService();



//get formularz dodania komentarza
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       request.getRequestDispatcher("/WEB-INF/views/add-comment.jsp").forward(request,response);
    }
//dodanie komentarza
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiscoveryComment commentSave = creatCommnet(req);
        commentService.addComment(commentSave);
        resp.sendRedirect(req.getContextPath());
    }

    private DiscoveryComment creatCommnet(HttpServletRequest request){
        String author = request.getUserPrincipal().getName();
        Integer discoveryId = Integer.valueOf(request.getParameter("id"));
        return new DiscoveryComment(author,request.getParameter("description"),discoveryId);
    }

}