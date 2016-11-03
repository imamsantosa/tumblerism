package id.co.tumblerism.tumblerism.model.api.service;

/**
 * Created by Ranu on 02/11/2016.
 */

import id.co.tumblerism.tumblerism.model.json.Tumbler;
import id.co.tumblerism.tumblerism.model.json.TumblerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TumblerService {

    @POST("check/{id_tumbler}")
    Call<TumblerResponse> getTumbler(@Path("id_tumbler") String id_tumbler);
}
