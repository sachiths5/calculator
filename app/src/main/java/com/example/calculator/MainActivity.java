package com.example.calculator;

import static android.text.method.TextKeyListener.clear;

import static javax.crypto.Cipher.SECRET_KEY;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    private int equalPressCount = 0;
    private SharedPreferences prefs;
    private static final String PREF_NAME = "SecretPrefs";
    private static final String SECRET_KEY = "secret_pwd";
    protected String current = "";
    private String operator = "";
    private double firstNumber = 0;
    public TextView display;
    Button b1, b2, b3, equal, bmt;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        bmt = findViewById(R.id.bmt);
        equal = findViewById(R.id.equal);


        b1.setOnClickListener(v -> {
            display.setText(display.getText().toString() + b1.getText().toString());
        });


        b2.setOnClickListener(v -> {
            display.setText(display.getText().toString() + b2.getText().toString());
        });


        b3.setOnClickListener(v -> {
            display.setText(display.getText().toString() + b3.getText().toString());
        });

        bmt.setOnClickListener(v -> {
            display.setText(display.getText().toString() + bmt.getText().toString());
        });
        equal.setOnClickListener(v -> {
            if (display.getText().toString().length() == 0) {
                Toast.makeText(getApplicationContext(), "please enter value", Toast.LENGTH_SHORT).show();
            } else{
                float value=Float.parseFloat(b2.getText().toString()) *Float.parseFloat(b3.getText().toString());
                display.setText(value+"");
            }

        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button b0 = findViewById(R.id.b0);
        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);
        Button b3 = findViewById(R.id.b3);
        Button b4 = findViewById(R.id.b4);
        Button b5 = findViewById(R.id.b5);
        Button b6 = findViewById(R.id.b6);
        Button b7 = findViewById(R.id.b7);
        Button b8 = findViewById(R.id.b8);
        Button b9 = findViewById(R.id.b9);

        b0.setOnClickListener(v -> {
            current += "0";
            display.setText(current);
        });
        b1.setOnClickListener(v -> {
            current += "1";
            display.setText(current);
        });
        b2.setOnClickListener(v -> {
            current += "2";
            display.setText(current);
        });
        b3.setOnClickListener(v -> {
            current += "3";
            display.setText(current);
        });
        b4.setOnClickListener(v -> {
            current += "4";
            display.setText(current);
        });
        b5.setOnClickListener(v -> {
            current += "5";
            display.setText(current);
        });
        b6.setOnClickListener(v -> {
            current += "6";
            display.setText(current);
        });
        b7.setOnClickListener(v -> {
            current += "7";
            display.setText(current);
        });
        b8.setOnClickListener(v -> {
            current += "8";
            display.setText(current);
        });
        b9.setOnClickListener(v -> {
            current += "9";
            display.setText(current);
        });
        findViewById(R.id.equal).setOnClickListener(v -> {
            calculate();
        });
        findViewById(R.id.bp).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.bm).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.bmt).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.bd).setOnClickListener(v -> setOperator("/"));
        findViewById(R.id.bc).setOnClickListener(v -> clear());
        findViewById(R.id.bs).setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_contaienr, new BlankFragment())
                .commit());
        Button by = findViewById(R.id.by);
        by.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });
    }

    private void clear() {
        current = "";
        display.setText(current);

    }

    private void setOperator(String op) {
        if (!current.isEmpty()) {
            firstNumber = Double.parseDouble(current);
            current = "";
            operator = op;
            display.setText(op);
        }
    }

    private void calculate() {
        double secondNumber = Double.parseDouble(current);
        double result = 0;
        try {

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = secondNumber != 0 ? firstNumber / secondNumber : 0;
                break;}
        }
        catch (ArithmeticException e){
            Context Tag = null;
            Toast.makeText(Tag,"Invalid Syntax",Toast.LENGTH_LONG).show();
        }
        display.setText(String.valueOf(result));
        current = "";
        operator = "";
        firstNumber = result;
    }

    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

    }
    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    public  void onStart(){
        super.onStart();
    }

}




