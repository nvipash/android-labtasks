package iot.nvipash_harvardapi.presenters;

import android.app.Activity;

public interface MainPresenter {
    void requestDataFromServer(Activity activity);
    void updateDataFromServer(Activity activity);
    void openFavourites(Activity activity);
}