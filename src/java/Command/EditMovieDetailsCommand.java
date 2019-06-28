/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Business.Movie;
import DAO.MovieDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class EditMovieDetailsCommand implements Command {

    public EditMovieDetailsCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String forwardToJsp;

        String details = request.getParameter("movieDescription");
        MovieDao mDao = new MovieDao();

        if (details != null && !details.isEmpty()) {
            Movie mDetails = mDao.findMovieByTitle(details);

            session.setAttribute("editMovieDetailsResults", mDetails);
            forwardToJsp = "/editMovieDetails.jsp";
        } else {
            forwardToJsp = "/error.html";
        }

        return forwardToJsp;

    }

}
