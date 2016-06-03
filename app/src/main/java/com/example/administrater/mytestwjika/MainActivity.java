package com.example.administrater.mytestwjika;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

	private Button mButtonNotification;//权限
	private TextView mTextViewLoca;//在代码中改变textview中文字的位置gravity
	private EditText mEditSetWords;//在代码中给输入框设置文字
	private Button mButtonDialog;//从底部弹出弹窗
	private Button mButtonSelfDia;//自定义dialog

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		setEditText();
	}

	private void initViews() {
		mButtonNotification = (Button) findViewById(R.id.button_permission);
		mButtonNotification.setOnClickListener(this);
		mTextViewLoca = (TextView) findViewById(R.id.text_address);
		mTextViewLoca.setOnClickListener(this);
		mButtonDialog = (Button) findViewById(R.id.bottom_dialog);
		mButtonDialog.setOnClickListener(this);
		mButtonSelfDia = (Button) findViewById(R.id.self_dialog);
		mButtonSelfDia.setOnClickListener(this);
	}

	private void setEditText() {
		mEditSetWords = (EditText) findViewById(R.id.edit_settext);
		mEditSetWords.setText("zheshizaiwenbenzhongshezhidewenzi");
		mEditSetWords.setEnabled(false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button_permission:
				startActivity(new Intent(MainActivity.this, GetPermission.class));
				break;
			case R.id.text_address:
				mTextViewLoca.setGravity(Gravity.LEFT);
				break;
			case R.id.bottom_dialog:
				Dialog dialog = new Dialog(this, R.style.dialog);
				dialog.setContentView(R.layout.bottom_dialog);
				Window window = dialog.getWindow();
				window.setGravity(Gravity.BOTTOM);
				window.setWindowAnimations(R.style.dialogAnimation);
				// 可以在此设置显示动画
				WindowManager.LayoutParams wl = window.getAttributes();
				// 以下这两句是为了保证按钮可以水平满屏
				wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
				wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
				dialog.onWindowAttributesChanged(wl);
				dialog.show();
				break;
			case R.id.self_dialog:
				Dialog dialog1 = new Dialog(this,R.style.dialog);
				dialog1.setContentView(R.layout.self_dialog);
				Window window1 = dialog1.getWindow();
				window1.setGravity(Gravity.CENTER);
				WindowManager.LayoutParams wl1 = window1.getAttributes();
				// 以下这两句是为了保证按钮可以水平满屏
				wl1.width = ViewGroup.LayoutParams.MATCH_PARENT;
				wl1.height = ViewGroup.LayoutParams.WRAP_CONTENT;
				dialog1.onWindowAttributesChanged(wl1);
				dialog1.show();
				break;
			default:
				break;
		}
	}
}
