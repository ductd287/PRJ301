/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.*;
import Models.Users;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author thanh
 */
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/Views/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("email");
        String password = request.getParameter("password");

        UsersDAO usersDAO = new UsersDAO(DAO.getInstance().getConnection());
        Users user = usersDAO.getUserByUsername(username);

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Username and password must not be empty.");
            RequestDispatcher rd = request.getRequestDispatcher("/Views/Login.jsp");
            rd.forward(request, response);
            return;
        }
 
        //PrintWriter out = response.getWriter();
        //out.print(user.getPassword());
        if (username != null && user != null && user.getPassword().equals(password)) {
            // Đăng nhập thành công, chuyển hướng đến trang Home.jsp
            HttpSession s = request.getSession();
            s.setAttribute("user", user);
            response.sendRedirect("HomeServlet");
        } else {
            // Đăng nhập không thành công, chuyển hướng lại đến trang đăng nhập với thông báo
            request.setAttribute("errorMessage", "Invalid username or password.");
//            PrintWriter out = response.getWriter();
//            out.print(user.getPassword());
            RequestDispatcher rd = request.getRequestDispatcher("/Views/Login.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
