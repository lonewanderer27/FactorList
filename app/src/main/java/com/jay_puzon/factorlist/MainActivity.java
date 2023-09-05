package com.jay_puzon.factorlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // UI variables
    EditText NumInput, FactorList;
    TextView FactorN;
    Button EraseBtn, ComputeBtn;
    Button ClearExitBtn;

    // state variables
    ArrayList<Integer> Factors = new ArrayList<>();
    int number;

    void findFactors() {
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                Factors.add(i);
            }
        }
    }

    void clear() {
        if (number == 0) {
            finish();
        }

        // reset all variables
        number = 0;
        Factors.clear();

        // reset ui
        NumInput.setText(null);
        FactorList.setText(null);
        FactorN.setText(null);
    }

    void insertNumber(int num) {
        // set state
        String numberString = String.valueOf(number);
        numberString += String.valueOf(num);
        number = Integer.parseInt(numberString);

        // set ui
        NumInput.setText(numberString);
    }

//    void eraseNumber() {
//        String numberString = String.valueOf(number).substring(0, String.valueOf(number).length() - 1);
//        if (NumInput.getText().length() == 1) {
//            number = 0;
//            NumInput.setText(0);
//            return;
//        }
//        number = Integer.parseInt(numberString);
//        NumInput.setText(numberString);
//    }

    void compute() {
        // set state
        findFactors();

        // set ui
        if (Factors.size() == 0) {
            FactorList.setText("No Factors");
            FactorN.setText("0");
        } else {
            String delimeter = ", ";
            FactorList.setText(Factors.toString().replace("[", "").replace("]", ""));
            FactorN.setText(Factors.size()+"");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign elements to their instances
        NumInput = findViewById(R.id.numInput);
        FactorList = findViewById(R.id.factorList);
        FactorN = findViewById(R.id.factorN);
        Button[] buttons = new Button[] {
                findViewById(R.id.btn0),
                findViewById(R.id.btn1),
                findViewById(R.id.btn2),
                findViewById(R.id.btn3),
                findViewById(R.id.btn4),
                findViewById(R.id.btn5),
                findViewById(R.id.btn6),
                findViewById(R.id.btn7),
                findViewById(R.id.btn8),
                findViewById(R.id.btn9)
        };
        ComputeBtn = findViewById(R.id.btnCompute);
//        ClearBtn = findViewById(R.id.btnClear);
//        ExitBtn = findViewById(R.id.btnExit);
        ClearExitBtn = findViewById(R.id.btnClearExit);
        // assign function buttons
//        ClearBtn.setOnClickListener(view -> clear());
//        ExitBtn.setOnClickListener(view -> finish());
//        EraseBtn.setOnClickListener(view -> eraseNumber());
        ComputeBtn.setOnClickListener(view -> compute());

        // assign number buttons
        for (int i = 0; i < buttons.length; i++) {
            int finalI = i;
            buttons[i].setOnClickListener(view -> insertNumber(finalI));
        }
        NumInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 1) {
                    ClearExitBtn.setText("Clear");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ClearExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number+"".length() != 1) {
                    clear();
                    ClearExitBtn.setText("Exit");
                }
            }
        });
    }
}