<%-- 
    Document   : Order
    Created on : Oct 22, 2023, 12:14:23 AM
    Author     : thanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <!-- Google Fonts -->
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />
        <!-- MDB -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.css"
            rel="stylesheet"
            />
        <style>
            @media (min-width: 1025px) {
                .h-custom {
                    height: 100vh !important;
                }
            }

            .horizontal-timeline .items {
                border-top: 2px solid #ddd;
            }

            .horizontal-timeline .items .items-list {
                position: relative;
                margin-right: 0;
            }

            .horizontal-timeline .items .items-list:before {
                content: "";
                position: absolute;
                height: 8px;
                width: 8px;
                border-radius: 50%;
                background-color: #ddd;
                top: 0;
                margin-top: -5px;
            }

            .horizontal-timeline .items .items-list {
                padding-top: 15px;
            }

        </style>
    </head>
    <body>
        <section class="h-100 h-custom" style="background-color: #eee;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-lg-8 col-xl-6">
                        <div class="card border-top border-bottom border-3" style="border-color: #f37a27 !important;">
                            <div class="card-body p-5">

                                <p class="lead fw-bold mb-5" style="color: #f37a27;">Purchase Reciept</p>

                                <div class="row">
                                    <div class="col mb-3">
                                        <p class="small text-muted mb-1">Date</p>
                                        <p>${order.getOrderDate()}</p>
                                    </div>
                                    <div class="col mb-3">
                                        <p class="small text-muted mb-1">Order No.</p>
                                        <p>${order.getOrderID()}</p>
                                    </div>
                                </div>

                                <c:forEach var="book" items="${list}">
                                    <div class="mx-n5 px-5 py-4" style="background-color: #f2f2f2;">
                                        <div class="row">
                                            <div class="col-md-8 col-lg-9">
                                                <p>${book.getName()}</p>
                                            </div>
                                            <div class="col-md-4 col-lg-3">
                                                <p>${book.getPrice()}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-8 col-lg-9">
                                                <p class="mb-0">Discount</p>
                                            </div>
                                            <div class="col-md-4 col-lg-3">
                                                <p class="mb-0">${book.getDiscount()}%</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-8 col-lg-9">
                                                <p class="mb-0">Quantity</p>
                                            </div>
                                            <div class="col-md-4 col-lg-3">
                                                <p class="mb-0">${book.getQuantity()}</p>
                                            </div>
                                        </div>
                                        <c:if test="${order.status == 'delivered'}">
                                            <div class="row">
                                                <a href="InfoBookServlet?permission=true&bookID=${book.bookID}">Rate</a>
                                            </div>
                                        </c:if>

                                    </div>
                                </c:forEach>

                                <div class="row my-4">
                                    <div class="col-md-4 offset-md-8 col-lg-3 offset-lg-9">
                                        <p class="lead fw-bold mb-0" style="color: #f37a27;">$${order.getPrice()}</p>
                                    </div>
                                </div>
                                <p class="mt-4 pt-2 mb-0"><a href="HomeServlet" style="color: #f37a27;">HOME</a></p>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
