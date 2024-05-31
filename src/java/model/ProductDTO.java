package model;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class ProductDTO implements Serializable {

    private String proid;
    private String proname;
    private int price;
    private static final long serialVersionUID = 1L;

    public ProductDTO() {
    }

    public ProductDTO(String proid, String proname, int price) {
        this.proid = proid;
        this.proname = proname;
        this.price = price;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
