package iot.nvipash_harvardapi.views;

import iot.nvipash_harvardapi.entities.Record;

public interface RecordDetailsView {

    void setTextToTextViews(Record recordResponse);

    void setImageAndSetUrlToFragment(Record recordResponse);

    void setFavouritesPreferences();

    void makeApiCall();
}
