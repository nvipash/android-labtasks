package iot.nvipash_harvardapi.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nvipash_harvardapi.R;
import iot.nvipash_harvardapi.http_client.GetRecordsData;
import iot.nvipash_harvardapi.entities.Records;
import iot.nvipash_harvardapi.adapters.RecordsAdapter;
import iot.nvipash_harvardapi.entities.RecordsList;
import iot.nvipash_harvardapi.http_client.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    protected @BindView(R.id.toolbar)
    Toolbar toolbar;
    protected @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    protected @BindView(android.R.id.content)
    View currentAppView;
    protected @BindView(R.id.no_data_image)
    ImageView noDataImage;
    protected @BindView(R.id.no_data_text_info)
    TextView noDataTextInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        noDataImage.setVisibility(View.INVISIBLE);
        noDataTextInfo.setVisibility(View.INVISIBLE);

        swipeToRefreshContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeApiCall();
    }

    private void swipeToRefreshContent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeApiCall();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void makeApiCall() {
        GetRecordsData data = RetrofitInstance
                .getRetrofitInstance().create(GetRecordsData.class);

        Call<RecordsList> call = data.getRecordsData();

        call.enqueue(new Callback<RecordsList>() {
            @Override
            public void onResponse(Call<RecordsList> call,
                                   Response<RecordsList> response) {
                if (response.body() != null) {
                    generateRecordsList(response.body().getRecordsArrayList());
                    noDataTextInfo.setVisibility(View.INVISIBLE);
                    noDataImage.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<RecordsList> call, Throwable throwable) {
                noDataImage.setVisibility(View.VISIBLE);
                noDataTextInfo.setVisibility(View.VISIBLE);
                Snackbar.make(currentAppView, R.string.on_failure_error,
                        Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void generateRecordsList(ArrayList<Records> recordsArrayList) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_records_list);
        RecordsAdapter adapter = new RecordsAdapter(recordsArrayList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}