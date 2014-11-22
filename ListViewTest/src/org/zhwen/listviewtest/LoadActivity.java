package org.zhwen.listviewtest;

import java.util.ArrayList;  

import org.zhwen.listviewtest.XListView.IXListViewListener;

import android.os.Bundle;  
import android.os.Handler;
import android.app.Activity;  
import android.support.v4.view.ViewPager;  
import android.support.v4.view.ViewPager.OnPageChangeListener;  
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;  
import android.view.View;  
import android.view.ViewGroup;
import android.view.View.OnClickListener;  
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;  
import android.widget.LinearLayout;  
  
public class LoadActivity extends Activity implements OnClickListener, OnPageChangeListener, IXListViewListener {    
    //ViewAdapter适配器  
    private LoadViewAdapter mAdapter;  
    private ViewGroup imageCircleView = null;		// 包含圆点图片的View
    //引导图片资源  
    private int[] Resources=new int[]{R.drawable.image01,R.drawable.image02,R.drawable.image03,R.drawable.image04};  
    //存放View的ArrayList  
    private ArrayList<View> Views;  
    //点的状态图片资源  
    private int[] PointState=new int[]{R.drawable.page,R.drawable.page_now};  
    //提示切换的Point  
    private ImageView[] Points;  
    //当前索引  
    private int index;  
    //ViewPager  
    private ViewPager mPager;     
    
    private Handler mHandler;
    private XListView mListView;
	private ArrayAdapter<String> mpAdapter;
	private ArrayList<String> items = new ArrayList<String>();
	private int start = 0;
	private static int refreshCnt = 0;
	
    @Override  
    protected void onCreate(Bundle savedInstanceState)   
    {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.load_view);
		geneItems();
		mListView = (XListView) findViewById(R.id.xListView);
		mListView.setPullLoadEnable(true);
		mpAdapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
		mListView.setAdapter(mpAdapter);
		mListView.setXListViewListener(this);
		mHandler = new Handler();
		

        //初始化View  
        Views=new ArrayList<View>();  
        for(int i=0;i<Resources.length;i++)  
        {  
            ImageView Image=new ImageView(this);  
            Image.setImageResource(Resources[i]);  
            Views.add(Image);  
        }  
  
        
        LayoutInflater mInflater = this.getLayoutInflater();
		LinearLayout home_activity_head = (LinearLayout) mInflater.inflate(R.layout.test_load, null);
	    //初始化Points  
        imageCircleView = (ViewGroup) home_activity_head.findViewById(R.id.LayoutForPoint);  
        Points = new ImageView[Resources.length];  
        for (int i = 0; i < Resources.length; i++) {  
            ImageView imageView = new ImageView(this);  
            imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));  
            Points[i] = imageView;  
            imageView.setImageResource(PointState[0]);  
            imageView.setPadding(10, 0, 10, 0);
            imageCircleView.addView(imageView);  
        } 
        
        //获取ViewPaper  
        mPager=(ViewPager)home_activity_head.findViewById(R.id.ViewPager);  
        //实例化适配器  
        mAdapter = new LoadViewAdapter(Views);  
        mPager.setAdapter(mAdapter);  
        mPager.setOnPageChangeListener(this);  
        //显示第一页  
        index=0;  
        Points[index].setImageResource(PointState[1]);        
       
        mListView.addHeaderView(home_activity_head);// 增加广告banner  
    }  
	private void geneItems() {
		for (int i = 0; i != 20; ++i) {
			items.add("refresh cnt " + (++start));
		}
	}

    @Override  
    public boolean onCreateOptionsMenu(Menu menu)   
    {  
        getMenuInflater().inflate(R.menu.main, menu);  
        return true;  
    }  
  
    @Override  
    public void onClick(View v)   
    {  
        index=(Integer)v.getTag();  
        setPoint(index);  
        setView(index);  
        Log.v("onClick", "index: "  + index);
    }  
  
    /* 
     * 设置索引为index的View 
     */  
    private void setView(int index)   
    {  
        if(index<0||index>Resources.length)  
        {  
            return;  
        }  
        mPager.setCurrentItem(index);  
    }  
  
    /* 
     * 设置索引为position的Point 
     */  
    private void setPoint(int position)   
    {  
        if (position < 0 || position > Resources.length - 1 || index == position) {  
            return;  
        }  
        for(int i=0;i<Resources.length;i++)  
        {  
            Points[i].setImageResource(PointState[0]);;  
        }  
        Points[position].setImageResource(PointState[1]);;  
        index = position;  
    }  
      
    public void onPageSelected(int position)   
    {  
        setPoint(position); 
        Log.v("onPageSelected", "index: "  + position);
    }  
  
    @Override  
    public void onPageScrollStateChanged(int arg0)   
    {  
          
    }  
  
    @Override  
    public void onPageScrolled(int arg0, float arg1, int arg2)   
    {            
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
				mpAdapter = new ArrayAdapter<String>(LoadActivity.this, R.layout.list_item, items);
				mListView.setAdapter(mpAdapter);
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