
<%@page import="Business.MovieRatingComparator"%>
<%@page import="java.util.HashMap"%>
<%@page import="Business.MoviePopularityComparator"%>
<%@page import="Business.MovieTitleDscComparator"%>
<%@page import="Business.MoviePriceDscComparator"%>
<%@page import="Business.MoviePriceAscComparator"%>
<%@page import="java.util.Collections"%>
<%@page import="Business.Review"%>
<%@page import="DAO.ReviewDao"%>
<%@page import="Business.Genre"%>
<%@page import="DAO.GenreDao"%>
<%@page import="Business.Movie"%>
<%@page import="DAO.MovieDao"%>
<%@page import="Business.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Shop Page</title>
        <meta charset="UTF-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="css/shop.css" />
        <script type="text/javascript" src="js/modernizr.custom.86080.js"></script>
        <script type="text/javascript" src="js/jquery.js"></script>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <link href='http://fonts.googleapis.com/css?family=Josefin+Slab' rel='stylesheet' type='text/css' />
        <%-- filter slider--%>
        <%-- <script rel="stylesheet" src="js/jquery-1.10.2.js"></script>--%>
        <script rel="stylesheet" src="js/jquery-ui.js"></script>
        <link rel="stylesheet" href="css/jquery-ui.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">

        <%-- ----------%>

        <%-- pagination--%>    
        <script rel="stylesheet" src="js/jPages.js"></script>
        <script>
            /* when document is ready */
            $(function () {
            /* initiate plugin */
            $("div.holder").jPages({
            containerID: "movies",
                    callback: function (pages,
                            items) {
                    $("#legend1").html("Showing " + items.range.start + "-" + items.range.end + " of " + items.count + " results");
                    }
            });
            });
        </script>
        <%-- rating stars --%>  
        <script rel="stylesheet" src="js/jquery.rating.js"></script>
        <link rel="stylesheet" href="css/rating.css">
        <%-- ----------------------- --%> 

    </head>
    <body>
        <%
            HashMap<Movie, Integer> cart = (HashMap<Movie, Integer>) session.getAttribute("cart");
            User c = (User) session.getAttribute("user");
            MovieDao mDao = new MovieDao();
            GenreDao gDao = new GenreDao();

            double minPriceFromDB = mDao.findMoviePriceMin();
            double maxPriceFromDB = mDao.findMoviePriceMax();

            String lower = (String) session.getAttribute("lowerFromCommand");
            String upper = (String) session.getAttribute("upperFromCommand");

            ArrayList<Genre> allGenres = gDao.displayAllGenres();
            ArrayList<Movie> allMovies = mDao.displayAllMovies();

            MoviePopularityComparator byPopularity = new MoviePopularityComparator();
            MovieTitleDscComparator titleDsc = new MovieTitleDscComparator();
            MoviePriceAscComparator priceAsc = new MoviePriceAscComparator();
            MoviePriceDscComparator priceDsc = new MoviePriceDscComparator();

            ArrayList<Movie> topMovies = mDao.displayAllMovies();

            MovieRatingComparator sortingByRatingPopularity = new MovieRatingComparator();
            Collections.sort(topMovies, sortingByRatingPopularity);

            ArrayList<Movie> movieArrayByGenre = (ArrayList) session.getAttribute("movieArrayByGenre");
            ArrayList<Movie> allMoviesBetweenPrice = (ArrayList) session.getAttribute("movieArrayBetweenPrice");
            int selected = 0;

            if (allMoviesBetweenPrice != null && movieArrayByGenre != null) {
                ArrayList<Movie> filtered = new ArrayList<>();
                for (Movie between : movieArrayByGenre) {
                    for (Movie genre : allMoviesBetweenPrice) {
                        if (between.getTitle().contains(genre.getTitle())) {
                            filtered.add(genre);
                        }
                    }
                }
                allMovies = filtered;
            } else if (allMoviesBetweenPrice != null) {
                allMovies = allMoviesBetweenPrice;
            } else if (movieArrayByGenre != null) {
                allMovies = movieArrayByGenre;
            }

            if (request.getParameter("sortingSelect") != null) {
                String value = request.getParameter("sortingSelect");
                if (value.contains("byPopularity")) {
                    Collections.sort(allMovies, byPopularity);
                    selected = 1;
                    session.setAttribute("selected", selected);
                    session.setAttribute("value", value);
                } else if (value.contains("byTitleAsc")) {
                    Collections.sort(allMovies);
                    selected = 2;
                    session.setAttribute("selected", selected);
                    session.setAttribute("value", value);
                } else if (value.contains("byTitleDsc")) {
                    Collections.sort(allMovies, titleDsc);
                    selected = 3;
                    session.setAttribute("selected", selected);
                    session.setAttribute("value", value);
                } else if (value.contains("lowHigh")) {
                    Collections.sort(allMovies, priceAsc);
                    selected = 4;
                    session.setAttribute("selected", selected);
                    session.setAttribute("value", value);
                } else if (value.contains("highLow")) {
                    Collections.sort(allMovies, priceDsc);
                    selected = 5;
                    session.setAttribute("selected", selected);
                    session.setAttribute("value", value);
                }
            } else if (session.getAttribute("value") != null) {
                String value = (String) session.getAttribute("value");
                if (value.contains("byPopularity")) {
                    Collections.sort(allMovies, byPopularity);
                    selected = 1;
                    session.setAttribute("selected", selected);
                    session.setAttribute("value", value);
                } else if (value.contains("byTitleAsc")) {
                    Collections.sort(allMovies);
                    selected = 2;
                    session.setAttribute("selected", selected);
                    session.setAttribute("value", value);
                } else if (value.contains("byTitleDsc")) {
                    Collections.sort(allMovies, titleDsc);
                    selected = 3;
                    session.setAttribute("selected", selected);
                    session.setAttribute("value", value);
                } else if (value.contains("lowHigh")) {
                    Collections.sort(allMovies, priceAsc);
                    selected = 4;
                    session.setAttribute("selected", selected);
                    session.setAttribute("value", value);
                } else if (value.contains("highLow")) {
                    Collections.sort(allMovies, priceDsc);
                    selected = 5;
                    session.setAttribute("selected", selected);
                    session.setAttribute("value", value);
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
                <h1>Shop </h1>
            </div>
        </div>  

        <div id="wrapperContent">

            <div id="leftcolumn">
                <% if (allMovies != null && !allMovies.isEmpty()) { %>
                <div id="leftSpace">
                    <p><div id="legend1"></div></p>
                </div>
                <div id="rightSpace">
                    <form name="sortFormSelect"  id="sortFormSelect" action="UserActionServlet" method="post" >
                        <select id="soflow" name="sortingSelect" onchange="this.form.submit();">
                            <option value="" <%if (selected == 0) {%>selected<%}%>>--- Select Option ---</option>
                            <option value="byPopularity" <%if (selected == 1) {%>selected<%}%>>Popularity</option>
                            <option value="byTitleAsc" <%if (selected == 2) {%>selected<%}%>>Title (a - z)</option>
                            <option value="byTitleDsc" <%if (selected == 3) {%>selected<%}%>>Title (z - a)</option>
                            <option value="lowHigh" <%if (selected == 4) {%>selected<%}%>>Price (lowest - highest)</option>
                            <option value="highLow" <%if (selected == 5) {%>selected<%}%>>Price (highest - lowest)</option>
                        </select>
                        <input type="hidden" name="action" value="sorting" /> 
                    </form>

                </div>

                <ul id="movies"> 
                    <%for (int i = 0; i < allMovies.size(); i++) {%>
                    <li>
                        <div class="list-item">	
                            <form name="myForm" id="myForm" action="UserActionServlet" method="post">
                                <a  href="#"  >
                                    <input type="hidden" name="action" value="movieDetails" /> 
                                    <input type="hidden" name="movieDetailsValue" value="<%=allMovies.get(i).getTitle()%>" /> 
                                    <input type="image" src="<%=allMovies.get(i).getImgUrl()%>"  onclick="parentNode.submit();
                                        return false;"   width="250" height="342" /> 
                                </a>
                            </form>  
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
                                </p>  
                                <h3 id="pTitleName"><%=allMovies.get(i).getTitle()%></h3>
                                <p>&euro; <%=allMovies.get(i).getPrice()%></p>

                            </div>
                            <form name="myForm1" id="myForm1" action="UserActionServlet" method="post">
                                <input type="hidden" name="action" value="addToCart" />
                                <%
                                    if (allMovies.get(i).getQuantityInStock() != 0) {
                                %>
                                <button type="submit" name="movieShopValue" value="<%=allMovies.get(i).getTitle()%> " class="button button--inline-block button--medium">Add to cart</button>
                                <%
                                } else {
                                %>
                                <h2 style="color:#FF1700; padding-top: 10px">Out of Stock</h2>
                                <%}%>
                            </form>
                        </div> 
                    </li>
                    <%}%>
                </ul>
                <div class="holder"></div>
                <%} else { %>
                <h1 id="center">...No Matches Found...</h1>
                <%}%>
            </div>

            <div id="rightcolumn">
                <div id="sortByPrice">
                    <h1>All Movies</h1>
                    <form  action="UserActionServlet" method="post">
                        <button type="submit"   name="action" value="allMovies" class="button button--inline-block button--medium">Reset</button>
                    </form>
                </div>
                <div id="sortByPrice">
                    <h1>Filter By Price</h1>


                    <label for="amount">Price range:</label>
                    <input type="text" id="amount" readonly  DISABLED style="border:0; color:#18C9D2; background-color: transparent; font-weight:bold;">

                    <div id="slider-range"></div>
                    <form  action="UserActionServlet" method="post">
                        <input type="hidden" id="min" name="lower">
                        <input type="hidden" id="max" name="upper">
                        <input type="hidden" name="action" value="sliderValues" />
                        <button type="submit"  class="button button--inline-block button--medium">Filter</button>
                    </form>

                </div>

                <div id="listMovieCategories">
                    <h1>Movie Genres</h1>
                    <%if (allGenres != null) {
                            for (int i = 0; i < allGenres.size(); i++) {
                    %>
                    <li><a href="UserActionServlet?action=byGenre&inputGenre=<%=allGenres.get(i).getGenreId()%>"><%=allGenres.get(i).getGenreName()%></a></li>
                        <%}
                            }%>
                </div>

                <div id="topRated">
                    <h1>Top 3 Movies</h1>
                    <%if (topMovies != null) {
                            for (int i = 0; i < 3; i++) {

                    %>
                    <a href="UserActionServlet?action=movieDetails&movieDetailsValue=<%=topMovies.get(i).getTitle()%>"><h3 style="color:#18c9d2;text-align: center;margin:16px;"><%=allMovies.get(i).getTitle()%> <%=allMovies.get(i).getReleaseYear()%></h3></a>
                    <%
                            }
                        }%>
                </div>
            </div>
            <div id="footer1">
                <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
                <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
            </div>            
        </div>
        <script>
            $(function() {
            $("#slider-range").slider({
            range:true,
                    min: <%=minPriceFromDB%>,
                    max: <%=maxPriceFromDB%>,
                    step: 1,
            <%
                if (lower != null && upper != null) {
            %>
            values: [<%=lower%>,<%=upper%>],
            <%} else {%>
            values: [<%=minPriceFromDB%>, <%=maxPriceFromDB%>],
            <%}%>
            slide: function(event, ui) {
            $("#min").val(ui.values[ 0 ]);
            $("#max").val(ui.values[ 1 ]);
            $("#amount").val("\u20AC" + ui.values[ 0 ] + " - \u20AC" + ui.values[ 1 ]);
            }, create: function (event, ui) {

            $("#min").val($("#slider-range").slider("values")[0]);
            $("#max").val($("#slider-range").slider("values")[1]);
            }
            });
            $("#amount").val("\u20AC" + $("#slider-range").slider("values", 0) +
                    " - \u20AC" + $("#slider-range").slider("values", 1));
            });
        </script> 

    </body>
</html>
