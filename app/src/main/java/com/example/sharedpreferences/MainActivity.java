package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edt_city, edt_no2, edt_no, edt_name, edt_email;
    private Button btn_save, btn_kill;
    private SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();


        edt_city = (EditText) findViewById(R.id.edt_city);
        edt_no2 = (EditText) findViewById(R.id.edt_no2);
        edt_no = (EditText) findViewById(R.id.edt_no);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_email = (EditText) findViewById(R.id.edt_email);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_kill = (Button) findViewById(R.id.btn_kill);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmailValid(edt_email.getText().toString())
                        && isNameValid(edt_name.getText().toString() ) && isValidMobile(edt_no.getText().toString() )
                        && isValidMobile(edt_no2.getText().toString() ) && isNameValid(edt_city.getText().toString() )) {
                    SaveDate();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter valid Name/Email/number/city", Toast.LENGTH_SHORT).show();

                }
            }
        });


        btn_kill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.super.onDestroy();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        RestoreData();
    }


    private void RestoreData() {
        if (sharedpreferences.contains("name")) {
            edt_name.setText(sharedpreferences.getString("name", ""));
        }
        if (sharedpreferences.contains("mobile_no")) {
            edt_no.setText(sharedpreferences.getString("mobile_no", ""));
        }

        if (sharedpreferences.contains("mobile_no2")) {
            edt_no2.setText(sharedpreferences.getString("mobile_no2", ""));
        }

        if (sharedpreferences.contains("email")) {
            edt_email.setText(sharedpreferences.getString("email", ""));
        }

        if (sharedpreferences.contains("city")) {
            edt_city.setText(sharedpreferences.getString("city", ""));
        }

    }

    private void SaveDate() {
        editor.putString("name", edt_name.getText().toString());
        editor.putString("mobile_no", edt_no.getText().toString());
        editor.putString("mobile_no2", edt_no2.getText().toString());
        editor.putString("email", edt_email.getText().toString());
        editor.putString("city", edt_city.getText().toString());
        editor.commit();

        Toast.makeText(MainActivity.this, "Data Save successfully", Toast.LENGTH_SHORT).show();
    }


    private boolean isValidMobile(String phone) {
        Boolean valid = android.util.Patterns.PHONE.matcher(phone).matches();

        return valid;
    }

    boolean isEmailValid(String email) {

        Boolean valid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

        return valid;

    }

    boolean isNameValid(String name) {
        if (name.length() == 0) {
            return false;
        } else if (!name.matches("[a-zA-Z ]+")) {
            return false;
        } else {
          return true;
        }

    }
}