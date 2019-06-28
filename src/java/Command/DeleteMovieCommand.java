package Command;

import Business.Movie;
import DAO.MovieDao;
import Exceptions.DaoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class DeleteMovieCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String forwardToJsp;

        String movie = (String) request.getParameter("movie");

        if (movie != null) {

            MovieDao mDao = new MovieDao();

            try {
                Movie movieFromDao = mDao.findMovieByTitle(movie);
                mDao.deleteMovie(movieFromDao);
            } catch (DaoException ex) {
                System.out.println("Exception occured in the DeleteMovieCommand" + ex);
            }
            forwardToJsp = "/admin.jsp";

        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }
}
