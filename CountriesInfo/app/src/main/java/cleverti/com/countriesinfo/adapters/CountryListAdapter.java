package cleverti.com.countriesinfo.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cleverti.com.countriesinfo.BuildConfig;
import cleverti.com.countriesinfo.R;
import cleverti.com.countriesinfo.activities.BaseActivity;
import cleverti.com.countriesinfo.adapters.holder.CountryHolder;
import cleverti.com.countriesinfo.model.Country;

public class CountryListAdapter extends RecyclerView.Adapter<CountryHolder> {

    private List<Country> countries;
    private Activity context;

    public CountryListAdapter(Activity context) {
        this.context = context;
        countries = new ArrayList<>();
    }

    @Override
    public CountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country_list, null);
        return new CountryHolder(context, this, itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final CountryHolder holder, int position) {

        Country item = countries.get(position);

        holder.tvTitle.setText(item.name);

        String flagUrl = BuildConfig.BASE_FLAG_URL + item.alpha2Code + ".png";
        ((BaseActivity) context).downloadResourceWithGlide(flagUrl, holder.ivFlag);
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
