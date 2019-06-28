
<%@page import="java.util.HashMap"%>
<%@page import="Business.Movie"%>
<%@page import="DAO.MovieDao"%>
<%@page import="Business.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>About Page</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" type="text/css" href="css/shop.css" />
        <link rel="stylesheet" type="text/css" href="css/about.css" />
        <link href='http://fonts.googleapis.com/css?family=Josefin+Slab' rel='stylesheet' type='text/css' />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">
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
                    <strong>Welcome:</strong> <%=c.getFirstName()%> <%=c.getLastName()%> 


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
                <h1>About</h1>
            </div>
        </div>  

        <div id="wrapperContent">
            <div id="imgbox">
                <table>
                    <tr>
                        <td>
                            <img src="images/img1.jpg" width="300" height="200" alt="" title=""/>
                        </td>
                        <td>
                            <img src="images/img8.jpg" width="300" height="200" alt="" title=""/>
                        </td>
                        <td>
                            <img src="images/img7.jpg" width="300" height="200" alt="" title=""/>
                        </td>
                    </tr>
                </table>
            </div>

            <div id="descriptionbox">
                <div id="leftcolumn">
                    <div id="definitionbox">
                        <h3>
                            LVA Movies Shop
                        </h3>
                        <p>
                            From DVDs of your favourite films to TV series box-sets, handy gift cards to beautiful coffee-table books,
                            and classic film posters to collections from the LVA, the LVA Movies Shop has it all under one roof! 
                            DVD Centre is one of the best known disc and digital media companies in Ireland
                            and the UK with over ten years of experience. Our number one priority is customer satisfaction,
                            Every DVD or digital file is quality reviewed and visually inspected before it leaves our shop.
                        </p> 
                    </div>
                </div>

                <div id="rightcolumn">
                    <div class="addressbox"> 
                        <div class="map">
                            <script src='https://maps.googleapis.com/maps/api/js?v=3.exp'></script>
                            <div style='overflow:hidden;height:350px;width:500px;'>
                                <div id='gmap_canvas' style='height:350px;width:500px;'></div>
                                <div>
                                    <small>
                                        <a href="http://embedgooglemaps.com">
                                            embed google maps
                                        </a>
                                    </small>
                                </div>
                                <div>
                                    <small>
                                        <a href="http://freedirectorysubmissionsites.com/">
                                            link directories
                                        </a>
                                    </small>
                                </div>
                                <style>#gmap_canvas img{
                                        max-width:none!important;background:none!important}
                                    </style>
                                </div>
                                <script type='text/javascript'>
                                    function init_map() {
                                        var myOptions = {zoom: 10, center: new google.maps.LatLng(53.6533558, -6.687637999999993), mapTypeId: google.maps.MapTypeId.ROADMAP};
                                        map = new google.maps.Map(document.getElementById('gmap_canvas'), myOptions);
                                        marker = new google.maps.Marker({map: map, position: new google.maps.LatLng(53.6533558, -6.687637999999993)});
                                        infowindow = new google.maps.InfoWindow({content: '<strong>LVA Movies</strong><br>Kennedy Road, Navan, Co. Meath, Ireland<br>'});
                                        google.maps.event.addListener(marker, 'click', function () {
                                            infowindow.open(map, marker);
                                        });
                                        infowindow.open(map, marker);
                                    }
                                    google.maps.event.addDomListener(window, 'load', init_map);
                                </script>
                            </div>
                            <div class="adr">
                            <h4>
                                LVA Movies
                            </h4>
                            <h5>
                                Kennedy Road,<br>
                                Navan,<br>
                                Co. Meath.<br>
                                Phone: 046 9005555<br>
                                <a href="http://lvamovies.azurewebsites.net/home">
                                    www.lvamovies.ie
                                </a>
                            </h5>
                        </div>
                    </div>
                </div>
            </div>


        </div>

        <div id="footer1">
            <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
            <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
        </div>   

    </body>
</html>
