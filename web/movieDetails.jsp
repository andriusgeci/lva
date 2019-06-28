
<%@page import="java.util.HashMap"%>
<%@page import="DAO.UserDao"%>
<%@page import="Business.Review"%>
<%@page import="Business.Genre"%>
<%@page import="DAO.MovieInGenreDaoJoin"%>
<%@page import="DAO.ReviewDao"%>
<%@page import="java.util.List"%>
<%@page import="Business.Movie"%>
<%@page import="DAO.MovieDao"%>
<%@page import="Business.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Movie Details Page</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <script type="text/javascript" src="js/jquery.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Slab' rel='stylesheet' type='text/css' />
        <%-- rating stars --%>  
        <script rel="stylesheet" src="js/jquery.rating.js"></script>
        <link rel="stylesheet" href="css/rating.css">   
        <link rel="stylesheet" type="text/css" href="css/shopDetails.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">
        <%-- ----------------------- --%> 

        <script type="text/javascript">
            $(document).ready(function () {
                $(".tabs-menu a").click(function (event) {
                    event.preventDefault();
                    $(this).parent().addClass("current");
                    $(this).parent().siblings().removeClass("current");
                    var tab = $(this).attr("href");
                    $(".tab-content").not(tab).css("display", "none");
                    $(tab).fadeIn();
                });
            });
        </script>

    </head>
    <body>        
        <%
            HashMap<Movie, Integer> cart = (HashMap) session.getAttribute("cart");
            User c = (User) session.getAttribute("user");
            Movie movies = (Movie) session.getAttribute("resultMovieDetails");
            UserDao uDao = new UserDao();
            ArrayList<User> allUsers = uDao.findAllUsers();

            MovieDao mDao = new MovieDao();
            Movie movies1 = mDao.findMovieByTitle(movies.getTitle());

            ReviewDao rDao = new ReviewDao();
            ArrayList<Review> allReviews = rDao.findAllReviews();

            MovieInGenreDaoJoin MovieInGenreDaoJoinDao = new MovieInGenreDaoJoin();
            ArrayList<Genre> movieGenres = MovieInGenreDaoJoinDao.findGenresByMovieId(movies.getMovieId());

            int leftReview = 0;
            if (c != null) {
                for (int i = 0; i < allReviews.size(); i++) {

                    if (allReviews.get(i).getUserID() == c.getUserId() && allReviews.get(i).getMovieID() == movies.getMovieId()) {
                        leftReview = 1;

                    }
                }
            }

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
                <h1>Movie Details</h1>
            </div>
        </div>  
        <%
            if (movies != null) {

        %>
        <div id="wrapperContent">
            <div id="leftcolumn">
                <img src="<%=movies.getImgUrl()%>" width="540" height="742">
            </div>

            <div id="rightcolumn">
                <h1 id="movieDetailsH1"><%=movies.getTitle()%> <%=movies.getReleaseYear()%> 
                    <form id="ratingForm">

                        <%if (movies1.getRatingAvg() < 1.0) {%>
                        <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="5" />
                        <%} else if ((movies1.getRatingAvg() >= 1) && (movies1.getRatingAvg() < 2.0)) { %>
                        <input type="radio" class="star" name="rating" disabled="disabled" value="1" checked="checked"/>
                        <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="5" />
                        <%} else if ((movies1.getRatingAvg() >= 2) && (movies1.getRatingAvg() < 3.0)) { %>
                        <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="2" checked="checked"/>
                        <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="5" /> 
                        <%} else if ((movies1.getRatingAvg() >= 3) && (movies1.getRatingAvg() < 4.0)) { %>
                        <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="3" checked="checked"/>
                        <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="5" />
                        <%} else if ((movies1.getRatingAvg() >= 4) && (movies1.getRatingAvg() < 5.0)) { %>
                        <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="4" checked="checked"/>
                        <input type="radio" class="star" name="rating" disabled="disabled" value="5" />
                        <%} else if (movies1.getRatingAvg() == 5) { %>
                        <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                        <input type="radio" class="star" name="rating" disabled="disabled" value="5" checked="checked"/>
                        <%} %>


                        <%if (movies1.getRatingNum() > 0) {%>
                        <p id="numRatingCount"> (<%=movies1.getRatingNum()%>)</p>
                        <%}%>
                    </form></h1>

                <h2 id="movieDetailsH2">
                    <%for (int i = 0; i < movieGenres.size(); i++) {%>
                    "<%=movieGenres.get(i).getGenreName()%>"

                    <%}%>
                    <p style="padding-left: 5px;"><%=movies.getAgeRating()%></p>
                </h2>
                <%
                    if (movies.getQuantityInStock() != 0) {
                %>



                <h2  id="movieDetailsH3" style="color:#5BC77C">In Stock</h1>
                    <h1 id="movieDetailsH3" style="color:#18C9D2">&euro; <%=movies.getPrice()%></h1>
                    <form  id="quantityForm" method="post" action="UserActionServlet" class="minimal" >

                        <div id="test">
                            <div id="test1">         
                                <strong><h3>Quantity</h3></strong>
                            </div>
                            <div id="test2">   
                                <input  id="inputQuantity" type="number" step="1" min="1" max="<%=movies.getQuantityInStock()%>" name="quantity" value="1" title="Qty" size="4" />
                            </div>
                            <div id="test3">   
                                <input type="hidden" name="action" value="addToCart" />
                                <button type="submit" name="cart" value="<%=movies%>" class="button button--inline-block button--medium btn" id="btn">Add To Cart</button>
                            </div>
                        </div>
                    </form>

                    <%
                    } else {
                    %>
                    <h1 id="movieDetailsH3" style="color:#FF1700">Out of Stock</h1>

                    <%
                        }
                    %>

                    <div id="tabs-container">
                        <ul class="tabs-menu">
                            <li class="current"><a href="#tab-1">Description</a></li>
                            <li><a href="#tab-2">Reviews</a></li>
                        </ul>
                        <div class="tab">
                            <div id="tab-1" class="tab-content">
                                <p><%=movies.getDescription()%></p>

                            </div>
                            <div id="tab-2" class="tab-content">
                                <section id="reviewForm">
                                    <div id="reviewer">
                                        <%
                                            for (int i = 0; i < allReviews.size(); i++) {
                                                if (allReviews.get(i).getMovieID() == movies.getMovieId()) {

                                        %>
                                        <p>
                                        <form id="ratingForm">
                                            <%if (allReviews.get(i).getReviewRating() < 1.0) {%>
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="5" />
                                            <%} else if ((allReviews.get(i).getReviewRating() >= 1) && (allReviews.get(i).getReviewRating() < 2.0)) { %>
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="1" checked="checked"/>
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="5" />
                                            <%} else if ((allReviews.get(i).getReviewRating() >= 2) && (allReviews.get(i).getReviewRating() < 3.0)) { %>
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="2" checked="checked"/>
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="5" /> 
                                            <%} else if ((allReviews.get(i).getReviewRating() >= 3) && (allReviews.get(i).getReviewRating() < 4.0)) { %>
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="3" checked="checked"/>
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="5" />
                                            <%} else if ((allReviews.get(i).getReviewRating() >= 4) && (allReviews.get(i).getReviewRating() < 5.0)) { %>
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="4" checked="checked"/>
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="5" />
                                            <%} else if (allReviews.get(i).getReviewRating() == 5) { %>
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="1" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="2" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="3" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="4" />
                                            <input type="radio" class="star" name="rating" disabled="disabled" value="5" checked="checked"/>
                                            <%} %>
                                        </form>
                                        </p>
                                        <%
                                            if (allUsers != null) {
                                                for (int a = 0; a < allUsers.size(); a++) {
                                                    if (allUsers.get(a).getUserId() == allReviews.get(i).getUserID()) {

                                        %>
                                        <h2 id="h2Form"><%=allUsers.get(a).getFirstName()%> <%=allUsers.get(a).getLastName()%></h2> 
                                        <%
                                                    }
                                                }
                                            }
                                        %>
                                        <p style="text-align:center;"><%=allReviews.get(i).getReviewText()%></p>

                                        <% if (c != null && c.isAdmin() == true) {%>
                                        <p style="font-style: italic; font-size: smaller; border-bottom: 1px solid #CCCBCB; padding-bottom: 5px; text-align: right;"><a href="UserActionServlet?action=delReview&reviewID=<%=allReviews.get(i).getReviewID()%>" style="font-style:normal;float: left;text-align: right;text-decoration: none;color:red;margin: 0px;padding:0px;"/>&#10006;</a><%=allReviews.get(i).getReviewDate()%></p>
                                        <%} else if (c == null) {%>
                                        <p style="font-style: italic; font-size: smaller; border-bottom: 1px solid #CCCBCB; padding-bottom: 5px; text-align: right;"><%=allReviews.get(i).getReviewDate()%></p>
                                        <%} else {%>
                                        <p style="font-style: italic; font-size: smaller; border-bottom: 1px solid #CCCBCB; padding-bottom: 5px; text-align: right;"><%=allReviews.get(i).getReviewDate()%></p>
                                        <%}%>
                                        <%
                                                }
                                            }
                                        %>
                                    </div>
                                    <%if (c == null) {%>
                                    <p style="text-align:center;">You need login, if you want leave review!</p>
                                    <%} else if (leftReview == 1) {%>
                                    <p style="text-align:center;">You already left review!</p>
                                    <%} else {%>
                                    <h2 id="h2Form">Add A Review</h2>
                                    <form name="loginCustomer"  id="requireStar"method="post" action="UserActionServlet" class="minimal">
                                        <label for="Your Rating">
                                            <p id="pForm1">Your Rating</p>
                                            <input type="radio" name="rating" value="1" class="star-rating  star required  ">
                                            <input type="radio" name="rating" value="2" class="star-rating  star " >
                                            <input type="radio" name="rating" value="3" class="star-rating  star " >
                                            <input type="radio" name="rating" value="4" class="star-rating  star " >
                                            <input type="radio" name="rating" value="5" class="star-rating  star " checked="checked">
                                        </label></br>
                                        <label for="First Name">
                                            <p id="pForm">Your Review</p>
                                            <textarea rows="7" cols="50" width="528" name="comment" placeholder="Enter text here..." required="required"></textarea>
                                        </label>
                                        <input type="hidden" name="action" value="reviewDetails" />
                                        <button type="submit" value="reviewDetails" class="button button--inline-block button--medium btn" id="btn">Submit</button>
                                    </form>
                                    <% }%>
                                </section>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
        <% }%>
        <div id="footer1">
            <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
            <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
        </div>  

    </body>
</html>
