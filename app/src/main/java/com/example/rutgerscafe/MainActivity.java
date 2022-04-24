package com.example.rutgerscafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity{

    private ImageButton donutButton;
    private ImageButton coffeeButton;
    private ImageButton cartButton;
    private ImageButton storeButton;
    public static List<MenuItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donutButton = findViewById(R.id.donut_button);
        donutButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, DonutActivity.class);
            startActivity(intent);
        });
        coffeeButton = findViewById(R.id.coffee_button);
        coffeeButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, CoffeeActivity.class);
            startActivity(intent);
        });
        cartButton = findViewById(R.id.cart_button);
        cartButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });
        storeButton = findViewById(R.id.store_button);
        storeButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, StoreOrdersActivity.class);
            startActivity(intent);
        });
    }
}