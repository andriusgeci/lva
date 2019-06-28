package Command;

import Business.User;
import DAO.UserDao;
import Exceptions.DaoException;
import Service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class UpdateEmailCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String forwardToJsp = null;
        UserDao usr = new UserDao();
        User user = (User) session.getAttribute("user");
        //Getting details from user form
        int ID = user.getUserId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        String email = request.getParameter("email");
        //cheking if user information to all fields
        if (firstName != null && lastName != null && email != null && password != null && !firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            //adding all details to the constructor
            User temp = new User(ID, firstName, lastName, password, email);
            boolean updateUser = false;
            try {
                //passing constructor to updateUser method in DAO
                updateUser = usr.updateUser(temp);
            } catch (DaoException ex) {
                System.out.println("DaoException in RegisterCommand cDao.addUser(cc);" + ex.getMessage());
            }
            //if updateUser == true it will forward to Success.jsp
            if (updateUser == true) {
                UserService userService = new UserService();
                User userLoggingIn = userService.update(email);
                session.setAttribute("user", userLoggingIn);
                forwardToJsp = "/Success.jsp";
            } else {//else forward to error.html
                forwardToJsp = "/error.html";
            }
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }

}
