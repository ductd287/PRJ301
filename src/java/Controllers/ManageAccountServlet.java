/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import DAL.UsersDAO;
import Models.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author thanh
 */
public class ManageAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageAccountServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Users user = (Users) request.getSession().getAttribute("user");
        boolean roleID = user.isRole();
        // out.print(user.getClass());
        if (roleID != true) {
            response.sendRedirect("HomeServlet");
            return;
        }
        UsersDAO usersDAO = new UsersDAO(DAO.getInstance().getConnection());
        String username_raw = request.getParameter("username");
        String type_raw = request.getParameter("type");
        if (type_raw != null && username_raw != null) {
            int type = Integer.parseInt(type_raw);
            if (type == 0) {
                Users userm = usersDAO.getUserByUsername(username_raw);
                request.setAttribute("userm", userm);
            } else {
                out.println("test");
                boolean delete = usersDAO.deleteUserByUsername(username_raw);
            }

        }

        UsersDAO listUser = new UsersDAO(DAO.getInstance().getConnection());

        List<Users> listUsers = listUser.getAllStudents();
        request.setAttribute("list", listUsers);
        request.getRequestDispatcher("Views/ManageAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        UsersDAO usersDAO = new UsersDAO(DAO.getInstance().getConnection());
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String roleStr = request.getParameter("role");
        String activeStr = request.getParameter("active");
        boolean role = Boolean.parseBoolean(roleStr);
        boolean active = Boolean.parseBoolean(activeStr);

        if (usersDAO.isUsernameExists(username) && usersDAO.isEmailExists(email)) {
            usersDAO.updateUser(username, password, name, email, phone, role, active);
        } else {
            usersDAO.addUser(username, password, name, email, phone, role, active);
        }
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
