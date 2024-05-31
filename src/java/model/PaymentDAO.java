package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.DBUtils;

public class PaymentDAO {

    public int addOrderID(String userID) {
        int orderID = -1; 
        try {
            Connection conn = DBUtils.createConnection();
            PreparedStatement p = conn.prepareStatement("INSERT INTO tblOrder (userid) VALUES (?)",Statement.RETURN_GENERATED_KEYS);

            p.setString(1, userID);

            int value = p.executeUpdate();
            if (value > 0) {
                ResultSet rs = p.getGeneratedKeys();
                if (rs.next()) {
                    orderID = rs.getInt(1);
                }
                rs.close();
            }
            p.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error insert at .model.PaymentDAO addOrderID" + e.getMessage());
        }
        return orderID;
    }

    public boolean addOrderDetail(int orderID, String proID, int quantity, int total) {
        boolean check = false;
        try {
            Connection conn = DBUtils.createConnection();
            PreparedStatement p = conn.prepareStatement("insert into tblOrder_Detail(orderid,proid,quantity,total) values(?,?,?,?)");

            p.setInt(1, orderID);
            p.setString(2, proID);
            p.setInt(3, quantity);
            p.setInt(4, total);

            int value = p.executeUpdate();
            
            if (value > 0) {
                check = true;
            }

            p.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error insert at .model.PaymentDAO addOrderID()");
        }
        return check;
    }
}
