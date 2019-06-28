
<%@page import="Business.Order"%>
<%@page import="DAO.OrderDao"%>
<%@page import="Business.OrderDetails"%>
<%@page import="DAO.OrderDetailsDao"%>
<%@page import="DAO.AddressDao"%>
<%@page import="Business.Address"%>
<%@page import="java.util.HashMap"%>
<%@page import="Business.Movie"%>
<%@page import="DAO.MovieDao"%>
<%@page import="Business.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Order History</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" type="text/css" href="css/shop.css" />
        <link href='http://fonts.googleapis.com/css?family=Josefin+Slab' rel='stylesheet' type='text/css' />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/admin.css" />
    </head>
    <body>  
        <%
            //declareing
            HashMap<Movie, Integer> cart = (HashMap) session.getAttribute("cart");
            User c = (User) session.getAttribute("user");
            OrderDetailsDao ord = new OrderDetailsDao();
            MovieDao mv = new MovieDao();
            OrderDao or = new OrderDao();
            ArrayList<Order> order = or.findOrdersByUserId(c.getUserId());
        %>
        <div id="header">
            <div id="wrapper">
                <%  if (c != null) {
                %> 
                <a href="userProfile.jsp">
                    <strong>Welcome: </strong> <%=c.getFirstName()%> <%=c.getLastName()%>
                </a>
                <% } else {%>
                <a>
                    <strong>Welcome: </strong> Guest, please Login or Register.
                </a>
                <% }%>
                <span class="right">   
                    <%
                        if (c == null) {
                    %> 
                    <a href="register.jsp"><i id="headerIcons" class="material-icons">create</i>Register</a>
                    <a href="login.jsp"><i id="headerIcons" class="material-icons">exit_to_app</i>Login</a>
                    <%} else{%>
                    <%
                        if (c.isAdmin() == true) {
                    %> 
                    <a href="admin.jsp"> <i id="headerIcons" class="material-icons">settings</i>Admin</a>
                    <%}%>
                    <a href="userProfile.jsp"> <i id="headerIcons" class="material-icons">account_circle</i>My Profile</a>
                    <a href="logout.jsp"><i id="headerIcons" class="material-icons">exit_to_app</i>Logout</a>
                    <% }%>
                    <% if (cart != null) {%>
                    <a href="shoppingCart.jsp"><i  id="headerIcons" class="material-icons">shopping_cart</i><%=cart.size()%></a>
                    <% } else { %>
                    <a href="shoppingCart.jsp"><i  id="headerIcons" class="material-icons">shopping_cart</i>0</a>
                    <% }%>
                </span>
            </div>
        </div>
        <div id="wrapper">
            <div id="navigation">
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="shop.jsp">Shop</a></li>                
                    <li><a href="MovieCharts.jsp">Movie Charts</a></li>
                    <li><a href="NewReleases.jsp">New Releases</a></li>
                    <li><a href="ComingSoon.jsp">Coming Soon</a></li>
                </ul> 
            </div>
        </div>
        <div id="grow">
            <div id="wrapper">
                <h1>Order History</h1>
            </div>
        </div>  
        <div id="wrapperContent">
            <div id="navigation">
                <ul>
                    <li><a href="userProfile.jsp">Personal Information</a></li>
                    <li><a href="Addresses.jsp">Addresses</a></li>
                    <li><a href="MyReviews.jsp">My Reviews</a></li>
                    <li><a href="OrderHistory.jsp">Order History</a></li>   
                </ul> 
            </div>
            <%if(order.isEmpty()){%>
            <section id="OrderHistory">
            <h1 style="text-align: center">You Don't have any Orders</h1>
            </section>
            <%}else{%>
            <%
                ArrayList<OrderDetails> ordDetails = ord.findOrderDetailsByOrderId(order.get(0).getOrderID());%>
            <%for (int i = 0; i < order.size(); i++) {%>
            <section id="OrderHistory">
                <b>Order Identification Number: </b><%=order.get(i).getOrderID()%> &nbsp;&nbsp;&nbsp; <b>Date Order Placed: </b><%=order.get(i).getOrderDate().toLocaleString()%>
                <hr>
                <%for (int j = 0; j < ordDetails.size(); j++) {
                %>
                <b>Title :</b>&nbsp;<%= mv.findMovieById(ordDetails.get(j).getMovieID()).getTitle()%></br>
                <b>Quantity :</b>&nbsp;<%=ordDetails.get(j).getQuantity()%></br>  
                <b>Price :</b>&nbsp;<%=ordDetails.get(j).getPrice()%></br><hr>
                <%}%>            
                <b>Total Price :</b> <%=order.get(i).getTotalOrderPrice()%>
            </section>
            <%}}%>   
        </div>
        <div id="footer1">
            <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
            <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
        </div>   
    </body>
</html>
