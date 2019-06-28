/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Business.User;
import DAO.UserDao;
import Exceptions.DaoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class DeleteUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp;

        String userEmail = (String) request.getParameter("user");

        if (userEmail != null) {

            UserDao uDao = new UserDao();

            try {
                User userFromDao = uDao.findUserByEmail(userEmail);
                uDao.deleteUser(userFromDao);

            } catch (DaoException ex) {
                System.out.println("Exception occured in the DeleteUserCommand" + ex);
            }
            forwardToJsp = "/admin.jsp";

        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }

}
