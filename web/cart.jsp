<%-- 
    Document   : cart
    Created on : Mar 7, 2024, 9:16:40 PM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="model.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/cartStyle.css" rel="stylesheet" type="text/css"/>          
    </head>
    <body>
        <div class="container">
            <div class="row cart-all">
                <div class="col-lg-3 col-md-2"></div>
                <div class="col-lg-12 cart-title">
                    Cart
                </div>
                <div class="col-lg-12">
                    <form action="MainController" method="POST">
                        <table>
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Book Title</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    String status = (String) request.getAttribute("STATUS_PAYMENT");
                                    if (status == null) {
                                        status = "";
                                    }
                                    int No = 1;
                                    List<CartDTO> cart = (List<CartDTO>) session.getAttribute("CARTS");
                                    if (cart != null) {
                                        String[] proname = (String[]) session.getAttribute("PRONAME");
                                        for (int i = 0; i < cart.size(); i++) {
                                %>
                                <tr>
                                    <td><%=No%></td>
                                    <td><%=proname[i]%></td>
                                    <td><%=cart.get(i).getQuantity()%></td>
                                    <td><%=cart.get(i).getPrice()%>/per</td>
                                    <td><input type="checkbox" name="selectedItems" value="<%= cart.get(i).getProid() + ":" + cart.get(i).getQuantity()%>">

                                    </td>
                                </tr>
                                <%
                                            No++;
                                        }
                                    }
                                %>
                            </tbody>
                        </table>

                        <div class="col-lg-12 cartbttm">
                            <div class="col-lg-6 cart-btm cart-text status">
                                <%=status%>
                            </div>
                            <div class="col-lg-6 cart-btm cart-button">
                                <button type="submit" class="btn btn-outline-primary" name="action" value="Payment">Payment</button> 
                                <button type="submit" class="btn btn-outline-primary" name="action" value="RemoveCart">Remove Cart</button>   
                            </div>
                        </div>
                    </form>
                    <div class="col-lg-6">
                        <button class="btn btn-outline-primary aref"><a href="MainController?action=Shopping">View Shopping</a></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
