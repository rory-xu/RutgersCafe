package com.example.rutgerscafe;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

/**
 * This class controls the functionality of the coffee activity
 * @author Rory Xu, Hassan Alfareed
 */
public class CoffeeActivity extends AppCompatActivity {

    CheckBox caramel;
    CheckBox cream;
    CheckBox milk;
    CheckBox syrup;
    CheckBox whippedCream;
    Spinner sizes;
    EditText quantity;
    TextView subtotal;
    Button addToOrder;
    ArrayAdapter<CharSequence> coffeeSizeAdapter;
    Coffee currCoffee = new Coffee(1.69);
    private final DecimalFormat df = new DecimalFormat("#0.00");


    /**
     * Initializes elements of the coffee activity and defines their functionalities
     * @param savedInstanceState Not used
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        subtotal = findViewById(R.id.tv_coffeeSubtotal);
        sizes = findViewById(R.id.sp_coffeeSize);
        coffeeSizeAdapter = ArrayAdapter.createFromResource(this, R.array.coffee_sizes,
                android.R.layout.simple_spinner_dropdown_item);
        sizes.setAdapter(coffeeSizeAdapter);
        sizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Changes the price of the coffee when the size is changed
             * @param adapterView Not used
             * @param view Not used
             * @param i Not used
             * @param l Not used
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (String.valueOf(sizes.getSelectedItem())) {
                    case "Short":
                        currCoffee.changeBasePrice(1.69);
                        updateSubtotal();
                        break;
                    case "Tall":
                        currCoffee.changeBasePrice(2.09);
                        updateSubtotal();
                        break;
                    case "Grande":
                        currCoffee.changeBasePrice(2.49);
                        updateSubtotal();
                        break;
                    case "Venti":
                        currCoffee.changeBasePrice(2.89);
                        updateSubtotal();
                        break;
                }
            }

            /**
             * Not used
             * @param adapterView Not used
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        quantity = findViewById(R.id.etn_coffeeQuantity);
        quantity.addTextChangedListener(new TextWatcher() {
            /**
             * Not used
             * @param charSequence Not used
             * @param i Not used
             * @param i1 Not used
             * @param i2 Not used
             */
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            /**
             * Not used
             * @param charSequence Not used
             * @param i Not used
             * @param i1 Not used
             * @param i2 Not used
             */
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            /**
             * Detects when the quantity is changed and updates subtotals to reflect the change
             * @param editable Not used
             */
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    currCoffee.changeQuantity(Integer.parseInt(String.valueOf(quantity.getText())));
                    updateSubtotal();
                }
                catch (NumberFormatException e) {
                    subtotal.setText("$0.00");
                }
            }
        });
        caramel = findViewById(R.id.cb_caramel);
        caramel.setOnClickListener(view -> {
            if (caramel.isChecked()) {
                currCoffee.add("Caramel");
            }
            else {
                currCoffee.remove("Caramel");
            }
            updateSubtotal();
        });
        cream = findViewById(R.id.cb_cream);
        cream.setOnClickListener(view -> {
            if (!cream.isChecked()) {
                currCoffee.remove("Cream");
            } else {
                currCoffee.add("Cream");
            }
            updateSubtotal();
        });
        milk = findViewById(R.id.cb_milk);
        milk.setOnClickListener(view -> {
            if (milk.isChecked()) {
                currCoffee.add("Milk");
            } else {
                currCoffee.remove("Milk");
            }
            updateSubtotal();
        });
        syrup = findViewById(R.id.cb_syrup);
        syrup.setOnClickListener(view -> {
            if (syrup.isChecked()) {
                currCoffee.add("Syrup");
            }
            else {
                currCoffee.remove("Syrup");
            }
            updateSubtotal();
        });
        whippedCream = findViewById(R.id.cb_whippedcream);
        whippedCream.setOnClickListener(view -> {
            if (whippedCream.isChecked()) {
                currCoffee.add("Whipped Cream");
            }
            else {
                currCoffee.remove("Whipped Cream");
            }
            updateSubtotal();
        });
        addToOrder = findViewById(R.id.btn_addCoffeeOrder);
        addToOrder.setOnClickListener(view -> {
            try {
                Integer.parseInt(String.valueOf(quantity.getText()));
                MainActivity.currOrder.add(currCoffee);
                currCoffee = new Coffee(1.69);
                resetScreen();
                Toast toast = Toast.makeText(this, "Coffee Successfully Added!", Toast.LENGTH_LONG);
                toast.show();
            }
            catch (Exception e) {
                Toast toast = Toast.makeText(this, "Please specify the number of coffees!", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    /**
     * Updates the monetary values on the coffee activity
     */
    private void updateSubtotal() {
        try {
            Integer.parseInt(String.valueOf(quantity.getText()));
            double st = currCoffee.itemPrice();
            subtotal.setText("$" + df.format(st));
        } catch (NumberFormatException e) {
            subtotal.setText("$0.00");
        }

    }

    /**
     * Resets the screen upon submitting a coffee item
     */
    private void resetScreen() {
        caramel.setChecked(false);
        cream.setChecked(false);
        milk.setChecked(false);
        syrup.setChecked(false);
        whippedCream.setChecked(false);
        sizes.setSelection(0);
        quantity.setText("");
        currCoffee.changeQuantity(0);
        subtotal.setText("$0.00");
    }
}