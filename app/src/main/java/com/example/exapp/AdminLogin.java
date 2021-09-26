package com.example.exapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminLogin extends AppCompatActivity {
    Button A_signIn_button,A_signup_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        A_signIn_button=(Button)findViewById(R.id.A_signIn_button);
        A_signup_button=(Button) findViewById(R.id.A_signup_button);


        A_signIn_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),viewAdmin.class);
                startActivity(intent);
            }
        });

        A_signup_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),AdminRegistaration.class);
                startActivity(intent);
            }
        });



    }
}