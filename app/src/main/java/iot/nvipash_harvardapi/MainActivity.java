package iot.nvipash_harvardapi;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nvipash_harvardapi.adapters.RecordsAdapter;
import iot.nvipash_harvardapi.entities.Record;
import iot.nvipash_harvardapi.fragments.RecordsFavouritesFragment;
import iot.nvipash_harvardapi.fragments.RecordsListFragment;

public class MainActivity extends AppCompatActivity {
    private ApplicationEx applicationEx;

    @BindView(android.R.id.content)
    protected View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        applicationEx = (ApplicationEx) getApplicationContext();
        FragmentNavigation fragmentNavigation = new FragmentNavigation(this);
        applicationEx.setFragmentNavigation(fragmentNavigation);
        applicationEx.getFragmentNavigation().setFragment(new RecordsListFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Fragment currentFragment = getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        if (currentFragment instanceof RecordsFavouritesFragment) {
            return false;
        } else {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.favourites_toolbar_button, menu);
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favourite:
                applicationEx.getFragmentNavigation().setFragment((new RecordsFavouritesFragment()));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public void showSnackBar(int textFromResources) {
//        Snackbar.make(parentView, getString(textFromResources),
//                Snackbar.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void generateRecordsList(ArrayList<Record> recordList,
//                                    RecyclerView recordsListView) {
//        RecordsAdapter adapter = new RecordsAdapter(recordList);
//        RecyclerView.LayoutManager layoutManager =
//                new LinearLayoutManager(getBaseContext());
//        recordsListView.setLayoutManager(layoutManager);
//        recordsListView.setAdapter(adapter);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }
    }
}