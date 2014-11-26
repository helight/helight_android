package org.zhwen.helight_ui.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;



import org.zhwen.helight_ui.R;
import org.zhwen.helight_ui.ItemDetailActivity;
import org.zhwen.helight_ui.adapter.BannerViewAdapter;
import org.zhwen.helight_ui.adapter.SecondListViewAdapter;
import org.zhwen.helight_ui.view.InfiniteViewPager;
import org.zhwen.helight_ui.view.XListView;
import org.zhwen.helight_ui.view.XListView.IXListViewListener;
import org.zhwen.helight_ui.utiliys.DataParser;


public class SecondFragment extends Fragment implements OnItemClickListener, IXListViewListener{  
	public static final String TAG = "Fragment2";  
	private ViewGroup main_view = null;
	private ViewGroup imageCircleView = null;		// 包含圆点图片的View		
	
    private Handler mHandler;
    private XListView showListView;
	private SecondListViewAdapter mlistAdapter;

    private int[] Resources=new int[]{R.drawable.image01,R.drawable.image02,R.drawable.image03,R.drawable.image04};  
    private ArrayList<View> Views;  //存放View的ArrayList  
  
    private int[] PointState=new int[]{R.drawable.page, R.drawable.page_now};    //点的状态图片资源  
    private ImageView[] Points;      //提示切换的Point   
    
    private InfiniteViewPager mViewPager; //ViewPager      
    private BannerViewAdapter mAdapter;  //ViewAdapter适配器  
    private List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
    private DataParser data_pase = new DataParser();
    //当前索引  
    private int index;  
    
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
    	Log.d(TAG, "onCreateView"); 
    	main_view = (ViewGroup)inflater.inflate(R.layout.layout2, container, false);  
        
        showListView = (XListView) main_view.findViewById(R.id.showListView);
		showListView.setPullLoadEnable(true);

		showListView.setXListViewListener(this);
		showListView.setOnItemClickListener(this);
		mHandler = new Handler();        
		
        //初始化View  
        Views = new ArrayList<View>();  
        for(int i=0;i<Resources.length;i++)  
        {  
            ImageView Image = new ImageView(getActivity());  
            Image.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT ,LayoutParams.MATCH_PARENT));             
            Image.setImageResource(Resources[i]);  
            Views.add(Image);  
        }  

        LayoutInflater mInflater = getActivity().getLayoutInflater();
		LinearLayout banner_head = (LinearLayout) mInflater.inflate(R.layout.banner_head, null);
	    //初始化Points  
        imageCircleView = (ViewGroup) banner_head.findViewById(R.id.LayoutForPoint);  
        Points = new ImageView[Resources.length];  
        for (int i = 0; i < Resources.length; i++) {  
            ImageView imageView = new ImageView(getActivity());  
            imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT));             
            Points[i] = imageView;  
            imageView.setImageResource(PointState[0]);  
            imageView.setPadding(10, 0, 10, 0);
            imageCircleView.addView(imageView);  
        } 
        
        //实例化适配器  
        mAdapter = new BannerViewAdapter(getActivity(), Views);  
        //获取ViewPaper    
        mViewPager = (InfiniteViewPager)banner_head.findViewById(R.id.ViewPager);
        mViewPager.setAdapter(mAdapter);  
        mViewPager.setOnPageChangeListener(new ImagePageChangeListener());         
        mViewPager.startAutoShow();

        //显示第一页  
        index = 0;  
        Points[index].setImageResource(PointState[1]);        
       
        showListView.addHeaderView(banner_head);// 增加广告banner  
        
        data_pase.getData(mData);
        mlistAdapter = new SecondListViewAdapter(getActivity(), mData);
        showListView.setAdapter(mlistAdapter);
   
        return main_view;  
    } 

    @Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
	{
    	System.out.println("你点击的是第" + arg3 + "项");
    	Log.v("XListView-onItemClick", "title " + (String)mData.get((int)arg3).get("title"));
    	Log.v("XListView-onItemClick", "info " + (String)mData.get((int)arg3).get("info"));
		Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
		intent.putExtra("title", (String)mData.get((int)arg3).get("title"));
		intent.putExtra("info", (String)mData.get((int)arg3).get("info"));
		// intent.putExtra("status", InvestListAdapter.myStats);
		// intent.putExtra("codevo", paramObject);
		startActivity(intent);
	}

    // 滑动页面更改事件监听器
    private class ImagePageChangeListener implements OnPageChangeListener {
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
            // TODO Auto-generated method stub    
        }  
  
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
            // TODO Auto-generated method stub    
        }  
        public void onPageSelected(int position)  {  
            setPoint(position); 
            Log.v("onPageSelected", "index: "  + position);
        }  
      
        /* 
         * 设置索引为position的Point 
         */  
        private void setPoint(int position)   
        {  
            if (position < 0 || position > Resources.length - 1 || index == position) {  
                return;  
            }  
            for(int i = 0; i < Resources.length; i++) {  
                Points[i].setImageResource(PointState[0]);;  
            }  
            Points[position].setImageResource(PointState[1]);;  
            index = position;  
        }        
    }
    
	private void onLoad() {
		mlistAdapter.notifyDataSetChanged();
		showListView.stopRefresh();
		showListView.stopLoadMore();
		showListView.setRefreshTime("刚刚");
	}
	
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// geneItems();
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// geneItems();				
				onLoad();
			}
		}, 2000);
	}
	
    
    @Override  
    public void onAttach(Activity activity) {  
        super.onAttach(activity);  
        Log.d(TAG, "onAttach");  
    }  
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        Log.d(TAG, "onCreate");  
    }  
  
    @Override  
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
        Log.d(TAG, "onActivityCreated");  
    }  
  
    @Override  
    public void onStart() {  
        super.onStart();  
        Log.d(TAG, "onStart");  
    }  
  
    @Override  
    public void onResume() {  
        super.onResume();  
        Log.d(TAG, "onResume");  
    }  
  
    @Override  
    public void onPause() {  
        super.onPause();  
        Log.d(TAG, "onPause");  
    }  
  
    @Override  
    public void onStop() {  
        super.onStop();  
        Log.d(TAG, "onStop");  
    }  
  
    @Override  
    public void onDestroyView() {  
        super.onDestroyView();  
        Log.d(TAG, "onDestroyView");  
    }  
  
    @Override  
    public void onDestroy() {  
        super.onDestroy();  
        Log.d(TAG, "onDestroy");  
    }  
  
    @Override  
    public void onDetach() {  
        super.onDetach();  
        Log.d(TAG, "onDetach");  
    }  
}  