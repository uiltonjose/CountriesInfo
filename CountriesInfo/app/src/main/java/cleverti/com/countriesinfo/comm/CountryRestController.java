package cleverti.com.countriesinfo.comm;

import java.util.List;

import cleverti.com.countriesinfo.model.Country;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by uiltonsantos on 08/03/2017.
 */

public class CountryRestController extends RestController {

    public static void getAllCountries(Callback<List<Country>> callbackResponse) {

        Call<List<Country>> call = getRetrofit().getAllCountries();
        call.enqueue(callbackResponse);
    }

    public static void getCountryByName(String name, Callback<Country> callbackResponse) {

        Call<Country> call = getRetrofit().getCountryByName(name);
        call.enqueue(callbackResponse);
    }
}
