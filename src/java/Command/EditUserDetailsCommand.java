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
import javax.servlet.http.HttpSession;

class EditUserDetailsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String forwardToJsp;

        String email = request.getParameter("userDescription");
        UserDao uDao = new UserDao();

        if (email != null && !email.isEmpty()) {

            User uDetailsFromDao = null;
            try {
                uDetailsFromDao = uDao.findUserByEmail(email);
            } catch (DaoException ex) {
                System.out.println("Exception occured in the EditUserDetailsCommand " + ex);
            }

            session.setAttribute("editUserDetailsResults", uDetailsFromDao);
            forwardToJsp = "/editUserDetails.jsp";
        } else {
            forwardToJsp = "/error.html";
        }

        return forwardToJsp;
    }

}
