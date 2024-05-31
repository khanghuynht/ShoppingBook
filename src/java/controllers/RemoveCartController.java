/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CartDAO;
import model.CartDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "RemoveCartController", urlPatterns = {"/RemoveCartController"})
public class RemoveCartController extends HttpServlet {

    private static final String CART_PAGE = "cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        String userid = (String) session.getAttribute("USER_ID");
        String[] selectedItems = request.getParameterValues("selectedItems");
        String []proid = null;
        String url = CART_PAGE;
        
        if(selectedItems!=null)
        {
            proid = new String[selectedItems.length];
            for (int i = 0; i < selectedItems.length; i++) {
                String []parts = selectedItems[i].split(":");
                proid[i] = parts[0];
                System.out.println(proid[i]);
            }
        }

        if (proid != null && userid != null) {
            CartDAO cartDAO = new CartDAO();
            for (String x : proid) {
                cartDAO.deleteCart(userid, x);
            }
            List<CartDTO> cList = cartDAO.getAllCart(userid);
            session.setAttribute("CARTS", cList);
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
