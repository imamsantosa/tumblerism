package id.co.tumblerism.tumblerism.model.api;

import id.co.tumblerism.tumblerism.model.api.service.BuyService;
import id.co.tumblerism.tumblerism.model.api.service.ProductService;
import id.co.tumblerism.tumblerism.model.api.service.TumblerService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ranu on 02/11/2016.
 */

public class RestClient {

    public static Retrofit retrofit;
    public static ProductService productService;
    public static TumblerService tumblerService;
    public static BuyService buyService;

    public static void initialize(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://shop.tumblerism.co.id/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        productService = retrofit.create(ProductService.class);
        tumblerService = retrofit.create(TumblerService.class);
        buyService = retrofit.create(BuyService.class);
    }
}
