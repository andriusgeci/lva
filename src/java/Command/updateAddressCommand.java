package Command;

import Business.Address;
import DAO.AddressDao;
import Exceptions.DaoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class updateAddressCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp;

        int id = Integer.parseInt(request.getParameter("param"));

        AddressDao adrD = new AddressDao();

        try {
            Address adrDetails = adrD.findAddressById(id);
            session.setAttribute("addressDetails", adrDetails);

        } catch (DaoException ex) {
            System.out.println("Exception occured in updateAddressCommand" + ex);
        }
        forwardToJsp = "/updateAddress.jsp";
        return forwardToJsp;
    }
}
