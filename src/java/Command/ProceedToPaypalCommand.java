/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Business.Address;
import Business.Movie;
import Business.Order;
import Business.User;
import DAO.AddressDao;
import DAO.OrderDao;
import DAO.OrderDetailsDao;
import Exceptions.DaoException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class ProceedToPaypalCommand implements Command {

    public ProceedToPaypalCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        HashMap<Movie, Integer> cartOld = (HashMap) (request.getSession().getAttribute("cart"));

        User c = (User) session.getAttribute("user");
        AddressDao addressDao = new AddressDao();
        OrderDao orderDao = new OrderDao();
        OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
        String forwardToJsp;

        //Getting details from registration form
        String address = request.getParameter("address1");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String postalCode = request.getParameter("postalCode");
        String county = request.getParameter("county");

//        String password = request.getParameter("password");
        String totalFromCart = (String) session.getAttribute("total");
        boolean complete = false;
        Address registerUserAddress = null;
        Order registerUserOrders = null;

        if (c != null) {
            ArrayList<Address> allAddressByUserID = addressDao.findAddressByUserId(c.getUserId());
            if (allAddressByUserID.isEmpty()) {
                registerUserAddress = new Address(c.getUserId(), address, "", city, county, postalCode, country, true);
                try {
                    addressDao.addAddress(registerUserAddress);
                } catch (DaoException ex) {
                    System.out.println("Exception occured in the command at addressDao.addAddress(registerUserAddress); " + ex.getMessage());
                }
                registerUserAddress = addressDao.findAddressByUserId(c.getUserId()).get(0);
            } else {
                for (int i = 0; i < allAddressByUserID.size(); i++) {
                    if (allAddressByUserID.get(i).isPrimaryAddress()) {
                        registerUserAddress = allAddressByUserID.get(i);
                    }
                }
            }

            registerUserOrders = new Order(c.getUserId(), registerUserAddress.getAddressID(), Double.parseDouble(totalFromCart));

            if (cartOld != null && !cartOld.isEmpty()) {
                try {
                    complete = orderDao.addOrder(registerUserOrders);
                    if (complete == true) {

                        int order_ID_After = orderDao.findOrderByUserId(c.getUserId()).getOrderID();

                        orderDetailsDao.addOrderDetails(cartOld, order_ID_After);

                    }
                } catch (DaoException ex) {
                    System.out.println("Exception occured in the command at orderDao.transaction(c, add, registerUserOrders, cartOld); " + ex.getMessage());
                }
            }
        }

        if (complete == true) {
            forwardToJsp = "/checkoutComplete.jsp";
        } else {
            forwardToJsp = "/error.html";
        }
        return forwardToJsp;
    }

}
