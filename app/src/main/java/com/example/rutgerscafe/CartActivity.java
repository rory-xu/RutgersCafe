package com.example.rutgerscafe;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    ListView itemList;
    TextView subtotal;
    TextView tax;
    TextView grandTotal;
    Button deleteItem;
    Button submitOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        itemList = findViewById(R.id.lv_itemList);
    }
}