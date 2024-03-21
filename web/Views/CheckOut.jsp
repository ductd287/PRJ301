<%@ page import="java.util.List"%>
<%@ page import="Models.Address"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Select or Add Address</title>
        <!-- Your CSS here -->
        <style>
            body {
                font-family: Arial, sans-serif;
            }

            .container {
                width: 300px;
                margin: 0 auto;
                padding-top: 20px;
            }

            form {
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            input[type="text"], select {
                width: 100%;
                padding: 8px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                width: 100%;
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

            #confirm-Delivery{
                width: 100%;
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            #confirm-Delivery:hover{
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div class="container">

            <h2>Select Address</h2>
            <%
                List<Address> addresses = (List<Address>) request.getAttribute("addresses");
                if (addresses != null) {
            %>
            <select name="addressDropdown" id="addressDropdown">
                <c:forEach items="${addresses}" var="address">
                    <option value="${address.addressID}">${address.address} - ${address.phone}</option>
                </c:forEach>
            </select>
            <%
                }
            %>


            <h2>Add New Address</h2>
            <form action="AddressManagementServlet" method="post">
                <input type="text" name="address" placeholder="Address" required /><br>
                <input type="text" name="phone" placeholder="Phone" required /><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Add Address" />
            </form>

            <h2>Delete Address</h2>
            <form action="AddressManagementServlet" method="post">
                <select name="deleteAddressID">
                    <%
                        if (addresses != null) {
                            for (Address address : addresses) {
                                out.println("<option value='" + address.getAddressID() + "'>" + address.getAddress() + "</option>");
                            }
                        }
                    %>
                </select><br>
                <input type="hidden" name="action" value="delete">
                <input type="submit" value="Delete Address" />

            </form>
            <form id="deliveryForm" method="get">
                <button type="button" onclick="setDeliveryAddress()">Confirm Delivery</button>
            </form>

        </div>

        <script>
            function setDeliveryAddress() {
                var addressID = document.getElementById('addressDropdown').value;
                fetch('DeliveryServlet?addressID=' + addressID)
                        .then(response => response.json())
                        .then(data => {
                            if (data.status === "success") {
                                window.location.href = "HomeServlet"; // Redirect
                            } else {
                                console.error('Error:', data.message);
                            }
                        })
                        .catch((error) => {
                            console.error('Error:', error);
                        });
            }


        </script>


    </body>
</html>
