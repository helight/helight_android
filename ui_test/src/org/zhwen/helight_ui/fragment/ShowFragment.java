package org.zhwen.helight_ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.zhwen.helight_ui.utiliys.NewsXmlParser;
import org.zhwen.helight_ui.utiliys.SlideImageLayout;
import org.zhwen.helight_ui.R;

public class ShowFragment extends Fragment {  
	
	private int pageIndex = 0; 	// 当前ViewPager索引
	private ArrayList<View> imagePageViews = null;// 滑动图片的集合
	private ViewGroup main_view = null;
	private ViewPager viewPager = null;	
	private ViewGroup imageCircleView = null;		// 包含圆点图片的View
	private ImageView[] imageCircleViews = null; 	
	private TextView tvSlideTitle = null;			// 滑动标题
	private SlideImageLayout slideLayout = null;	// 布局设置类
	private NewsXmlParser parser = null; 			// 数据解析类
	
	private ListView list_view;
	private List<Map<String, Object>> mData;
	
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
    	main_view = (ViewGroup)inflater.inflate(R.layout.show_layout, container, false);  
        initeViews();
        
        list_view = (ListView) main_view.findViewById(R.id.minelistView);  
        mData = getData();
        MyAdapter adapter = new MyAdapter(getActivity());
        list_view.setAdapter(adapter);
        return main_view;  
    }  
    /**
	 * 初始化
	 */
	private void initeViews(){
		// 滑动图片区域
		imagePageViews = new ArrayList<View>();
		viewPager = (ViewPager) main_view.findViewById(R.id.image_slide_page);  
		
		// 圆点图片区域
		parser = new NewsXmlParser();
		int length = parser.getSlideImages().length;
		imageCircleViews = new ImageView[length];
		imageCircleView = (ViewGroup) main_view.findViewById(R.id.layout_circle_images);
		slideLayout = new SlideImageLayout(getActivity());
		slideLayout.setCircleImageLayout(length);
		
		for(int i = 0;i < length;i++){
			imagePageViews.add(slideLayout.getSlideImageLayout(parser.getSlideImages()[i]));
			imageCircleViews[i] = slideLayout.getCircleImageLayout(i);
			imageCircleView.addView(slideLayout.getLinearLayout(imageCircleViews[i], 10, 10));
		}
		
		// 设置默认的滑动标题
		tvSlideTitle = (TextView) main_view.findViewById(R.id.tvSlideTitle);
		tvSlideTitle.setText(parser.getSlideTitles()[0]);
		
		// setContentView(main);
		
		// 设置ViewPager
        viewPager.setAdapter(new SlideImageAdapter());  
        viewPager.setOnPageChangeListener(new ImagePageChangeListener());
	}
	
	// 滑动图片数据适配器
    private class SlideImageAdapter extends PagerAdapter {  
        @Override  
        public int getCount() { 
            return imagePageViews.size();  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public int getItemPosition(Object object) {  
            // TODO Auto-generated method stub  
            return super.getItemPosition(object);  
        }  
  
        @Override  
        public void destroyItem(View arg0, int arg1, Object arg2) {  
            // TODO Auto-generated method stub  
            ((ViewPager) arg0).removeView(imagePageViews.get(arg1));  
        }  
  
        @Override  
        public Object instantiateItem(View arg0, int arg1) {  
            // TODO Auto-generated method stub  
        	((ViewPager) arg0).addView(imagePageViews.get(arg1));
            
            return imagePageViews.get(arg1);  
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
            // TODO Auto-generated method stub    
        }  
  
        @Override  
        public Parcelable saveState() {  
            // TODO Auto-generated method stub  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
            // TODO Auto-generated method stub    
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
            // TODO Auto-generated method stub    
        }  
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
  
        @Override  
        public void onPageSelected(int index) {  
        	pageIndex = index;
        	slideLayout.setPageIndex(pageIndex);
        	tvSlideTitle.setText(parser.getSlideTitles()[pageIndex]);
        	
            for (int i = 0; i < imageCircleViews.length; i++) {  
            	imageCircleViews[pageIndex].setBackgroundResource(R.drawable.dot_selected);
                
                if (index != i) {  
                	imageCircleViews[i].setBackgroundResource(R.drawable.dot_none);  
                }  
            }
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
     
	/**
	 * listview中点击按键弹出对话框
	 */
	public void showInfo() {
		/*new AlertDialog.Builder(this).setTitle("我的listview")
				.setMessage("介绍...")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();*/
		Log.v("showInfo", "test");
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
					// Log.v("test", v.getId());
					showInfo();
				}
			});

			return convertView;
		}
	}
}  