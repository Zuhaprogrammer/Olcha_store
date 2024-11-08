<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Product List</title>
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
                <li class="list-group-item"><a>Category</a></li>
                <li class="list-group-item"><a href="/product-list">Product</a></li>
                <li class="list-group-item"><a href="/cart-list">Cart</a></li>
                <li class="list-group-item"><a href="/order-list">Order</a></li>
            </ul>
        </div>



        <!-- Right Chat Content Container -->
        <div class="col-md-9 col-lg-10 chat-content">
            <div class="container pb-3">
                <a href="#" class="btn btn-success" role="button" aria-pressed="true">ADD</a>
            </div>

            <table class="table table-bordered table-responsive">
                <caption>List of products</caption>
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Images</th>
                    <th scope="col">Params</th>
                    <th scope="col">Colours</th>
                    <th scope="col">Description</th>
                    <th scope="col">Discount</th>
                    <th scope="col">FromDelivery</th>
                    <th scope="col">ToDelivery</th>
                    <th scope="col">CreatedDate</th>
                    <th scope="col">UpdatedDate</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${productList}" var="product">
                        <tr>
                            <th scope="row">${product.getId()}</th>
                            <td>${product.getName()}</td>
                            <td>${product.getPrice()}</td>
                            <td>${product.getImages()}</td>
                            <td>${product.getParams()}</td>
                            <td>${product.getColours()}</td>
                            <td>${product.getDescription()}</td>
                            <td>${product.getDiscount()}</td>
                            <td>${product.getFromDelivery()}</td>
                            <td>${product.getToDelivery()}</td>
                            <td>${product.getCreatedDate()}</td>
                            <td>${product.getUpdatedDate()}</td>
                            <td><a href="#" class="btn btn-danger" role="button" aria-pressed="true">Delete</a>
                                <a href="#" class="btn btn-warning" role="button" aria-pressed="true">Update</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
