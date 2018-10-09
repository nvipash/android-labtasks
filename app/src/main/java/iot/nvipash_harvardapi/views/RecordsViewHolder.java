package iot.nvipash_harvardapi.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nvipash_harvardapi.R;

public class RecordsViewHolder extends RecyclerView.ViewHolder {
    public @BindView(R.id.api_records_title)
    TextView recordsTitle;
    public @BindView(R.id.api_records_credit_line)
    TextView recordsCreditLine;
    public @BindView(R.id.api_records_dimensions)
    TextView recordsDimensions;
    public @BindView(R.id.api_records_primary_image)
    ImageView recordsPrimaryImage;

    public RecordsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}