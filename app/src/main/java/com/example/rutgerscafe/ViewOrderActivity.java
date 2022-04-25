package com.example.rutgerscafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

/**
 * This class controls the functionality of the view order activity
 * @author Rory Xu, Hassan Alfareed
 */
public class ViewOrderActivity extends AppCompatActivity {

    ListView itemList;
    TextView orderNumber;
    TextView subtotal;
    TextView tax;
    TextView grandTotal;
    Order order;
    private final DecimalFormat df = new DecimalFormat("#0.00");

    /**
     * Initializes elements of the view order activity and defines their functionalities
     * @param savedInstanceState Not used
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        Intent intent = getIntent();
        order = MainActivity.storeOrders.getOrders().get((int) intent.getExtras().get("Selected Order"));
        orderNumber.findViewById(R.id.tv_orderNumber);
        orderNumber.setText(order.getOrderNumber());
        itemList = findViewById(R.id.lv_itemList);
        ArrayAdapter<MenuItem> itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, order.getItems());
        itemList.setAdapter(itemsAdapter);
        subtotal = findViewById(R.id.tv_orderSubtotal);
        tax = findViewById(R.id.tv_orderTax);
        grandTotal = findViewById(R.id.tv_orderGrandTotal);
        updateTotals();
    }

    /**
     * Updates the monetary values on the view order activity
     */
    private void updateTotals() {
        double st = order.getSubtotal();
        subtotal.setText("$" + df.format(st));
        double tx = order.getSubtotal() * 0.0625;
        tax.setText("$" + df.format(tx));
        double gt = st + tx;
        grandTotal.setText("$" + df.format(gt));
    }
}