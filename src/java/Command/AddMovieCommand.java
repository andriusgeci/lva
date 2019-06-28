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

class AddMovieCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp;

        //Getting details from editMovieDetails form
        String Title = request.getParameter("Title");
        String Description = request.getParameter("Description");
        String ReleaseYear = request.getParameter("ReleaseYear");
        String AgeRating = request.getParameter("AgeRating");
        String MovieLegth = request.getParameter("MovieLegth");
        String MovieUrl = request.getParameter("MovieUrl");
        String Price = request.getParameter("Price");
        String QTY = request.getParameter("QTY");

        //checking null and empty imput values from form
        if (Title != null && Description != null && ReleaseYear != null
                && AgeRating != null && MovieLegth != null && MovieUrl != null && Price != null
                && QTY != null && !Title.isEmpty() && !Description.isEmpty() && !ReleaseYear.isEmpty()
                && !AgeRating.isEmpty() && !MovieLegth.isEmpty() && !MovieUrl.isEmpty() && !Price.isEmpty()
                && !QTY.isEmpty()) {

            MovieDao mDao = new MovieDao();

            Movie mv = new Movie(Title, Description, Integer.parseInt(ReleaseYear), Integer.parseInt(MovieLegth), AgeRating, MovieUrl, Double.parseDouble(Price), Integer.parseInt(QTY));

            boolean answer = false;

            try {
                answer = mDao.addMovie(mv);
            } catch (DaoException ex) {
                System.out.println("Exception occured in the AddMovieCommand " + ex);
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
