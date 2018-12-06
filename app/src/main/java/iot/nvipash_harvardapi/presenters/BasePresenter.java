package iot.nvipash_harvardapi.presenters;

import android.app.Activity;

import iot.nvipash_harvardapi.ApplicationEx;

public class BasePresenter {
    protected ApplicationEx getApplicationInstance(Activity activity){
        return (ApplicationEx) activity.getApplication();
    }
}
