package Command;

import Business.BCrypt;

import Business.User;
import DAO.UserDao;
import Exceptions.DaoException;
import Service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String forwardToJsp;
        //Getting details from registration form
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("lName");
        String password = request.getParameter("password");
        String rpassword = request.getParameter("rPassword");
        String email = request.getParameter("email");
        //Password Encryption
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        //Calling UserDao Object
        UserDao cDao = new UserDao();

        User temp = null;
        try {
            temp = cDao.findUserByEmail(email);
        } catch (DaoException ex) {
            System.out.println("DaoException in RegisterCommand findUserByEmail" + ex.getMessage());
        }
        //if user not a null it means its already on database
        if (temp != null) {
            forwardToJsp = "/existingCustomerError.jsp";
        }//cheking if user information to all fields and password matching 
        else if (firstName != null && lastName != null && email != null && password != null && rpassword != null && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty() && !rpassword.isEmpty() && password.matches(rpassword)) {
            //adding all details to the constructor
            User cc = new User(firstName, lastName, hash, email);
            boolean addUsr = false;
            try {
                //passing constructor to addUser method in DAO
                addUsr = cDao.addUser(cc);
            } catch (DaoException ex) {
                System.out.println("DaoException in RegisterCommand cDao.addUser(cc);" + ex.getMessage());
            }
            //if addUsr == true it will forward to index.jsp
            if (addUsr == true) {
                UserService userService = new UserService();
                User userLoggingIn = userService.login(email, hash);
                session.setAttribute("user", userLoggingIn);
                forwardToJsp = "/index.jsp";
            } else {//else forward to error.html
                forwardToJsp = "/error.html";
            }
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }
}
