package com.valaithalam.studoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sign_up extends AppCompatActivity {

    DatabaseHelper db;
    EditText et_name, et_email, et_pass,et_conpassword;
    Button b1;
    String name, email, password,conpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this);

        setContentView(R.layout.activity_sign_up);
        et_name = findViewById(R.id.signup_name);
        et_email = findViewById(R.id.signup_email);
        et_pass = findViewById(R.id.signup_password);
        b1 = findViewById(R.id.bn_submit);
        et_conpassword = findViewById(R.id.signup_conpassword);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et_name.getText().toString().trim();
                email = et_email.getText().toString().trim();
                password = et_pass.getText().toString().trim();
                conpass = et_conpassword.getText().toString().trim();

                if (name.equals("")|email.equals("")|password.equals("")|conpass.equals(""))
                {
                    Toast.makeText(sign_up.this,"Enter all the data",Toast.LENGTH_SHORT).show();
                }
                else if (password.equals(conpass)){
                    long val = db.adduser(name,email,password);
                    if (val > 0) {
                        Toast.makeText(sign_up.this," You Have registered",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(sign_up.this,MainActivity.class));

                    }
                    else {
                        Toast.makeText(sign_up.this,"Registration Error" ,Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(sign_up.this,"Password is not matching",Toast.LENGTH_SHORT).show();


                }



            }

        });
    }
}