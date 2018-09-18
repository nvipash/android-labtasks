package iot.nvipash_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private ListView usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        usersList = findViewById(R.id.users_list);
        setUserData();
    }

    private void setUserData() {
        SharedPreferences sharedPref = getSharedPreferences(Constants.USER_DATA_KEY,
                Context.MODE_PRIVATE);
        String savedFirstName = sharedPref.getString(Constants.FIRST_NAME_KEY, "");
        String savedLastName = sharedPref.getString(Constants.LAST_NAME_KEY, "");
        String savedEmail = sharedPref.getString(Constants.EMAIL_KEY, "");
        String savedPhone = sharedPref.getString(Constants.PHONE_KEY, "");
        String[] savedUserData = new String[]{savedFirstName, savedLastName,
                savedEmail, savedPhone};
        List<String> listSavedUsers = new ArrayList<>(Arrays.asList(savedUserData));
        ArrayAdapter<String> adapterList = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listSavedUsers);
        usersList.setAdapter(adapterList);
    }
}