package id.co.tumblerism.tumblerism.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import id.co.tumblerism.tumblerism.model.json.Product;

/**
 * Created by Ranu on 02/11/2016.
 */

public class Tumbler implements Serializable {

    @SerializedName("id_tumbler")
    @Expose
    private String id_tumbler;

    @SerializedName("saldo")
    @Expose
    private double saldo;

    public String getId_tumbler() {
        return id_tumbler;
    }

    public void setId_tumbler(String id_tumbler) {
        this.id_tumbler = id_tumbler;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String beli(Product product){
        String msg;
        if(this.saldo >= product.getHarga()){
            this.saldo -= product.getHarga();
            msg = "Pembelian sukses, sisa saldo Rp. "+this.saldo;
        } else
            msg = "Saldo tidak cukup";
        return msg;
    }
}
