package iot.nvipash_harvardapi.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nvipash_harvardapi.R;
import iot.nvipash_harvardapi.activities.MainActivity;
import iot.nvipash_harvardapi.adapters.RecordsAdapter;
import iot.nvipash_harvardapi.entities.Record;
import iot.nvipash_harvardapi.entities.RecordsList;
import iot.nvipash_harvardapi.http_client.GetRecordsData;
import iot.nvipash_harvardapi.http_client.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordsListFragment extends Fragment {
    @BindView(R.id.recycler_view_records_list)
    protected RecyclerView recordsListView;

    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.no_data_image)
    protected ImageView noDataImage;

    @BindView(R.id.no_data_text_info)
    protected TextView noDataTextInfo;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_records_list, container, false);
        ButterKnife.bind(this, view);

        ((MainActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        swipeToRefreshContent();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        makeApiCall();
    }

    private void swipeToRefreshContent() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
                    makeApiCall();
                    swipeRefreshLayout.setRefreshing(false);
                }
        );
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
                Snackbar.make(recordsListView, R.string.on_failure_error,
                        Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void generateRecordsList(ArrayList<Record> recordList) {
        RecordsAdapter adapter = new RecordsAdapter(recordList, item -> { });
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        recordsListView.setLayoutManager(layoutManager);
        recordsListView.setAdapter(adapter);
    }
}