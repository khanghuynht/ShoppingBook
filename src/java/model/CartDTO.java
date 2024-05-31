package model;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class CartDTO implements Serializable {

    private String userID;
    private String proid;
    private int quantity;
    private int price;
    private static final long serialVersionUID = 1L;

    public CartDTO() {
    }

    public CartDTO(String userID, String proid, int quantity, int price) {
        this.userID = userID;
        this.proid = proid;
        this.quantity = quantity;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
