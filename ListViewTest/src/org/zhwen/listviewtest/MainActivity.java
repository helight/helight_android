package org.zhwen.listviewtest;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.zhwen.listviewtest.XListView.IXListViewListener;


public class MainActivity extends Activity implements IXListViewListener {
	private XListView mListView;
	private ViewGroup containerView;
	private ArrayAdapter<String> mAdapter;
	InfiniteViewPager myHomePagerView;
	private ArrayList<String> items = new ArrayList<String>();
	private ImageView[] pointViews = null;
	private ArrayList<View> imagePageViews = null;
	HomeGalleryPagerAdapter homePagerAdapter;
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;
	private int[] Resources = new int[]{R.drawable.image01,R.drawable.image02,R.drawable.image03,R.drawable.image04};  
	private int[] PointState = new int[]{R.drawable.page,R.drawable.page_now};  
    //提示切换的Point  
 	private ArrayList<HomeGalleryProject> galleryList = new ArrayList<HomeGalleryProject>();// 滑动图片的集合	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		geneItems();
		mListView = (XListView) findViewById(R.id.xListView);
		mListView.setPullLoadEnable(true);
		mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
		mListView.setAdapter(mAdapter);
//		mListView.setPullLoadEnable(false);
//		mListView.setPullRefreshEnable(false);
		mListView.setXListViewListener(this);
		mHandler = new Handler();
		LayoutInflater mInflater = this.getLayoutInflater();

		LinearLayout home_activity_head = (LinearLayout) mInflater.inflate(R.layout.home_main_head, null);
		myHomePagerView = (InfiniteViewPager) (home_activity_head.findViewById(R.id.productGallery));
		myHomePagerView.setContainerView(mListView);
		// LayoutParams params = (LayoutParams) (myHomePagerView.getLayoutParams());
		// myHomePagerView.setLayoutParams(params);		

		// 推荐轮播图
		myHomePagerView.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				int pointIndex = position % imagePageViews.size();
				setImg2Point(pointIndex);
			}
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}
			public void onPageScrollStateChanged(int position) {
			}
		});
        //初始化View  
		imagePageViews =new ArrayList<View>();  
        for(int i=0;i<Resources.length;i++)  
        {  
            ImageView Image=new ImageView(this);  
            Image.setImageResource(Resources[i]);  
            imagePageViews.add(Image);  
        }  
		// homePagerAdapter = new HomeGalleryPagerAdapter(this, galleryList);
		homePagerAdapter = new HomeGalleryPagerAdapter(this, imagePageViews);
		myHomePagerView.setAdapter(homePagerAdapter);	
		
		mListView.addHeaderView(home_activity_head);// 增加广告banner
	}
	
	protected void setImg2Point(int pointIndex) {
		for (int i = 0; i < galleryList.size(); i++) {
			if (pointIndex == i) {
				pointViews[i].setImageResource(R.drawable.dot_selected);
			} else {
				pointViews[i].setImageResource(R.drawable.dot_none);
			}
		}
	}
	private void geneItems() {
		for (int i = 0; i != 20; ++i) {
			items.add("refresh cnt " + (++start));
		}
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}
	
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				items.clear();
				geneItems();
				// mAdapter.notifyDataSetChanged();
				mAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.list_item, items);
				mListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems();
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

}