package iot.nvipash_harvardapi.fragments;

import android.support.v4.app.Fragment;

import iot.nvipash_harvardapi.FragmentNavigation;

public abstract class BaseFragment extends Fragment implements FragmentNavigation.View {

    protected FragmentNavigation.Presenter navigationPresenter;

    @Override
    public void attachPresenter(FragmentNavigation.Presenter presenter) {
        navigationPresenter = presenter;
    }
}