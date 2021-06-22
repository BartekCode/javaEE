package readstack.client.signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import readstack.domain.api.UserRegistration;
import readstack.domain.api.UserService;

import java.io.IOException;

@WebServlet("/signup")
public class SignupController extends HttpServlet {
    private final UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegistration userRegistration = getUserData(req);
        userService.register(userRegistration);
        req.setAttribute("user", userRegistration);
        resp.sendRedirect(req.getContextPath());
    }


    //pobieramy dane z formularza i tworzymy obiekt UserRegistration
    private UserRegistration getUserData(HttpServletRequest req) {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        return new UserRegistration(username,email,password);
    }
}