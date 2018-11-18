package iot.nvipash_harvardapi.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.github.piasy.biv.view.BigImageView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nvipash_harvardapi.R;

public class RecordImageFragment extends Fragment {
    private String imageUrl;

    @BindView(R.id.api_record_image_fullscreen)
    protected BigImageView recordImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
        BigImageViewer.initialize(GlideImageLoader.with(context));
        ViewGroup view = (ViewGroup) inflater.inflate
                (R.layout.fragment_record_image, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showBigImageView();
    }

    private void showBigImageView() {
        if (getArguments() != null) {
            imageUrl = getArguments().getString(RecordDetailsFragment.IMAGE_URL);
        }

        if (imageUrl != null) {
            recordImage.showImage(Uri.parse(imageUrl));
        } else {
            Snackbar.make(Objects.requireNonNull(getActivity()).findViewById(android.R.id.content),
                    R.string.image_error, Snackbar.LENGTH_SHORT).show();
        }
    }
}