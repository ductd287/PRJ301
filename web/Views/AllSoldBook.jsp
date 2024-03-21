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
                                                    <a href="Views/Home.jsp"><img src="images/logo.png" alt="#"></a>
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
                                                            <a href="AccountInfoServlet">Account Information</a>
                                                            <a href="OrderServlet">My Orders</a>
                                                            <a href="LogoutServlet">Log out</a>
                                                        </div>
                                                    </li>
                                                </c:if>

                                                <c:if test="${sessionScope.user.role == true}"> <!-- Admin -->
                                                    <li class="menu_iconb">
                                                        <a href="#">${sessionScope.user.name}<img style="margin-left: 15px;" src="icon/6.png" alt="#" /></a>
                                                        <div class="dropdown">
                                                            <a href="ManageBookServlet">Book Manage</a>
                                                            <a href="ManageAccountServlet">Account Manage</a>
                                                            <a href="ManageCatalogServlet">Catalog Manage</a>
                                                            <a href="LogoutServlet">Log out</a>
                                                        </div>
                                                    </li>
                                                </c:if>
                                                <c:if test="${sessionScope.user.role != true}">
                                                <li class="tytyu">
                                                    <a href="CartServlet"> <img style="margin-right: 15px;" src="icon/2.png" alt="#" /></a>
                                                </li>
                                                </c:if>
                                                <li class="menu_iconb">
                                                    <a href="#"><img id="searchIcon" style="margin-right: 15px;" src="icon/3.png" alt="#" /></a>
                                                    <input type="text" id="searchInput"  placeholder="Search..." >
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
                                <div class="Categories">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="title">
                                    <h2>Best Sellers</h2>
                                </div>
                            </div>
                        </div>

                        <!-- news brand -->
                        <div id="brand" class="brand-bg">
                            <h3>All books</h3>
                            <div class="row">
                                <c:forEach var="book" items="${newBooks}">
                                    <div style="padding:15px;" class="col-xl-3 col-lg-3 col-md-6 col-sm-12 margintop">
                                        <div class="brand-box">
                                            <!-- Đặt ảnh trong một liên kết tới InfoBookServlet với tham số bookId -->
                                            <a class="iii" href="InfoBookServlet?bookID=${book.bookID}"><img src="images/${book.imageLink}"/></a>
                                            <h4>Price $<span class="nolmal">${book.price}</span></h4>
                                        </div>
                                            <h4 style="text-align: center;">${book.name}</h4>
                                            <c:if test="${sessionScope.user == null}">
                                                <a class="buynow" href="LoginServlet">Add to cart</a>
                                            </c:if>
                                            <c:if test="${sessionScope.user.role == false}">
                                                <a class="buynow" href="AddToCartServlet?bookID=${book.bookID}" onclick="addToCart(event, ${book.bookID})">Add to cart</a>
                                            </c:if>
                                            <c:if test="${sessionScope.user.role == true}">
                                                <a class="buynow" href="UpdateBookByIDServlet">Edit</a>
                                            </c:if>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
                                <footer>
            <div class="footer">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="footer_top">
                                <div class="row">
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
                                        <a href="index.html"> <img class="logo1" src="images/logo1.png" /></a>
                                    </div>
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
                                        <ul class="sociel">
                                            <li> <a href="#"><i class="fa fa-facebook-f"></i></a></li>
                                            <li> <a href="#"><i class="fa fa-twitter"></i></a></li>
                                            <li> <a href="#"><i class="fa fa-instagram"></i></a></li>
                                            <li> <a href="#"><i class="fa fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 ">
                            <div class="row">
                                <div class="col-xl-3 col-lg-3 col-md-6 col-sm-6 ">
                                    <div class="address">
                                        <h3>Contact us </h3>
                                        <ul class="loca">
                                            <li>
                                                <a href="#"><img src="icon/loc.png" alt="#" /></a>FPT University
                                                <br>Hanoi-Vietnam </li>
                                            <li>
                                                <a href="#"><img src="icon/call.png" alt="#" /></a>+84 888888888 </li>
                                            <li>
                                                <a href="#"><img src="icon/email.png" alt="#" /></a>ductdhe176281@fpt.edu.vn </li>

                                        </ul>

                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-6 col-sm-6">
                                    <div class="address">
                                        <h3>customer service</h3>
                                        <ul class="Menu_footer">
                                            <li class="active"> <a href="#">My account</a> </li>
                                            <li><a href="#">Wishlist</a> </li>
                                            <li><a href="#">Orders</a> </li>
                                            <li><a href="#">Checkout</a> </li>
                                            <li><a href="#">FAQ</a> </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-6 col-sm-6">
                                    <div class="address">
                                        <h3>corporation</h3>
                                        <ul class="Links_footer">
                                            <li class="active"><a href="#">My account</a> </li>
                                            <li><a href="#">About us</a> </li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="col-lg-3 col-md-6 col-sm-6 ">
                                    <div class="address">
                                        <h3>why choose us</h3>
                                        <p>Vi duc dep zai </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="copyright"> 
                    <div class="container">
                        <p>Copyright 2023 All Right Reserved By abcxyz.vn</a></p>
                    </div>
                </div>
            </div>

        </footer>
    <div class="overlay"></div>
    
    <script>
            function addToCart(event, bookId) {
                event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ <a>

                var xhr = new XMLHttpRequest();
                xhr.open("GET", "AddToCartServlet?bookID=" + bookId, true);
                xhr.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        // Xử lý kết quả trả về từ servlet
                        alert("Product added to cart!");
                    }
                };
                xhr.send();
            }
        </script>

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
