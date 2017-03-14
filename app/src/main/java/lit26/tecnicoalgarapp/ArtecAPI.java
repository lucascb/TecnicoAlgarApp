package lit26.tecnicoalgarapp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lucas on 05/03/17.
 */

public interface ArtecAPI {
    @GET("demandas")
    Call<List<Demand>> getDemands();

    @POST("criaDemanda")
    Call<JsonObject> postDemand(@Body Demand demand);

    @GET("notificaCliente")
    Call<JsonObject> notifyNext(@Query("id") int userId);
}
