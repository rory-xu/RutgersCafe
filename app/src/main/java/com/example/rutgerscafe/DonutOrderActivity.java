package com.example.rutgerscafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

/**
 * This class controls the functionality of the donut order activity
 * @author Rory Xu, Hassan Alfareed
 */
public class DonutOrderActivity extends AppCompatActivity{

    ListView donutOrder;
    TextView flavor;
    TextView type;
    EditText quantity;
    TextView subtotal;
    Button addToOrder;
    Button deleteDonut;
    Button submitOrder;
    Donut selected;
    Intent intent;
    private final DecimalFormat df = new DecimalFormat("#0.00");

    /**
    * Initializes elements of the coffee activity and defines their functionalities
    * @param savedInstanceState Not used
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);
        intent = getIntent();
        donutOrder = findViewById(R.id.lv_currDonutOrder);
        ArrayAdapter<Donut> donutsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, MainActivity.donutOrder);
        donutOrder.setAdapter(donutsAdapter);
        donutOrder.setOnItemClickListener((adapterView, view, i, l) -> selected = (Donut) donutOrder.getItemAtPosition(i));
        flavor = findViewById(R.id.tv_selectedFlavor);
        type = findViewById(R.id.tv_selectedType);
        quantity = findViewById(R.id.etn_donutQuantity);
        subtotal = findViewById(R.id.tv_donutSubtotal);

        if (intent != null) {
            flavor.setText(intent.getExtras().getString("Flavor"));
            type.setText(intent.getExtras().getString("Type"));
        }
        addToOrder = findViewById(R.id.btn_addDonutOrder);
        addToOrder.setOnClickListener(view -> {
            try {
                switch (String.valueOf(type.getText())) {
                    case "Yeast Donut":
                        donutsAdapter.add(new YeastDonut(flavor.getText().toString(), Integer.parseInt(quantity.getText().toString())));
                        donutsAdapter.notifyDataSetChanged();
                        updateSubtotal();
                        break;
                    case "Cake Donut":
                        donutsAdapter.add(new CakeDonut(flavor.getText().toString(), Integer.parseInt(quantity.getText().toString())));
                        donutsAdapter.notifyDataSetChanged();
                        updateSubtotal();
                        break;
                    case "Donut Hole":
                        donutsAdapter.add(new DonutHole(flavor.getText().toString(), Integer.parseInt(quantity.getText().toString())));
                        donutsAdapter.notifyDataSetChanged();
                        updateSubtotal();
                        break;
                }
                Toast toast = Toast.makeText(this, "Donut Successfully Added!", Toast.LENGTH_LONG);
                toast.show();
            } catch (NumberFormatException e) {
                Toast toast = Toast.makeText(this, "Please specify the number of donuts!", Toast.LENGTH_LONG);
                toast.show();
            }


        });

        deleteDonut = findViewById(R.id.btn_deleteDonut);
        deleteDonut.setOnClickListener(view -> {
            try {
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Are you sure you want to delete this Donut?")
                        .setTitle("Delete Donut")
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            donutsAdapter.remove(selected);
                            donutsAdapter.notifyDataSetChanged();
                            updateSubtotal();
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> {
                        })
                        .show();
            }
            catch (NullPointerException e) {
                Toast toast = Toast.makeText(this, "No Donut Selected to Delete", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        submitOrder = findViewById(R.id.btn_submitDonutOrder);
        submitOrder.setOnClickListener(view -> {
            if (!MainActivity.donutOrder.isEmpty()) {
                for (Donut donut : MainActivity.donutOrder) {
                    MainActivity.currOrder.add(donut);
                }
                donutsAdapter.clear();
                donutsAdapter.notifyDataSetChanged();
                updateSubtotal();
                Toast toast = Toast.makeText(this, "Donut Order Successfully Placed!", Toast.LENGTH_LONG);
                toast.show();
                super.onBackPressed();
            }
            else {
                Toast toast = Toast.makeText(this, "No Donuts in Cart to Order", Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

    /**
     * Updates the monetary values on the donut order activity
     */
    private void updateSubtotal() {
        double st = 0.00;
        for (Donut donut : MainActivity.donutOrder) {
            st += donut.itemPrice();
        }
        subtotal.setText("$" + df.format(st));
    }
}