package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodapp.Helper.ManagementCart;
import com.example.foodapp.Interface.ChangeNumberItemsListener;
import com.example.foodapp.adapter.CartListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        calculateCart();
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

                startActivity(new Intent(CartListActivity.this, CartListActivity.class));

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CartListActivity.this, MainActivity.class));

            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CartListActivity.this, SettingsActivity.class));

            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, ProfileActivity.class));
            }
        });

    }

    private void initView() {

        totalFeeTxt = findViewById(R.id.priceItemTotal);
        taxTxt = findViewById(R.id.priceTax);
        deliveryTxt = findViewById(R.id.priceDelivery);
        totalTxt = findViewById(R.id.priceTotal);
        scrollView = findViewById(R.id.scrollViewCart);
        emptyTxt = findViewById(R.id.emptyTxt);


    }

    private void initList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        recyclerViewList = findViewById(R.id.recyclerViewCart);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {

                calculateCart();

            }
        });

        recyclerViewList.setAdapter(adapter);

        if (managementCart.getListCart().isEmpty()){

            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);

        }else {

            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

        }

    }

    private void calculateCart(){

        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee() + percentTax) * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery)*100)/100;
        double itemTotal = Math.round(managementCart.getTotalFee()*100)/100;

        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);

    }
}