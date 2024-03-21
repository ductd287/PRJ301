/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.*;
import Models.Users;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author thanh
 */
public class SaveCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaveCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveCartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO(DAO.getInstance().getConnection());

        // Phân tích cú pháp JSON từ request
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        JSONObject json = new JSONObject(data);
        JSONArray quantities = json.getJSONArray("quantities");

        // Lấy danh sách OrderDetailID
        Users user = (Users) request.getSession().getAttribute("user");
        int userID = user.getUserID();
        List<Integer> orderDetailIds = null;
        try {
            orderDetailIds = orderDetailsDAO.getOrderDetailIdsByUserIdAndStatus(userID, "cart");
        } catch (SQLException ex) {
            Logger.getLogger(SaveCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Cập nhật dữ liệu vào CSDL
        boolean updateSuccess = true;
        for (int i = 0; i < quantities.length(); i++) {
            int quantity = quantities.getInt(i);
            int orderDetailId = orderDetailIds.get(i);

            try {
                orderDetailsDAO.updateOrderDetailQuantity(orderDetailId, quantity);
            } catch (SQLException ex) {
                updateSuccess = false;
                Logger.getLogger(SaveCartServlet.class.getName()).log(Level.SEVERE, null, ex);
                break; // Thoát khỏi vòng lặp nếu có lỗi
            }
        }

        // Thiết lập kiểu dữ liệu trả về là JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Tạo đối tượng JSON để trả về
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("success", updateSuccess);

        // Gửi JSON phản hồi về client
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
    }
}


