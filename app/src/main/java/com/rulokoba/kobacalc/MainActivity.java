package com.rulokoba.kobacalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    Button button0, button1, button2, button3,
            button4, button5, button6,
            button7, button8, button9,
            buttonDot, buttonEqual,
            buttonAdd, buttonSubstract, buttonMultiply, buttonDivide,
            buttonPercent, buttonSqrt, buttonOposite, buttonInverse,
            buttonAC, buttonC, buttonM, buttonMR;

    TextView textViewOperator, textViewOperandus, textViewDisplay, textViewMemory;

    boolean
            isNewInput, // start new number?
            isOperated, //
            isEqualed;

    String display;

    int operatorButtonId;

    float operandus, memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonDot = (Button) findViewById(R.id.buttonDot);
        buttonEqual = (Button) findViewById(R.id.buttonEqual);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSubstract = (Button) findViewById(R.id.buttonSubstract);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonPercent = (Button) findViewById(R.id.buttonPercent);
        buttonSqrt = (Button) findViewById(R.id.buttonSqrt);
        buttonOposite = (Button) findViewById(R.id.buttonOposite);
        buttonInverse = (Button) findViewById(R.id.buttonInverse);
        buttonAC = (Button) findViewById(R.id.buttonAC);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonM = (Button) findViewById(R.id.buttonM);
        buttonMR = (Button) findViewById(R.id.buttonMR);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonSubstract.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonSqrt.setOnClickListener(this);
        buttonOposite.setOnClickListener(this);
        buttonInverse.setOnClickListener(this);
        buttonAC.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonM.setOnClickListener(this);
        buttonMR.setOnClickListener(this);

        textViewOperator = (TextView) findViewById(R.id.textViewOperator);
        textViewOperandus = (TextView) findViewById(R.id.textViewOperandus);
        textViewDisplay = (TextView) findViewById(R.id.textViewDisplay);
        textViewMemory = (TextView) findViewById(R.id.textViewMemory);

        doAC();

    }

    @Override
    public void onClick(View v) {
        int id;
        id = v.getId();

        switch (id) {
            case R.id.button0:
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
            case R.id.buttonDot:
                doDigit(id);
                break;
            case R.id.buttonAC:
                doAC();
                break;
            case R.id.buttonC:
                doC();
                break;
            case R.id.buttonM:
                doM();
                break;
            case R.id.buttonMR:
                doMR();
                break;
            case R.id.buttonPercent:
                doPercent();
                break;
            case R.id.buttonSqrt:
                doSqrt();
                break;
            case R.id.buttonOposite:
                doOposite();
                break;
            case R.id.buttonInverse:
                doInverse();
                break;
            case R.id.buttonEqual:
                doEqual();
                break;
            case R.id.buttonAdd:
            case R.id.buttonSubstract:
            case R.id.buttonMultiply:
            case R.id.buttonDivide:
                doOperation(id);
                break;
        }
    }

    public String getSignForOperatorId(int id) {
        String sign;

        switch (id) {
            case R.id.buttonAdd:
                sign = "+";
                break;
            case R.id.buttonSubstract:
                sign = "-";
                break;
            case R.id.buttonMultiply:
                sign = "x";
                break;
            case R.id.buttonDivide:
                sign = "/";
                break;
            case R.id.buttonEqual:
                sign = "=";
                break;
            case R.id.buttonPercent:
                sign = "%";
                break;
            default:
                sign = "";
                break;
        }

        return sign;
    }

    public void updateViews() {
        textViewOperator.setText( getSignForOperatorId(operatorButtonId) );
        if (isEqualed) {
            textViewOperator.setTextColor(getResources().getColor(R.color.primary_text_disabled_material_light));
        } else {
            textViewOperator.setTextColor(getResources().getColor(R.color.primary_text_default_material_light));
        }
        textViewOperandus.setText(Float.toString(operandus));
        textViewDisplay.setText(display);
        updateViewMemory();
    }

    public void updateViewMemory() {
        textViewMemory.setText(Float.toString(memory));
    }

    public void doAC() {
        operatorButtonId = R.id.buttonEqual;
        operandus = 0;
        isNewInput = true;
        isOperated = false;
        isEqualed = false;
        display = "0";
        memory = 0;
        updateViews();
    }

    public void doC() {
        isNewInput = true;
        display = "0";
        updateViews();
    }

    public void doM() {
        memory += Float.parseFloat(display);
        updateViewMemory();
    }

    public void doMR() {
        display = Float.toString(memory);
        memory = 0;
        updateViews();
    }

    public void doOperation(int id) {
        float fDisplay, result;
        fDisplay = Float.parseFloat(display);
        result = fDisplay;

        boolean changeOperator, changeOperandus;
        changeOperator = true;
        changeOperandus = true;

        // Operate
        if ( !isEqualed
                && (!isOperated || id==operatorButtonId || operatorButtonId==R.id.buttonPercent) ) {

            switch (operatorButtonId) {
                case R.id.buttonAdd:
                    result = operandus + fDisplay;
                    break;
                case R.id.buttonSubstract:
                    result = operandus - fDisplay;
                    break;
                case R.id.buttonMultiply:
                    result = operandus * fDisplay;
                    break;
                case R.id.buttonDivide:
                    result = operandus / fDisplay;
                    break;
                case R.id.buttonPercent:
                    switch (id) {
                        case R.id.buttonAdd:
                            result = operandus + fDisplay;
                            changeOperator = false;
                            changeOperandus = false;
                            break;
                        case R.id.buttonSubstract:
                            result = operandus - fDisplay;
                            changeOperator = false;
                            changeOperandus = false;
                            break;
                    }
                    break;
            }

            isOperated = true;

        }

        // Change operate and operandus
        if (changeOperator) {
            operatorButtonId = id;
        }
        if (changeOperandus) {
            operandus = result;
        }

        // Change display
        display = Float.toString(result);

        isEqualed = false;
        isNewInput = true;

        updateViews();
    }

    public void doPercent() {

        if (!isOperated && operatorButtonId==R.id.buttonMultiply) {

            float fDisplay, result;
            fDisplay = Float.parseFloat(display);

            // Operate
            result = operandus * fDisplay / 100;

            isOperated = true;

            // Update display
            display = Float.toString(result);

            isNewInput = true;
            isEqualed = true;

            updateViews();
        }

    }

    public void doSqrt() {
        float fDisplay, result;
        fDisplay = Float.parseFloat(display);

        // Operate
        result = (float)Math.sqrt(fDisplay);

        // Update display
        display = Float.toString(result);

        isNewInput = true;

        updateViews();
    }

    public void doOposite() {
        float fDisplay, result;
        fDisplay = Float.parseFloat(display);

        // Operate
        result = -fDisplay;

        // Update display
        display = Float.toString(result);

        isNewInput = true;

        updateViews();
    }

    public void doInverse() {
        float fDisplay, result;
        fDisplay = Float.parseFloat(display);

        // Operate
        result = 1 / fDisplay;

        // Update display
        display = Float.toString(result);

        isNewInput = true;

        updateViews();
    }

    public void doEqual() {
        float fDisplay, result;
        fDisplay = Float.parseFloat(display);
        result = fDisplay;

        // Operate
        switch (operatorButtonId) {
            case R.id.buttonAdd:
                result = operandus + fDisplay;
                break;
            case R.id.buttonSubstract:
                result = operandus - fDisplay;
                break;
            case R.id.buttonMultiply:
                result = operandus * fDisplay;
                break;
            case R.id.buttonDivide:
                result = operandus / fDisplay;
                break;
        }

        isOperated = true;
        isEqualed = true;
        isNewInput = true;

        // Update display
        display = Float.toString(result);

        updateViews();
    }

    public void doDigit(int id) {
        String d;
        d = "";

        switch (id) {
            case R.id.button0:
                d = "0";
                break;
            case R.id.button1:
                d = "1";
                break;
            case R.id.button2:
                d = "2";
                break;
            case R.id.button3:
                d = "3";
                break;
            case R.id.button4:
                d = "4";
                break;
            case R.id.button5:
                d = "5";
                break;
            case R.id.button6:
                d = "6";
                break;
            case R.id.button7:
                d = "7";
                break;
            case R.id.button8:
                d = "8";
                break;
            case R.id.button9:
                d = "9";
                break;
            case R.id.buttonDot:
                d = ".";
                break;
        }

        if (isNewInput) {
            display = d;
            isNewInput = false;
        } else {
            if (id != R.id.buttonDot || !display.contains(".")) {
                display += d;
            }
        }

        isOperated = false;

        updateViews();
    }

}
