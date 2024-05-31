package model;

public class PaymentDTO {

    private String orderid;
    private String userid;
    private String proid;
    private int quantity;
    private int price;

    public PaymentDTO() {
    }

    public PaymentDTO(String orderid, String userid, String proid, int quantity, int price) {
        this.orderid = orderid;
        this.userid = userid;
        this.proid = proid;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
