package com.example.exapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentLogin extends AppCompatActivity {
    EditText new_sl_username, new_sl_password;
    Button sl_signIn_button, sl_signup_button;
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        new_sl_username = findViewById(R.id.new_sl_username);
        new_sl_password = findViewById(R.id.new_sl_password);
        sl_signIn_button = findViewById(R.id.sl_signIn_button);
        sl_signup_button = findViewById(R.id.sl_signup_button);
        myDB = new MyDatabaseHelper(this);

        sl_signIn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = new_sl_username.getText().toString();
                String pass = new_sl_password.getText().toString();
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(StudentLogin.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean result = myDB.checkStudentUsernamePassword(user, pass);

                    if (result == true) {
                        Intent intent = new Intent(getApplicationContext(), StudentLanding.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(StudentLogin.this, "OOPS!! Entered password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        sl_signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentRegistration.class);
                startActivity(intent);
            }
        });

    }
}