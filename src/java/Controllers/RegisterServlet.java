
package Controllers;

import DAL.DAO;
import DAL.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username1");
        String name = request.getParameter("name1");
        String password = request.getParameter("password1");
        String email = request.getParameter("email1");
        
        UsersDAO usersDAO = new UsersDAO(DAO.getInstance().getConnection());

        // Kiểm tra tài khoản và email
        if (usersDAO.isUsernameExists(username) || usersDAO.isEmailExists(email)) {
            request.setAttribute("errorMessage1", "Username or email was used!");
            request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
            return;
        }
        
        usersDAO.addUser(username, password, name, email, false, false);
        
        request.setAttribute("errorMessage2", "Register sucessfully!");
        request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
