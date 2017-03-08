package cleverti.com.countriesinfo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uiltonsantos on 08/03/2017.
 */

public class Country implements Serializable {

    @SerializedName("name")
    public String name;

    @SerializedName("alpha2Code")
    public String alpha2Code;

    @SerializedName("capital")
    public String capital;

    @SerializedName("region")
    public String region;

    @SerializedName("subregion")
    public String subregion;

    @SerializedName("translations")
    public Translation translations;

    @SerializedName("population")
    public long population;

    @SerializedName("latlng")
    public double[] latlng = new double[2];

    @SerializedName("demonym")
    public String demonym;

    @SerializedName("area")
    public double area;

    @SerializedName("timezones")
    public List<String> timezones;

    @SerializedName("borders")
    public List<String> borders;

    @SerializedName("nativeName")
    public String nativeName;

    @SerializedName("currencies")
    public List<Currency> currencies;

    @SerializedName("languages")
    public List<Language> languages;

    public Country() {
        timezones = new ArrayList<>();
        borders = new ArrayList<>();
        currencies = new ArrayList<>();
        languages = new ArrayList<>();
    }
}
