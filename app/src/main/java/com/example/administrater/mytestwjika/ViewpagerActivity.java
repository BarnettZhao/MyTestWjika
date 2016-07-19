package com.example.administrater.mytestwjika;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoweiwei on 2016/7/8.
 */
public class ViewpagerActivity extends Activity {

	private LinearLayout mContains;
	private ViewPager mViewpager;
	private List<View> mViews;
	private MyViewPagerAdapter myViewPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_test);
		mViewpager = (ViewPager) findViewById(R.id.viewpager);
		mContains = (LinearLayout) findViewById(R.id.container);
		LayoutInflater inflater = getLayoutInflater();
		initViews(inflater);
		myViewPagerAdapter = new MyViewPagerAdapter(mViews);
		mViewpager.setAdapter(myViewPagerAdapter);
		mViewpager.setCurrentItem(Integer.MAX_VALUE/2-Integer.MAX_VALUE/2%mViews.size());
		// 1.设置幕后item的缓存数目
		mViewpager.setOffscreenPageLimit(2);
// 2.设置页与页之间的间距
		mViewpager.setPageMargin(100);
// 3.将父类的touch事件分发至viewPgaer，否则只能滑动中间的一个view对象
		mContains.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return mViewpager.dispatchTouchEvent(event);
			}
		});
	}

	private void initViews(LayoutInflater inflater) {
		View view1 = inflater.inflate(R.layout.viewpager_item,null);
		ImageView imageView1 = (ImageView) view1.findViewById(R.id.imageview);
		imageView1.setImageResource(R.drawable.img1);
		View view2 = inflater.inflate(R.layout.viewpager_item,null);
		ImageView imageView2 = (ImageView) view2.findViewById(R.id.imageview);
		imageView2.setImageResource(R.drawable.img2);
		View view3 = inflater.inflate(R.layout.viewpager_item,null);
		ImageView imageView3 = (ImageView) view3.findViewById(R.id.imageview);
		imageView3.setImageResource(R.drawable.img5);
		View view4 = inflater.inflate(R.layout.viewpager_item,null);
		ImageView imageView4 = (ImageView) view4.findViewById(R.id.imageview);
		imageView4.setImageResource(R.drawable.img4);
		View view5 = inflater.inflate(R.layout.viewpager_item,null);
		ImageView imageView5 = (ImageView) view5.findViewById(R.id.imageview);
		imageView5.setImageResource(R.drawable.img5);
		mViews = new ArrayList<>();
		mViews.add(view1);
		mViews.add(view2);
		mViews.add(view3);
		mViews.add(view4);
		mViews.add(view5);
	}
}
