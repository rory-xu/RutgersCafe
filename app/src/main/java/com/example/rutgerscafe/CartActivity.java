package com.example.rutgerscafe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {

    ListView itemList;
    TextView subtotal;
    TextView tax;
    TextView grandTotal;
    Button deleteItem;
    Button submitOrder;
    MenuItem selected;
    private final DecimalFormat df = new DecimalFormat("#0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        itemList = findViewById(R.id.lv_itemList);
        ArrayAdapter<MenuItem> itemsAdapter = new ArrayAdapter<MenuItem>(this,
                android.R.layout.simple_list_item_1, MainActivity.currOrder.getItems());
        itemList.setAdapter(itemsAdapter);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected = (MenuItem) itemList.getItemAtPosition(i);
            }
        });
        deleteItem = findViewById(R.id.btn_deleteItem);
        deleteItem.setOnClickListener(view -> {
            try {
                itemsAdapter.remove(selected);
                itemsAdapter.notifyDataSetChanged();
                updateTotals();
            } catch (NullPointerException e) {
                Toast toast = Toast.makeText(this, "No Item Selected to Delete", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        subtotal = findViewById(R.id.tv_orderSubtotal);
        tax = findViewById(R.id.tv_orderTax);
        grandTotal = findViewById(R.id.tv_orderGrandTotal);
        updateTotals();
    }

    private void updateTotals() {
        double st = MainActivity.currOrder.getSubtotal();
        subtotal.setText("$" + df.format(st));
        double tx = MainActivity.currOrder.getSubtotal() * 0.0625;
        tax.setText("$" + df.format(tx));
        double gt = st + tx;
        grandTotal.setText("$" + df.format(gt));
    }
}