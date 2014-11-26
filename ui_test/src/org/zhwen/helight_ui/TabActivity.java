package org.zhwen.helight_ui;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener; 
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;

import org.zhwen.helight_ui.adapter.FragmentAdapter;
import org.zhwen.helight_ui.fragment.ThirdFragment;
import org.zhwen.helight_ui.fragment.ForthFragment;
import org.zhwen.helight_ui.fragment.FristFragment;
import org.zhwen.helight_ui.fragment.SecondFragment;
import org.zhwen.helight_ui.fragment.FifthFragment;

/** 
 * 项目的主Activity，所有的Fragment都嵌入在这里。 
 *  
 */  
public class TabActivity extends FragmentActivity implements OnClickListener {  

	private ViewPager mPager;
	// 界面布局 
    private View Layout1; 
    private View Layout2;    
    private View Layout3;    
    private View Layout4;
    private View Layout5;   
    // 图标的控件 
    private ImageView Image1;    
    private ImageView Image2;
    private ImageView Image3;
    private ImageView Image4;
    private ImageView Image5;
    // 消息标题的控件
    private TextView Text1;
    private TextView Text2;
    private TextView Text3;
    private TextView Text4;
    private TextView Text5; 
    
    private int tab_index = 0;
    
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.main_frame);  
        mPager = (ViewPager) findViewById(R.id.viewpager);
		
		ArrayList<Fragment> fragmentArray = new ArrayList<Fragment>();
		fragmentArray.add(new FristFragment());
		fragmentArray.add(new SecondFragment());
		fragmentArray.add(new ThirdFragment());
		fragmentArray.add(new ForthFragment());
		fragmentArray.add(new FifthFragment());
		mPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentArray));		
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());	
		mPager.setOffscreenPageLimit(4);
        
		initViews();  // 初始化布局元素   
		
		setTabSelection(2);
    }  
  
    /** 
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。 
     */  
    private void initViews() {  
        Layout1 = findViewById(R.id.layout1);  
        Layout2 = findViewById(R.id.layout2);  
        Layout3 = findViewById(R.id.layout3);  
        Layout4 = findViewById(R.id.layout4);
        Layout5 = findViewById(R.id.layout5); 
        
        Image1 = (ImageView) findViewById(R.id.image1);  
        Image2 = (ImageView) findViewById(R.id.image2);  
        Image3 = (ImageView) findViewById(R.id.image3);  
        Image4 = (ImageView) findViewById(R.id.image4);  
        Image5 = (ImageView) findViewById(R.id.image5);  
        
        Text1 = (TextView) findViewById(R.id.text1);  
        Text2 = (TextView) findViewById(R.id.text2);  
        Text3 = (TextView) findViewById(R.id.text3);  
        Text4 = (TextView) findViewById(R.id.text4);  
        Text5 = (TextView) findViewById(R.id.text5);  
        
        Layout1.setOnClickListener(this);  
        Layout2.setOnClickListener(this);  
        Layout3.setOnClickListener(this);  
        Layout4.setOnClickListener(this);  
        Layout5.setOnClickListener(this);  
    }  
  
    @Override  
    public void onClick(View v) {  
        switch (v.getId()) {  
        case R.id.layout1:               
        	tab_index = 0;// 当点击了消息tab时，选中第1个tab 
            break;  
        case R.id.layout2:                
        	tab_index = 1;// 当点击了联系人tab时，选中第2个tab
            break;  
        case R.id.layout3:                
        	tab_index = 2;// 当点击了联系人tab时，选中第2个tab
            break;  
        case R.id.layout4:              
        	tab_index = 3;// 当点击了动态tab时，选中第3个tab
            break;  
        case R.id.layout5:               
        	tab_index = 4;// 当点击了设置tab时，选中第4个tab  
            break;  
        default: 
        	tab_index = 2;
            break;  
        }  
        setTabSelection(tab_index);        
    }  
    
    /** 
     * 清除掉所有的选中状态。 
     */  
    private void clearSelection() {  
        Image1.setImageResource(R.drawable.image1_unselected);  
        Text1.setTextColor(Color.parseColor("#82858b"));  
        Image2.setImageResource(R.drawable.image2_unselected);  
        Text2.setTextColor(Color.parseColor("#82858b"));  
        Image3.setImageResource(R.drawable.image3_unselected);  
        Text3.setTextColor(Color.parseColor("#82858b"));  
        Image4.setImageResource(R.drawable.image4_unselected);  
        Text4.setTextColor(Color.parseColor("#82858b"));  
        Image5.setImageResource(R.drawable.image5_unselected);  
        Text5.setTextColor(Color.parseColor("#82858b"));  
    }  
	
    /** 
     * 根据传入的index参数来设置选中的tab页。 
     *  
     * @param index  每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。 
     */  
    private void setTabSelection(int index) {  
    	clearSelection();
        switch (index) {  
        case 0:  
            // 当点击了消息tab时，改变控件的图片和文字颜色  
        	mPager.setCurrentItem(0);
            Image1.setImageResource(R.drawable.image1_selected);  
            Text1.setTextColor(Color.WHITE);              
            break;  
        case 1:  
            // 当点击了联系人tab时，改变控件的图片和文字颜色
        	mPager.setCurrentItem(1);
            Image2.setImageResource(R.drawable.image2_selected);  
            Text2.setTextColor(Color.WHITE);              
            break;  
        case 2:  
            // 当点击了动态tab时，改变控件的图片和文字颜色 
        	mPager.setCurrentItem(2);
            Image3.setImageResource(R.drawable.image3_selected);  
            Text3.setTextColor(Color.WHITE);              
            break;  
        case 3:  
            // 当点击了动态tab时，改变控件的图片和文字颜色 
        	mPager.setCurrentItem(3);
            Image4.setImageResource(R.drawable.image4_selected);  
            Text4.setTextColor(Color.WHITE);              
            break;  
        case 4:  
        default:  
            // 当点击了设置tab时，改变控件的图片和文字颜色  
        	mPager.setCurrentItem(4);
            Image5.setImageResource(R.drawable.image5_selected);  
            Text5.setTextColor(Color.WHITE);             
            break;  
        }  
    }  
  

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			setTabSelection(arg0);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	
   	/**
	 * 返回按钮的监听，用来询问用户是否退出程序
	 * */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				Builder builder = new Builder(TabActivity.this);
				builder.setTitle("提示");
				builder.setMessage("你确定要退出吗？");
				builder.setIcon(R.drawable.zhwen);

				DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						if (arg1 == DialogInterface.BUTTON_POSITIVE) {
							arg0.cancel();
						} else if (arg1 == DialogInterface.BUTTON_NEGATIVE) {
							TabActivity.this.finish();
						}
					}
				};
				builder.setPositiveButton("取消", dialog);
				builder.setNegativeButton("确定", dialog);
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
			}
		}
		return false;
	}
}  
