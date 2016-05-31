package com.example.administrater.mytestwjika;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

	private Button mButtonNotification;//权限
	private TextView mTextViewLoca;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mButtonNotification = (Button) findViewById(R.id.button_permission);
		mButtonNotification.setOnClickListener(this);
		mTextViewLoca = (TextView) findViewById(R.id.text_address);
		mTextViewLoca.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button_permission:
				startActivity(new Intent(MainActivity.this,GetPermission.class));
				break;
			case R.id.text_address:
				mTextViewLoca.setGravity(Gravity.LEFT);
				break;
			default:
				break;
		}
	}
}
