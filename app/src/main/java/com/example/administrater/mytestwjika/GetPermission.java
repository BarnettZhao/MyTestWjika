package com.example.administrater.mytestwjika;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by kkkkk on 2016/5/20.
 * 权限相关的测试页面
 */
public class GetPermission extends Activity implements View.OnClickListener{

	private Button mButtonNotification;//通知权限是否开启
	private Button mButtonAllPermission;//获取应用所有权限
	private static final String TAG = "GetPermission";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_permission_act);
		mButtonNotification = (Button) findViewById(R.id.isopen_notification);
		mButtonNotification.setOnClickListener(this);
		mButtonAllPermission = (Button) findViewById(R.id.all_permission);
		mButtonAllPermission.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		PackageManager pm = getPackageManager();
		switch (v.getId()) {
			case R.id.isopen_notification:
				boolean permission = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.ACCESS_NOTIFICATION_POLICY", "com.example.administrater.mytestwjika"));
				Log.e(TAG,"" + permission);
				if (permission) {
					Toast.makeText(getApplicationContext(),"有这个权限",Toast.LENGTH_SHORT).show();
					Log.e(TAG,"有这个权限");
				}else {
					Toast.makeText(getApplicationContext(),"木有这个权限",Toast.LENGTH_SHORT).show();
					Log.e(TAG,"木有这个权限");
				}
				break;
			case R.id.all_permission:
				try {
					PackageInfo pack = pm.getPackageInfo("com.example.administrater.mytestwjika",PackageManager.GET_PERMISSIONS);
					String[] permissionStrings = pack.requestedPermissions;
					if (permissionStrings != null) {
						Toast.makeText(getApplicationContext(),"权限清单--->" + permissionStrings.length + permissionStrings[0],Toast.LENGTH_SHORT).show();
						Log.e(TAG,"权限清单--->" + permissionStrings.toString());
					}
				} catch (PackageManager.NameNotFoundException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
	}
}
