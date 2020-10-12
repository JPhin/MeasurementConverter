package com.example.measurementconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Sets up the objects needed for the elements in the UI
    TextView txtUnit_1;
    TextView txtUnit_2;
    TextView txtConvertedValue;
    EditText etxtEnteredValue;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connects each UI object to the actual UI element
        txtUnit_1 = findViewById(R.id.txtUnit_1);
        txtUnit_2 = findViewById(R.id.txtUnit_2);
        txtConvertedValue = findViewById(R.id.txtConvertedValue);
        etxtEnteredValue = findViewById(R.id.etxtEnteredValue);

        //Sets up the spinner to connect to an array adapter.
        //This lets the spinner have different options
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conversionTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //These three methods allow for the txtConvertedValue to auto update
        //whenever new numbers are put into etxtEnteredValue.
        //Only the last method is needed for needs to happen.
        etxtEnteredValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String textSpin = spinner.getSelectedItem().toString();
                String textEdit = etxtEnteredValue.getText().toString();

                //The first if statement attaches an error condition to the etxtEnteredValue UI object
                if (TextUtils.isEmpty(textEdit))
                {
                    etxtEnteredValue.setError("You must enter a number.");
                }
                else {
                    if (textSpin.equals("Miles to Kilometers")) {
                        milesToKilometers();
                    } else if (textSpin.equals("Kilometers to Miles")) {
                        kilometersToMiles();
                    } else if (textSpin.equals("Inches to Centimeters")) {
                        inchesToCentimeters();
                    } else if (textSpin.equals("Centimeters to Inches")) {
                        centimetersToInches();
                    }
                }


            }
        });
    }

    //This method has txtConvertedValue, txtUnit_1, and txtUnit_2 update whenever
    //the spinner is changed to a new option.
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String textSpin = parent.getItemAtPosition(position).toString();
        String textEdit = etxtEnteredValue.getText().toString();

        //The first if statement attaches an error condition to the etxtEnteredValue UI object
        if (TextUtils.isEmpty(textEdit))
        {
            etxtEnteredValue.setError("You must enter a number.");
        }
        else {
            if (textSpin.equals("Miles to Kilometers")) {
                txtUnit_1.setText("Miles");
                txtUnit_2.setText("Kilometers");
                milesToKilometers();
            } else if (textSpin.equals("Kilometers to Miles")) {
                txtUnit_1.setText("Kilometers");
                txtUnit_2.setText("Miles");
                kilometersToMiles();
            } else if (textSpin.equals("Inches to Centimeters")) {
                txtUnit_1.setText("Inches");
                txtUnit_2.setText("Centimeters");
                inchesToCentimeters();
            } else if (textSpin.equals("Centimeters to Inches")) {
                txtUnit_1.setText("Centimeters");
                txtUnit_2.setText("Inches");
                centimetersToInches();
            }
        }

    }

    //this method is must be implemented but I don't need to do anything with it.
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //These last four methods all contain the calculations needed for
    //the unit conversions.
    //Each method sets up variables, extracts data from etxtEnteredValue,
    //and calculates the converted value which is then displayed.
    public void milesToKilometers()
    {

        String result = "";
        double MTK = 1.6093;
        double miles = Double.parseDouble(etxtEnteredValue.getText().toString());

        result = String.valueOf(miles * MTK);

        txtConvertedValue.setText(result);

    }

    public void kilometersToMiles()
    {

        String result = "";
        double KTM = 0.6214;
        double kilometers = Double.parseDouble(etxtEnteredValue.getText().toString());

        result = String.valueOf(kilometers * KTM);

        txtConvertedValue.setText(result);

    }

    public void inchesToCentimeters()
    {

        String result = "";
        double ITC = 2.54;
        double inches = Double.parseDouble(etxtEnteredValue.getText().toString());

        result = String.valueOf(inches * ITC);

        txtConvertedValue.setText(result);
    }

    public void centimetersToInches()
    {

        String result = "";
        double CTI = 0.3937;
        double centimeters = Double.parseDouble(etxtEnteredValue.getText().toString());

        result = String.valueOf(centimeters * CTI);

        txtConvertedValue.setText(result);

    }
}