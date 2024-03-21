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
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanh
 */
public class ManageBookServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageBookServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageBookServlet at " + request.getContextPath() + "</h1>");
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

        BooksDAO booksDAO = new BooksDAO(DAO.getInstance().getConnection());
        String bookID_raw = request.getParameter("bookID");
        String type_raw = request.getParameter("type");

        if (type_raw != null && bookID_raw != null) {
            int type = Integer.parseInt(type_raw);
            int bookID = Integer.parseInt(bookID_raw);

            if (type == 0) { // Type 0 for editing
                Books book = null;
                try {
                    book = booksDAO.getBookById(bookID);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(book.getPostedDate());
                    request.setAttribute("formattedDate", formattedDate);
                } catch (SQLException ex) {
                    Logger.getLogger(ManageBookServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("book", book);
            } else if (type == 1) {
                try {
                    // Type 1 for deleting
                    booksDAO.deleteBook(bookID);
                } catch (SQLException ex) {
                    Logger.getLogger(ManageBookServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            booksDAO.getAllBooks();
        } catch (SQLException ex) {
            Logger.getLogger(ManageBookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Books> listBooks = booksDAO.getAllBooksList();
        request.setAttribute("list", listBooks);
        request.getRequestDispatcher("Views/ManageBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        BooksDAO booksDAO = new BooksDAO(DAO.getInstance().getConnection());
        int bookID = 0;
        // Gather all book details from request
        try{
        bookID = Integer.parseInt(request.getParameter("bookID"));}
        catch (NumberFormatException n){
        }
        int catalogID = Integer.parseInt(request.getParameter("catalogID"));
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));
        double inPrice = Double.parseDouble(request.getParameter("inPrice"));
        int discount = Integer.parseInt(request.getParameter("discount"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int sold = Integer.parseInt(request.getParameter("sold"));   
        String description = request.getParameter("description");
        String imageLink = request.getParameter("imageLink");
        int view = Integer.parseInt(request.getParameter("view"));


        try {
            if (booksDAO.isBookExists(bookID)) {
                try {
                    booksDAO.updateBook(new Books(bookID, catalogID, name, author, price, inPrice, discount, stock, sold, description, imageLink, view));
                } catch (SQLException ex) {
                    Logger.getLogger(ManageBookServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    booksDAO.addBook(new Books(bookID, catalogID, name, author, price, inPrice, discount, stock, sold, description, imageLink, view));
                } catch (SQLException ex) {
                    Logger.getLogger(ManageBookServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageBookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.print(booksDAO.isBookExists(bookID));
            doGet(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ManageBookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
