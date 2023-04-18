package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodapp.adapter.CategoryAdapter;
import com.example.foodapp.adapter.PopularAdapter;
import com.example.foodapp.model.CategoryModel;
import com.example.foodapp.model.PopularModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        recyclerViewCategory();
        recyclerViewPopular();
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

                startActivity(new Intent(MainActivity.this, CartListActivity.class));

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, MainActivity.class));

            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, SettingsActivity.class));

            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<PopularModel> popular = new ArrayList<>();
        popular.add(new PopularModel("Pepperoni pizza", "pizza_1", "slices pepperoni, mozzerella cheese, fresh oregano, ground black pepper, pizza sauce ", 8.14, 0));
        popular.add(new PopularModel("Cheese Burger", "burger", "beef, Gouda Cheese, Special Sauce, Lettuce, tomatoes", 7.80, 0));
        popular.add(new PopularModel("Vegetable pizza", "pizza_2", "olive oil, Vegetable oil, pitted kalamata, cherry tomatoes, fresh oregano, basil", 8.05, 0));
        popular.add(new PopularModel("Drink", "drink", "This is a widely enjoyed delicacy, it can be cooked and eaten differntly according to the taste of the individual", 2.10, 0));
        popular.add(new PopularModel("Hotdog", "hotdog", "This is a widely enjoyed delicacy, it can be cooked and eaten differntly according to the taste of the individual", 13.80, 0));

        adapter2 = new PopularAdapter(popular);
        recyclerViewPopularList.setAdapter(adapter2);

    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryModel> category = new ArrayList<>();
        category.add(new CategoryModel("Pizza", "cat_1"));
        category.add(new CategoryModel("Burger", "cat_2"));
        category.add(new CategoryModel("Hotdog", "cat_3"));
        category.add(new CategoryModel("Drink", "cat_4"));
        category.add(new CategoryModel("Others", "cat_5"));

        adapter = new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}