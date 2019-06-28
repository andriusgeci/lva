package Command;

import Business.BCrypt;
import Business.User;
import DAO.UserDao;
import Exceptions.DaoException;
import Service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class editPasswordCommand implements Command {

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
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String retypeNewPassword = request.getParameter("retypeNewPassword");
        String email = user.getEmail();
        //cheking if user information to all fields and all passwords match
        if (firstName != null && lastName != null && email != null && oldPassword != null && newPassword != null && retypeNewPassword != null && !firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !oldPassword.isEmpty() && !newPassword.isEmpty() && !retypeNewPassword.isEmpty() && BCrypt.checkpw(oldPassword, user.getPassword()) && newPassword.matches(retypeNewPassword)) {
            //Password Encryption
            String hash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            //adding all details to the constructor
            User temp = new User(ID, firstName, lastName, hash, email);
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
