<%@page import="DAL.*"%>
<%@page import="Models.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">

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
            .img_bg {

                padding-left: 550px;
                margin: 0 auto;
                width: 100%;
                display: flex;
                height: 100%;
                text-align: center;
                flex-wrap: wrap;
            }

            .img_bg figure {
                margin: 0px;
                max-height: 700px;
            }

            .img_bg figure img {
                width: 100%;
                max-height: 480px;
                border-radius: 15px;
                transition: transform 0.3s ease, filter 0.3s ease;
            }
            .img_bg figure img:hover{
                filter: brightness(1.1); /* Làm sáng hình ảnh một chút khi hover */
                transform: scale(1.05); /* Phóng to hình ảnh một chút khi hover */
            }
            .text-bg {
                position: relative; /* Để đảm bảo nội dung trong .text-bg được căn chỉnh tương đối */
                height: 650px;
            }
            .banner_main .text-bg h1 {
                color: #fff;
                font-weight: bold;
                font-size: 65px;
                line-height: 100px;
                text-transform: uppercase;
            }

            .text-bg h1 {
                max-height: 50%; /* Tối đa 50% chiều cao của div cha */
                overflow: hidden; /* Ẩn nội dung vượt quá */
                text-overflow: ellipsis; /* Thêm ba chấm "..." khi nội dung bị cắt */
            }

            .special-price {
                font-weight: bold; /* Đặt độ đậm cho văn bản giá */
                color: #FF0000; /* Đặt màu chữ cho giá, ví dụ màu đỏ (#FF0000) */
                font-size: 24px; /* Đặt kích thước chữ cho giá, ví dụ 24px */
            }
            .rating-summary {
                display: flex;
                justify-content: space-between;
                align-items: flex-start;
                padding: 20px;
                margin: 20px 100px;

            }

            .average-rating {
                flex: 1;
                border: 1px solid #ccc;
                margin-right: 5px;
                padding: 10px;
            }

            .stars {
                font-size: 24px;
            }

            .star {
                margin-right: 5px;
            }

            .individual-ratings {
                flex: 1;
                text-align: left;
                border: 1px solid #ccc;
                margin-left: 5px;

            }
            .individual-rate{
                height: 100%;
            }

            .rating-div{
                margin:5px;
                border: 1px solid #ccc;
                margin:5px;
            }

            .rating {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
                padding: 0px 5px;
            }

            .rating-form {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                margin-top: 20px;
            }

            .rating-form h3 {
                margin-bottom: 15px;
                color: #333;
                text-align: center;
            }

            .rating-form .form-group {
                margin-bottom: 15px;
            }

            .rating-form .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            .rating-form .form-group input[type="number"],
            .rating-form .form-group textarea {
                width: 40%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .rating-form .form-group textarea {
                resize: vertical;
            }

            .btn-primary {
                background-color: #orange;
                border-color: #orange;
                color: white;
                padding: 10px 15px;
                border-radius: 4px;
                cursor: pointer;
                display: inline-block;
                text-align: center;
            }

            .btn-primary:hover {
                background-color: #0069d9;
                border-color: #0062cc;
            }



        </style>
    </head>
    <body>
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

                        <!-- end header inner -->

                        <!-- end header -->
                        <section class="slider_section">
                            <div class="banner_main">
                                <div class="container-fluid padding3">
                                    <div class="row">
                                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 mapimg">
                                            <div class="text-bg">
                                                <h1>${book_detail.name}</h1>
                                                <span style="margin-bottom:5px;">Price: $${book_detail.price}</span>  
                                                <span>Author: ${book_detail.author}</span>
                                                <span>Catalog: ${book_detail.catalogName}</span>
                                                <span>View: ${book_detail.getView()}</span>
                                                <span>Sold: ${book_detail.sold} </span>
                                                <span>Stock: ${book_detail.sold} </span>
                                                <span>Listing time: ${book_detail.postedDate}</span>
                                                <span>Description: ${book_detail.getDescription()}</span>
                                                <c:if test="${sessionScope.user == null}">
                                                    <a class="buynow" href="LoginServlet">Add to cart</a>
                                                </c:if>
                                                <c:if test="${sessionScope.user.role == false}">
                                                    <a class="buynow" href="AddToCartServlet?bookID=${book.bookID}" onclick="addToCart(event, ${book_detail.bookID})">Add to cart</a>
                                                </c:if>
                                                <c:if test="${sessionScope.user.role == true}">
                                                    <a class="buynow" href="UpdateBookByIDServlet">Edit</a>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="col-xl-8 col-lg-8 col-md-8 col-sm-12">
                                            <div id="myCarousel" class="carousel slide banner_Client" data-ride="carousel">
                                                <ol class="carousel-indicators">
                                                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                                    <li data-target="#myCarousel" data-slide-to="1"></li>
                                                    <li data-target="#myCarousel" data-slide-to="2"></li>
                                                </ol>
                                                <div class="carousel-inner">
                                                    <div class="carousel-item active">
                                                        <div class="container">
                                                            <div class="carousel-caption text">
                                                                <div class="row">
                                                                    <div class="col-md-12">
                                                                        <div class="img_bg">
                                                                            <figure><img src="images/${book_detail.imageLink}" /></figure>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="carousel-item">
                                                        <div class="container">
                                                            <div class="carousel-caption text">
                                                                <div class="row">
                                                                    <div class="col-md-12">
                                                                        <div class="img_bg">
                                                                            <figure><img src="images/${book_detail.getImageLink()}" /></figure>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="carousel-item">
                                                        <div class="container">
                                                            <div class="carousel-caption text">
                                                                <div class="row">
                                                                    <div class="col-md-12">
                                                                        <div class="img_bg">
                                                                            <figure><img src="images/${book_detail.imageLink}" /></figure>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>

                    </div>
                </header>


            </div>


            <!--  footer -->
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
                                                    <a href="#"><img src="icon/loc.png" alt="#" /></a>145 londone
                                                    <br>uk </li>
                                                <li>
                                                    <a href="#"><img src="icon/call.png" alt="#" /></a>+12586954775 </li>
                                                <li>
                                                    <a href="#"><img src="icon/email.png" alt="#" /></a>demo@gmail.com </li>

                                            </ul>

                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6">
                                        <div class="address">
                                            <h3>customer service</h3>
                                            <ul class="Menu_footer">
                                                <li class="active"> <a href="#">My account</a> </li>
                                                <li><a href="#">Wishlist</a> </li>
                                                <li><a href="#">My Cart</a> </li>
                                                <li><a href="#">Checkout</a> </li>
                                                <li><a href="#">Login</a> </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6">
                                        <div class="address">
                                            <h3>corporation</h3>
                                            <ul class="Links_footer">
                                                <li class="active"><a href="#">My account</a> </li>
                                                <li><a href="#">Wishlist</a> </li>
                                                <li><a href="#">My Cart</a> </li>
                                                <li><a href="#"> Checkout</a> </li>
                                                <li><a href="#">Login</a> </li>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="col-lg-3 col-md-6 col-sm-6 ">
                                        <div class="address">
                                            <h3>why choose us</h3>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna </p>
                                            <form class="newtetter">
                                                <input class="tetter" placeholder="Your email" type="text" name="Your email">
                                                <button class="submit">Subscribe</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="copyright"> 
                        <div class="container">
                            <p>Copyright 2019 All Right Reserved By <a href="https://html.design/">Free html Templates</a></p>
                        </div>
                    </div>
                </div>

            </footer>
            <!-- end footer -->


        </div>

        <div class="overlay"></div>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                let h1Element = document.querySelector('.text-bg h1');

                const maxChars = 25; // Giới hạn số lượng ký tự tối đa
                if (h1Element.textContent.length > maxChars) {
                    h1Element.textContent = h1Element.textContent.substring(0, maxChars - 5) + '...';
                }
            });

        </script>
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

        <script>
            // JavaScript
            searchInput.style.display = "none";
            document.addEventListener("click", function (event) {
                const searchIcon = document.getElementById("searchIcon");
                const searchInput = document.getElementById("searchInput");



                // Kiểm tra nếu người dùng nhấp vào biểu tượng tìm kiếm
                if (event.target === searchIcon) {
                    searchInput.style.display = "inline-block";
                    searchInput.focus();
                } else {
                    // Kiểm tra nếu người dùng nhấp ra ngoài ô nhập
                    if (event.target !== searchInput) {
                        searchInput.style.display = "none";
                    }
                }
            });

            // Xử lý sự kiện khi nhấn Enter
            document.getElementById("searchInput").addEventListener("keydown", function (event) {
                if (event.key === "Enter") {
                    const searchString = this.value;
                    if (searchString.trim() !== "") {
                        window.location.href = "SearchServlet?searchString=" + encodeURIComponent(searchString);
                    }
                }
            });
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