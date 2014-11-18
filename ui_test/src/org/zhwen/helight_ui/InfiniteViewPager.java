package org.zhwen.helight_ui;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class InfiniteViewPager extends ViewPager {

	ViewGroup containerView;
	// 是否自动轮播
	public boolean autoShow = true;

	public ViewGroup getContainerView() {
		return containerView;
	}

	public void setContainerView(ViewGroup container) {
		this.containerView = container;
	}

	public InfiniteViewPager(Context context) {
		super(context);
	}

	public InfiniteViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// 开启自动轮播
	public void startAutoShow() {
		handler.removeCallbacks(taskAutoShow);
		handler.postDelayed(taskAutoShow, 4000);
	}

	Handler handler = new Handler();
	private Runnable taskAutoShow = new Runnable() {
		@Override
		public void run() {
			if (!autoShow)
				return;
			int currentImgIndex = getCurrentItem();
			currentImgIndex++;
			if (currentImgIndex >= getAdapter().getCount())
				currentImgIndex = 0;
			setCurrentItem(currentImgIndex);

			startAutoShow();
		}
	};

	// 需要在页面销毁时调用
	public void destory() {

	}

	float xDistance, yDistance, lastX, lastY;
	boolean disallowIntercept = false;

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDistance = yDistance = 0f;
			lastX = ev.getX();
			lastY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float curX = ev.getX();
			final float curY = ev.getY();
			xDistance += Math.abs(curX - lastX);
			yDistance += Math.abs(curY - lastY);
			lastX = curX;
			lastY = curY;
			if (xDistance > yDistance)
				disallowIntercept = true;
			else
				disallowIntercept = false;
		}
		if (containerView != null) {
			containerView.requestDisallowInterceptTouchEvent(disallowIntercept);
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			handler.removeCallbacks(taskAutoShow);
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			startAutoShow();
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	// 获取当前展示的索引
	public int getCurrentIndex() {
		return getCurrentItem();
	}

	@Override
	public void setAdapter(PagerAdapter adapter) {
		super.setAdapter(adapter);
		if (adapter != null && adapter.getCount() > 0)
			setCurrentItem(0);
	}

	@Override
	public void setCurrentItem(int item) {
		item = item % getAdapter().getCount();
		super.setCurrentItem(item);
	}
}
