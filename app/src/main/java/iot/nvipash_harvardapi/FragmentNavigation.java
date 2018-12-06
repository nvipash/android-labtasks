package iot.nvipash_harvardapi;

import android.support.v4.app.Fragment;

public class FragmentNavigation {

    private final MainActivity activity;

    private Fragment currentFragment;

    public FragmentNavigation(final MainActivity activity) {
        this.activity = activity;
    }

    public void setFragment(final Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null).commit();
    }

}