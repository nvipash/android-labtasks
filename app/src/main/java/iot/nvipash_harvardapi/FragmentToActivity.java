package iot.nvipash_harvardapi;

import iot.nvipash_harvardapi.fragments.BaseFragment;

public interface FragmentToActivity {
    interface View{
        void setFragment(BaseFragment fragment);
    }
    interface Presenter{
        void getRandomFragment();
    }
}
