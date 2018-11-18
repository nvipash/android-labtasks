package iot.nvipash_harvardapi.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import iot.nvipash_harvardapi.R;
import iot.nvipash_harvardapi.activities.MainActivity;
import iot.nvipash_harvardapi.adapters.RecordsAdapter;
import iot.nvipash_harvardapi.entities.Record;
import iot.nvipash_harvardapi.http_client.GetRecordsData;
import iot.nvipash_harvardapi.http_client.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordDetailsFragment extends Fragment {
    public static final String IMAGE_URL = "PRIMARY_IMAGE_URL";
    private RecordImageFragment imageFragment;

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        makeApiCall();
    }

    @OnClick(R.id.image)
    public void onImageClickListener(View view) {
        ((MainActivity) Objects.requireNonNull(view.getContext()))
                .setFragment(imageFragment);
    }

    private void makeApiCall() {
        GetRecordsData data = RetrofitInstance
                .getRetrofitInstance().create(GetRecordsData.class);
        int recordId = Objects.requireNonNull(getArguments()).getInt(RecordsAdapter.RECORD_ID);
        Call<Record> call = data.getRecordWithId(recordId);

        call.enqueue(new Callback<Record>() {
            @Override
            public void onResponse(Call<Record> call,
                                   Response<Record> response) {
                Record recordResponse = response.body();

                if (recordResponse != null) {
                    String primaryImageUrl = recordResponse.getPrimaryImageUrl();
                    Bundle bundleImageUrl = new Bundle();

                    collapsingToolbarLayout.setTitle(recordResponse.getTitle());
                    recordsProvenance.setText(recordResponse.getProvenance());
                    recordsCredits.setText(recordResponse.getCreditLine());
                    recordsTechnique.setText(recordResponse.getTechnique());
                    recordsDimensions.setText(recordResponse.getDimensions());
                    recordsDepartment.setText(recordResponse.getDepartment());
                    Picasso.with(getContext()).load(primaryImageUrl)
                            .centerCrop().fit().into(primaryImage);

                    bundleImageUrl.putString(IMAGE_URL, primaryImageUrl);
                    imageFragment = new RecordImageFragment();
                    imageFragment.setArguments(bundleImageUrl);

                }
            }

            @Override
            public void onFailure(Call<Record> call, Throwable throwable) {
                Snackbar.make(Objects.requireNonNull(getActivity())
                                .findViewById(android.R.id.content), R.string.on_failure_error,
                        Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
