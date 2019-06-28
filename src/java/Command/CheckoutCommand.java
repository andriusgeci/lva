/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Business.Movie;
import Business.User;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class CheckoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String forwardToJsp;

        User user = (User) session.getAttribute("user");
        HashMap<Movie, Integer> cartOld = (HashMap) (request.getSession().getAttribute("cart"));

        if (user != null && cartOld != null) {
            forwardToJsp = "/Checkout.jsp";
        } else {
            forwardToJsp = "/login.jsp";
        }
        return forwardToJsp;

    }

}
