package iot.nvipash_harvardapi.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import iot.nvipash_harvardapi.R;
import iot.nvipash_harvardapi.fragments.FavouriteRecordsFragment;
import iot.nvipash_harvardapi.fragments.RecordsListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentOnStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favourites_toolbar_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favourite:
                FavouriteRecordsFragment favouriteRecordsFragment
                        = new FavouriteRecordsFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, favouriteRecordsFragment)
                        .addToBackStack(null).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fragmentOnStart() {
        RecordsListFragment recordsListFragment = new RecordsListFragment();
        getSupportActionBar();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, recordsListFragment)
                .addToBackStack(null).commit();
    }
}