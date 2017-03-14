package lit26.tecnicoalgarapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lucas on 05/03/17.
 */

public class ArtecApplication {
    private static Retrofit retrofit = null;
    private static ArtecAPI api = null;
    private static final String serverUrl = "http://10.13.32.214:8080/artec/";

    public static ArtecAPI getApi() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                                    .baseUrl(serverUrl)
                                    .addConverterFactory(GsonConverterFactory.create(gson))
                                    .build();
            api = retrofit.create(ArtecAPI.class);
        }
        return api;
    }

}
