package id.co.tumblerism.tumblerism.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ranu on 03/11/2016.
 */

public class TumblerResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private Tumbler data;

    public TumblerResponse(String status, Tumbler data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tumbler getData() {
        return data;
    }

    public void setData(Tumbler data) {
        this.data = data;
    }
}
