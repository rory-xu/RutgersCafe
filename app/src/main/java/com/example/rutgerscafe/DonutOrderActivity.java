package com.example.rutgerscafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class DonutOrderActivity extends AppCompatActivity{

    TextView flavor;
    TextView type;
    EditText quantity;
    TextView subtotal;
    Button addToOrder;
    Intent intent;
    private final DecimalFormat df = new DecimalFormat("#0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);

        intent = getIntent();

        flavor = findViewById(R.id.tv_selectedFlavor);
        type = findViewById(R.id.tv_selectedType);
        quantity = findViewById(R.id.etn_donutQuantity);

        if (intent != null) {
            flavor.setText(intent.getExtras().getString("Flavor"));
            type.setText(intent.getExtras().getString("Type"));
        }
        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                subtotal = findViewById(R.id.tv_subtotal);
                try {
                    subtotal.setText("$" + df.format(1.69 * Integer.parseInt(String.valueOf(quantity.getText()))));
                } catch (NumberFormatException e) {
                    subtotal.setText("$0.00");
                }
            }
        });

        addToOrder = findViewById(R.id.btn_addDonutOrder);
        addToOrder.setOnClickListener(view -> {
            MainActivity.currOrder = new Order(MainActivity.orderNumber);
            switch (String.valueOf(type.getText())) {
                case "Yeast Donut":
                    MainActivity.currOrder.add(new YeastDonut(flavor.getText().toString(), Integer.parseInt(quantity.getText().toString())));
                    break;
                case "Cake Donut":
                    MainActivity.currOrder.add(new CakeDonut(flavor.getText().toString(), Integer.parseInt(quantity.getText().toString())));
                    break;
                case "Donut Hole":
                    MainActivity.currOrder.add(new DonutHole(flavor.getText().toString(), Integer.parseInt(quantity.getText().toString())));
                    break;
            }
            Toast toast = Toast.makeText(this, "Donut Successfully Added!", Toast.LENGTH_LONG);
            toast.show();

        });

    }
}