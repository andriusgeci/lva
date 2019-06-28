package Command;

import Business.Movie;
import DAO.MovieDao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class SliderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String forwardToJsp;

        String lower = request.getParameter("lower");
        String upper = request.getParameter("upper");

        MovieDao mDao = new MovieDao();

        ArrayList<Movie> allMovies;
        if (!lower.isEmpty() && !upper.isEmpty()) {

            allMovies = mDao.findMoviesByPriceBetweenMinAndMaxPrice(Integer.parseInt(lower), Integer.parseInt(upper));
            if (allMovies != null) {
                session.setAttribute("movieArrayBetweenPrice", allMovies);
                session.setAttribute("lowerFromCommand", lower);
                session.setAttribute("upperFromCommand", upper);
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
