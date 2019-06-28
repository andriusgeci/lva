package Command;

import Business.Movie;
import DAO.MovieDao;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DelFromCartCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String forwardToJsp;
        MovieDao mm = new MovieDao();

        String movieIdFromCart = request.getParameter("movieIdToDelete");

        HashMap<Movie, Integer> cartOld = (HashMap) (request.getSession().getAttribute("cart"));

        if (cartOld != null && movieIdFromCart != null) {

            Movie m = mm.findMovieByTitle(movieIdFromCart);

            if (cartOld.containsKey(m) && cartOld.get(m).intValue() > 1) {
                cartOld.replace(m, cartOld.get(m) - 1);
                session.setAttribute("cart", cartOld);
            } else {
                cartOld.remove(m);
                session.setAttribute("cart", cartOld);
            }
            forwardToJsp = "/shoppingCart.jsp";
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;

    }

}
