package cleverti.com.countriesinfo.comm;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RestController {

    private static final String BASE_WS_URL = "https://restcountries.eu/";

    protected static EndpointInterface getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_WS_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndpointInterface.class);
    }

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .readTimeout(45, TimeUnit.SECONDS)
                .connectTimeout(45, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
    }

}
