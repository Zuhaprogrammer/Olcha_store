<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11/7/2024
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .chat-sidebar {
            background-color: #f8f9fa;
            border-right: 1px solid #e0e0e0;
            height: 100vh;
        }
        .chat-content {
            padding: 20px;
            height: 100vh;
            overflow-y: auto;
        }
        /* Har bir ustunning kengligini bir xil qilish */
        .table td, .table th {
            width: 150px; /* Ustun kengligini kerakli o'lchamga o'zgartirishingiz mumkin */
            max-width: 180px;
            overflow: auto;
            white-space: nowrap;
        }
        a {
            color: inherit; /* Link rangini ota element rangiga moslashtiradi */
            text-decoration: none; /* Tag chiziqni olib tashlaydi */
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">


        <!-- Left Sidebar Container -->
        <div class="col-md-3 col-lg-2 chat-sidebar p-3">
            <!--            <h5></h5>-->
            <ul class="list-group">
                <li class="list-group-item"><a href="/admin/category-list">Category</a></li>
                <li class="list-group-item"><a href="/admin/product-list">Product</a></li>
                <li class="list-group-item"><a href="/admin/cart-list">Cart</a></li>
                <li class="list-group-item"><a href="/admin/order-list">Order</a></li>
            </ul>
        </div>



        <!-- Right Chat Content Container -->
        <div class="col-md-9 col-lg-10 chat-content">
<%--            <div class="container pb-3">--%>
<%--                <a href="#" class="btn btn-success" role="button" aria-pressed="true" style="${fn:contains(privileges, 'Create') ? '' : 'display: none;'}">ADD</a>--%>
<%--            </div>--%>

            <table class="table table-bordered table-responsive">
                <caption>List of orders</caption>
                <thead class="thead-dark">
                <tr>
                    <th scope="col">OrderId</th>
                    <th scope="col">UserName</th>
                    <th scope="col">UserPhoneNumber</th>
                    <th scope="col">UserEmail</th>
                    <th scope="col">OrderStatus</th>
                    <th scope="col">PromoCodeValue</th>
                    <th scope="col">PromoCodeType</th>
                    <th scope="col">PromoCodeStartDate</th>
                    <th scope="col">PromoCodeDays</th>
                    <th scope="col">PromoCodeFixedAmount</th>
                    <th scope="col">PromoCodePercent</th>
                    <th scope="col">PromoCodeMinAmount</th>
                    <th scope="col">OrderCreatedDate</th>
                    <th scope="col">OrderUpdatedDate</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orderList}" var="order">
                    <tr>
                        <th scope="row">${order.getOrderId()}</th>
                        <td>${order.getUserName()}</td>
                        <td>${order.getUserPhoneNumber()}</td>
                        <td>${order.getUserEmail()}</td>
                        <td>${order.getOrderStatus()}</td>
                        <td>${order.getPromoCodeValue()}</td>
                        <td>${order.getPromoCodeType()}</td>
                        <td>${order.getPromoCodeStartDate()}</td>
                        <td>${order.getPromoCodeDays()}</td>
                        <td>${order.getPromoCodeFixedAmount()}</td>
                        <td>${order.getPromoCodePercent()}</td>
                        <td>${order.getPromoCodeMinAmount()}</td>
                        <td>${order.getOrderCreatedDate()}</td>
                        <td>${order.getOrderUpdatedDate()}</td>
<%--                        <td><a href="/admin/delete-order?id=${order.getId()}" class="btn btn-danger" role="button" aria-pressed="true" style="${fn:contains(privileges, 'Delete') ? '' : 'display: none;'}">Delete</a>--%>
<%--                            <a href="#" class="btn btn-warning" role="button" aria-pressed="true" style="${fn:contains(privileges, 'Update') ? '' : 'display: none;'}">Update</a></td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
