package com.example.administrater.mytestwjika;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends Activity implements View.OnClickListener {

	private Button mButtonNotificationPermission;//权限
	private TextView mTextViewLoca;//在代码中改变textview中文字的位置gravity
	private EditText mEditSetWords;//在代码中给输入框设置文字
	private Button mButtonDialog;//从底部弹出弹窗
	private Button mButtonSelfDia;//自定义dialog
	private Button mButtonLocation;//判断定位服务是否开启
	private Button mButtonNotification;//判断是否开启通知服务
	private Button mButtonSetString;//设置不同语言
	private Button mButtonViewpager;//viewpager

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		setEditText();
	}

	private void initViews() {
		mButtonNotificationPermission = (Button) findViewById(R.id.button_permission);
		mButtonNotificationPermission.setOnClickListener(this);
		mTextViewLoca = (TextView) findViewById(R.id.text_address);
		mTextViewLoca.setOnClickListener(this);
		mButtonDialog = (Button) findViewById(R.id.bottom_dialog);
		mButtonDialog.setOnClickListener(this);
		mButtonSelfDia = (Button) findViewById(R.id.self_dialog);
		mButtonSelfDia.setOnClickListener(this);
		mButtonLocation = (Button) findViewById(R.id.button_location);
		mButtonLocation.setOnClickListener(this);
		mButtonNotification = (Button) findViewById(R.id.button_notification);
		mButtonNotification.setOnClickListener(this);
		mButtonSetString = (Button) findViewById(R.id.button_set_string);
		mButtonSetString.setOnClickListener(this);
		mButtonViewpager = (Button) findViewById(R.id.button_viewpager);
		mButtonViewpager.setOnClickListener(this);
	}

	private void setEditText() {
		mEditSetWords = (EditText) findViewById(R.id.edit_settext);
		mEditSetWords.setText("zheshizaiwenbenzhongshezhidewenzi");
		mEditSetWords.setEnabled(false);
	}

	@TargetApi(Build.VERSION_CODES.M)
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
				bottomDialog();
				break;
			case R.id.self_dialog:
				selfDialog();
				break;
			case R.id.button_location:
				LocationManager alm = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
				if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
					Toast.makeText(getApplicationContext(),"开启了定位服务",Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(),"未开启定位服务",Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.button_notification:
				NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
				if (notificationManager.isNotificationPolicyAccessGranted()) {
					Toast.makeText(getApplicationContext(),"开启了tongzhi服务",Toast.LENGTH_SHORT).show();
					Log.e("notification","true");
				} else {
					Toast.makeText(getApplicationContext(),"未开启tongzhi服务",Toast.LENGTH_SHORT).show();
					Log.e("notification","false");
				}
				Log.e("notification",isEnabled()+"");
				break;
			case R.id.button_set_string:
				switchLanguage(Locale.ENGLISH);
				finish();
				startActivity(new Intent(this,MainActivity.class));
				break;
			case R.id.button_viewpager:
				startActivity(new Intent(this,ViewpagerActivity.class));
				break;
			default:
				break;
		}
	}
	private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
	private boolean isEnabled() {
		String pkgName = getPackageName();
		final String flat = Settings.Secure.getString(getContentResolver(),
				ENABLED_NOTIFICATION_LISTENERS);
		if (!TextUtils.isEmpty(flat)) {
			final String[] names = flat.split(":");
			for (int i = 0; i < names.length; i++) {
				final ComponentName cn = ComponentName.unflattenFromString(names[i]);
				if (cn != null) {
					if (TextUtils.equals(pkgName, cn.getPackageName())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	private void bottomDialog() {
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
	}

	private void selfDialog() {
		Dialog dialog1 = new Dialog(this, R.style.dialog);
		dialog1.setContentView(R.layout.self_dialog);
		Window window1 = dialog1.getWindow();
		window1.setGravity(Gravity.CENTER);
		WindowManager.LayoutParams wl1 = window1.getAttributes();
		// 以下这两句是为了保证按钮可以水平满屏
		wl1.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl1.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		dialog1.onWindowAttributesChanged(wl1);
		dialog1.show();
	}

	public void switchLanguage(Locale locale) {
		Configuration config = getResources().getConfiguration();// 获得设置对象
		Resources resources = getResources();// 获得res资源对象
		DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
		config.locale = locale; // 简体中文
		resources.updateConfiguration(config, dm);
	}
}
