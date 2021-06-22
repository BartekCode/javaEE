package readstack.client.authentication;

import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import readstack.domain.user.User;
import readstack.domain.user.UserDao;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@WebServlet("/login")
//dodajemy servlet security metdoa get role dozwolone USER
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER")
        }
)
public class LoginController extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.sendRedirect(request.getContextPath());

    }

}