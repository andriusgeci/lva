
<%@page import="java.util.HashMap"%>
<%@page import="Business.Movie"%>
<%@page import="DAO.MovieDao"%>
<%@page import="Business.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Register Page</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" type="text/css" href="css/shop.css" />
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
                <h1>Register</h1>
            </div>
        </div>  

        <div id="wrapperContent">
            <section id="LoginRegisterBox">
                <h2 id="h2Form">Please Fill In Registration Form</h2>
                <form name="registerCustomer" id="formValidation" method="post" action="UserActionServlet" class="minimal">
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
                        <input type="password" minlength="6" maxlength="30" name="rPassword" id="password" placeholder="Password must contain 1 uppercase, lowercase and number" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required="required" title="Password must contain 1 uppercase, lowercase and number"/>
                    </label>
                    <input type="hidden" name="action" value="register" />
                    <button type="submit" value="register" class="button button--inline-block button--medium btn" id="btn">Register</button>
                </form>
            </section>

        </div>
        <div id="footer1">
            <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
            <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
        </div>   
    </body>
</html>
