package com.example.rutgerscafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
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
                    switch (String.valueOf(type.getText())) {
                        case "Yeast Donut":
                            subtotal.setText("$" + df.format(1.59 * Integer.parseInt(String.valueOf(quantity.getText()))));
                            break;
                        case "Cake Donut":
                            subtotal.setText("$" + df.format(1.79 * Integer.parseInt(String.valueOf(quantity.getText()))));
                            break;
                        case "Donut Hole":
                            subtotal.setText("$" + df.format(0.39 * Integer.parseInt(String.valueOf(quantity.getText()))));
                            break;
                    }
                } catch (NumberFormatException e) {
                    subtotal.setText("$0.00");
                }
            }
        });

        addToOrder = findViewById(R.id.btn_addDonutOrder);
        addToOrder.setOnClickListener(view -> {
            try {
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
            } catch (NumberFormatException e) {
                Toast toast = Toast.makeText(this, "Please specify the number of donuts!", Toast.LENGTH_LONG);
                toast.show();
            }


        });

    }
}