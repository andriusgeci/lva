

<%@page import="Business.Audit"%>
<%@page import="DAO.auditDao"%>
<%@page import="DAO.UserDao"%>
<%@page import="java.util.HashMap"%>
<%@page import="Business.Movie"%>
<%@page import="DAO.MovieDao"%>
<%@page import="Business.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Admin Page</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" type="text/css" href="css/shop.css" />
        <link rel="stylesheet" type="text/css" href="css/admin.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">
        <script type="text/javascript" src="js/jquery.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Slab' rel='stylesheet' type='text/css' />

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
            MovieDao mDao = new MovieDao();
            UserDao uDao = new UserDao();
            auditDao aDao = new auditDao();
            ArrayList<Movie> allMovies = mDao.displayAllMovies();
            ArrayList<User> allUsers = uDao.findAllUsers();
            ArrayList<Audit> allDescriptions = aDao.getDescriptions();
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
                <h1>Admin</h1>
            </div>
        </div>  

        <div id="wrapperContent">
            <%
                if (c != null && c.isAdmin() == true) {
            %>
            <div id="tabs-container">
                <ul class="tabs-menu">
                    <li class="current"><a href="#tab-1">News</a></li>
                    <li ><a href="#tab-2">Movies</a></li>
                    <li><a href="#tab-3">Users</a></li>
                    <li><a href="#tab-4">Add Movie</a></li>
                    <li><a href="#tab-5">Add User</a></li>
                </ul>
                <div class="tab">

                    <div id="tab-1" class="tab-content">
                        <table class="tg">



                            <%
                                for (int i = allDescriptions.size() - 1; i >= 0; i--) {
                                    String background;

                                    if (i % 2 == 0) {
                                        background = "tg-6k2t";

                                    } else {
                                        background = "tg-6k2e";
                                    }
                            %>
                            <tr>
                                <th class="<%=background%>"><%=allDescriptions.get(i).getDescription()%></th>

                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>

                    <div id="tab-2" class="tab-content">
                        <table class="tg">
                            <tr>
                                <th colspan="12">All Movies</th>
                            </tr>
                            <tr>
                                <th class="tg-s6z2">ID</th>
                                <th class="tg-yw4l">Title</th>
                                <th class="tg-yw4l">Release Year</th>
                                <th class="tg-yw4l">Age Rating</th>
                                <th class="tg-yw4l">Length</th>
                                <th class="tg-yw4l">Price</th>
                                <th class="tg-yw4l">QTY In Stock</th>
                                <th class="tg-yw4l">AVG Rating</th>
                                <th class="tg-yw4l">Num Of Reviews</th>
                                <th class="tg-yw4l">Total Rating</th>
                                <th class="tg-yw4l"></th>
                                <th class="tg-yw4l"></th>
                            </tr>

                            <%
                                for (int i = 0; i < allMovies.size(); i++) {
                                    String background;

                                    if (i % 2 == 0) {
                                        background = "tg-6k2t";

                                    } else {
                                        background = "tg-6k2e";
                                    }
                            %>
                            <tr>
                                <th class="<%=background%>"><%=allMovies.get(i).getMovieId()%></th>
                                <th class="<%=background%>"><%=allMovies.get(i).getTitle()%></th>
                                <th class="<%=background%>"><%=allMovies.get(i).getReleaseYear()%></th>
                                <th class="<%=background%>"><%=allMovies.get(i).getAgeRating()%></th>
                                <th class="<%=background%>"><%=allMovies.get(i).getMovieLegth()%></th>
                                <th class="<%=background%>"><%=allMovies.get(i).getPrice()%></th>
                                <th class="<%=background%>"><%=allMovies.get(i).getQuantityInStock()%></th>
                                <th class="<%=background%>"><%=allMovies.get(i).getRatingAvg()%></th>
                                <th class="<%=background%>"><%=allMovies.get(i).getRatingNum()%></th>
                                <th class="<%=background%>"><%=allMovies.get(i).getRatingTotal()%></th>
                                <th class="<%=background%>"> <a href="UserActionServlet?action=editMovieDetails&movieDescription=<%=allMovies.get(i).getTitle()%>">Edit</a></th>
                                <th class="<%=background%>"> <a href="UserActionServlet?action=deleteMovie&movie=<%=allMovies.get(i).getTitle()%>">Delete</a></th>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>

                    <div id="tab-3" class="tab-content">
                        <table class="tg">
                            <tr>
                                <th colspan="12">All Users</th>
                            </tr>
                            <tr>
                                <th class="tg-s6z2">ID</th>
                                <th class="tg-yw4l">First Name</th>
                                <th class="tg-yw4l">Last Name</th>
                                <th class="tg-yw4l">DOB</th>
                                <th class="tg-yw4l">Phone</th>
                                <th class="tg-yw4l">Email</th>
                                <th class="tg-yw4l">Admin</th>

                            </tr>

                            <%
                                for (int i = 0; i < allUsers.size(); i++) {
                                    String background;

                                    if (i % 2 == 0) {
                                        background = "tg-6k2t";

                                    } else {
                                        background = "tg-6k2e";
                                    }
                            %>
                            <tr>
                                <th class="<%=background%>"><%=allUsers.get(i).getUserId()%></th>
                                <th class="<%=background%>"><%=allUsers.get(i).getFirstName()%></th>
                                <th class="<%=background%>"><%=allUsers.get(i).getLastName()%></th>
                                <th class="<%=background%>"><%=allUsers.get(i).getDOB()%></th>
                                <th class="<%=background%>"><%=allUsers.get(i).getPhone()%></th>
                                <th class="<%=background%>"><%=allUsers.get(i).getEmail()%></th>
                                <th class="<%=background%>"><%=allUsers.get(i).isAdmin()%></th>
                                <th class="<%=background%>"> <a href="UserActionServlet?action=editUserDetails&userDescription=<%=allUsers.get(i).getEmail()%>">Edit</a></th>
                                <th class="<%=background%>"> <a href="UserActionServlet?action=deleteUser&user=<%=allUsers.get(i).getEmail()%>">Delete</a></th>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>

                    <div id="tab-4" class="tab-content">
                        <h2 style="text-align: center">Fill all fields</h2>
                        <form id="addMovieForm" method="post" action="UserActionServlet" class="minimal">
                            <label for="Title">
                                <p id="pForm">Title</p>
                                <input type="text"  maxlength="50" name="Title"  title="maximum 50 characters" value="" required="required" />
                            </label>
                            <label for="Description">
                                <p id="pForm">Description</p>
                                <input type="text"  maxlength="1000" name="Description"  title="maximum 1000 characters"value="" required="required"  />
                            </label>
                            <label for="ReleaseYear">
                                <p id="pForm">Release Year</p>
                                <input type="number" min="1900" max="2100" title="year field must contain four digits" name="ReleaseYear" value=""  required="required" />
                            </label>
                            <label for="AgeRating">
                                <p id="pForm">Age Rating</p>
                                <input type="text" maxlength="10" name="AgeRating" title="maximum 10 characters" value="" required="required" />
                            </label>
                            <label for="MovieLegth">
                                <p id="pForm">Movie Length</p>
                                <input type="number" max="999" name="MovieLegth" title="maximum 3 numbers" value="" required="required"  />
                            </label>
                            <label for="MovieUrl">
                                <p id="pForm">Movie Url</p>
                                <input type="text" maxlength="255" name="MovieUrl" title="maximum 255 characters" value="" required="required" />
                            </label>
                            <label for="Price">
                                <p id="pForm">Price</p>
                                <input type="number" max="99.99" step="0.01" name="Price" title="maximum 4 numbers and 2 after decimal" pattern="\d+(\.\d*)?" required="required"  />
                            </label>
                            <label for="QTY">
                                <p id="pForm">Qty In Stock</p>
                                <input type="number" max="999" name="QTY" title="maximum 3 numbers" value="" required="required" />
                            </label>

                            <input type="hidden" name="action" value="addMovie" />
                            <button type="submit" value="addMovie" class="button button--inline-block button--medium btn" id="btn">Add</button>
                        </form>
                    </div>

                    <div id="tab-5" class="tab-content">
                        <h2 style="text-align: center">Fill all fields</h2>

                        <form id="addMovieForm" method="post" action="UserActionServlet" class="minimal">
                            <label for="First Name">
                                <p id="pForm">First Name</p>
                                <input type="text" minlength="3" maxlength="10" name="name" placeholder="First Name" required="required" title="Please enter First Name" pattern="([a-zA-Z][a-zA-Z0-9\s]*)"/>
                            </label>
                            <label for="Last Name">
                                <p id="pForm">Last Name</p>
                                <input type="text" minlength="3" maxlength="10"  name="lName" placeholder="Last Name" required="required" title="Please enter Last Name" pattern="([a-zA-Z][a-zA-Z0-9\s]*)"/>
                            </label>
                            <label for="Email">
                                <p id="pForm">Email</p>
                                <input type="text" name="email" id="email" placeholder="Example@gmail.com" pattern="([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})" required="required" title="Please enter email: Example@gmail.com"/>
                            </label>
                            <label for="Password">
                                <p id="pForm">Password</p>
                                <input type="password" minlength="6" maxlength="30" name="password" id="password" placeholder="Password must contain 1 uppercase, lowercase and number" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required="required" title="Password must contain 1 uppercase, lowercase and number"/>
                            </label>
                            <label for="Retype-Password">
                                <p id="pForm">Retype-Password</p>
                                <input type="password" minlength="6" maxlength="30" name="rPassword" id="rpassword" placeholder="Password must contain 1 uppercase, lowercase and number" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required="required" title="Password must contain 1 uppercase, lowercase and number"/>
                            </label>
                            <label for="Retype-Password">
                                <p>Admin</p>


                                <input  name="answer" type="radio" value="true" /> True
                                <input checked="checked" name="answer" type="radio" value="false" /> False
                            </label>


                            <input type="hidden" name="action" value="addUser" />
                            <button type="submit" value="addUser" class="button button--inline-block button--medium btn" id="btn">Add</button>
                        </form>


                    </div>

                </div>
            </div>
            <%
            } else {
            %>           
            <h1 id="center">You are not authorized to view this page</h1>  

            <%}
            %>
        </div>

        <div id="footer1">
            <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
            <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
        </div>   

    </body>
</html>