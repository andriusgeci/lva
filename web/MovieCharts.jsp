

<%@page import="java.util.Collections"%>
<%@page import="Business.MovieRatingComparator"%>
<%@page import="java.util.HashMap"%>
<%@page import="Business.Movie"%>
<%@page import="DAO.MovieDao"%>
<%@page import="Business.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Movie Charts Page</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" type="text/css" href="css/shop.css" />
        <link rel="stylesheet" type="text/css" href="css/movieCharts.css" />
        <link href='http://fonts.googleapis.com/css?family=Josefin+Slab' rel='stylesheet' type='text/css' />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">


        <script type="text/javascript" src="js/modernizr.custom.86080.js"></script>
        <script type="text/javascript" src="js/jquery.js"></script>
        <%-- rating stars --%>  
        <script rel="stylesheet" src="js/jquery.rating.js"></script>
        <link rel="stylesheet" href="css/rating.css">
    </head>
    <body>        
        <%
            HashMap<Movie, Integer> cart = (HashMap) session.getAttribute("cart");
            User c = (User) session.getAttribute("user");
            MovieDao mDao = new MovieDao();
            ArrayList<Movie> allMovies = mDao.displayAllMovies();

            MovieRatingComparator sortingByRatingPopularity = new MovieRatingComparator();
            Collections.sort(allMovies, sortingByRatingPopularity);

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
                <h1>Movie Charts</h1>
            </div>
        </div>  

        <div id="wrapperContent">

            <ul id="movies"> 
                <%for (int i = 0; i < 10; i++) {%>
                <li>
                    <div class="list-item">
                        <div>
                            <h1 id="MovieChartPlace" style="color:#18C9D2"><%=i + 1%>.</h1>
                        </div>
                        <form name="myForm" id="myForm" action="UserActionServlet" method="post">
                            <a  href="#"  >
                                <input type="hidden" name="action" value="movieDetails" /> 
                                <input type="hidden" name="movieDetailsValue" value="<%=allMovies.get(i).getTitle()%>" /> 
                                <input type="image" src="<%=allMovies.get(i).getImgUrl()%>"  onclick="parentNode.submit();
                                        return false;"   width="250" height="342" /> 
                            </a>
                        </form>
                        <div id="movieDescription">
                            <h1 style="color:#18C9D2"><%=allMovies.get(i).getTitle()%> <%=allMovies.get(i).getReleaseYear()%></h1>
                            <p><%=allMovies.get(i).getDescription()%></p>
                        </div>  

                        <div id="movieInfo">
                            <p>
                            <form id="ratingForm">
                                <%if (allMovies.get(i).getRatingAvg() < 1.0) {%>
                                <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="5" />
                                <%} else if ((allMovies.get(i).getRatingAvg() >= 1) && (allMovies.get(i).getRatingAvg() < 2.0)) { %>
                                <input type="radio" class="star" name="rating" disabled="disabled" value="1" checked="checked"/>
                                <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="5" />
                                <%} else if ((allMovies.get(i).getRatingAvg() >= 2) && (allMovies.get(i).getRatingAvg() < 3.0)) { %>
                                <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="2" checked="checked"/>
                                <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="5" /> 
                                <%} else if ((allMovies.get(i).getRatingAvg() >= 3) && (allMovies.get(i).getRatingAvg() < 4.0)) { %>
                                <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="3" checked="checked"/>
                                <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="5" />
                                <%} else if ((allMovies.get(i).getRatingAvg() >= 4) && (allMovies.get(i).getRatingAvg() < 5.0)) { %>
                                <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="4" checked="checked"/>
                                <input type="radio" class="star" name="rating" disabled="disabled" value="5" />
                                <%} else if (allMovies.get(i).getRatingAvg() == 5) { %>
                                <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                                <input type="radio" class="star" name="rating" disabled="disabled" value="5" checked="checked"/>   
                                <%}%>   
                                <%if (allMovies.get(i).getRatingNum() > 0) {%>
                                <p id="numRatingCount"> (<%=allMovies.get(i).getRatingNum()%>)</p>
                                <%}%>
                            </form>
                            <%
                                if (allMovies.get(i).getQuantityInStock() != 0) {
                            %>
                            <h2 style="color:#5BC77C; padding-top:110px">In Stock</h2>
                            <p>&euro; <%=allMovies.get(i).getPrice()%></p>
                            <%} %>
                            <form name="myForm1" id="myForm1" action="UserActionServlet" method="post">
                                <input type="hidden" name="action" value="addToCart" />
                                <%
                                    if (allMovies.get(i).getQuantityInStock() != 0) {
                                %>
                                <button type="submit" id="movieShopValue" name="movieShopValue" value="<%=allMovies.get(i).getTitle()%> " class="button button--inline-block button--medium">Add to cart</button>
                                <%
                                } else {
                                %>
                                <h2 style="color:#FF1700; padding-top: 205px" id="outOfStock">Out of Stock</h2>
                                <%}%>
                            </form>
                        </div>

                    </div> 
                </li>
                <%}%>
            </ul>

        </div>
        <div id="footer1">
            <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
            <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
        </div>   

    </body>
</html>
