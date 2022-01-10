package network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class JPmerketClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jpmarket.herokuapp.com/";

    public static Retrofit getJPmarketInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}