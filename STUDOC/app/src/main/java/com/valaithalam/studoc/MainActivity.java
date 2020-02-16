package com.valaithalam.studoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {



    ViewPager viewPager;


    private TextInputLayout username_input;
    private TextInputLayout password_input;
    CheckBox remember;


    DatabaseHelper db;
    EditText signin_email,signin_password;
    Button bn_login,bn_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);





        username_input = findViewById(R.id.username_input);
        password_input = findViewById(R.id.password_input);
        remember = findViewById(R.id.remember);


        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");

        if (checkbox.equals("true")){
            startActivity(new Intent(MainActivity.this,profile.class));


        }


        signin_email = findViewById(R.id.signin_username);
        signin_password = findViewById(R.id.signin_password);


        bn_signup = findViewById(R.id.bn_signupinlogin);
        bn_login = findViewById(R.id.bn_login);

        bn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = signin_email.getText().toString();
                String password = signin_password.getText().toString();
                Boolean res = db.checkuser(username,password);

                if (password.isEmpty()||username.isEmpty()){
                    username_input.setError("Field can't be empty");
                    password_input.setError("Field can't be empty");

                }
                else if(username.isEmpty())
                {
                    username_input.setError("Field can't be empty");

                }

                else if(password.isEmpty()){

                    password_input.setError("Field can't be empty");
                }
                else if (res == true)
                {
                    startActivity(new Intent(MainActivity.this,profile.class));
                    signin_email.setText("");
                    signin_password.setText("");

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }


            }
        });
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();

                }else if(!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();

                }

            }
        });




        bn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, sign_up.class));
            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
