/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Business.Address;
import Business.User;
import DAO.AddressDao;
import Exceptions.DaoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class AddAddressCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String forwardToJsp;

        //Getting details from Address form
        int usrID = user.getUserId();
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
            Address temp = new Address(usrID, address1, address2, city, county, pCode, country, Boolean.parseBoolean(primaryAddress));

            boolean updated = false;

            try {
                AddressDao adrD = new AddressDao();
                updated = adrD.addAddress(temp);
            } catch (DaoException ex) {
                System.out.println("Exception occured in the AddAddressCommand " + ex);
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
