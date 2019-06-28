package Command;

import Business.Address;
import DAO.AddressDao;
import Exceptions.DaoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAddressCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp;

        int id = Integer.parseInt(request.getParameter("delete"));

        AddressDao adrD = new AddressDao();

        try {
            Address adrDetails = adrD.findAddressById(id);
            adrD.deleteAddress(adrDetails);

        } catch (DaoException ex) {
            System.out.println("Exception occured in deleteAddressCommand" + ex);
        }
        forwardToJsp = "/Addresses.jsp";
        return forwardToJsp;
    }
}
