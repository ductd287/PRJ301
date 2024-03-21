/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Models.*;
import DAL.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanh
 */
public class CartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrdersDAO orders = new OrdersDAO(DAO.getInstance().getConnection());
        PrintWriter out = response.getWriter();

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("LoginServlet");
        } else {
            Users user = (Users) request.getSession().getAttribute("user");
            int userID = user.getUserID();

            List<Books> booksList = null;
            try {
                booksList = orders.getBooksInCartByUserId(userID);
            } catch (SQLException ex) {
                Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("list", booksList);

            CatalogsDAO catalogsDAO = new CatalogsDAO(DAO.getInstance().getConnection());
            Map<Integer, String> catalogNames = new HashMap<>();
            for (Books book : booksList) {
                catalogNames.put(book.getBookID(), catalogsDAO.getCatalogNameById(book.getBookID()));
            }
            request.setAttribute("catalogNames", catalogNames);
            request.getRequestDispatcher("Views/Cart.jsp").forward(request, response);
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
