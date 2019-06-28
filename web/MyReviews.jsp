
<%@page import="Business.Review"%>
<%@page import="DAO.ReviewDao"%>
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
        <title>My Reviews</title>
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
            ReviewDao rev = new ReviewDao();
            ArrayList<Review> reviews = rev.findReviewByUserId(c.getUserId());
        %>
        <div id="header">
            <div id="wrapper">
                <%
                    if (c != null) {
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
                <div class="clr"></div>
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
                <h1>My Reviews</h1>
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

        </div>
        <div id="wrapperContent">           
            <style type="text/css">
                .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
                .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;border-top-width:1px;border-bottom-width:1px;}
                .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;border-top-width:1px;border-bottom-width:1px;}
                .tg .tg-baqh{text-align:center;vertical-align:top; min-width:  100px;}
                .tg .tg-spn1{background-color:#f9f9f9;text-align:center}
            </style>
            <table class="tg">

                <%if (reviews.isEmpty()) {%>
                <section id="OrderHistory">
                    <h1 style="text-align: center">You Don't have any Reviews</h1>
                </section>

                <%} else {%>
                <tr>
                    <th class="tg-baqh">Review Date</th>
                    <th class="tg-baqh">Review Rating</th>
                    <th class="tg-baqh">Review Text</th>
                </tr>
                <% for (int i = 0; i < reviews.size(); i++) {%>
                <tr>
                    <td class="tg-spn1"><%=reviews.get(i).getReviewDate()%></td>
                    <td class="tg-spn1"><%=reviews.get(i).getReviewRating()%></td>
                    <td class="tg-spn1"><%=reviews.get(i).getReviewText()%></td>
                </tr>
                <%}
                    }%>
            </table>
        </div>


        <div id="footer1">
            <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
            <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
        </div>   
    </body>
</html>
