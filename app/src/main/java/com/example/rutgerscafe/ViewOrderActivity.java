package com.example.rutgerscafe;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

public class ViewOrderActivity extends AppCompatActivity {

    ListView itemList;
    TextView subtotal;
    TextView tax;
    TextView grandTotal;
    Order order;
    private final DecimalFormat df = new DecimalFormat("#0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        Intent intent = getIntent();
        order = MainActivity.storeOrders.getOrders().get((int) intent.getExtras().get("Selected Order"));
        itemList = findViewById(R.id.lv_itemList);
        ArrayAdapter<MenuItem> itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, order.getItems());
        itemList.setAdapter(itemsAdapter);
        subtotal = findViewById(R.id.tv_orderSubtotal);
        tax = findViewById(R.id.tv_orderTax);
        grandTotal = findViewById(R.id.tv_orderGrandTotal);
        updateTotals();
    }

    private void updateTotals() {
        double st = order.getSubtotal();
        subtotal.setText("$" + df.format(st));
        double tx = order.getSubtotal() * 0.0625;
        tax.setText("$" + df.format(tx));
        double gt = st + tx;
        grandTotal.setText("$" + df.format(gt));
    }
}