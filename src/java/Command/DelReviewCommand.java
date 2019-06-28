/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import DAO.ReviewDao;
import Exceptions.DaoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class DelReviewCommand implements Command {

    public DelReviewCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String forwardToJsp;
        boolean result = false;
        int reviewID = Integer.parseInt(request.getParameter("reviewID"));

        if (reviewID != 0) {

            ReviewDao rDao = new ReviewDao();

            try {
                result = rDao.deleteReviewById(reviewID);
            } catch (DaoException ex) {
                System.out.println("SQLException occured in the DelReviewCommand()" + ex.getMessage());
            }

            if (result != false) {

                forwardToJsp = "/shop.jsp";
            } else {
                forwardToJsp = "/error.html";
            }
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }

}
