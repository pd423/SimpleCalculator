package com.example.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int TAG_BTN_ZERO = 0;
    public static final int TAG_BTN_ONE = 1;
    public static final int TAG_BTN_TWO = 2;
    public static final int TAG_BTN_THREE = 3;
    public static final int TAG_BTN_FOUR = 4;
    public static final int TAG_BTN_FIVE = 5;
    public static final int TAG_BTN_SIX = 6;
    public static final int TAG_BTN_SEVEN = 7;
    public static final int TAG_BTN_EIGHT = 8;
    public static final int TAG_BTN_NINE = 9;

    public static final int OP_CODE_NOT_DEFINE = 0;
    public static final int TAG_BTN_OPERATOR_ADD = 1;
    public static final int TAG_BTN_OPERATOR_SUBTRACT = 2;
    public static final int TAG_BTN_OPERATOR_MULTIPLY = 4;
    public static final int TAG_BTN_OPERATOR_DIVIDER = 8;

    public static final int TAG_BTN_OPERATOR_EQUAL = 16;
    public static final int TAG_BTN_OPERATOR_ALL_CLEAR = 32;

    private int mResultNumber, mLastInputNumber;
    private int mOpCode;
    private boolean mIsOperatorPressed = false;


    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        clearScreenNumber();
    }

    private OnClickListener mNumberClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mIsOperatorPressed) {
                clearScreenNumber();
                mIsOperatorPressed = false;
            }
            int btnNumber = Integer.valueOf(view.getTag().toString());
            int screenNumber = Integer.valueOf(mTextView.getText().toString());
            screenNumber = screenNumber * 10 + btnNumber;
            mTextView.setText(String.valueOf(screenNumber));
            mLastInputNumber = screenNumber;
        }
    };

    private OnClickListener mOperatorClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mOpCode != OP_CODE_NOT_DEFINE) {
                mResultNumber = actionToOperate(mOpCode, mResultNumber, getScreenNumber());
                mTextView.setText(String.valueOf(mResultNumber));
            }
            int opCode = Integer.valueOf(view.getTag().toString());
            mOpCode = opCode;
            mResultNumber = getScreenNumber();
            mIsOperatorPressed = true;
        }
    };

    private OnClickListener mEqualClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mOpCode != OP_CODE_NOT_DEFINE) {
                mResultNumber = actionToOperate(mOpCode, mResultNumber, mLastInputNumber);
                mTextView.setText(String.valueOf(mResultNumber));
                mOpCode = OP_CODE_NOT_DEFINE;
                mLastInputNumber = 0;
            }
        }
    };

    private OnClickListener mClearClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            clearScreenNumber();
            mOpCode = 0;
            mResultNumber = 0;
            mLastInputNumber = 0;
        }
    };

    private void initViews() {
        // Find all views from the layout.
        mTextView = (TextView) findViewById(R.id.textResult);
        Button btn0 = (Button) findViewById(R.id.btn0);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);
        Button btnAllClear = (Button) findViewById(R.id.btnAllClear);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        Button btnSubtract = (Button) findViewById(R.id.btnSubtract);
        Button btnMultiply = (Button) findViewById(R.id.btnMultiply);
        Button btnDivider = (Button) findViewById(R.id.btnDivider);
        Button btnEqual = (Button) findViewById(R.id.btnEqual);

        // Set tags for buttons
        btn0.setTag(TAG_BTN_ZERO);
        btn1.setTag(TAG_BTN_ONE);
        btn2.setTag(TAG_BTN_TWO);
        btn3.setTag(TAG_BTN_THREE);
        btn4.setTag(TAG_BTN_FOUR);
        btn5.setTag(TAG_BTN_FIVE);
        btn6.setTag(TAG_BTN_SIX);
        btn7.setTag(TAG_BTN_SEVEN);
        btn8.setTag(TAG_BTN_EIGHT);
        btn9.setTag(TAG_BTN_NINE);
        btnAllClear.setTag(TAG_BTN_OPERATOR_ALL_CLEAR);
        btnAdd.setTag(TAG_BTN_OPERATOR_ADD);
        btnSubtract.setTag(TAG_BTN_OPERATOR_SUBTRACT);
        btnMultiply.setTag(TAG_BTN_OPERATOR_MULTIPLY);
        btnDivider.setTag(TAG_BTN_OPERATOR_DIVIDER);
        btnEqual.setTag(TAG_BTN_OPERATOR_EQUAL);

        btn0.setOnClickListener(mNumberClickListener);
        btn1.setOnClickListener(mNumberClickListener);
        btn2.setOnClickListener(mNumberClickListener);
        btn3.setOnClickListener(mNumberClickListener);
        btn4.setOnClickListener(mNumberClickListener);
        btn5.setOnClickListener(mNumberClickListener);
        btn6.setOnClickListener(mNumberClickListener);
        btn7.setOnClickListener(mNumberClickListener);
        btn8.setOnClickListener(mNumberClickListener);
        btn9.setOnClickListener(mNumberClickListener);

        btnAdd.setOnClickListener(mOperatorClickListener);
        btnSubtract.setOnClickListener(mOperatorClickListener);
        btnMultiply.setOnClickListener(mOperatorClickListener);
        btnDivider.setOnClickListener(mOperatorClickListener);

        btnEqual.setOnClickListener(mEqualClickListener);
        btnAllClear.setOnClickListener(mClearClickListener);
    }

    private void clearScreenNumber() {
        if (mTextView != null) {
            mTextView.setText("0");
        }
    }

    private int getScreenNumber() {
        if (mTextView != null) {
            return Integer.valueOf(mTextView.getText().toString());
        }
        return 0;
    }

    private int actionToOperate(int opCode, int X, int Y) {
        switch (opCode) {
            case TAG_BTN_OPERATOR_ADD:
                return X + Y;
            case TAG_BTN_OPERATOR_SUBTRACT:
                return X - Y;
            case TAG_BTN_OPERATOR_MULTIPLY:
                return X * Y;
            case TAG_BTN_OPERATOR_DIVIDER:
                if (Y != 0) {
                    return X / Y;
                }
        }
        return 0;
    }
}
