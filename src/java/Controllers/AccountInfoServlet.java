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

/**
 *
 * @author thanh
 */
public class AccountInfoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AccountInfoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountInfoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsersDAO usersDAO = new UsersDAO(DAO.getInstance().getConnection());
        Users user = (Users) request.getSession().getAttribute("user");

        request.setAttribute("user", user);
        request.getRequestDispatcher("Views/AccountInfo.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String oldPassword = request.getParameter("oldPassword");
        String pass = request.getParameter("newPassword");

        UsersDAO usersDAO = new UsersDAO(DAO.getInstance().getConnection());
        Users user = (Users) request.getSession().getAttribute("user");
        int userID = user.getUserID();
        if(phone == null && oldPassword == null){
        usersDAO.updateUser(userID, name, email);
        } else if(oldPassword == null){
            usersDAO.activateUser(userID, phone);
        } else {
            usersDAO.updateUser(userID, pass);
        }

        request.getSession().invalidate();
        response.sendRedirect("LoginServlet");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
