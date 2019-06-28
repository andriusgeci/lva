
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
        <title>Edit Address</title>
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
            HashMap<Movie, Integer> cart = (HashMap) session.getAttribute("cart");
            User c = (User) session.getAttribute("user");
            Address address = (Address) session.getAttribute("addressDetails");
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
                <h1>Edit Address</h1>
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
            <section id="AddressBox">

                <hr>
                <form name="updateAddress" id="formValidation" method="post" action="UserActionServlet" class="minimal">
                    <label for="PrimaryAddress">


                        <% if (address.isPrimaryAddress()) { %>
                        <b>Primary/Shipping Address</b>

                        <% } else { %>
                        <input name="tick" type="radio" value="true" /><b>Primary/Shipping</b>
                        <input checked="checked" name="tick" type="radio" value="false" /> <b>Secondary Address</b>
                        <% }%>
                        <hr>
                    </label>
                    <label for="Address1">
                        <p id="pForm">Address Line 1 </p>
                        <input type="text" minlength="3" maxlength="100" value="<%=address.getAddress1()%>" name="address1" required="required" title="Please enter Address line 1" />
                    </label>
                    <label for="Address2">
                        <p id="pForm">Address Line 2 </p>
                        <%if (address.getAddress2() == null) {%>
                        <input type="text" minlength="3" maxlength="100" value="" name="address2" />
                        <%} else {%>
                        <input type="text" minlength="3" maxlength="100" value="<%=address.getAddress2()%>" name="address2" />
                        <%}%>
                    </label>
                    <label for="City">
                        <p id="pForm">City</p>
                        <input type="text" minlength="3" maxlength="100" value="<%=address.getCity()%>" name="city" required="required" title="Please enter you're City"/>
                    </label>
                    <label for="County">
                        <p id="pForm">County</p>
                        <input type="text" minlength="3" maxlength="100" value="<%=address.getCounty()%>" name="county" />
                    </label>
                    <label for="PostalCode">
                        <p id="pForm">Postal Code</p>
                        <% if (address.getPostalCode() == null) {%>
                        <input type="text" minlength="3" maxlength="100" value="" name="postalCode"/>
                        <%} else {%>
                        <input type="text" minlength="3" maxlength="100" value="<%=address.getPostalCode()%>" name="postalCode"/>
                        <%}%>
                    </label>
                    <label for="Country">
                        <p id="pForm">Country</p>
                        <input type="text" minlength="3" maxlength="100"  value="<%=address.getCountry()%>" name="country" required="required" title="Please enter you're Country"/>
                    </label>
                    <br>
                    <hr>

                    <input type="hidden" name="action" value="updateAddressDetails" />
                    <button type="submit" name="action" value="updateAddressDetails" class="button button--inline-block button--medium btn" id="btn">Edit Address</button>

                </form> 
                <a href='Addresses.jsp'>
                    <button class="button button--inline-block button--medium btn" id="btn">  &nbsp;&nbsp;&nbsp;Go Back &nbsp;&nbsp;  </button>
                </a>
            </section>
        </div>
        <div id="footer1">
            <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
            <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
        </div>   
    </body>
</html>
