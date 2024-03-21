<%-- 
    Document   : Cart
    Created on : Oct 22, 2023, 12:19:13 AM
    Author     : thanh
--%>

<%@page import="DAL.*"%>
<%@page import="Models.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
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
            }</style>
    </head>
    <body>
        <section class="h-100 h-custom">
            <div class="container h-100 py-5">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col">

                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col" class="h5">Shopping Bag</th>
                                        <th scope="col">Format</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="book" items="${list}">
                                        <tr class="book-row">
                                            <th scope="row">
                                                <div class="d-flex align-items-center">
                                                    <img src="images/${book.getImageLink()}" class="img-fluid rounded-3"
                                                         style="width: 120px;" alt="Book">
                                                    <div class="flex-column ms-4">
                                                        <p class="mb-2">${book.name}</p>
                                                        <p class="mb-0">${book.author}</p>
                                                    </div>
                                                </div>
                                            </th>
                                            <td class="align-middle">
                                                <p class="mb-0" style="font-weight: 500;">${catalogNames[book.bookID]}</p>
                                            </td>
                                            <td class="align-middle">
                                                <div class="d-flex flex-row">
                                                    <button class="btn btn-link px-2"
                                                            onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                                                        <i class="fas fa-minus"></i>
                                                    </button>

                                                    <input  id="form1" min="0" name="quantity" value="${book.quantity}" type="number"
                                                            class="form-control form-control-sm quantity" style="width: 50px;" onchange="updateTotals()" />

                                                    <button class="btn btn-link px-2"
                                                            onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                                        <i class="fas fa-plus"></i>
                                                    </button>
                                                </div>
                                            </td>
                                            <td class="align-middle">
                                                <p class="mb-0 price"  style="font-weight: 500;">${book.price}</p>
                                            </td>
                                            <td>
                                                <button class="btn btn-danger" onclick="deleteBookFromCart(${book.bookID})"> X
                                                </button>
                                            </td>

                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>

                        <h5 style="display: none;" class="discount">10</h5>

                        <div class="card shadow-2-strong mb-5 mb-lg-0" style="border-radius: 16px;">
                            <div class="card-body p-4">

                                <div class="row">
                                    <div class="col-md-6 col-lg-4 col-xl-3 mb-4 mb-md-0">
                                        <form>
                                            <div class="d-flex flex-row pb-3">
                                                <div class="d-flex align-items-center pe-2">
                                                    <input class="form-check-input" type="radio" name="radioNoLabel" id="radioNoLabel1v"
                                                           value="" aria-label="..." checked />
                                                </div>
                                                <div class="rounded border w-100 p-3">
                                                    <p class="d-flex align-items-center mb-0">
                                                        <i class="fab fa-cc-mastercard fa-2x text-dark pe-2"></i>Credit
                                                        Card
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="d-flex flex-row pb-3">
                                                <div class="d-flex align-items-center pe-2">
                                                    <input class="form-check-input" type="radio" name="radioNoLabel" id="radioNoLabel2v"
                                                           value="" aria-label="..." />
                                                </div>
                                                <div class="rounded border w-100 p-3">
                                                    <p class="d-flex align-items-center mb-0">
                                                        <i class="fab fa-cc-visa fa-2x fa-lg text-dark pe-2"></i>Debit Card
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="d-flex flex-row">
                                                <div class="d-flex align-items-center pe-2">
                                                    <input class="form-check-input" type="radio" name="radioNoLabel" id="radioNoLabel3v"
                                                           value="" aria-label="..." />
                                                </div>
                                                <div class="rounded border w-100 p-3">
                                                    <p class="d-flex align-items-center mb-0">
                                                        <i class="fab fa-cc-paypal fa-2x fa-lg text-dark pe-2"></i>PayPal
                                                    </p>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="col-md-6 col-lg-4 col-xl-6">
                                        <div class="row">
                                            <div class="col-12 col-xl-6">
                                                <div class="form-outline mb-4 mb-xl-5">
                                                    <input type="text" id="typeName" class="form-control form-control-lg" siez="17"
                                                           placeholder="John Smith" />
                                                    <label class="form-label" for="typeName">Name on card</label>
                                                </div>

                                                <div class="form-outline mb-4 mb-xl-5">
                                                    <input type="text" id="typeExp" class="form-control form-control-lg" placeholder="MM/YY"
                                                           size="7" id="exp" minlength="7" maxlength="7" />
                                                    <label class="form-label" for="typeExp">Expiration</label>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xl-6">
                                                <div class="form-outline mb-4 mb-xl-5">
                                                    <input type="text" id="typeText" class="form-control form-control-lg" siez="17"
                                                           placeholder="1111 2222 3333 4444" minlength="19" maxlength="19" />
                                                    <label class="form-label" for="typeText">Card Number</label>
                                                </div>

                                                <div class="form-outline mb-4 mb-xl-5">
                                                    <input type="password" id="typeText" class="form-control form-control-lg"
                                                           placeholder="&#9679;&#9679;&#9679;" size="1" minlength="3" maxlength="3" />
                                                    <label class="form-label" for="typeText">Cvv</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-xl-3">
                                        <div class="d-flex justify-content-between" style="font-weight: 500;">
                                            <p class="mb-2">Subtotal</p>
                                            <p class="mb-2 subtotal" id="subtotal">$0.00</p>
                                        </div>

                                        <div class="d-flex justify-content-between" style="font-weight: 500;">
                                            <p class="mb-0">Shipping</p>
                                            <p class="mb-0">$2.99</p>
                                        </div>

                                        <div class="d-flex justify-content-between" style="font-weight: 500;">
                                            <p class="mb-0">Tax</p>
                                            <p class="mb-0">7%</p>
                                        </div>

                                        <div class="d-flex justify-content-between" style="font-weight: 500;">
                                            <p class="mb-0">Discount</p>
                                            <p class="mb-0 totalDiscount" id="totalDiscount">$0.00</p>
                                        </div>

                                        <hr class="my-4">

                                        <div class="d-flex justify-content-between mb-4" style="font-weight: 500;">
                                            <p class="mb-2">Total (tax included)</p>
                                            <p class="mb-2 totalPriceWithTax"id="totalPriceWithTax">$0.00</p>
                                        </div>

                                        <button type="button" class="btn btn-primary btn-block btn-lg" id="saveCartButton">
                                            <div class="d-flex justify-content-between">
                                                <span>Save cart</span>
                                            </div>
                                        </button>
                                        <button type="button" class="btn btn-primary btn-block btn-lg" id="checkout-button">
                                            <div class="d-flex justify-content-between">
                                                <span>Checkout</span>
                                                <span class="totalCheckoutPrice" id="totalCheckoutPrice">$0.00</span>
                                            </div>
                                        </button>


                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </section>
        <script>
            document.getElementById("saveCartButton").addEventListener("click", function () {
                let quantities = document.querySelectorAll(".quantity");
                let quantitiesArray = Array.from(quantities).map(input => input.value);


                // Gửi request AJAX
                fetch('SaveCartServlet', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({quantities: quantitiesArray})
                })
                        .then(response => response.json())
                        .then(data => {
                            console.log('Success:', data);
                            if (data.success) {
                                alert("Giỏ hàng đã được cập nhật!");
                                window.location.href = 'HomeServlet'; // Chuyển hướng tới HomeServlet
                            } else {
                                alert("Có lỗi xảy ra khi cập nhật giỏ hàng.");
                            }
                        })
                        .catch((error) => {
                            console.error('Error:', error);
                        });
            });

        </script>
        <script>
            document.getElementById("checkout-button").addEventListener("click", function () {
                let quantities = document.querySelectorAll(".quantity");
                let quantitiesArray = Array.from(quantities).map(input => input.value);


                // Gửi request AJAX
                fetch('CheckOutServlet', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({quantities: quantitiesArray})
                })
                        .then(response => response.json())
                        .then(data => {
                            console.log('Success:', data);
                            if (data.success) {
                                alert("Bạn sẽ được chuyển đến trang địa chỉ!");
                                window.location.href = 'AddressServlet';
                            } else {
                            }
                        })
                        .catch((error) => {
                            console.error('Error:', error);
                        });
            });

        </script>
        
        <script>
            function deleteBookFromCart(orderDetailID) {
                var url = "DeleteBookCartServlet?BookID=" + orderDetailID;
                window.location.href = url;
                alert("Book was deleted from cart!");
            }
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {

                updateTotals();
            });

            function updateTotals() {
                let subtotal = 0;
                let totalDiscount = 0;
                const shippingFee = 2.99;
                const taxRate = 0.07;

                // Calculate subtotal and total discounts
                const books = document.querySelectorAll('.book-row');
                books.forEach(book => {
                    const price = parseFloat(book.querySelector('.price').textContent.replace(/[^\d.]/g, ''));  // Sửa selector
                    const quantity = parseInt(book.querySelector('.quantity').value);
                    if (quantity <= 0) {
                        let quantity = 0;
                    }

                    const discountPercent = parseFloat(book.querySelector('.discount')) || 0;
                    const totalItemPrice = price * quantity;
                    const discountAmount = totalItemPrice * (10 / 100);

                    subtotal += totalItemPrice;
                    totalDiscount += discountAmount;
                });

                let totalBeforeTaxAndShipping = subtotal - totalDiscount;
                let tax = totalBeforeTaxAndShipping * taxRate;
                let total = totalBeforeTaxAndShipping + tax + shippingFee;

                console.log("Subtotal: ", subtotal);
                console.log("Total Before Tax and Shipping: ", totalBeforeTaxAndShipping);
                console.log("Tax: ", tax);
                console.log("Total: ", total);

                // Display the calculated totals
                document.getElementById('subtotal').textContent = '$' + subtotal.toFixed(2);
                document.getElementById('totalDiscount').textContent = `-$` + totalDiscount.toFixed(2);
                document.getElementById('totalPriceWithTax').textContent = `$` + total.toFixed(2);
                document.getElementById('totalCheckoutPrice').textContent = `$` + total.toFixed(2);

            }

        </script>
    </body>
</html>
