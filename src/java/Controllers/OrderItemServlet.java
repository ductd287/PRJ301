/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.DAO;
import DAL.OrderDetailsDAO;
import DAL.OrdersDAO;
import Models.OrderDetails;
import Models.Orders;
import Models.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanh
 */
@WebServlet(name="OrderItemServlet", urlPatterns={"/OrderItemServlet"})
public class OrderItemServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderItemServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderItemServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         Users user = (Users) request.getSession().getAttribute("user");
        int userID = user.getUserID();
        OrdersDAO ordersDAO = new OrdersDAO(DAO.getInstance().getConnection());
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO(DAO.getInstance().getConnection());
        
        String orderIDraw = request.getParameter("orderID");
        int orderID = Integer.parseInt(orderIDraw);
        List<OrderDetails> ord = new ArrayList<>();
        try {
            ord = orderDetailsDAO.getOrderDetailsByOrderID(orderID);
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Orders order = new Orders();
        try {
            order = ordersDAO.getOrdersByID(orderID);
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        order.setPrice(ordersDAO.calculateTotal(ord));
        request.setAttribute("list", ord);
        request.setAttribute("order", order);
        request.getRequestDispatcher("Views/Order.jsp").forward(request, response);
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
