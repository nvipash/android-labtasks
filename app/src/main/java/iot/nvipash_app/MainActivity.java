package iot.nvipash_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText inputFirstName;
    private EditText inputLastName;
    private EditText inputEmail;
    private EditText inputPhone;
    private EditText inputPassword;
    private EditText inputPasswordReEnter;
    private ArrayList<String> errorsList;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        inputFirstName = findViewById(R.id.input_first_name);
        inputLastName = findViewById(R.id.input_last_name);
        inputEmail = findViewById(R.id.input_email);
        inputPhone = findViewById(R.id.input_mobile);
        inputPassword = findViewById(R.id.input_password);
        inputPasswordReEnter = findViewById(R.id.input_password_conf);
        errorText = findViewById(R.id.error_text);
        FloatingActionButton submitFab = findViewById(R.id.fab);
        setSupportActionBar(toolbar);

        submitFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitValidation();
                if (errorsList.isEmpty()) {
                    inputFirstName.setText("");
                    inputLastName.setText("");
                    inputPhone.setText("");
                    inputEmail.setText("");
                    inputPassword.setText("");
                    inputPasswordReEnter.setText("");
                    Snackbar.make(view, "Validation was successfully completed", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    public void submitValidation() {
        userValidation();
    }

    private boolean userValidation() {
        boolean validation = true;
        errorsList = new ArrayList<>();
        String firstName = inputFirstName.getText().toString();
        String lastName = inputLastName.getText().toString();
        String email = inputEmail.getText().toString();
        String phone = inputPhone.getText().toString();
        String password = inputPassword.getText().toString();
        String passwordReEnter = inputPasswordReEnter.getText().toString();

        if (firstName.isEmpty() || firstName.length() < 3) {
            errorsList.add("- First name must have at least 3 characters \n");
            validation = false;
        } else if (Character.isLowerCase(firstName.charAt(0))) {
            errorsList.add("- First name must be started with upper case \n");
            validation = false;
        } else {
            inputFirstName.setError(null);
        }

        if (lastName.isEmpty() || lastName.length() < 4) {
            errorsList.add("- Last name must have at least 4 characters \n");
            validation = false;
        } else if (Character.isLowerCase(lastName.charAt(0))) {
            errorsList.add("- Last name must be started with upper case \n");
            validation = false;
        } else {
            inputLastName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorsList.add("- Email address is invalid \n");
            validation = false;
        } else {
            inputEmail.setError(null);
        }

        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            errorsList.add("- Phone number is invalid \n");
            validation = false;
        } else {
            inputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            errorsList.add("- Password must be between 4 and 10 alphanumeric characters \n");
            validation = false;
        } else {
            inputPassword.setError(null);
        }

        if (passwordReEnter.isEmpty() || !passwordReEnter.equals(password)) {
            errorsList.add("- Re-entered password not equals password \n");
            validation = false;
        } else {
            inputPasswordReEnter.setError(null);
        }

        errorText.setText(errorsList.toString());
        return validation;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}