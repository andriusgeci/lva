package Command;

import Business.Movie;
import DAO.MovieDao;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HashMap<Movie, Integer> cart = new HashMap<>(100);

        HttpSession session = request.getSession();
        String forwardToJsp;
        //movie id from shop page
        String mShop = request.getParameter("movieShopValue");
        //get qty from input
        int qty = 1;
        if (request.getParameter("quantity") != null) {
            qty = Integer.parseInt(request.getParameter("quantity"));
        } else {
            qty = 1;
        }
        //movie object from movie details page
        Movie m = (Movie) (request.getSession().getAttribute("resultMovieDetails"));
        HashMap<Movie, Integer> cartOld = (HashMap<Movie, Integer>) (request.getSession().getAttribute("cart"));

        //check if movie id from shop page not null then process it otverwise go to next else if
        if (mShop != null && qty != 0) {
            MovieDao mDao = new MovieDao();
            Movie mov = new Movie();
            mov = mDao.findMovieByTitle(mShop);
            if (mov != null && cartOld != null) {
                cart.putAll(cartOld);
                if (cart.containsKey(mov)) {
                    cart.put(mov, cart.get(mov) + qty);
                } else {
                    cart.put(mov, qty);
                }
                session.setAttribute("cart", cart);
                forwardToJsp = "/shop.jsp";
            } else if (mov != null && cartOld == null && qty != 0) {
                if (cart.containsKey(mov)) {
                    cart.put(mov, cart.get(mov) + qty);
                } else {
                    cart.put(mov, qty);
                }
                session.setAttribute("cart", cart);
                forwardToJsp = "/shop.jsp";
            } else {
                forwardToJsp = "/error.html";
            }
        } else if (m != null && cartOld != null && qty != 0) { //if movie id from shop page was null then process movie object from movie details page
            cart.putAll(cartOld);
            if (cart.containsKey(m)) {
                cart.put(m, cart.get(m) + qty);
            } else {
                cart.put(m, qty);
            }
            session.setAttribute("cart", cart);
            forwardToJsp = "/shop.jsp";
        } else if (m != null && cartOld == null && qty != 0) {
            if (cart.containsKey(m)) {
                cart.put(m, cart.get(m) + qty);
            } else {
                cart.put(m, qty);
            }
            session.setAttribute("cart", cart);
            forwardToJsp = "/shop.jsp";
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }
}
