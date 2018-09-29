package iot.nvipash_harvardapi.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GetRecordsData data = RetrofitInstance
                .getRetrofitInstance().create(GetRecordsData.class);

        Call<RecordsList> call = data.getRecordsData();

        call.enqueue(new Callback<RecordsList>() {
            @Override
            public void onResponse(Call<RecordsList> call,
                                   Response<RecordsList> response) {
                assert response.body() != null;
                generateRecordsList(response.body().getRecordsArrayList());
            }

            @Override
            public void onFailure(Call<RecordsList> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, R.string.on_failure_error,
                        Toast.LENGTH_LONG).show();
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