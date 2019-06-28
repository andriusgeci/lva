

<%@page import="Business.Address"%>
<%@page import="DAO.AddressDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.HashMap"%>
<%@page import="Business.Movie"%>
<%@page import="DAO.MovieDao"%>
<%@page import="Business.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Checkout Page</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" type="text/css" href="css/shop.css" />
        <link rel="stylesheet" type="text/css" href="css/checkout.css" />
        <link href='http://fonts.googleapis.com/css?family=Josefin+Slab' rel='stylesheet' type='text/css' />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">

        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/modernizr.custom.86080.js"></script>
        <script src="js/jquery.placeholder.js" type="text/javascript"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $(function () {
                    // Get the form fields and hidden div
                    var checkbox = $("#acc");
                    var hidden = $("#hidden_fields");
                    var populate = $("#populate");
                    // Hide the fields.
                    hidden.hide();
                    checkbox.change(function () {
                        // Check to see if the checkbox is checked.
                        // If it is, show the fields and populate the input.
                        // If not, hide the fields.
                        if (checkbox.is(':checked')) {
                            // Show the hidden fields.
                            hidden.show();
                            populate.val("Dude, this input got populated!");
                        } else {
                            hidden.hide();

                        }
                    });
                });

                /**
                 Shows the form label, in place of the placeholder, when a value has been entered into a field
                 */
                $('.field').each(function () {
                    var parent = $(this),
                            field = parent.find('input, select');

                    // Focus: Show label
                    field.focus(function () {
                        parent.addClass('show-label');
                    });

                    // Blur: Hide label if no value was entered (go back to placeholder)
                    field.blur(function () {
                        if (field.val() === '') {
                            parent.removeClass('show-label');
                        }
                    });

                    // Handles change without focus/blur action (i.e. form auto-fill)
                    field.change(function () {
                        if (field.val() !== '') {
                            parent.addClass('show-label');
                        } else {
                            parent.removeClass('show-label');
                        }
                    });
                });

                // Support placeholders in IE8 via https://github.com/mathiasbynens/jquery-placeholder
                $('input, textarea').placeholder();

                // Add class no-selection class to select elements
                $('select').change(function () {
                    var field = $(this);
                    if (field.val() === '') {
                        field.addClass('no-selection');
                    } else {
                        field.removeClass('no-selection');
                    }
                });
                /**
                 Validate that a field is not empty.
                 Add/remove has-error class from the field container.
                 */
                function validateNotEmpty() {
                    var field = $(this),
                            container = field.parents('.field');

                    if (!container.length) {
                        return false;
                    }

                    if ($.trim(field.val()) !== '') {
                        container.removeClass('has-error');
                        return true;
                    } else {
                        container.addClass('has-error');
                        return false;
                    }
                }
                // Validate on blur
                $('.field input, .field select').blur(validateNotEmpty);

                // If field has an error, validate as you type
                $('.field input').keypress(function () {
                    var field = this,
                            container = $(field).parents('.field');

                    if (container.length && container.hasClass('has-error')) {
                        //debugger;
                        validateNotEmpty.call(field);
                    }
                });
            });
        </script>
    </head>
    <body onload="validateDays()">        
        <%
            HashMap<Movie, Integer> cart = (HashMap) session.getAttribute("cart");
            User c = (User) session.getAttribute("user");
            AddressDao adr = new AddressDao();
            AddressDao addDao = new AddressDao();
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
                <h1>Checkout</h1>
                <div class="progress">
                    <div class="circle done">
                        <span class="label">1</span>
                        <span class="title">Cart</span>
                    </div>
                    <span class="bar done"></span>
                    <div class="circle active">
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
            <% if (c == null) {%>
            <p id="centerAlign" style="padding-top: 30px;">Returning Customer? <a href="login.jsp">Click Here To Login</a></p>
            <%}%>
            <div id="all">

                <form action="UserActionServlet" method="post" >
                    <div id="right">

                        <%if (c != null && !addDao.findAddressByUserId(c.getUserId()).isEmpty()) {

                                ArrayList<Address> addresses = adr.findAddressByUserId(c.getUserId());

                                for (int i = 0; i < addresses.size(); i++) {

                        %>
                        <div id="AddressBox">
                            <%                                if (addresses.get(i).isPrimaryAddress() == true) {
                            %>
                            <h2><b>Shipping Address</b><br></h2>
                            <hr>
                            <div id="center">
                                <h3><%=c.getFirstName()%> <%=c.getLastName()%></h3>
                                <h3><%=addresses.get(i).getAddress1()%><br></h3>
                                    <%if (!addresses.get(i).getAddress2().isEmpty()) {%>
                                <h3><%=addresses.get(i).getAddress2()%><br></h3>
                                    <%}%>
                                <h3><%=addresses.get(i).getCity()%><br></h3>
                                <h3><%=addresses.get(i).getCounty()%><br></h3>
                                <h3><%=addresses.get(i).getCountry()%><br></h3>
                                <h3><%=addresses.get(i).getPostalCode()%><br></h3>
                            </div>
                            <div id="diffaddres">
                                <a href="Addresses.jsp">Add/Edit shipping address</a>

                            </div>
                            <%}%>
                        </div>
                        <%
                            }
                        %>

                        <%} else {%>
                        <h2 id="h2Form">Shipping Details</h2>
                        <ol>
                            <li class="fname">
                                <span class="field">
                                    <input type="text" minlength="3" maxlength="10" id="fname" name="fname" value="<%=c.getFirstName()%>" placeholder="First Name" title="Please enter First Name" pattern="([a-zA-Z][a-zA-Z0-9\s]*)" readonly/>
                                    <label for="fName">First Name</label>
                                    <span class="error">Please enter your first name</span>
                                </span>
                            </li>
                            <li class="lname">
                                <span class="field">
                                    <input type="text" minlength="3" maxlength="10" id="lName" name="lname" value="<%=c.getLastName()%>"placeholder="Last Name" title="Please enter Last Name" pattern="([a-zA-Z][a-zA-Z0-9\s]*)" readonly />
                                    <label for="lName">Last Name</label>
                                    <span class="error">Please enter your last name</span>
                                </span>
                            </li>
                            <li class="email">
                                <span class="field">
                                    <input type="text" id="email" name="email" value="<%=c.getEmail()%>"placeholder="Email" pattern="([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})" required="required" title="Please enter email: Example@gmail.com" readonly />
                                    <label for="fName">Email</label>
                                    <span class="error">Please enter your email</span>
                                </span>
                            </li>
                            <li class="phone">
                                <span class="field">
                                    <%if (c.getPhone() != null) {%>
                                    <input type="text" id="phone"  name="phone" value="<%=c.getPhone()%>"  />
                                    <%} else {%>
                                    <input type="text" id="phone" name="phone" placeholder="Phone" placeholder="Phone" required  pattern="(^\d{3}-\d{9}$)" title="353-862211333"  />
                                    <% }%>
                                    <label for="lName">Phone</label>
                                    <span class="error">Please enter your phone number e.g +353862211333</span>
                                </span>
                            </li>
                            <li class="address1">
                                <span class="field">
                                    <input type="text" id="address1"  name="address1" placeholder="Address" title="Please enter a street address" required />
                                    <label for="address">Address</label>
                                    <span class="error">Please enter a street address</span>
                                </span>
                            </li>

                            <li class="city">
                                <span class="field">
                                    <input type="text" id="city" name="city" minlength="3" maxlength="50" placeholder="City" title="Please use letter only" pattern="([a-zA-Z][a-zA-Z\s]*)" required />
                                    <label for="city">City</label>
                                    <span class="error">Please enter a city</span>
                                </span>
                            </li>
                            <li class="county">
                                <span class="field">
                                    <input type="text" id="county" name="county" minlength="3" maxlength="50" placeholder="County" title="E.g. Co.Louth"  required />
                                    <label for="postal">County</label>
                                    <span class="error">Please enter county</span>
                                </span>
                            </li>
                            <li class="state">
                                <span class="field">
                                    <select id="state" name="country" class="no-selection"required>
                                        <option value="">Country</option>
                                        <option value="Afganistan">Afghanistan</option>
                                        <option value="Albania">Albania</option>
                                        <option value="Algeria">Algeria</option>
                                        <option value="Argentina">Argentina</option>
                                        <option value="Armenia">Armenia</option>
                                        <option value="Aruba">Aruba</option>
                                        <option value="Australia">Australia</option>
                                        <option value="Austria">Austria</option>
                                        <option value="Azerbaijan">Azerbaijan</option>
                                        <option value="Bahamas">Bahamas</option>
                                        <option value="Bahrain">Bahrain</option>
                                        <option value="Barbados">Barbados</option>
                                        <option value="Belarus">Belarus</option>
                                        <option value="Belgium">Belgium</option>
                                        <option value="Brazil">Brazil</option>
                                        <option value="Bulgaria">Bulgaria</option>
                                        <option value="Canada">Canada</option>
                                        <option value="Channel Islands">Channel Islands</option>
                                        <option value="Chile">Chile</option>
                                        <option value="China">China</option>
                                        <option value="Colombia">Colombia</option>
                                        <option value="Croatia">Croatia</option>
                                        <option value="Cuba">Cuba</option>
                                        <option value="Cyprus">Cyprus</option>
                                        <option value="Czech Republic">Czech Republic</option>
                                        <option value="Denmark">Denmark</option>
                                        <option value="Egypt">Egypt</option>
                                        <option value="Estonia">Estonia</option>
                                        <option value="Finland">Finland</option>
                                        <option value="France">France</option>
                                        <option value="Georgia">Georgia</option>
                                        <option value="Germany">Germany</option>
                                        <option value="Great Britain">Great Britain</option>
                                        <option value="Greece">Greece</option>
                                        <option value="Hawaii">Hawaii</option>
                                        <option value="Honduras">Honduras</option>
                                        <option value="Hong Kong">Hong Kong</option>
                                        <option value="Hungary">Hungary</option>
                                        <option value="Iceland">Iceland</option>
                                        <option value="India">India</option>
                                        <option value="Indonesia">Indonesia</option>
                                        <option value="Iran">Iran</option>
                                        <option value="Iraq">Iraq</option>
                                        <option value="Ireland">Ireland</option>
                                        <option value="Israel">Israel</option>
                                        <option value="Italy">Italy</option>
                                        <option value="Jamaica">Jamaica</option>
                                        <option value="Japan">Japan</option>
                                        <option value="Korea North">Korea North</option>
                                        <option value="Korea Sout">Korea South</option>
                                        <option value="Latvia">Latvia</option>
                                        <option value="Lithuania">Lithuania</option>
                                        <option value="Luxembourg">Luxembourg</option>
                                        <option value="Malaysia">Malaysia</option>
                                        <option value="Malawi">Malawi</option>
                                        <option value="Maldives">Maldives</option>
                                        <option value="Mali">Mali</option>
                                        <option value="Malta">Malta</option>
                                        <option value="Mexico">Mexico</option>
                                        <option value="Moldova">Moldova</option>
                                        <option value="New Zealand">New Zealand</option>
                                        <option value="Norway">Norway</option>
                                        <option value="Phillipines">Philippines</option>
                                        <option value="Poland">Poland</option>
                                        <option value="Portugal">Portugal</option>
                                        <option value="Qatar">Qatar</option>
                                        <option value="Romania">Romania</option>
                                        <option value="Russia">Russia</option>
                                        <option value="Serbia">Serbia</option>
                                        <option value="Singapore">Singapore</option>
                                        <option value="Slovakia">Slovakia</option>
                                        <option value="Slovenia">Slovenia</option>
                                        <option value="Spain">Spain</option>
                                        <option value="Swaziland">Swaziland</option>
                                        <option value="Sweden">Sweden</option>
                                        <option value="Switzerland">Switzerland</option>
                                        <option value="Taiwan">Taiwan</option>
                                        <option value="Thailand">Thailand</option>
                                        <option value="Tunisia">Tunisia</option>
                                        <option value="Turkey">Turkey</option>
                                        <option value="Uganda">Uganda</option>
                                        <option value="Ukraine">Ukraine</option>
                                        <option value="United Arab Erimates">United Arab Emirates</option>
                                        <option value="United Kingdom">United Kingdom</option>
                                        <option value="United States of America">United States of America</option>
                                        <option value="Uraguay">Uruguay</option>
                                        <option value="Venezuela">Venezuela</option>
                                        <option value="Vietnam">Vietnam</option>
                                    </select>
                                    <label for="state">Country</label>
                                    <span class="error">Please select country</span>
                                </span>

                            <li class="postal">
                                <span class="field">
                                    <input type="text" id="postal" name="postalCode" minlength="3" maxlength="20" placeholder="Postcode" required />
                                    <label for="postal">Postcode</label>
                                    <span class="error">Please enter postcode </span>
                                </span>
                            </li>


                            <li class="note">
                                <textarea  id="note" name="note" placeholder="Notes about your order, e.g. special notes for delivery"></textarea>
                            </li>

                            <%if (c == null) {%>
                            <li class="Account">
                                <input type="checkbox" id="acc" name="acc" value="acc">Create an account?<br>
                            </li>

                            <div id="hidden_fields">
                                <p id="pAcc">Create an account by entering the password below.</br> 
                                    If you are a returning customer
                                    please login at the top of the page.
                                </p>
                                <li class="password">
                                    <span class="field">
                                        <input type="password" id="password" name="password" placeholder="Password" required />
                                        <label for="password">Password</label>
                                        <span class="error">Please enter your password</span>
                                    </span>
                                </li>
                            </div>

                            <%}%>
                        </ol>
                        <%}%>
                    </div>

                    <div id="left">
                        <h2 id="h2Form">Your Order</h2>
                        <% if (cart != null) {
                                double total = 0;
                                DecimalFormat df = new DecimalFormat("0.00");
                        %> 
                        <table style="border-collapse:collapse;width: 418px" cellpadding="8">
                            <%for (Movie key : cart.keySet()) {%> 
                            <tr>
                                <td id="r"><strong><%=key.getTitle()%></strong></td>
                                <td id="rr">x <%=cart.get(key)%></td>
                                <td>&euro; <%=key.getPrice()%></td>
                            </tr>
                            <%total += cart.get(key) * key.getPrice();
                                }%>
                            <tr>
                                <td id="r">Cart Subtotal</td>
                                <td id="rr"></td>
                                <td>&euro; <%=df.format(total)%></td>
                            </tr>
                            <tr>
                                <td id="r">Shipping</td>
                                <td id="rr"></td>
                                <td>
                                    <form id="shipping">
                                        <input type="radio" name="shipping" id="option1" value="50" onClick="validateDays();"> International: &euro; 50<br>
                                        <input type="radio" name="shipping" id="option2" value="10" onClick="validateDays();"> Delivery: &euro; 10<br>
                                        <input type="radio" name="shipping" id="option3" value="0" onClick="validateDays();" checked> Collect (Free)
                                    </form>
                                </td>
                            </tr>
                            <tr id="trLast">
                                <td id="r">VAT</td>
                                <td id="rr"></td>
                                <td><div id="vat"></div></td>
                            </tr>
                            <tr>
                                <td id="r"><strong>Order Total</strong></td>
                                <td id="rr"> </td>
                                <td><strong><div id="msg"></div></strong></td>
                            </tr>
                            <tr >
                                <td colspan="2" id="paymentTR">
                                    <input id="payment_method_paypal" type="radio"  name="payment_method" checked value="paypal" > PayPal <img src="https://www.paypalobjects.com/webstatic/mktg/Logo/AM_mc_vs_ms_ae_UK.png" style="width:46px;height: 20px;padding-left: 10px; "alt="PayPal Acceptance Mark">
                                </td>
                                <td id="paymentTR">
                                    <a href="https://www.paypal.com/gb/webapps/mpp/paypal-popup"  style="color: #18C9D2;
                                       text-decoration: none;" title="What is PayPal?">What is PayPal?</a>
                                </td>
                            </tr>
                            <tr >
                                <td colspan="3" id="rulesTR">
                                    <input type="checkbox" id="rules" name="rules" value="rules" title="Do you agree with Terms & Conditions?" required > I read and accept the Terms & Conditions *<br>                            
                                </td>
                            </tr>
                            <script>
                                function validateDays() {
                                    if (document.getElementById("option1").checked === true) {
                                        document.getElementById('vat').innerHTML = "&euro; " + <%=df.format((50 + total) / 100 * 23)%>;
                                        document.getElementById('msg').innerHTML = "&euro; " + <%=df.format((50 + total) + ((50 + total) / 100 * 23))%>;

                                <%
                                    session.setAttribute("vat", df.format((50 + total) / 100 * 23));
                                    session.setAttribute("total", df.format((50 + total) + ((50 + total) / 100 * 23)));
                                %>

                                    } else if (document.getElementById("option2").checked === true) {
                                        document.getElementById('vat').innerHTML = "&euro; " + <%=df.format((10 + total) / 100 * 23)%>;
                                        document.getElementById('msg').innerHTML = "&euro; " + <%=df.format((10 + total) + ((10 + total) / 100 * 23))%>;

                                <%
                                    session.setAttribute("vat", df.format((10 + total) / 100 * 23));
                                    session.setAttribute("total", df.format((10 + total) + ((10 + total) / 100 * 23)));
                                %>

                                    } else if (document.getElementById("option3").checked === true) {
                                        document.getElementById('vat').innerHTML = "&euro; " + <%=df.format((total) / 100 * 23)%>;
                                        document.getElementById('msg').innerHTML = "&euro; " + <%=df.format(total + (total / 100 * 23))%>;

                                <%
                                    session.setAttribute("vat", df.format((total) / 100 * 23));
                                    session.setAttribute("total", df.format(total + (total / 100 * 23)));
                                %>

                                    } else {
                                        var msg = '<span style="color:red;">You must select your shipping!</span><br /><br />';
                                        document.getElementById('msg').innerHTML = msg;
                                    }
                                }
                            </script>
                            <% }%>
                        </table>
                        <input type="hidden" name="action" value="proceedToPaypal" />
                        <button type="submit" formaction="UserActionServlet" value="proceedToPaypal" class="button button--inline-block button--medium btn" id="btnPaypal">Proceed To Paypal</button>
                    </div>
                </form>   
            </div>

            <div id="footer1">
                <p>Copyright &copy; 2016 LVA Movies All Rights Reserved.</p>
                <a href="about.jsp">About </a><a href="https://www.facebook.com/profile.php?id=100011449903415"> Facebook</a><a href="https://twitter.com/lvamoviess"> Twitter</a>
            </div>   

    </body>
</html>