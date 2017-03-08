package cleverti.com.countriesinfo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by uiltonsantos on 08/03/2017.
 */

public class Currency implements Serializable {

    @SerializedName("code")
    public String code;

    @SerializedName("name")
    public String name;

    @SerializedName("symbol")
    public String symbol;
}
