package Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class SortSelectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp;

        String selectedOption = request.getParameter("sortingSelect");
        session.setAttribute("value", selectedOption);
        if (selectedOption != null) {
            forwardToJsp = "/shop.jsp";
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }
}
