package iot.nvipash_harvardapi.presenters;

import android.app.Activity;

import java.util.List;

import iot.nvipash_harvardapi.entities.Record;
import iot.nvipash_harvardapi.models.MainInteractor;
import iot.nvipash_harvardapi.views.MainView;

public class MainPresenterImpl extends BasePresenter implements MainPresenter {

    private MainView view;

    public MainPresenterImpl(MainView mainView) {
        this.view = mainView;
    }

    @Override
    public void requestDataFromServer(Activity activity) {
    }

    @Override
    public void updateDataFromServer(Activity activity) {
        getApplicationInstance(activity).getMainInteractor();
    }

    @Override
    public void openFavourites(Activity activity) {

    }
}
