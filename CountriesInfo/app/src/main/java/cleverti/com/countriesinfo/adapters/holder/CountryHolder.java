package cleverti.com.countriesinfo.adapters.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import cleverti.com.countriesinfo.R;
import cleverti.com.countriesinfo.activities.CountryDetail;
import cleverti.com.countriesinfo.adapters.CountryListAdapter;
import cleverti.com.countriesinfo.model.Country;

public class CountryHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle;
    public ImageView ivFlag;

    private Activity activity;

    public CountryHolder(Activity context, final CountryListAdapter adapter, View itemView) {
        super(itemView);

        this.activity = context;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Country item = adapter.getCountries().get(getAdapterPosition());

                Gson gson = new Gson();
                Intent intent = new Intent(activity, CountryDetail.class);
                intent.putExtra("country", gson.toJson(item));
                activity.startActivity(intent);
            }
        });

        tvTitle = (TextView) itemView.findViewById(R.id.country_name);
        ivFlag = (ImageView) itemView.findViewById(R.id.iv_flag);
    }
}
