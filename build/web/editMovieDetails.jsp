
<%@page import="java.util.HashMap"%>
<%@page import="Business.Movie"%>
<%@page import="DAO.MovieDao"%>
<%@page import="Business.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Edit Movie Details Page</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" type="text/css" href="css/shop.css" />
        <link rel="stylesheet" type="text/css" href="css/admin.css" />
        <script type="text/javascript" src="js/jquery.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Slab' rel='stylesheet' type='text/css' />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">
    </head>
    <body>        
        <%
            HashMap<Movie, Integer> cart = (HashMap) session.getAttribute("cart");

            Movie movie = (Movie) session.getAttribute("editMovieDetailsResults");
            User c = (User) session.getAttribute("user");
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
                <h1>Edit Details</h1>
            </div>
        </div>  
        <%
            if (movie != null) {
        %>       
        <div id="wrapperContent">

            <section id="MovieEditBox">
                <h2 id="h2Form" style="text-align: center;">Movie:&nbsp;<%=movie.getTitle()%></h2>
                <h2 id="h2Form">Details:</h2>
                <form method="post" action="UserActionServlet" class="minimal">

                    <label for="ID">
                        <p id="pForm">ID</p>
                        <input type="number"  max="99999999999" name="ID" title="maximum 11 numbers"value="<%=movie.getMovieId()%>" required="required" readonly/>
                    </label>
                    <label for="Title">
                        <p id="pForm">Title</p>
                        <input type="text"  maxlength="50" name="Title"  title="maximum 50 characters" value="<%=movie.getTitle()%>" required="required" />
                    </label>
                    <label for="Description">
                        <p id="pForm">Description</p>
                        <input type="text"  maxlength="1000" name="Description"  title="maximum 1000 characters"value="<%=movie.getDescription()%>" required="required"  />
                    </label>
                    <label for="ReleaseYear">
                        <p id="pForm">Release Year</p>
                        <input type="number" min="1900" max="2100" title="year field must contain four digits" name="ReleaseYear" value="<%=movie.getReleaseYear()%>"  required="required" />
                    </label>
                    <label for="AgeRating">
                        <p id="pForm">Age Rating</p>
                        <input type="text" maxlength="10" name="AgeRating" title="maximum 10 characters" value="<%=movie.getAgeRating()%>" required="required" />
                    </label>
                    <label for="MovieLegth">
                        <p id="pForm">Movie Length</p>
                        <input type="number" max="999" name="MovieLegth" title="maximum 3 numbers" value="<%=movie.getMovieLegth()%>" required="required"  />
                    </label>
                    <label for="MovieUrl">
                        <p id="pForm">Movie Url</p>
                        <input type="text" maxlength="255" name="MovieUrl" title="maximum 255 characters" value="<%=movie.getImgUrl()%>" required="required" />
                    </label>
                    <label for="Price">
                        <p id="pForm">Price</p>
                        <input type="number" max="99.99" step="0.01" name="Price" title="maximum 4 numbers and 2 after decimal" pattern="\d+(\.\d*)?"value="<%=movie.getPrice()%>" required="required"  />
                    </label>
                    <label for="QTY">
                        <p id="pForm">Qty In Stock</p>
                        <input type="number" max="999" name="QTY" title="maximum 3 numbers" value="<%=movie.getQuantityInStock()%>" required="required" />
                    </label>
                    <label for="RatingAvg">
                        <p id="pForm">Rating Avg</p>
                        <input type="number" max="5.0" step="0.1" name="RatingAvg" title="maximum 5 number" value="<%=movie.getRatingAvg()%>" required="required"  />
                    </label>
                    <label for="NumberOfReviews">
                        <p id="pForm">Number Of Reviews</p>
                        <input type="number" max="99999999999" name="NumberOfReviews" title="maximum 11 numbers" value="<%=movie.getRatingNum()%>" required="required"  />
                    </label>
                    <label for="RatingTotal">
                        <p id="pForm">Rating Total</p>
                        <input type="number" max="999999999" name="RatingTotal"  title="maximum 9 numbers" value="<%=movie.getRatingTotal()%>" required="required"  />
                    </label>


                    <input type="hidden" name="action" value="updateMovieDetails" />
                    <button type="submit" value="updateMovieDetails" class="button button--inline-block button--medium btn" id="btn">Update</button>
                </form>
            </section>

        </div>
        <%
            }
        %>
        <div id="footer1">
            <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
            <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
        </div>   

    </body>
</html>