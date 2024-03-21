/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.*;
import Models.Catalogs;
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
public class CatalogAllBookServlet extends HttpServlet {
   
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
            out.println("<title>Servlet CatalogAllBookServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CatalogAllBookServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String catalogIDraw = request.getParameter("catalogID");
        int catalogID = Integer.parseInt(catalogIDraw);
        
        BooksDAO booksDAO = new BooksDAO(DAO.getInstance().getConnection());
        try {
            booksDAO.getAllBooksByCatalog(catalogID);
        } catch (SQLException ex) {
            Logger.getLogger(CatalogAllBookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        out.print(catalogID);
        CatalogsDAO catalogsDAO = new CatalogsDAO(DAO.getInstance().getConnection());
        List<Catalogs> allCatalogs = catalogsDAO.getAllCatalogs();
        request.setAttribute("catalogsList", allCatalogs);
        
        request.setAttribute("catalogName", catalogsDAO.getCatalogNameById(catalogID));
        request.setAttribute("allBooks", booksDAO.getAllBooksCatalog());
        request.getRequestDispatcher("Views/catalogBooks.jsp").forward(request, response);
    } 

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
