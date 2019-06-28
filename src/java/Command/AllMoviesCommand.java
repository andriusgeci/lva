package Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class AllMoviesCommand implements Command {

    public AllMoviesCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp;

        session.setAttribute("lowerFromCommand", null);
        session.setAttribute("upperFromCommand", null);
        session.setAttribute("movieArrayByGenre", null);
        session.setAttribute("movieArrayBetweenPrice", null);

        request.setAttribute("sortingSelect", null);
        request.setAttribute("selected", null);
        session.setAttribute("value", null);
        forwardToJsp = "/shop.jsp";

        return forwardToJsp;
    }
}
