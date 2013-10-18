package com.shumz.scalc;

import java.util.LinkedList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class sCalcActivity extends Activity implements OnClickListener {

	private static final String APP = "Simple Calculator";

	// Declaring Elements of Activity...

	TextView tvResultString;

	Button ClearButton, OffButton;

	Button Button_1, Button_2, Button_3, Button_4, Button_5, Button_6,
			Button_7, Button_8, Button_9, Button_0;

	Button Button_Add, Button_Sub, Button_Mul, Button_Div, Button_Floater,
			Button_Equal;

	String resultString = "0";

	char funcKey = 'Z';

	Float result = 0F;
	Float operand_1 = 0F;
	Float operand_2 = 0F;

	Boolean isDotPressed = false;

	Boolean isEqualPressed = false;
	Boolean isFuncKeyPressed = false;
	Boolean isDigitPressed = false;

	Boolean isBittonAddPressed = false;
	Boolean SubIsPressed = false;
	Boolean MulIsPressed = false;
	Boolean DivIsPressed = false;

	Boolean AddWasPressed = false;
	Boolean SubWasPressed = false;
	Boolean MulWasPressed = false;
	Boolean DivWasPressed = false;

	Boolean wasFuncKeyPressed = false;

	LinkedList<String> llStack = new LinkedList<String>();

	// Debug Logger Code BEGIN
	boolean isDebugLoggerEnabled = true;

	TextView tvOperandOneLogger;// = (TextView)
								// findViewById(R.id.tv_operand_one_logger);
	TextView tvOperandTwoLogger; // = (TextView)
									// findViewById(R.id.tv_operand_two_logger);
	TextView tvOperandResultLogger; // = (TextView)
									// findViewById(R.id.tv_result_logger);
	TextView tvOperandStackLogger; // = (TextView)
									// findViewById(R.id.tv_stack_sequence);

	// Debug Logger Code END

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (isDebugLoggerEnabled) {
			if (android.os.Build.VERSION.SDK_INT > 10) {
				ActionBar ABar = getActionBar();
				ABar.hide();
			}
			setContentView(R.layout.activity_scalc_logger);

			initializeDebugLoggerTVs();
		} else {
			setContentView(R.layout.activity_scalc);
		}

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

				isDotPressed = false;

				isEqualPressed = false;
				isFuncKeyPressed = false;
				isDigitPressed = false;

				wasFuncKeyPressed = false;

				isBittonAddPressed = false;
				SubIsPressed = false;
				MulIsPressed = false;
				DivIsPressed = false;

				AddWasPressed = false;
				SubWasPressed = false;
				MulWasPressed = false;
				DivWasPressed = false;

				funcKey = 'Z';

				Log.i(APP, "All vars are cleared...");

				llStack.clear();

				// Debug Logger Code BEGIN

				if (isDebugLoggerEnabled) {
					getDebugLoggerInfo();
				}
				// Debug Logger Code END

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

	private void initializeFuncKeys() {

		Button_Add = (Button) findViewById(R.id.button_Add);
		Button_Sub = (Button) findViewById(R.id.button_Sub);
		Button_Mul = (Button) findViewById(R.id.button_Mul);
		Button_Div = (Button) findViewById(R.id.button_Div);
		Button_Floater = (Button) findViewById(R.id.button_Dot);
		Button_Equal = (Button) findViewById(R.id.button_Eql);

		Button_Floater.setOnClickListener(this);

		Button_Add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				onFunctionalKeyPressEvaluation("+");

			}
		});

		Button_Sub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onFunctionalKeyPressEvaluation("-");
			}
		});

		Button_Mul.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onFunctionalKeyPressEvaluation("*");
			}
		});

		Button_Div.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onFunctionalKeyPressEvaluation("/");

			}
		});

		Button_Equal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				onFunctionalKeyPressEvaluation("=");

			}
		});

		Log.i(APP, "initialized Functional keys...");
	}

	protected void onFunctionalKeyPressEvaluation(String fun_key) {
		// TODO Auto-generated method stub

		if (llStack.isEmpty()) { // if llStack is empty at all

			if (fun_key.equals("=")) {

				isEqualPressed = true;

				resultString = formatInputString(resultString);
				tvResultString.setText(resultString);

				// llStack.clear();
				llStack.addLast(resultString);

				getDebugLoggerInfo();

			} else {

				isFuncKeyPressed = true;

				resultString = formatInputString(resultString);
				tvResultString.setText(resultString);

				llStack.addLast(resultString);
				llStack.addLast(fun_key);

				getDebugLoggerInfo();

			}

		} else { // llStack is not empty

			if (fun_key.equals("=")) { // clearing stack for now

				isEqualPressed = true;

				resultString = formatInputString(resultString);
				tvResultString.setText(resultString);

				llStack.clear();
				llStack.addLast(resultString);

				getDebugLoggerInfo();

			} else { // if fun_key is not "="

				if ((llStack.getLast().equals("+"))
						|| (llStack.getLast().equals("-"))
						|| (llStack.getLast().equals("*"))
						|| (llStack.getLast().equals("/"))) {

					isFuncKeyPressed = true;

					resultString = formatInputString(resultString);
					tvResultString.setText(resultString);

					llStack.removeLast();
					llStack.addLast(fun_key);

					getDebugLoggerInfo();

				} else {

					isFuncKeyPressed = true;

					resultString = formatInputString(resultString);
					tvResultString.setText(resultString);

					// llStack.removeLast();

					llStack.addLast(resultString);
					llStack.addLast(fun_key);

					getDebugLoggerInfo();

				}

			}
		}

	}

	@Override
	public void onClick(View v) {

		if (isFuncKeyPressed) {
			resultString = "0";
		}

		if (isEqualPressed) {
			resultString = "0";
		}

		char chr = 'Z';

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
		default:
			chr = 'Z';
			break;
		}

		isDigitPressed = true;
		isFuncKeyPressed = false;
		isEqualPressed = false;

		resultString = getInputFromDigitalKeyboard(chr, resultString);
		tvResultString.setText(resultString);

	}

	// This method will format entered char sequence into the string applicable
	// to convert it into the Float number.
	private String formatInputString(String str) {
		if (str.contains(".")) {
			while (str.length() > 1) {
				if (str.endsWith(".")) {
					str = str.substring(0, str.length() - 1);
					isDotPressed = false;
					break;
				} else if (str.endsWith("0")) {
					str = str.substring(0, str.length() - 1);
				} else {
					break;
				}
			}
		}

		if (str.contains(".")) {
			isDotPressed = true;
		} else {
			isDotPressed = false;
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

				if (!isDotPressed) {
					if (chr_inner == '.') {
						isDotPressed = true;
					}
					inner_str = inner_str + chr_inner;
					if (inner_str.startsWith(".")) {
						inner_str = "0.";
					}
				} else {
					if (chr_inner != '.') {
						inner_str = inner_str + chr_inner;
					}

				}// End of: if (!isDotPressed)

			} // End of: if (chr_inner != 'Z')

		} // End of: if (resultString.length() < 13)
		return inner_str;

	}

	// Debug Logger Code BEGIN
	private void initializeDebugLoggerTVs() {
		tvOperandOneLogger = (TextView) findViewById(R.id.tv_operand_one_logger);
		tvOperandTwoLogger = (TextView) findViewById(R.id.tv_operand_two_logger);
		tvOperandResultLogger = (TextView) findViewById(R.id.tv_result_logger);
		tvOperandStackLogger = (TextView) findViewById(R.id.tv_stack_sequence);

		Log.i(APP, "DebugLogger TextViews initialized...");
	}

	private void getDebugLoggerInfo() {
		tvOperandOneLogger.setText(String.valueOf(operand_1));
		tvOperandTwoLogger.setText(String.valueOf(operand_2));
		tvOperandResultLogger.setText(String.valueOf(result));

		if (llStack.isEmpty()) {

			tvOperandStackLogger
					.setText("llStack.size() is: " + llStack.size());
		} else {

			String str_llStackInfo = "llStack.size() is: " + llStack.size()
					+ " => ";

			for (int i = 0; i < llStack.size(); i++) {
				str_llStackInfo += llStack.get(i); // + "|";
			}

			tvOperandStackLogger.setText(str_llStackInfo);
		}

		Log.i(APP, "getDebugLoggerInfo() called...");
	}
	// Debug Logger Code END

}

// @Override
// public boolean onCreateOptionsMenu(Menu menu) {
// // Inflate the menu; this adds items to the action bar if it is present.
// getMenuInflater().inflate(R.menu.main, menu);
// return true;
// }
