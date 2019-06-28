package Command;

import Business.Address;
import DAO.AddressDao;
import Exceptions.DaoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class UpdateAddressDetailsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //get session
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        // get address dao
        AddressDao adrD = new AddressDao();
        // get address details from the session
        Address adr = (Address) session.getAttribute("addressDetails");

        //Getting details from address form
        int ID = adr.getAddressID();
        int usrID = adr.getUserID();
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String county = request.getParameter("county");
        String pCode = request.getParameter("postalCode");
        String country = request.getParameter("country");
        String primaryAddress = request.getParameter("tick");

        //cheking for the null and empty values// address2, county and postal code can be NULL or EMPTY
        if (address1 != null && city != null && country != null && !address1.isEmpty() && !city.isEmpty() && !country.isEmpty()) {
            //adding all details to the  Address constructor
            Address temp = new Address(ID, usrID, address1, address2, city, county, pCode, country, Boolean.parseBoolean(primaryAddress));
            boolean updated = false;
            try {//passing contructor to the updateAddress method it will return true if its updated
                updated = adrD.updateAddress(temp);
            } catch (DaoException ex) {
                System.out.println("Exception occured in the UpdateAddressDetailsCommand " + ex);
            }
            if (updated == true) {
                forwardToJsp = "/Addresses.jsp";
            } else {
                forwardToJsp = "/error.html";
            }
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }

}
