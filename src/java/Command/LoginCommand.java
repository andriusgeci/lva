package Command;

import Business.BCrypt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Business.User;
import DAO.UserDao;
import Exceptions.DaoException;
import Service.UserService;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;
        //The user wants to log in...
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email != null && password != null) {

            UserService userService = new UserService();

            UserDao uDao = new UserDao();
            User u = null;
            try {
                u = uDao.findUserByEmail(email);
            } catch (DaoException ex) {
                System.out.println("DaoException in LoginCommand uDao.findUserByEmail(email)" + ex.getMessage());
            }

            User userLoggingIn = null;
            if (u != null) {
                if (BCrypt.checkpw(password, u.getPassword())) {
                    //Use the UserServive class to login...
                    userLoggingIn = userService.login(email, u.getPassword());
                }
                if (userLoggingIn != null) {
                    //If login successful, store the session id for this client...
                    HttpSession session = request.getSession();
                    String clientSessionId = session.getId();
                    session.setAttribute("loggedSessionId", clientSessionId);
                    session.setAttribute("user", userLoggingIn);
                    forwardToJsp = "/index.jsp";
                } else {
                    forwardToJsp = "/loginFailure.jsp";
                }
            } else {
                forwardToJsp = "/error.html";
            }
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }
}
