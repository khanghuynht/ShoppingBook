package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author ADMIN
 */
public class UserDAO {

    public UserDTO getUser(String userID, String password) {
        UserDTO userInfo = null;
        try {
            Connection conn = DBUtils.createConnection();
            PreparedStatement p = conn.prepareStatement("select * from tblUser where userID = ? and password = ?");

            p.setString(1, userID);
            p.setString(2, password);

            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                String fullname = rs.getNString("fullname");
                String role = rs.getNString("role");
                userInfo = new UserDTO(userID, "*****", fullname, role);
            }

            rs.close();
            p.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error connect DB at .model.UserDAO getUser()\nError: " + e.toString());
        }
        return userInfo;
    }
}
