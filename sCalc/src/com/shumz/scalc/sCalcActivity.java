package com.shumz.scalc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;

public class sCalcActivity extends Activity implements OnClickListener {

	static public final String APP = "Simple Calculator";

	// Declaring Elements of Activity...

	TextView tvResultString;

	Button ClearButton, OffButton;

	Button Button_1, Button_2, Button_3, Button_4, Button_5, Button_6,
			Button_7, Button_8, Button_9, Button_0;

	Button Button_Add, Button_Sub, Button_Mul, Button_Div, Button_Floater,
			Button_Equal;

	String resultString = "0";
	String operandString = "0";

	Float result = 0F;
	Float operand_1 = 0F;
	Float operand_2 = 0F;

	Boolean DotIsPressed = false;
	Boolean EqualIsPressed = false;
	Boolean FuncKeyIsPressed = false;

	Boolean MemoryTrigger = false;

	Boolean memoryCell_1 = false;
	Boolean memoryCell_2 = false;

	Boolean AddIsPressed = false;
	Boolean SubIsPressed = false;
	Boolean MulIsPressed = false;
	Boolean DivIsPressed = false;

	Boolean AddWasPressed = false;
	Boolean SubWasPressed = false;
	Boolean MulWasPressed = false;
	Boolean DivWasPressed = false;

