package id.co.tumblerism.tumblerism.model.json;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import id.co.tumblerism.tumblerism.activity.ChooseProductActivity;
import id.co.tumblerism.tumblerism.activity.ConfirmProductActivity;

/**
 * Created by Ranu on 02/11/2016.
 */

public class Product implements Serializable, View.OnClickListener {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("merk")
    @Expose
    private String merk;

    @SerializedName("harga")
    @Expose
    private double harga;

    @SerializedName("gambar")
    @Expose
    private String gambar;


    public Product(String id, String merk, double harga, String gambar) {
        this.id = id;
        this.merk = merk;
        this.harga = harga;
        this.gambar = gambar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    @Override
    public void onClick(View view) {
        ChooseProductActivity context = (ChooseProductActivity)view.getContext();
        if(context.getTumbler().getSaldo() < this.harga){
            context.balanceToast();
        } else {
            Intent intent = new Intent(context, ConfirmProductActivity.class);
            intent.putExtra("product", this);
            intent.putExtra("tumbler", context.getTumbler());
            context.startActivity(intent);
        }
    }
}
