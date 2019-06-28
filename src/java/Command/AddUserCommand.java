/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Business.BCrypt;
import Business.User;
import DAO.UserDao;
import Exceptions.DaoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class AddUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp;

        //Getting details from addUser form
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("lName");
        String password = request.getParameter("password");
        String rpassword = request.getParameter("rPassword");
        String email = request.getParameter("email");
        String admin = request.getParameter("answer");

        //Password Encryption
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        //Calling UserDao Object
        UserDao cDao = new UserDao();

        User temp = null;
        try {
            temp = cDao.findUserByEmail(email);
        } catch (DaoException ex) {
            System.out.println("DaoException in AddUserCommand findUserByEmail" + ex.getMessage());
        }
        //if user not a null it means its already on database
        if (temp != null) {
            forwardToJsp = "/existingCustomerErrorAdmin.jsp";
        }//cheking if user information to all fields and password matching 
        else if (firstName != null && lastName != null && email != null && password != null && rpassword != null && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty() && !rpassword.isEmpty() && password.matches(rpassword)) {
            //adding all details to the constructor
            User cc = new User(firstName, lastName, hash, email, Boolean.parseBoolean(admin));
            boolean addUsr = false;
            try {
                //passing constructor to addUser method in DAO
                addUsr = cDao.addUser(cc);
            } catch (DaoException ex) {
                System.out.println("DaoException in AddUserCommand cDao.addUser(cc);" + ex.getMessage());
            }
            //if addUsr == true it will forward to admin.jsp
            if (addUsr == true) {

                forwardToJsp = "/admin.jsp";
            } else {//else forward to error.html
                forwardToJsp = "/error.html";
            }
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }
}
