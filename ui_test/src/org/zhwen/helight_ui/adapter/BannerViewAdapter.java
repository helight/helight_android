package org.zhwen.helight_ui.adapter;

import java.util.ArrayList;  

import android.content.Context;
import android.support.v4.view.ViewPager;  
import android.support.v4.view.PagerAdapter;  
import android.util.Log;
import android.view.View;  
import android.view.View.OnClickListener;
  
  
public class BannerViewAdapter extends PagerAdapter {    
	private Context context;
    private ArrayList<View> Views;//存放View的ArrayList  
    private int index = 0;
      
    /* 
     * ViewAdapter构造函数 
     */  
    public BannerViewAdapter(Context context, ArrayList<View> Views)  
    {  
    	this.context = context;
        this.Views = Views;  
    }    
      
    /* 
     * 返回View的个数 
     */  
    @Override  
    public int getCount()   
    {  
        if(Views != null)  
        {  
            return Views.size();  
        }  
        return 0;  
    }  
  
    /* 
     * 销毁View 
     */  
    @Override  
    public void destroyItem(View container, int position, Object object)   
    {  
        ((ViewPager)container).removeView(Views.get(position));  
    }  
  
    /* 
     * 初始化 
     */  
    @Override  
	public Object instantiateItem(View container, int position) {
		View view = Views.get(position);
		index = position;
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.v("page onClick", "v.Id: " + v.getId() + " position: " + index);
			}
		});

		((ViewPager) container).addView(view, 0);

		return view;
	}
      
    /* 
     * 判断View是否来自Object 
     */  
    @Override  
    public boolean isViewFromObject(View view, Object object)   
    {  
        return (view == object);  
    }    
}  