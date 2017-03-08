package cleverti.com.countriesinfo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by uiltonsantos on 08/03/2017.
 */

public class Language implements Serializable {

    @SerializedName("iso639_1")
    public String iso639_1;

    @SerializedName("iso639_2")
    public String iso639_2;

    @SerializedName("name")
    public String name;

    @SerializedName("nativeName")
    public String nativeName;
}
