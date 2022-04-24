package com.example.rutgerscafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class DonutActivity extends AppCompatActivity implements OnRVListener{
    Spinner donutTypes;
    ArrayAdapter<CharSequence> donutTypeAdapter;
    private RecyclerView flavorRecyclerView;
    private RecyclerView.Adapter flavorRVAdapter;
    private RecyclerView.LayoutManager flavorRVLayoutManager;
    private final List<String> flavors = new ArrayList<>(Arrays.asList("Strawberry",
            "Chocolate", "Glazed", "Jelly", "Vanilla", "Oreo", "Matcha", "Creme-Filled", "Sugar",
            "Plain", "Ube", "Swirl"));



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        donutTypes = (Spinner) findViewById(R.id.donutType);
        donutTypeAdapter = ArrayAdapter.createFromResource(this, R.array.donut_types,
                android.R.layout.simple_spinner_dropdown_item);
        donutTypes.setAdapter(donutTypeAdapter);
        flavorRecyclerView = (RecyclerView) findViewById(R.id.donut_flavor_list);
        flavorRecyclerView.setHasFixedSize(true);
        flavorRVLayoutManager = new LinearLayoutManager(this);
        flavorRecyclerView.setLayoutManager((flavorRVLayoutManager));
        flavorRVAdapter = new MyAdapter(flavors, this, this);
        flavorRecyclerView.setAdapter(flavorRVAdapter);
    }

    @Override
    public void onRVClick(int position) {
        Intent intent = new Intent(this, DonutOrderActivity.class);
        intent.putExtra("Flavor", flavors.get(position));
        intent.putExtra("Type", String.valueOf(donutTypes.getSelectedItem()));
        startActivity(intent);
    }
}