	Boolean FuncKeyWasPressed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scalc_activity);

		Log.i(APP, "Activity is created...");

		initializeDigits();
		initializeFuncKeys();
		initializePanelKeys();

		Log.i(APP, "initialization performed...");

		tvResultString.setText(resultString);

		// Clear results
		ClearButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				resultString = "0";

				tvResultString.setText(resultString);

				result = 0F;
				operand_1 = 0F;
				operand_2 = 0F;

				DotIsPressed = false;
				EqualIsPressed = false;

				FuncKeyIsPressed = false;
				FuncKeyWasPressed = false;

				AddIsPressed = false;
				SubIsPressed = false;
				MulIsPressed = false;
				DivIsPressed = false;

				AddWasPressed = false;
				SubWasPressed = false;
				MulWasPressed = false;
				DivWasPressed = false;

				Log.i(APP, "All vars are cleared...");
			}
		});

		// Exit Application
		OffButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Log.i(APP, "Exit...");

				finish();
				System.exit(0);
			}
		});
	}

	private void initializePanelKeys() {

		OffButton = (Button) findViewById(R.id.buttonOff);
		ClearButton = (Button) findViewById(R.id.buttonClear);
		tvResultString = (TextView) findViewById(R.id.tvResult);

		Log.i(APP, "initialized Panel keys...");
	}

	private void initializeFuncKeys() {


		Button_Add = (Button) findViewById(R.id.button_Add);
		Button_Sub = (Button) findViewById(R.id.button_Sub);
		Button_Mul = (Button) findViewById(R.id.button_Mul);
		Button_Div = (Button) findViewById(R.id.button_Div);
		Button_Floater = (Button) findViewById(R.id.button_Dot);
		Button_Equal = (Button) findViewById(R.id.button_Eql);

		Button_Add.setOnClickListener(this);
		Button_Sub.setOnClickListener(this);
		Button_Mul.setOnClickListener(this);
		Button_Div.setOnClickListener(this);

		Button_Floater.setOnClickListener(this);
		Button_Equal.setOnClickListener(this);

		Log.i(APP, "initialized Functional keys...");
	}

	private void initializeDigits() {

		Button_1 = (Button) findViewById(R.id.button_1);
		Button_2 = (Button) findViewById(R.id.button_2);
		Button_3 = (Button) findViewById(R.id.button_3);
		Button_4 = (Button) findViewById(R.id.button_4);
		Button_5 = (Button) findViewById(R.id.button_5);
		Button_6 = (Button) findViewById(R.id.button_6);
		Button_7 = (Button) findViewById(R.id.button_7);
		Button_8 = (Button) findViewById(R.id.button_8);
		Button_9 = (Button) findViewById(R.id.button_9);
		Button_0 = (Button) findViewById(R.id.button_0);

		Button_1.setOnClickListener(this);
		Button_2.setOnClickListener(this);
		Button_3.setOnClickListener(this);
		Button_4.setOnClickListener(this);
		Button_5.setOnClickListener(this);
		Button_6.setOnClickListener(this);
		Button_7.setOnClickListener(this);
		Button_8.setOnClickListener(this);
		Button_9.setOnClickListener(this);
		Button_0.setOnClickListener(this);

		Log.i(APP, "initialized Digital keys...");
	}

	@Override
	public void onClick(View v) {

		char chr = 'Z';
		char lastActionKey = 'Z';

		switch (v.getId()) {

		// switching between digital buttons
		case R.id.button_0:
			chr = '0';
			break;
		case R.id.button_1:
			chr = '1';
			break;
		case R.id.button_2:
			chr = '2';
			break;
		case R.id.button_3:
			chr = '3';
			break;
		case R.id.button_4:
			chr = '4';
			break;
		case R.id.button_5:
			chr = '5';
			break;
		case R.id.button_6:
			chr = '6';
			break;
		case R.id.button_7:
			chr = '7';
			break;
		case R.id.button_8:
			chr = '8';
			break;
		case R.id.button_9:
			chr = '9';
			break;
		case R.id.button_Dot:
			chr = '.';
			break;

		// // Checking if Equal button was pressed
		case R.id.button_Eql:
			EqualIsPressed = true;
			break;
		// Checking if Add button was pressed
		case R.id.button_Add:
			lastActionKey = 'A';
			FuncKeyIsPressed = true;
			break;
		// Checking if Sub button was pressed
		case R.id.button_Sub:
			lastActionKey = 'S';
			FuncKeyIsPressed = true;
			break;
		// Checking if Mul button was pressed
		case R.id.button_Mul:
			lastActionKey = 'M';
			FuncKeyIsPressed = true;
			break;
		// Checking if Div button was pressed
		case R.id.button_Div:
			lastActionKey = 'D';
			FuncKeyIsPressed = true;
			break;
		default: {
			chr = 'Z';
		}
			break;
		}

		if (FuncKeyIsPressed) {

			Log.i(APP, "'FuncKeyIsPressed' is: " + FuncKeyIsPressed.toString());

			FuncKeyWasPressed = true;
			Log.i(APP, "'FuncKeyWasPressed' is: " + FuncKeyWasPressed.toString());
		}

		if ((FuncKeyIsPressed) || (EqualIsPressed)) {

			if ((FuncKeyIsPressed) && (!EqualIsPressed)) {
				resultString = formatInputString(resultString);
				tvResultString.setText(resultString);
				FuncKeyIsPressed = false;
				Log.i(APP, "'FuncKeyIsPressed' is: " + FuncKeyIsPressed.toString());
			} else {
				EqualIsPressed = false;
				Log.i(APP, "'EqualIsPressed' is: " + FuncKeyIsPressed.toString());
				
				resultString = formatInputString(resultString);
				tvResultString.setText(resultString);
				
				Log.i(APP, "operand_1 now is: " + operand_1.toString());
				Log.i(APP, "operand_2 now is: " + operand_2.toString());
				
			}

		} else {
			
			
			if (FuncKeyWasPressed) {
				FuncKeyWasPressed = false;
				Log.i(APP, "'FuncKeyWasPressed' is: " + FuncKeyWasPressed.toString());

				
				operand_1 = operand_2;
				Log.i(APP, "operand_1 now is: " + operand_1.toString());
			
				
				resultString = formatInputString(resultString);
				operand_2 = Float.valueOf(resultString);
				Log.i(APP, "operand_2 now is: " + operand_2.toString());

				resultString = "0";
				Log.i(APP, "resultString now is: " + resultString);

				DotIsPressed = false;

			}

			
			resultString = getInputFromDigitalKeyboard(chr, resultString);
//			resultString = String.valueOf(evaluate(operand_1, operand_2, chr));
			
			tvResultString.setText(resultString);
		}
		
		
	}

	private float evaluate(float op_one, float op_two, char func) {
		
		float res = 0;
		
		switch (func) {
		case 'A': res = op_one + op_two;
			
			break;
		case 'S': res = op_one - op_two;
			
			break;
		case 'M': res = op_one * op_two;
			
			break;
		case 'D': res = op_one / op_two;
			
			break;

		default:
			break;
		}	
		
		return res;	
	}
	
	// This method will format entered char sequence into the string applicable
	// to convert it into the Float number.
	private String formatInputString(String str) {
		if (str.contains(".")) {
			while (str.length() > 1) {
				if (str.endsWith(".")) {
					str = str.substring(0, str.length() - 1);
					DotIsPressed = false;
					break;
				} else if (str.endsWith("0")) {
					str = str.substring(0, str.length() - 1);
				} else {
					break;
				}
			}
		}

		if (str.contains(".")) {
			DotIsPressed = true;
		} else {
			DotIsPressed = false;
		}
		return str;
	}

	// This method will get input from string
	private String getInputFromDigitalKeyboard(char chr_inner, String inner_str) {

		if (inner_str.length() < 13) {

			// Checking if first digit is '0'
			if (chr_inner != 'Z') {
				if ((chr_inner == '0') && (inner_str.equals("0"))) {
					chr_inner = 'Z';
					inner_str = "0";
				} else if ((chr_inner != '0') && (inner_str.equals("0"))
						&& (chr_inner != '.')) {
					inner_str = Character.toString(chr_inner);
					chr_inner = 'Z';
				}
			}

			// Adding digits to a resultString one by one
			if (chr_inner != 'Z') {

				if (!DotIsPressed) {
					if (chr_inner == '.') {
						DotIsPressed = true;
					}
					inner_str = inner_str + chr_inner;
					if (inner_str.startsWith(".")) {
						inner_str = "0.";
					}
				} else {
					if (chr_inner != '.') {
						inner_str = inner_str + chr_inner;
					}

				}// End of: if (!DotIsPressed)

			} // End of: if (chr_inner != 'Z')

		} // End of: if (resultString.length() < 13)
		return inner_str;

	}

}

// @Override
// public boolean onCreateOptionsMenu(Menu menu) {
// // Inflate the menu; this adds items to the action bar if it is present.
// getMenuInflater().inflate(R.menu.main, menu);
// return true;
// }
