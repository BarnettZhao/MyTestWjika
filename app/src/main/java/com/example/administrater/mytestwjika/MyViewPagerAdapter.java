package com.example.administrater.mytestwjika;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhaoweiwei on 2016/7/8.
 */
public class MyViewPagerAdapter  extends PagerAdapter{
	private List<View> mViews;

	public MyViewPagerAdapter(List<View> mViews) {
		this.mViews = mViews;
	}

	@Override
	public int getCount() {
//		return mViews.size();
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
//		super.destroyItem(container, position, object);
//		container.removeView(mViews.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		/*container.addView(mViews.get(position));
		return mViews.get(position);*/
		View view=mViews.get(position%mViews.size());
		if (view.getParent()!=null){
			container.removeView(view);
		}
		container.addView(view);
		return view;
	}
}
