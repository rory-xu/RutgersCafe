package com.example.rutgerscafe;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class CartActivity extends AppCompatActivity {

    public static List<MenuItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }
}