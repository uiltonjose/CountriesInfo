package cleverti.com.countriesinfo.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import cleverti.com.countriesinfo.R;
import cleverti.com.countriesinfo.adapters.CountryListAdapter;
import cleverti.com.countriesinfo.comm.CountryRestController;
import cleverti.com.countriesinfo.model.Country;
import cleverti.com.countriesinfo.util.AndroidUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private CountryListAdapter countryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_countries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent), ContextCompat.getColor(this, R.color.colorPrimaryDark));

        countryListAdapter = new CountryListAdapter(this);
        recyclerView.setAdapter(countryListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchAllCountries();
    }

    @Override
    public void onRefresh() {
        if (swipeRefreshLayout != null) {
            fetchAllCountries();
        }
    }

    private void fetchAllCountries() {

        showProgressDialog();

        if (!AndroidUtil.hasConnectivity(this)) {
            AndroidUtil.showMessageOK(this, getString(R.string.no_connectivity_title), getString(R.string.no_connectivity), null);
            dismissProgressDialog();
            swipeRefreshLayout.setRefreshing(false);
            return;
        }

        CountryRestController.getAllCountries(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                dismissProgressDialog();
                swipeRefreshLayout.setRefreshing(false);

                if (response.code() == 200 || response.code() == 201) {
                    if (response.body() != null) {
                        List<Country> body = response.body();
                        countryListAdapter.setCountries(body);
                        countryListAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "Fail to get country list.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Fail to get country list.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                dismissProgressDialog();
                swipeRefreshLayout.setRefreshing(false);
                System.out.println(t.getMessage());
            }
        });
    }
}
