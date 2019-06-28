

<%@page import="java.util.HashMap"%>
<%@page import="Business.Movie"%>
<%@page import="DAO.MovieDao"%>
<%@page import="Business.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Coming Soon Page</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" type="text/css" href="css/shop.css" />
        <link rel="stylesheet" type="text/css" href="css/comingSoon.css" />
        <script type="text/javascript" src="js/modernizr.custom.63321.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Slab' rel='stylesheet' type='text/css' />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>
    <body>        
        <%
            HashMap<Movie, Integer> cart = (HashMap) session.getAttribute("cart");
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
                <h1>Coming Soon</h1>
            </div>
        </div>  

        <div id="wrapperContent">
            <div class="main">
                <div id="mi-slider" class="mi-slider">
                    <ul>
                        <li><a href="http://www.imdb.com/title/tt3949660/?ref_=cs_ov_i">
                                <img src="http://ia.media-imdb.com/images/M/MV5BMjAzODQyNDQxOV5BMl5BanBnXkFtZTgwMjYxMzUwODE@.jpg" alt="img01" width="260" height="362">
                                <h4>Ninja Turtles (2016)</h4>
                            </a>
                        </li>
                        <li><a href="http://www.imdb.com/title/tt3960412/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BMTAyMDYzMDExNTReQTJeQWpwZ15BbWU4MDM3NjY5MTgx.jpg" alt="img02" width="260" height="362"><h4>Popstar (2016)</h4></a></li>
                        <li><a href="http://www.imdb.com/title/tt2674426/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BMTQ2NjE4NDE2NV5BMl5BanBnXkFtZTgwOTcwNDE5NzE@.jpg" alt="img03" width="260" height="362"><h4>Me Before You (2016)</h4></a></li>
                        <li><a href="http://www.imdb.com/title/tt4238858/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BYmY1OWFjNWUtMTc3YS00ZTM3LTk0MDYtNzBhOTEwYzBmMTlmXkEyXkFqcGdeQXVyNjQ4NTMyMTg@.jpg" alt="img04" width="260" height="362"><h4>The Conjuring 2 (2016)</h4></a></li>
                    </ul>
                    <ul>
                        <li><a href="http://www.imdb.com/title/tt3691740/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BMjI5MjI4NTEzOF5BMl5BanBnXkFtZTgwNjM2Nzg3NzE@.jpg" width="260" height="362"><h4>The BFG (2016)</h4></a></li>
                        <li><a href="http://www.imdb.com/title/tt0918940/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BMTk2NDQ0MjA5OF5BMl5BanBnXkFtZTgwNTIyMjYzODE@.jpg" width="260" height="362"><h4>Legend of Tarzan (2016)</h4></a></li>
                        <li><a href="http://www.imdb.com/title/tt1289401/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BNzAzODQ1NTk4OF5BMl5BanBnXkFtZTgwODIwOTIwODE@.jpg" alt="img07" width="260" height="362"><h4>Ghostbusters (2016)</h4></a></li>
                        <li><a href="http://www.imdb.com/title/tt1355631/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BMTEwNzM2NjY2MTNeQTJeQWpwZ15BbWU4MDQ3MDI3Njgx.jpg" alt="img08" width="260" height="362"><h4>The Infiltrator (2016)</h4></a></li>
                    </ul>
                    <ul>
                        <li><a href="http://www.imdb.com/title/tt1386697/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BOTY1MzU1MDQ1MF5BMl5BanBnXkFtZTgwNjAzMjY3NzE@.jpg" alt="img09" width="260" height="362"><h4>Suicide Squad (2016)</h4></a></li>
                        <li><a href="http://www.imdb.com/title/tt4383594/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BMTc3NDA5MjMyMF5BMl5BanBnXkFtZTgwODUwMTU4NzE@.jpg" alt="img10"width="260" height="362"><h4>Nine Lives (2016)</h4></a></li>
                        <li><a href="http://www.imdb.com/title/tt4276820/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BMTg5NzUxNzIyNV5BMl5BanBnXkFtZTgwOTY1MTQ0ODE@.jpg" alt="img11"width="260" height="362"><h4>The Founder (2016)</h4></a></li>
                        <li><a href="http://www.imdb.com/title/tt2638144/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BNzU5ODQzOTUwMF5BMl5BanBnXkFtZTgwMzMwMjUzODE@.jpg" alt="img12"width="260" height="362"><h4>Ben-Hur (2016)</h4></a></li>
                    </ul>
                    <ul>
                        <li><a href="http://www.imdb.com/title/tt1291570/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BNTY2MWFkOTMtMTlkMC00YzE0LTkzNjAtYzA5OWIzMmQ2MTgzXkEyXkFqcGdeQXVyNTg0ODIxMDI@.jpg" alt="img13"width="260" height="362"><h4>Solace (2015)</h4></a></li>
                        <li><a href="http://www.imdb.com/title/tt4731008/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BMTkwMzgxNDc2MV5BMl5BanBnXkFtZTgwMDI2NDQ0NzE@.jpg" alt="img14"width="260" height="362"><h4>The Wild Life (2016)</h4></a></li>
                        <li><a href="http://www.imdb.com/title/tt3774114/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BNDEwMDIzOTYzNF5BMl5BanBnXkFtZTgwOTY0OTUxODE@.jpg" alt="img15"width="260" height="362"><h4>Snowden (2016)</h4></a></li>
                        <li><a href="http://www.imdb.com/title/tt4624424/?ref_=cs_ov_tt"><img src="http://ia.media-imdb.com/images/M/MV5BMTg2NTk1MjkzM15BMl5BanBnXkFtZTgwMzExNjA1NzE@._V1_UY209_CR0,0,140,209_AL_.jpg" alt="img16"width="260" height="362"><h4>Storks (2016)</h4></a></li>
                    </ul>
                    <nav>
                        <a href="#">June</a>
                        <a href="#">July</a>
                        <a href="#">August</a>
                        <a href="#">September</a>
                    </nav>
                </div>
            </div>

            <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
            <script src="js/jquery.catslider.js"></script>
            <script>
                $(function () {

                    $('#mi-slider').catslider();

                });
            </script>
        </div>
        <div id="footer1">
            <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
            <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
        </div>   

    </body>
</html>