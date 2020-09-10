package itp341.lee.uilistenerpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;

    private CheckBox checkBoxPepperoni;

    private CheckBox checkBoxPineapple;

    private CheckBox checkBoxTofu;

    private RadioGroup radioGroupSize;

    private Spinner spinnerSpecials;

    private SeekBar seekBarNumberOfPizzas;

    private TextView textViewOrderSummary;

    private TextView textViewNumberOfPizzas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        checkBoxPepperoni = findViewById(R.id.checkboxPepperoni);
        checkBoxPineapple = findViewById(R.id.checkboxPineapple);
        checkBoxTofu = findViewById(R.id.checkboxTofu);
        radioGroupSize = findViewById(R.id.radioGroupSize);
        spinnerSpecials = findViewById(R.id.spinnerSpecials);
        seekBarNumberOfPizzas = findViewById(R.id.seekBarNumberOfPizzas);
        textViewOrderSummary = findViewById(R.id.textViewOrderSummary);
        textViewNumberOfPizzas = findViewById(R.id.textViewNumberOfPizzas);

        editTextName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                // Call helper function that takes all the input from the UI, and generates order summary
                updateOrderSummary();
                return true;
            }
        });

        checkBoxPepperoni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateOrderSummary();
            }
        });

        checkBoxTofu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateOrderSummary();
            }
        });

        checkBoxPineapple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateOrderSummary();
            }
        });

        radioGroupSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                updateOrderSummary();
            }
        });

        seekBarNumberOfPizzas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateNumberOfPizzasLabel(i);
                updateOrderSummary();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    // Takes all the input from the UI, and generates order summary
    private void updateOrderSummary(){
        // Order for vikram, you ordered 2 medium pizzas with the following toppings: cheese, pepperoni, pineapple. You wil get the following specials: Chicken Wings.
        final String name = editTextName.getText().toString();
        final int numberOfPizzas = seekBarNumberOfPizzas.getProgress();
        final int radioButtonResourceId = radioGroupSize.getCheckedRadioButtonId();
        final RadioButton radioButton = radioGroupSize.findViewById(radioButtonResourceId);

        if (radioButton == null){
            textViewOrderSummary.setText("");
            return;
        }

        final String size = radioButton.getText().toString();

        List<String> toppings = new ArrayList<>();

        if (checkBoxPepperoni.isChecked()){
            toppings.add("pepperoni");
        }

        if (checkBoxPineapple.isChecked()){
            toppings.add("pineapple");
        }

        if (checkBoxTofu.isChecked()){
            toppings.add("tofu");
        }

        final String orderSummary = String.format("Order for %s, you ordered %d %s pizzas with the following toppings: %s.", name, numberOfPizzas, size, TextUtils.join(", ", toppings));

        textViewOrderSummary.setText(orderSummary);

    }

    // Given a number, update the number of pizzas label
    private void updateNumberOfPizzasLabel(int number){
        textViewNumberOfPizzas.setText(String.valueOf(number));
    }
}