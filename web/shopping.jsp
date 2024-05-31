<%-- 
    Document   : shopping
    Created on : Mar 7, 2024, 2:43:13 PM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="model.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Shopping</title>

        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/shoppingStyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            String status = (String) request.getAttribute("STATUS");
            String fullname = (String) session.getAttribute("USER_FULLNAME");
            if (status == null) {
                status = "";
            }
        %>
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-2">
                </div>
                <div class="col-lg-6 col-md-8 shopping-box">

                    <div class="col-lg-12 shopping-title">
                        Welcome, <%=fullname%><br/>
                        Shopping Book 
                    </div>
                    <div class="col-lg-12 select-form"></div>
                    <div class="col-lg-12 select-form">
                        <form action="MainController" method="post">

                            <div class="select-group">
                                <select name="productID">
                                    <option value="notselect" selected>Open this select menu</option>
                                    <%List<ProductDTO> pList = (List<ProductDTO>) session.getAttribute("PRODUCTS");
                                        if (pList != null) {
                                            for (ProductDTO x : pList) {%>
                                    <option value="<%=x.getProid()%>"><%=x.getProname()%></option>
                                    <%}
                                        }%>
                                </select>
                            </div>
                            <div class="col-lg-12 shoppingbttm">
                                <div class="col-lg-6 shopping-btm shopping-text">
                                    <%=status%>
                                </div>
                                <div class="col-lg-6 shopping-btm shopping-button">
                                    <button type="submit" class="btn btn-outline-primary" name="action" value="AddToCart">Add To Cart</button>
                                </div>
                            </div>
                        </form>
                        <button class="btn btn-outline-primary aref"><a href="MainController?action=ViewCart">View Cart</a></button>

                    </div> 
                    <div class="col-lg-12 select-form logout">
                        <div class="col-lg-6 shopping-btm shopping-text"></div>
                        <div class="col-lg-6 shopping-btm shopping-button">
                            <button class="btn btn-outline-primary"><a href="MainController?action=Logout">Logout</a></button>
                        </div>
                    </div>

                </div>
                </body>
                </html>
