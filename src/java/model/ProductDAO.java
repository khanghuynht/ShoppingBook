
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
public class ProductDAO {

    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> pList = new ArrayList<>();

        try {
            Connection conn = DBUtils.createConnection();
            PreparedStatement p = conn.prepareStatement("select * from tblProducts");

            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                String proid = rs.getNString(1);
                String proName = rs.getNString(2);
                int price = rs.getInt(3);

                ProductDTO in = new ProductDTO(proid, proName, price);
                pList.add(in);

            }
            rs.close();
            p.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error connect DB at .ProductDAO getAllProduct() ");
        }
        return pList;
    }

    public int getPrice(String proid) {
        int price = -1;
        try {
            Connection conn = DBUtils.createConnection();
            PreparedStatement p = conn.prepareStatement("select price from tblProducts where proid =?");

            p.setString(1, proid);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                price = rs.getInt(1);
            }
            rs.close();
            p.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error connect DB at .ProductDAO getPrice() ");
        }
        return price;
    }

    public String getProductName(String proid) {
        String proName = "";
        try {
            Connection conn = DBUtils.createConnection();
            PreparedStatement p = conn.prepareStatement("select proname from tblProducts where proid =?");

            p.setString(1, proid);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                proName = rs.getNString(1);
            }
            rs.close();
            p.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error connect DB at .ProductDAO getPrice() ");
        }
        return proName;
    }
}
