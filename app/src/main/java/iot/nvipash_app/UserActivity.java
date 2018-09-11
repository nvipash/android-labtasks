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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ListView usersList = findViewById(R.id.users_list);
        SharedPreferences sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String savedFirstName = sharedPref.getString("first_name", "");
        String savedLastName = sharedPref.getString("last_name", "");
        String savedEmail = sharedPref.getString("email", "");
        String savedPhone = sharedPref.getString("phone", "");
        String[] savedUserData = new String[]{savedFirstName, savedLastName, savedEmail, savedPhone};
        List<String> listSavedUsers = new ArrayList<>(Arrays.asList(savedUserData));
        ArrayAdapter<String> adapterList = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listSavedUsers);
        usersList.setAdapter(adapterList);
    }
}