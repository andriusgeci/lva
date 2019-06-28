
<%@page import="java.util.HashMap"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="Business.Movie"%>
<%@page import="DAO.MovieDao"%>
<%@page import="Business.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Shopping Cart Page</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" type="text/css" href="css/shop.css" />
        <link rel="stylesheet" type="text/css" href="css/shoppingCart.css" />
        <link href='http://fonts.googleapis.com/css?family=Josefin+Slab' rel='stylesheet' type='text/css' />
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    </head>
    <body>        
        <%
            HashMap<Movie, Integer> cart = (HashMap<Movie, Integer>) session.getAttribute("cart");
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
                <h1>Shopping Cart</h1>
                <div class="progress">

                    <div class="circle active">
                        <span class="label">1</span>
                        <span class="title">Cart</span>
                    </div>

                    <span class="bar"></span>
                    <div class="circle ">
                        <span class="label">2</span>
                        <span class="title">Checkout</span>
                    </div>

                    <span class="bar "></span>
                    <div class="circle ">
                        <span class="label">3</span>
                        <span class="title">Complete</span>
                    </div>
                </div>
            </div>
        </div>  

        <div id="wrapperContent">

            <% if (cart == null || cart.size() == 0) {%> 
            <div id="centerForCart">
                <p><img src="images/cart-empty.png" alt="Empty Shopping Cart" ></p>
                <h1>Your cart is currently empty.</h1>
                <p><a href="shop.jsp" class="button button--inline-block button--small" style="text-decoration: none">Return To Shop</a></p>
            </div>
            <%} else { %>
            <form id="cartForm" action="UserActionServlet" method="post" class="minimal" style="padding-top: 50px;">
                <div class="container" >
                    <table id="cart" >
                        <%
                            DecimalFormat df = new DecimalFormat("0.00");

                            if (cart != null) {
                        %>
                        <thead>
                            <tr>
                                <th class="productname" >Product</th>
                                <th class="price">Price</th>
                                <th class="qty">Quantity</th>
                                <th class="total">Total</th>
                                <th class="del"></th>
                            </tr>
                        </thead>
                        <%
                            double total = 0;
                            for (Movie key : cart.keySet()) {
                        %>

                        <tbody>

                        <td class="productname">
                            <div>
                                <div id="shoppingCartfirstPart">
                                    <img id="img1" src="<%=key.getImgUrl()%>" alt="..."  width="100"/>
                                    <section>
                                        <h3><%=key.getTitle()%></h3>
                                        <p><%=key.getDescription()%></p>
                                    </section>
                                </div>
                            </div>
                        </td>

                        <td class="price">&euro; <%=key.getPrice()%></td>  
                        <td class="qty"><%=cart.get(key)%></td> 
                        <td class="total">&euro;<%total += cart.get(key) * key.getPrice();%> <%=df.format(cart.get(key) * key.getPrice())%></td> 
                        <td class="del">

                            <form  action="UserActionServlet" method="post">
                                <input type="hidden" name="action" value="delFromCart" />
                                <button type="submit" name="movieIdToDelete" value="<%=key.getTitle()%>" class="button button--inline-block button--small"><i class="material-icons">delete</i></button>
                            </form>

                        </td>
                        </tbody>
                        <%
                            }
                        %> 

                        <tfoot >
                            <tr >
                                <td class="productname1"  ></td>
                                <td class="total1"  ></td>
                                <td class="qty1" ></td>
                                <td class="total1"><strong>Total: &euro; <%=df.format(total)%> </strong></td>

                                <td class="del1">

                                    <form  action="UserActionServlet" method="post">
                                    <input type="submit" name="action" value="Checkout" class="button button--inline-block button--medium" style="margin-bottom: 10px;"/></td>
                                </form>
                            </tr>
                        </tfoot>
                        <%  }
                        %>
                    </table>
                </div>
            </form>

            <%
                }
            %>
        </div>
        <div id="footer1">
            <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
            <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
        </div>   

    </body>
</html>