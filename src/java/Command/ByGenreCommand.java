/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Business.Movie;
import DAO.MovieInGenreDaoJoin;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class ByGenreCommand implements Command {

    public ByGenreCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String forwardToJsp;

        int genreID = Integer.parseInt(request.getParameter("inputGenre"));

        if (genreID != 0) {
            MovieInGenreDaoJoin gDao = new MovieInGenreDaoJoin();
            ArrayList<Movie> movieArrayByGenre = gDao.findMoviesByGenreId(genreID);
            session.setAttribute("movieArrayByGenre", movieArrayByGenre);
            forwardToJsp = "/shop.jsp";
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }

}
