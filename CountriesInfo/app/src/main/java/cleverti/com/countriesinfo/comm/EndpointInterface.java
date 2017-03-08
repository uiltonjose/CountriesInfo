package cleverti.com.countriesinfo.comm;

import java.util.List;

import cleverti.com.countriesinfo.model.Country;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndpointInterface {

    @GET("/rest/v2/all")
    Call<List<Country>> getAllCountries();

    @GET("/name/")
    Call<Country> getCountryByName(@Query("countryName") String countryName);
}
