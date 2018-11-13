package iot.nvipash_harvardapi.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nvipash_harvardapi.R;

public class RecordsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.api_records_title)
    public TextView recordsTitle;

    @BindView(R.id.api_records_credit_line)
    public TextView recordsCreditLine;

    @BindView(R.id.api_records_dimensions)
    public TextView recordsDimensions;

    @BindView(R.id.api_records_primary_image)
    public ImageView recordsPrimaryImage;

    @BindView(R.id.api_records_details_button)
    public Button detailsButton;

    public RecordsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}