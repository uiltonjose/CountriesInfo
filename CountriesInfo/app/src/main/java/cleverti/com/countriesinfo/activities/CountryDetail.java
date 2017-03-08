package cleverti.com.countriesinfo.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import cleverti.com.countriesinfo.BuildConfig;
import cleverti.com.countriesinfo.R;
import cleverti.com.countriesinfo.model.Country;
import cleverti.com.countriesinfo.model.Currency;
import cleverti.com.countriesinfo.model.Language;
import cleverti.com.countriesinfo.util.AndroidUtil;

/**
 * Created by uiltonsantos on 08/03/2017.
 */

public class CountryDetail extends BaseActivity {

    private Country selectedCountry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.country_detail_activity);

        Bundle bundle = getIntent().getExtras();
        String countryJson = bundle.getString("country");
        Gson gson = new Gson();
        selectedCountry = gson.fromJson(countryJson, Country.class);
        if (selectedCountry == null) {
            AndroidUtil.showMessageOK(this, "Error", " Can not show country detail.", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            return;
        }

        initViews();
    }

    private void initViews() {
        ImageView ivFlag = (ImageView) findViewById(R.id.iv_flag);
        String flagUrl = BuildConfig.BASE_FLAG_URL + selectedCountry.alpha2Code + ".png";
        downloadResourceWithGlide(flagUrl, ivFlag);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_info);
        layout.addView(createTextView("Country: " + selectedCountry.name));
        layout.addView(createTextView("Capital: " + selectedCountry.capital));
        layout.addView(createTextView("Region: " + selectedCountry.region));
        layout.addView(createTextView("Population: " + selectedCountry.population));

        Currency currency = selectedCountry.currencies.get(0);
        layout.addView(createTextView("Currency: " + currency.name));
        layout.addView(createTextView("Currency symbol: " + currency.symbol));

        List<Language> languages = selectedCountry.languages;
        int count = 1;
        for (Language lng: languages) {
            layout.addView(createTextView("Language " + count + ": " + lng.name));
            count++;
        }

        /*
            Missing the rest of information...
         */
    }

    private TextView createTextView(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(ContextCompat.getColor(this, R.color.text_333333));
        return tv;
    }
}
