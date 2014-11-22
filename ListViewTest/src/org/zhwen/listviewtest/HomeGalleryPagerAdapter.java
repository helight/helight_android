package org.zhwen.listviewtest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomeGalleryPagerAdapter extends PagerAdapter {

	Context act;
	private ArrayList<View> Views;//存放View的ArrayList  

   public HomeGalleryPagerAdapter(Context act,  ArrayList<View> Views)  
    {  
	   this.act = act;
	   this.Views = Views;  
    }  
	  
	@Override
	public int getCount() {
		return Views.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view == obj;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {		
		 ((ViewPager)container).addView(Views.get(position),0);  
		 return Views.get(position);  
	}
  
    /* 
     * 销毁View 
     */  
    @Override  
    public void destroyItem(View container, int position, Object object)   
    {  
        ((ViewPager)container).removeView(Views.get(position));  
    }  

	@Override
	public void notifyDataSetChanged() {
		// viewCache.clear();
		super.notifyDataSetChanged();
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
}

