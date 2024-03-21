/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.*;
import Models.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanh
 */
public class AddToCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddToCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        Users user = (Users) request.getSession().getAttribute("user");
        int userID = user.getUserID();

        String bookIDraw = request.getParameter("bookID");
        int bookID = Integer.parseInt(bookIDraw);
        

        BooksDAO booksDAO = new BooksDAO(DAO.getInstance().getConnection());
        OrdersDAO ordersDAO = new OrdersDAO(DAO.getInstance().getConnection());
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO(DAO.getInstance().getConnection());

        // Fetch book details
        Books book = null;
        try {
            book = booksDAO.getBookById(bookID);
        } catch (SQLException ex) {
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Check or create a cart and get the orderID
        int orderID = 0;
        try {
            orderID = ordersDAO.checkOrCreateCart(userID);
        } catch (SQLException ex) {
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set up OrderDetails object
        try {
            // Kiểm tra xem sách đã có trong giỏ hàng chưa
            OrderDetails existingOrderDetail = orderDetailsDAO.getOrderDetailByBookIdAndOrderId(bookID, orderID);

            if (existingOrderDetail != null) {
                // Nếu sách đã có trong giỏ hàng, cập nhật số lượng
                existingOrderDetail.setQuantity(existingOrderDetail.getQuantity() + 1);
                orderDetailsDAO.updateOrderDetail(existingOrderDetail);
            } else {
                // Nếu sách chưa có trong giỏ hàng, thêm mới
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setOrderID(orderID);
                orderDetail.setBookID(bookID);
                orderDetail.setQuantity(1); // Số lượng ban đầu
                orderDetail.setDiscount(book.getDiscount());
                // Không cần set price ở đây vì nó đã được lấy từ bảng Books
                orderDetailsDAO.addOrderDetail(orderDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
