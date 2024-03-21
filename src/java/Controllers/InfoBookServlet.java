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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanh
 */
public class InfoBookServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InfoBookServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InfoBookServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookID");
        PrintWriter out = response.getWriter();
        
        CatalogsDAO catalogsDAO = new CatalogsDAO(DAO.getInstance().getConnection());
        List<Catalogs> allCatalogs = catalogsDAO.getAllCatalogs();
        request.setAttribute("catalogsList", allCatalogs);

        
        
        if (bookIdStr != null) {
            try {
                int bookId = Integer.parseInt(bookIdStr);
                
                BooksDAO booksDAO = new BooksDAO(DAO.getInstance().getConnection());

                Books book = booksDAO.getBookById(bookId);
                book.setView(book.getView()+1);
                booksDAO.updateBook(book);
                if (book != null) {
                    request.setAttribute("book_detail", book);
                    request.getRequestDispatcher("Views/InfoBook.jsp").forward(request, response);
                } else {
                    // Book not found logic here, maybe redirect to an error page.
                }

            } catch (Exception e) {
                e.printStackTrace();
                
            }
        } else {
            
        }
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("HomeServlet");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
