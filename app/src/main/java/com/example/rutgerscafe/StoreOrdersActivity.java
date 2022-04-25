package com.example.rutgerscafe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class StoreOrdersActivity extends AppCompatActivity {

    ListView storeOrdersList;
    Button viewOrder;
    Button deleteOrder;
    int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        storeOrdersList = findViewById(R.id.lv_storeOrders);
        ArrayAdapter<Order> ordersAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, MainActivity.storeOrders.getOrders());
        storeOrdersList.setAdapter(ordersAdapter);
        storeOrdersList.setOnItemClickListener((adapterView, view, i, l) -> selected = i);
        viewOrder = findViewById(R.id.btn_viewOrder);
        viewOrder.setOnClickListener(view -> {
            if (!ordersAdapter.isEmpty()) {
                try {
                    Intent intent = new Intent(this, ViewOrderActivity.class);
                    intent.putExtra("Selected Order", selected);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast toast = Toast.makeText(this, "No Order Selected to View", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            else {
                Toast toast = Toast.makeText(this, "No Order Selected to View", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        deleteOrder = findViewById(R.id.btn_deleteOrder);
        deleteOrder.setOnClickListener(view -> {
            if (!ordersAdapter.isEmpty()) {
                try {
                    new AlertDialog.Builder(this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setMessage("Are you sure you want to delete this order?")
                            .setTitle("Delete Order")
                            .setPositiveButton("Yes", (dialogInterface, i) -> {
                                ordersAdapter.remove(MainActivity.storeOrders.getOrders().get(selected));
                                ordersAdapter.notifyDataSetChanged();
                            })
                            .setNegativeButton("No", (dialogInterface, i) -> {
                                return;
                            })
                            .show();
                }
                catch (NullPointerException e) {
                    Toast toast = Toast.makeText(this, "No Order Selected to Delete", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            else {
                Toast toast = Toast.makeText(this, "No Order Selected to Delete", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}