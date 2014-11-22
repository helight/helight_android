package org.zhwen.helight_ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.zhwen.helight_ui.R;
import org.zhwen.helight_ui.xlistview.InfiniteViewPager;
import org.zhwen.helight_ui.xlistview.BannerViewAdapter;
import org.zhwen.helight_ui.xlistview.XListView;
import org.zhwen.helight_ui.xlistview.XListView.IXListViewListener;

public class ShowFragment extends Fragment implements IXListViewListener{  

	private ViewGroup main_view = null;
	private ViewGroup imageCircleView = null;		// 包含圆点图片的View		
	
    private Handler mHandler;
    private XListView showListView;
	private ArrayAdapter<String> mpAdapter;
	private ArrayList<String> items = new ArrayList<String>();

    private int[] Resources=new int[]{R.drawable.image01,R.drawable.image02,R.drawable.image03,R.drawable.image04};  
    //存放View的ArrayList  
    private ArrayList<View> Views;  
    //点的状态图片资源  
    private int[] PointState=new int[]{R.drawable.page, R.drawable.page_now};  
    //提示切换的Point  
    private ImageView[] Points;  
    //ViewPager  
    // private ViewPager mPager;   
    private InfiniteViewPager mViewPager;
    //ViewAdapter适配器  
    private BannerViewAdapter mAdapter;  
    private List<Map<String, Object>> mData;
    //当前索引  
    private int index;  
    
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
    	main_view = (ViewGroup)inflater.inflate(R.layout.show_layout, container, false);  
        
        showListView = (XListView) main_view.findViewById(R.id.showListView);
		showListView.setPullLoadEnable(true);
		mpAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, items);
		showListView.setAdapter(mpAdapter);
		showListView.setXListViewListener(this);
		mHandler = new Handler();        
		
        //初始化View  
        Views = new ArrayList<View>();  
        for(int i=0;i<Resources.length;i++)  
        {  
            ImageView Image = new ImageView(getActivity());  
            Image.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT ,LayoutParams.MATCH_PARENT));             
            // Image.setAdjustViewBounds(true);
            // Image.setMaxHeight(110);
            // int padding = getActivity().getResources().getDimensionPixelSize(R.dimen.padding_medium);
            // Image.setPadding(padding, padding, padding, padding);
            // Image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
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
        
        //显示第一页  
        index = 0;  
        Points[index].setImageResource(PointState[1]);        
       
        showListView.addHeaderView(banner_head);// 增加广告banner  
        
        mData = getData();
        MyAdapter adapter = new MyAdapter(getActivity());
        showListView.setAdapter(adapter);
   
        return main_view;  
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
    
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "天天开心");
        map.put("info", "中国最大的SNS社交网站");
        map.put("img", R.drawable.logo_kaixin);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("title", "QQ同学");
        map.put("info", "中国浏览量最大的中文门户网站");
        map.put("img", R.drawable.logo_qq);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("title", "明道");
        map.put("info", "为中国企业开发的社会化协作平台");
        map.put("img", R.drawable.logo_mingdao);
        list.add(map);
         
        return list;
    }
      
    // ListView 中某项被选中后的逻辑
    protected void onListItemClick(ListView l, View v, int position, long id) {         
        Log.v("MyListView4-click", (String)mData.get(position).get("title"));
    }

	public final class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView info;
		public Button viewBtn;
	}

	public class MyAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final int pos = position;
			ViewHolder holder = null;
			if (convertView == null) {

				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.date_list, null);
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.info = (TextView) convertView.findViewById(R.id.info);
				holder.viewBtn = (Button) convertView.findViewById(R.id.view_btn);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.img.setImageResource((Integer) mData.get(position).get("img"));
			holder.title.setText((String) mData.get(position).get("title"));
			holder.info.setText((String) mData.get(position).get("info"));

			holder.viewBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.v("ShowFragment", "viewBtn press" + v.getId() + " pos: " + pos);
				}
			});
			return convertView;
		}
	}

	private void onLoad() {
		showListView.stopRefresh();
		showListView.stopLoadMore();
		showListView.setRefreshTime("刚刚");
	}
	
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// start = ++refreshCnt;
				items.clear();
				// geneItems();
				// mAdapter.notifyDataSetChanged();
				MyAdapter adapter = new MyAdapter(getActivity());
				showListView.setAdapter(adapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				/// geneItems();
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}
}  