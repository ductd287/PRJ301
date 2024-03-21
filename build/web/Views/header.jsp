
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                        <!-- end header -->
                        <section class="slider_section">
                            <div class="banner_main">
                                <div class="container-fluid padding3">
                                    <div class="row">
                                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 mapimg">
                                            <div class="text-bg">
                                                <h1>Read <br>
                                                    Explore<br>
                                                    Dicover <br>
                                                    Enjoy</h1>
                                                <span>Unlock your imagination, travel new worlds, all between the pages of a book.</span>
                                                <a href="#">Buy now</a>
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
                                                                            <figure><img src="images/baking_banner.png" /></figure>
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
                                                                            <figure><img src="images/atomic_habits_banner.png" /></figure>
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
                                                                            <figure><img src="images/art_of_war_banner.png" /></figure>
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
