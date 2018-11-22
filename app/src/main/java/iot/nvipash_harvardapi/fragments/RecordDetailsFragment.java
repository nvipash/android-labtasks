package iot.nvipash_harvardapi.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import iot.nvipash_harvardapi.MainActivity;
import iot.nvipash_harvardapi.R;
import iot.nvipash_harvardapi.adapters.RecordsAdapter;
import iot.nvipash_harvardapi.entities.Record;
import iot.nvipash_harvardapi.http_client.GetRecordsData;
import iot.nvipash_harvardapi.http_client.RetrofitInstance;
import iot.nvipash_harvardapi.views.RecordDetailsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordDetailsFragment extends Fragment implements RecordDetailsView {
    public static final String IMAGE_URL = "PRIMARY_IMAGE_URL";
    public static final String FAVOURITE_RECORD = "FAVOURITE_RECORD";
    private SharedPreferences favouritesPreferences;
    private RecordImageFragment imageFragment;
    private Record recordResponse;

    @BindView(R.id.collapsing_toolbar)
    protected CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.image)
    protected ImageButton primaryImage;

    @BindView(R.id.api_records_provenance)
    protected TextView recordsProvenance;

    @BindView(R.id.api_records_credits)
    protected TextView recordsCredits;

    @BindView(R.id.api_records_technique)
    protected TextView recordsTechnique;

    @BindView(R.id.api_records_dimensions)
    protected TextView recordsDimensions;

    @BindView(R.id.api_records_department)
    protected TextView recordsDepartment;

    @BindView(R.id.fab_add_favourites)
    protected FloatingActionButton addToFavouritesButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        makeApiCall();

        favouritesPreferences = Objects.requireNonNull(getActivity())
                .getSharedPreferences(FAVOURITE_RECORD, Context.MODE_PRIVATE);
    }

    @OnClick(R.id.image)
    public void onImageClickListener(View view) {
        ((MainActivity) Objects.requireNonNull(getActivity()))
                .setFragment(imageFragment);
    }

    @OnClick(R.id.fab_add_favourites)
    public void onAddToFavouritesButtonListener(View view) {
        setFavouritesPreferences();
    }

    @Override
    public void setTextToTextViews(Record recordResponse) {
        collapsingToolbarLayout.setTitle(recordResponse.getTitle());
        recordsProvenance.setText(recordResponse.getProvenance());
        recordsCredits.setText(recordResponse.getCreditLine());
        recordsTechnique.setText(recordResponse.getTechnique());
        recordsDimensions.setText(recordResponse.getDimensions());
        recordsDepartment.setText(recordResponse.getDepartment());
    }

    @Override
    public void setImageAndSetUrlToFragment(Record recordResponse) {
        String primaryImageUrl = recordResponse.getPrimaryImageUrl();
        Bundle bundleImageUrl = new Bundle();
        imageFragment = new RecordImageFragment();

        Picasso.with(getContext()).load(primaryImageUrl)
                .centerCrop().fit().into(primaryImage);

        bundleImageUrl.putString(IMAGE_URL, primaryImageUrl);
        imageFragment.setArguments(bundleImageUrl);
    }

    @Override
    public void setFavouritesPreferences() {
        if (favouritesPreferences.contains(recordResponse.getTitle())) {
            favouritesPreferences.edit().remove(recordResponse.getTitle()).apply();
            ((MainActivity) Objects.requireNonNull(getActivity()))
                    .showSnackBar(R.string.record_deleted_info);
        } else {
            favouritesPreferences.edit().putString(recordResponse.getTitle(),
                    new Gson().toJson(recordResponse)).apply();
            ((MainActivity) Objects.requireNonNull(getActivity()))
                    .showSnackBar(R.string.record_added_info);
        }
    }

    @Override
    public void makeApiCall() {
        GetRecordsData data = RetrofitInstance
                .getRetrofitInstance().create(GetRecordsData.class);
        int recordId = Objects.requireNonNull(getArguments()).getInt(RecordsAdapter.RECORD_ID);
        Call<Record> call = data.getRecordWithId(recordId);

        call.enqueue(new Callback<Record>() {
            @Override
            public void onResponse(Call<Record> call,
                                   Response<Record> response) {
                recordResponse = response.body();

                if (recordResponse != null) {
                    setTextToTextViews(recordResponse);
                    setImageAndSetUrlToFragment(recordResponse);
                }
            }

            @Override
            public void onFailure(Call<Record> call, Throwable throwable) {
                ((MainActivity) Objects.requireNonNull(getActivity()))
                        .showSnackBar(R.string.on_failure_error);
            }
        });
    }
}