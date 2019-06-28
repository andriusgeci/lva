package Command;

import Business.User;
import DAO.UserDao;
import Exceptions.DaoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class UpdateUserDetailsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String forwardToJsp;
        UserDao uDao = new UserDao();
        User user = (User) session.getAttribute("editUserDetailsResults");
        //Getting details from editUserDetails form

        String name = request.getParameter("name");
        String lName = request.getParameter("lName");
        String email = request.getParameter("email");
        String admin = request.getParameter("answer");

        //checking null and empty imput values from form
        if (user != null && name != null && lName != null && email != null && !name.isEmpty() && !lName.isEmpty() && !email.isEmpty()) {
            //creating user object if values not null or empty
            User cc = new User(user.getUserId(), name, lName, user.getPassword(), email, Boolean.parseBoolean(admin));

            boolean answer = false;
            try {
                answer = uDao.updateUser(cc);
            } catch (DaoException ex) {
                System.out.println("Exception occured in the UpdateUserDetailsCommand " + ex);
            }
            if (answer == true) {
                forwardToJsp = "/admin.jsp";
            } else {
                forwardToJsp = "/error.html";
            }
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }

}
