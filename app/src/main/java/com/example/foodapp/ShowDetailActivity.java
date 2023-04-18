package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Helper.ManagementCart;
import com.example.foodapp.model.PopularModel;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, priceTxt, descriptionTxt, numberOrderTxt;
    private ImageView addBox, minusBox, picFood, backBtn;
    private PopularModel object;
    private ManagementCart managementCart;

    int numberOrder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        backBtn = findViewById(R.id.backBtnDetails);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {

        object = (PopularModel) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        priceTxt.setText("$" + object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        addBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
            }
        });

    }

    private void initView() {

        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        priceTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        addBox = findViewById(R.id.addBox);
        minusBox = findViewById(R.id.minusBox);
        picFood = findViewById(R.id.picFood);

    }
}