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
import model.ProductDAO;

@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    private static final String SHOPPING_PAGE = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String url = SHOPPING_PAGE;
        String userID = (String) session.getAttribute("USER_ID");
        String productID = request.getParameter("productID");
        System.out.println(productID);
        ProductDAO pDAO = new ProductDAO();
        int price = pDAO.getPrice(productID);

        if (productID.equals("notselect")) {
            request.setAttribute("STATUS", "Please select a product");
        } else {
            CartDAO cartDAO = new CartDAO();
            CartDTO cart = new CartDTO(userID, productID, 1, price);
            if (cartDAO.insertCart(cart)) {
                List<CartDTO> cList = cartDAO.getAllCart(userID);
                session.setAttribute("CARTS", cList);
                String[] proName = new String[cList.size()];
                for (int i = 0; i < cList.size(); i++) {
                    proName[i] = pDAO.getProductName(cList.get(i).getProid());
                }
                session.setAttribute("PRONAME", proName);
                request.setAttribute("STATUS", "Add to cart successfully");
            } else {
                request.setAttribute("STATUS", "Error occured add to cart failed");
            }
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
