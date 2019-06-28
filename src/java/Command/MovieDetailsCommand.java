package Command;

import Business.Movie;
import DAO.MovieDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MovieDetailsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String forwardToJsp;

        String details = request.getParameter("movieDetailsValue");
        MovieDao mDao = new MovieDao();

        if (details != null && !details.isEmpty()) {
            Movie mDetails = mDao.findMovieByTitle(details);

            session.setAttribute("resultMovieDetails", mDetails);
            forwardToJsp = "/movieDetails.jsp";
        } else {
            forwardToJsp = "/error.html";
        }

        return forwardToJsp;
    }
}
