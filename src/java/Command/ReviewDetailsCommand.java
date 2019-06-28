/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Business.Movie;
import Business.Review;
import Business.User;
import DAO.ReviewDao;
import Exceptions.DaoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class ReviewDetailsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String forwardToJsp;

        //Getting details from review form
        User u = (User) session.getAttribute("user");
        Movie m = (Movie) session.getAttribute("resultMovieDetails");
        String comment = request.getParameter("comment");
        String rating = request.getParameter("rating");
        double aDouble = Double.parseDouble(rating);

        if (comment != null) {

            Review review = new Review();
            ReviewDao rDao = new ReviewDao();

            review.setMovieID(m.getMovieId());
            review.setUserID(u.getUserId());
            review.setReviewText(comment);
            review.setReviewRating(aDouble);

            boolean answer = false;
            try {
                answer = rDao.addReview(review);
            } catch (DaoException ex) {
                System.out.println("DaoException  rDao.addReview() in reviewDetailsCommand " + ex);
            }
            if (answer == true) {
                forwardToJsp = "/movieDetails.jsp";
            } else {
                forwardToJsp = "/error.html";
            }
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }

}
