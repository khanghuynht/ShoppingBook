package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author ADMIN
 */
public class CartDAO {

    public boolean insertCart(CartDTO cart) {
        boolean check = false;
        if (checkDuplicate(cart.getUserID(), cart.getProid())) {
            updateQuantity(cart.getUserID(), cart.getProid());
            check = true;
        } else {
            addFirstCart(cart.getUserID(), cart.getProid(), cart.getQuantity(), cart.getPrice());
            check = true;
        }
        return check;
    }

    public void addFirstCart(String userID, String proID, int quantity, int price) {
        try {
            Connection conn = DBUtils.createConnection();
            PreparedStatement p = conn.prepareStatement("insert into tblCart(userid,proid,quantity,price) values(?,?,?,?)");

            p.setString(1, userID);
            p.setString(2, proID);
            p.setInt(3, quantity);
            p.setInt(4, price);

            int value = p.executeUpdate();

            System.out.println("Insert with value: " + value);

            p.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error insert at .model.CartDAO addCart()");
        }
    }

    public void updateQuantity(String userID, String proID) {
        try {
            Connection conn = DBUtils.createConnection();
            PreparedStatement p = conn.prepareStatement("UPDATE tblCart SET quantity = quantity + 1 WHERE userid = ? and proid = ?");

            p.setString(1, userID);
            p.setString(2, proID);

            int value = p.executeUpdate();

            System.out.println("update quantity with value : " + value);
            p.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error insert at .model.CartDAO updateQuantity()");
        }
    }

    public boolean checkDuplicate(String userID, String proID) {
        boolean check = false;
        try {
            Connection conn = DBUtils.createConnection();
            PreparedStatement p = conn.prepareStatement("select * from tblCart where userid=? and proid=?");

            p.setString(1, userID);
            p.setString(2, proID);

            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                check = true;
            }

            rs.close();
            p.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error insert at .model.CartDAO checkDuplicate()");
        }
        return check;
    }

    public List<CartDTO> getAllCart(String userID) {
        List<CartDTO> cList = new ArrayList<>();
        CartDTO cart = null;
        try {
            Connection conn = DBUtils.createConnection();
            PreparedStatement p = conn.prepareStatement("select * from tblCart where userid=?");

            p.setString(1, userID);

            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                String proid = rs.getNString("proid");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                cart = new CartDTO(userID, proid, quantity, price);
                cList.add(cart);
            }
            rs.close();
            p.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error at .model.CartDAO getAllCart()");
        }
        return cList;
    }

    public boolean deleteCart(String userid, String proid) {
        boolean check = false;
        try {
            Connection conn = DBUtils.createConnection();
            PreparedStatement p = conn.prepareStatement("delete from tblCart where userid = ? and proid = ?");
            p.setString(1, userid);
            p.setString(2, proid);
            int value = p.executeUpdate();
            if (value > 0) {
                check = true;
            }
            System.out.println("Delete's value: " + value);

            p.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error delete at .model.CartDAO deleteCart()");
        }
        return check;
    }

    public int getTotalAmount(String userID, String proID) {
        int total = -1;
        try {
            Connection conn = DBUtils.createConnection();
            PreparedStatement p = conn.prepareStatement("select sum(quantity * price) from tblCart where userid=? and proid=?");

            p.setString(1, userID);
            p.setString(2, proID);

            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }

            rs.close();
            p.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error insert at .model.CartDAO getTotalAmount()");
        }
        return total;
    }
}
