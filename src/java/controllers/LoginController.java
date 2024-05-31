
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
import model.ProductDTO;
import model.UserDAO;
import model.UserDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String CUSTOMER = "customer";
    private static final String SHOPPING_PAGE = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;

        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        UserDTO userInfo = userDAO.getUser(userID, password);

        if (userInfo != null) {
            HttpSession session = request.getSession();
            session.setAttribute("USER_ID", userInfo.getUsername());
            session.setAttribute("USER_FULLNAME", userInfo.getFullname());
            String role = userInfo.getRole();
            if (CUSTOMER.equals(role)) {
                ProductDAO pDAO = new ProductDAO();
                CartDAO cDAO = new CartDAO();
                List<ProductDTO> pList = pDAO.getAllProduct();
                session.setAttribute("PRODUCTS", pList);
                List<CartDTO> cList = cDAO.getAllCart(userID);
                session.setAttribute("CARTS", cList);
                if (cList != null) {
                    String[] proName = new String[cList.size()];
                    for (int i = 0; i < cList.size(); i++) {
                        proName[i] = pDAO.getProductName(cList.get(i).getProid());
                    }
                    session.setAttribute("PRONAME", proName);
                }
                url = SHOPPING_PAGE;
            }
        } else {
            request.setAttribute("ERROR_LOGIN", "INVALID USERID OR PASSWORD");
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
