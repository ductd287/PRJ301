/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AddressDAO;
import DAL.DAO;
import Models.Address;
import Models.Users;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author thanh
 */
@WebServlet(name = "AddressManagementServlet", urlPatterns = {"/AddressManagementServlet"})
public class AddressManagementServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddressManagementServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddressManagementServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AddressDAO dao = new AddressDAO(DAO.getInstance().getConnection());
        Users user = (Users) request.getSession().getAttribute("user");
        int userID = user.getUserID();

        List<Address> addresses = dao.listAddresses(userID);

        request.setAttribute("addresses", addresses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Views/CheckOut.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AddressDAO add = new AddressDAO(DAO.getInstance().getConnection());

        Users user = (Users) request.getSession().getAttribute("user");
        int userID = user.getUserID();

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            Address a = new Address(userID, address, phone);
            add.insertAddress(a);
        } else if ("delete".equals(action)) {
            int addressID = Integer.parseInt(request.getParameter("deleteAddressID"));
            add.deleteAddress(addressID);
        }
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
