package id.co.tumblerism.tumblerism.model.api.service;

/**
 * Created by Ranu on 02/11/2016.
 */

import id.co.tumblerism.tumblerism.model.json.Product;
import id.co.tumblerism.tumblerism.model.json.ProductList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ProductService {

    @GET("get_product")
    Call<ProductList> getProduct();
}
