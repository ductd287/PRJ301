<%-- 
    Document   : catalogBooks
    Created on : Oct 28, 2023, 4:39:43 PM
    Author     : thanh
--%>

<%@page import="DAL.*"%>
<%@page import="Models.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>lion</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- style css -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
        <style>
            .menu_iconb .dropdown {
                display: none;
                position: absolute;
                top: 38px;
                right: 140px;
                background-color: #f9f9f9;
                min-width: 200px;
                z-index: 1;
                box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            }

            .menu_iconb .dropdown a {
                padding: 10px 12px;
                text-decoration: none;
                display: block;
                color: black;
            }

            .menu_iconb .dropdown a:hover {
                background-color: #ddd;
            }

            .menu_iconb:hover .dropdown {
                display: block;
            }
            body {
                margin: 0;
                padding: 0;
                font-family: Arial, sans-serif;
                ;
            }
            .page{
                display: flex;
                margin-top: 80px;
            }

            .content {
                margin-left: 5%;
                padding: 10px;
                width: 88%;
            }
            .form-container {
                display: flex;
            }

            .right-column {
                width: 30%;
                padding: 10px;
            }
            .left-column {
                width: 70%;
                overflow-x: auto;
                margin-right: 30px;
            }

            /*table account*/
            table.account {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
                font-size: 15px;
                text-align: left;

            }

            table.account thead th {
                background-color: #009879;
                color: white;
                padding: 10px;
            }

            table.account tbody td {
                border-bottom: 1px solid #dddddd;
                padding: 10px;
            }

            table.account tbody tr:nth-of-type(even) {
                background-color: #f3f3f3;
            }

            table.account tbody tr:nth-of-type(odd) {
                background-color: #ffffff;
            }
            /*input account*/
            .row {
                display: flex;
                margin-bottom: 10px;
            }
            .label {
                width: 30%;
                text-align: left;
                padding-right: 10px;
            }
            .input {
                width: 70%;
            }
            
                    #body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
            margin: 0;
        }

        h2 {
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        a {
            color: #1a0dab;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .btn-view {
            padding: 5px 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }

        .btn-view:hover {
            background-color: #45a049;
        }




        </style>
    </head>
    <body class="main-layout">
        <!-- loader  -->
        <div class="loader_bg">
            <div class="loader"><img src="images/loading.gif" alt="#" /></div>
        </div>
        <div class="wrapper">
            <!-- end loader -->
            <div class="sidebar">
                <!-- Sidebar  -->
                <nav id="sidebar">
                    <div id="dismiss">
                        <i class="fa fa-arrow-left"></i>
                    </div>
                    <ul class="list-unstyled components">
                        <li class="active">
                            <a href="index.html">Home</a>
                        </li>
                        <c:forEach var="catalog" items="${catalogsList}">
                            <li>
                                <a href="#?catalogID=${catalog.catalogID}">${catalog.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>

            </div>

            <div id="content">
                <!-- header -->
                <header>
                    <!-- header inner -->
                    <div class="head_top">
                        <div class="header">

                            <div class="container-fluid">

                                <div class="row">
                                    <div class="col-lg-3 logo_section">
                                        <div class="full">
                                            <div class="center-desk">
                                                <div class="logo">
                                                    <a href="HomeServlet"><img src="images/logo.png" alt="#"></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-9">
                                        <div class="right_header_info">
                                            <ul>
                                                <li class="menu_iconb">
                                                    <a href="#"><img style="margin-right: 15px;" src="icon/1.png" alt="#" />088888888</a>
                                                </li>

                                                <c:if test="${sessionScope.user == null}">
                                                    <li class="menu_iconb">
                                                        <a href="LoginServlet">Log in <img style="margin-right: 15px;" src="icon/5.png" alt="#" /> </a>
                                                    </li>
                                                </c:if>

                                                <c:if test="${sessionScope.user.role == false}"> <!-- User -->
                                                    <li class="menu_iconb">
                                                        <a href="#">${sessionScope.user.name}<img style="margin-left: 15px;" src="icon/6.png" alt="#" /></a>
                                                        <div class="dropdown">
                                                            <a href="#">Account Information</a>
                                                            <a href="#">My Orders</a>
                                                            <a href="LogoutServlet">Log out</a>
                                                        </div>
                                                    </li>
                                                </c:if>

                                                <c:if test="${sessionScope.user.role == true}"> <!-- Admin -->
                                                    <li class="menu_iconb">
                                                        <a href="#">${sessionScope.user.name}<img style="margin-left: 15px;" src="icon/6.png" alt="#" /></a>
                                                        <div class="dropdown">
                                                            <a href="#">Book Manage</a>
                                                            <a href="#">Account Manage</a>
                                                            <a href="#">Catalog Manage</a>
                                                            <a href="LogoutServlet">Log out</a>
                                                        </div>
                                                    </li>
                                                </c:if>

                                                <li class="tytyu">
                                                    <a href="CartServlet"> <img style="margin-right: 15px;" src="icon/2.png" alt="#" /></a>
                                                </li>
                                                <li class="menu_iconb">
                                                    <a href="#"><img style="margin-right: 15px;" src="icon/3.png" alt="#" /></a>
                                                </li>
                                                <li>
                                                    <button type="button" id="sidebarCollapse">
                                                        <img src="images/menu_icon.png" alt="#" />
                                                    </button>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>

                <div id="body">
                <h2>Order List</h2>
                <div>
                    <table>
                        <tr>
                            <th>Order ID</th>
                            <th>Date</th>
                            <th>Status</th>
                            <th>Quantity</th>
                            <th>Total Price</th>
                            <th>Actions</th>
                        </tr>

                        <c:forEach items="${list}" var="order">
                            <c:if test="${order.status == 'ondelivering' || order.status == 'delivered'}">
                                <tr>
                                    <td>${order.orderID}</td>
                                    <td>${order.getOrderDate()}</td>
                                <td>${order.status}</td>
                                <td>${order.quantity}</td>
                                <td>${order.price}</td>
                                <td>
                                    <a href="OrderItemServlet?orderID=${order.orderID}" class="btn-view">View</a>
                                </td>
                                </tr>
                            </c:if>
                        </c:forEach>

                    </table>
                </div>
                </div>





                <div class="overlay"></div>

                <!-- Javascript files-->
                <script src="js/jquery.min.js"></script>
                <script src="js/popper.min.js"></script>
                <script src="js/bootstrap.bundle.min.js"></script>
                <script src="js/jquery-3.0.0.min.js"></script>


                <!-- sidebar -->
                <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
                <script src="js/custom.js"></script>
                <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
                <script type="text/javascript">
                    $(document).ready(function () {
                        $("#sidebar").mCustomScrollbar({
                            theme: "minimal"
                        });

                        $('#dismiss, .overlay').on('click', function () {
                            $('#sidebar').removeClass('active');
                            $('.overlay').removeClass('active');
                        });

                        $('#sidebarCollapse').on('click', function () {
                            $('#sidebar').addClass('active');
                            $('.overlay').addClass('active');
                            $('.collapse.in').toggleClass('in');
                            $('a[aria-expanded=true]').attr('aria-expanded', 'false');
                        });

                        // Thêm chức năng này để đóng sidebar khi nhấp vào bên ngoài
                        $(document).on('click', function (event) {
                            if (!$(event.target).closest('#sidebar, #sidebarCollapse').length) {
                                if ($('#sidebar').hasClass('active')) {
                                    $('#sidebar').removeClass('active');
                                    $('.overlay').removeClass('active');
                                }
                            }
                        });
                    });

                </script>

                <script>
                    $(document).ready(function () {
                        $(".fancybox").fancybox({
                            openEffect: "none",
                            closeEffect: "none"
                        });

                        $(".zoom").hover(function () {

                            $(this).addClass('transition');
                        }, function () {

                            $(this).removeClass('transition');
                        });
                    });
                </script>
                </body>
                </html>
