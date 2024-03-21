/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import DAL.OrdersDAO;
import Models.Orders;
import Models.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author thanh
 */
public class DeliveryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeliveryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeliveryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String addressIDraw = request.getParameter("addressID");
        int addressID = 0;
        try {
            addressID = Integer.parseInt(addressIDraw);
        } catch (Exception e) {

        }
        Users user = (Users) request.getSession().getAttribute("user");
        int userID = user.getUserID();
        OrdersDAO ordersDAO = new OrdersDAO(DAO.getInstance().getConnection());
        int orderID = 0;
        try {
            orderID = ordersDAO.findCartOrderID(userID);
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Date currentDate = new Date();  // Current date and time
            Orders o = new Orders(orderID, userID, addressID, currentDate, "ondelivering");
            ordersDAO.updateOrder(o);

        } catch (SQLException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject json = new JSONObject();
        try {
            // Assuming you want to send back the orderID and status
            json.put("status", "success");
            // Other data if necessary
            response.getWriter().write(json.toString());
        } catch (Exception e) {
            json.put("status", "error");
            json.put("message", e.getMessage());
            response.getWriter().write(json.toString());
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
