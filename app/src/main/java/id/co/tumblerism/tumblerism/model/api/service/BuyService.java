package id.co.tumblerism.tumblerism.model.api.service;

import id.co.tumblerism.tumblerism.model.json.BuyResponse;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Ranu on 03/11/2016.
 */

public interface BuyService {

    @POST("buy/{id_product}/{id_tumbler}/{password}")
    Call<BuyResponse> buyProduct(@Path("id_product") String id_product
            , @Path("id_tumbler") String id_tumbler
            , @Path("password") String password);
}
