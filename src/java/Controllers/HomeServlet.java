/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.*;
import Models.Catalogs;
import Models.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanh
 */
public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewBooksServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewBooksServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        BooksDAO booksDAO = new BooksDAO(DAO.getInstance().getConnection());
        Users user = (Users) request.getSession().getAttribute("user");
        try {
            booksDAO.getNewlyPostedBooks();
            booksDAO.getMostSoldBooks();
            booksDAO.getMostViewBooks();
        } catch (SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("newBooks", booksDAO.getNewBooksList());
        request.setAttribute("soldBooks", booksDAO.getSoldBooksList());
        request.setAttribute("viewBooks", booksDAO.getViewBookList());
        
        
        

        //Catalogs-sidebar
        CatalogsDAO catalogsDAO = new CatalogsDAO(DAO.getInstance().getConnection());
        List<Catalogs> allCatalogs = catalogsDAO.getAllCatalogs();
        request.setAttribute("catalogsList", allCatalogs);
        
        request.getRequestDispatcher("/Views/Home.jsp").forward(request, response);

    }
        

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
