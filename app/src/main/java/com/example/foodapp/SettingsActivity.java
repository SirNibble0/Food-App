package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsActivity extends AppCompatActivity {

    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        backBtn = findViewById(R.id.backBtnSettings);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        bottomNav();
    }

    private void bottomNav() {

        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        LinearLayout supportBtn = findViewById(R.id.supportBtn);
        LinearLayout settingsBtn = findViewById(R.id.settingsBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SettingsActivity.this, CartListActivity.class));

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SettingsActivity.this, MainActivity.class));

            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));

            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, ProfileActivity.class));
            }
        });

    }
}