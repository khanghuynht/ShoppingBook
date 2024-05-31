package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CartDAO;
import model.PaymentDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "PaymentController", urlPatterns = {"/PaymentController"})
public class PaymentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("USER_ID");
        String[] selectedItems = request.getParameterValues("selectedItems");
        String[] quantity_raw = null;
        String[] proID = null;
        int[] quantity = null;

        if (selectedItems != null) {

            quantity_raw = new String[selectedItems.length];
            proID = new String[selectedItems.length];

            for (int i = 0; i < selectedItems.length; i++) {
                String[] parts = selectedItems[i].split(":");
                proID[i] = parts[0];
                quantity_raw[i] = parts[1];
            }
        } 

        if (quantity_raw != null && proID != null) {
            quantity = new int[quantity_raw.length];
            for (int i = 0; i < quantity_raw.length; i++) {
                quantity[i] = Integer.parseInt(quantity_raw[i]);
            }
            PaymentDAO payDAO = new PaymentDAO();
            CartDAO cartDAO = new CartDAO();
            int orderID = payDAO.addOrderID(userID);
            int total;
            for (int i = 0; i < selectedItems.length; i++) {
                total = cartDAO.getTotalAmount(userID, proID[i]);
                if (payDAO.addOrderDetail(orderID, proID[i], quantity[i], total)) {
                    cartDAO.deleteCart(userID, proID[i]);
                }
            }
            session.setAttribute("CARTS", cartDAO.getAllCart(userID));
            request.setAttribute("STATUS_PAYMENT", "PAYMENT SUCCESSFULLY");
        } else {
            request.setAttribute("STATUS_PAYMENT", "PAYMENT FAILED");
        }

        request.getRequestDispatcher("cart.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
