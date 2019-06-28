/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Business.Movie;
import DAO.MovieDao;
import Exceptions.DaoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class UpdateMovieDetailsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp;
        MovieDao mDao = new MovieDao();

        //Getting details from editMovieDetails form
        String ID = request.getParameter("ID");
        String Title = request.getParameter("Title");
        String Description = request.getParameter("Description");
        String ReleaseYear = request.getParameter("ReleaseYear");
        String AgeRating = request.getParameter("AgeRating");
        String MovieLegth = request.getParameter("MovieLegth");
        String MovieUrl = request.getParameter("MovieUrl");
        String Price = request.getParameter("Price");
        String QTY = request.getParameter("QTY");
        String RatingAvg = request.getParameter("RatingAvg");
        String NumberOfReviews = request.getParameter("NumberOfReviews");
        String RatingTotal = request.getParameter("RatingTotal");
        //checking null and empty imput values from form
        if (ID != null && Title != null && Description != null && ReleaseYear != null
                && AgeRating != null && MovieLegth != null && MovieUrl != null && Price != null
                && QTY != null && RatingAvg != null && NumberOfReviews != null && RatingTotal != null
                && !ID.isEmpty() && !Title.isEmpty() && !Description.isEmpty() && !ReleaseYear.isEmpty()
                && !AgeRating.isEmpty() && !MovieLegth.isEmpty() && !MovieUrl.isEmpty() && !Price.isEmpty()
                && !QTY.isEmpty() && !RatingAvg.isEmpty() && !NumberOfReviews.isEmpty() && !RatingTotal.isEmpty()) {
            //creating movie object if values not null or empty
            Movie updatedMovie = new Movie(
                    Integer.parseInt(ID),
                    Title,
                    Description,
                    Integer.parseInt(ReleaseYear),
                    AgeRating,
                    Integer.parseInt(MovieLegth),
                    MovieUrl, Double.parseDouble(Price),
                    Integer.parseInt(QTY),
                    Double.parseDouble(RatingAvg),
                    Integer.parseInt(NumberOfReviews),
                    Long.parseLong(RatingTotal)
            );
            boolean answer = false;
            try {
                answer = mDao.updateMovie(updatedMovie);
            } catch (DaoException ex) {
                System.out.println("Exception occured in the UpdateMovieDetailsCommand " + ex);
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
