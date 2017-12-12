package com.example.michael.musicapp.AccountActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.michael.musicapp.MainActivity;
import com.example.michael.musicapp.R;
import com.example.michael.musicapp.SettingsActivity;

public class StartUpActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        btnLogin = (Button) findViewById(R.id.login_button);
        //If this button is clicked then it activates the sign up class
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartUpActivity.this, LoginActivity.class));
            }
        });

        btnRegister = (Button) findViewById(R.id.register_button);
        //If this button is clicked then it activates the sign up class
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartUpActivity.this, SignupActivity.class));
            }
        });
    }
}
