package Command;

import Business.BCrypt;
import Business.User;
import DAO.UserDao;
import Exceptions.DaoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePasswordCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp = null;
        UserDao cDao = new UserDao();

        String oldEmail = request.getParameter("email");
        String password = request.getParameter("password");
        String rpassword = request.getParameter("rpassword");

        if (oldEmail != null && password != null && rpassword != null && !oldEmail.isEmpty() && !password.isEmpty() && !rpassword.isEmpty() && password.matches(rpassword)) {
            User temp = null;
            try {
                temp = cDao.findUserByEmail(oldEmail);
                if (temp != null) {
                    String hash = BCrypt.hashpw(password, BCrypt.gensalt());
                    User updatedUser = null;
                    if (temp.isAdmin()) {
                        updatedUser = new User(temp.getUserId(), temp.getFirstName(), temp.getLastName(), hash, temp.getEmail(), true);
                    } else {
                        updatedUser = new User(temp.getUserId(), temp.getFirstName(), temp.getLastName(), hash, temp.getEmail(), false);
                    }
                    if (cDao.updateUser(updatedUser)) {
                        forwardToJsp = "/login.jsp";
                    }
                } else {
                    forwardToJsp = "/error.html";
                }
            } catch (DaoException ex) {
                System.out.println("DaoException in ChangePasswordCommand " + ex.getMessage());
            }
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }
}
