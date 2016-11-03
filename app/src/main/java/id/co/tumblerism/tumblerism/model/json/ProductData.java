package id.co.tumblerism.tumblerism.model.json;

/**
 * Created by Ranu on 03/11/2016.
 */

public class ProductData {

    private Product P0001,P0002,P0003,P0004;

    public ProductData(Product p0001, Product p0002, Product p0003, Product p0004) {
        P0001 = p0001;
        P0002 = p0002;
        P0003 = p0003;
        P0004 = p0004;
    }

    public Product getP0001() {
        return P0001;
    }

    public void setP0001(Product p0001) {
        P0001 = p0001;
    }

    public Product getP0002() {
        return P0002;
    }

    public void setP0002(Product p0002) {
        P0002 = p0002;
    }

    public Product getP0003() {
        return P0003;
    }

    public void setP0003(Product p0003) {
        P0003 = p0003;
    }

    public Product getP0004() {
        return P0004;
    }

    public void setP0004(Product p0004) {
        P0004 = p0004;
    }
}
