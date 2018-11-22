package iot.nvipash_harvardapi;

import iot.nvipash_harvardapi.fragments.BaseFragment;

public interface FragmentNavigation {

    interface View {
        void attachPresenter(Presenter presenter);
    }
    interface Presenter {
        void addFragment(BaseFragment fragment);
    }
}
