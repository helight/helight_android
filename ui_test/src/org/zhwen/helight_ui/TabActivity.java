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
import org.zhwen.helight_ui.fragment.DateFragment;
import org.zhwen.helight_ui.fragment.MineFragment;
import org.zhwen.helight_ui.fragment.NewsFragment;
import org.zhwen.helight_ui.fragment.ShowFragment;
import org.zhwen.helight_ui.fragment.SettingFragment;

/** 
 * 项目的主Activity，所有的Fragment都嵌入在这里。 
 *  
 */  
public class TabActivity extends FragmentActivity implements OnClickListener {  

	private ViewPager mPager;

    private View newsLayout;       // 消息界面布局 
    private View showLayout;       // 联系人界面布局 
    private View dateLayout;       // 动态界面布局 
    private View mineLayout;
    private View settingLayout;    // 设置界面布局 
  
    
    private ImageView newsImage;     // 在Tab布局上显示消息图标的控件 
    private ImageView showImage;     // 在Tab布局上显示联系人图标的控件 
    private ImageView dateImage;     // 在Tab布局上显示动态图标的控件 
    private ImageView mineImage;
    private ImageView settingImage;  // 在Tab布局上显示设置图标的控件 
    
    private TextView newsText;       // 在Tab布局上显示消息标题的控件 
    private TextView showText;       // 在Tab布局上显示联系人标题的控件 
    private TextView dateText;       // 在Tab布局上显示动态标题的控件 
    private TextView mineText;
    private TextView settingText;    // 在Tab布局上显示设置标题的控件 
    
    private int tab_index = 0;
    
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.main_frame);  
        mPager = (ViewPager) findViewById(R.id.viewpager);
		
		ArrayList<Fragment> fragmentArray = new ArrayList<Fragment>();
		fragmentArray.add(new NewsFragment());
		fragmentArray.add(new ShowFragment());
		fragmentArray.add(new DateFragment());
		fragmentArray.add(new MineFragment());
		fragmentArray.add(new SettingFragment());
		mPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentArray));		
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());		
        
		initViews();  // 初始化布局元素   
		
		setTabSelection(2);
    }  
  
    /** 
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。 
     */  
    private void initViews() {  
        newsLayout = findViewById(R.id.news_layout);  
        showLayout = findViewById(R.id.show_layout);  
        dateLayout = findViewById(R.id.date_layout);  
        mineLayout = findViewById(R.id.mine_layout);
        settingLayout = findViewById(R.id.setting_layout); 
        
        newsImage = (ImageView) findViewById(R.id.news_image);  
        showImage = (ImageView) findViewById(R.id.show_image);  
        dateImage = (ImageView) findViewById(R.id.date_image);  
        mineImage = (ImageView) findViewById(R.id.mine_image);  
        settingImage = (ImageView) findViewById(R.id.setting_image);  
        
        newsText = (TextView) findViewById(R.id.news_text);  
        showText = (TextView) findViewById(R.id.show_text);  
        dateText = (TextView) findViewById(R.id.date_text);  
        mineText = (TextView) findViewById(R.id.mine_text);  
        settingText = (TextView) findViewById(R.id.setting_text);  
        
        newsLayout.setOnClickListener(this);  
        showLayout.setOnClickListener(this);  
        dateLayout.setOnClickListener(this);  
        mineLayout.setOnClickListener(this);  
        settingLayout.setOnClickListener(this);  
    }  
  
    @Override  
    public void onClick(View v) {  
        switch (v.getId()) {  
        case R.id.news_layout:               
        	tab_index = 0;// 当点击了消息tab时，选中第1个tab 
            break;  
        case R.id.show_layout:                
        	tab_index = 1;// 当点击了联系人tab时，选中第2个tab
            break;  
        case R.id.date_layout:                
        	tab_index = 2;// 当点击了联系人tab时，选中第2个tab
            break;  
        case R.id.mine_layout:              
        	tab_index = 3;// 当点击了动态tab时，选中第3个tab
            break;  
        case R.id.setting_layout:               
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
        newsImage.setImageResource(R.drawable.message_unselected);  
        newsText.setTextColor(Color.parseColor("#82858b"));  
        showImage.setImageResource(R.drawable.contacts_unselected);  
        showText.setTextColor(Color.parseColor("#82858b"));  
        dateImage.setImageResource(R.drawable.news_unselected);  
        dateText.setTextColor(Color.parseColor("#82858b"));  
        mineImage.setImageResource(R.drawable.contacts_unselected);  
        mineText.setTextColor(Color.parseColor("#82858b"));  
        settingImage.setImageResource(R.drawable.setting_unselected);  
        settingText.setTextColor(Color.parseColor("#82858b"));  
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
            newsImage.setImageResource(R.drawable.message_selected);  
            newsText.setTextColor(Color.WHITE);              
            break;  
        case 1:  
            // 当点击了联系人tab时，改变控件的图片和文字颜色
        	mPager.setCurrentItem(1);
            showImage.setImageResource(R.drawable.contacts_selected);  
            showText.setTextColor(Color.WHITE);              
            break;  
        case 2:  
            // 当点击了动态tab时，改变控件的图片和文字颜色 
        	mPager.setCurrentItem(2);
            dateImage.setImageResource(R.drawable.news_selected);  
            dateText.setTextColor(Color.WHITE);              
            break;  
        case 3:  
            // 当点击了动态tab时，改变控件的图片和文字颜色 
        	mPager.setCurrentItem(3);
            mineImage.setImageResource(R.drawable.contacts_selected);  
            mineText.setTextColor(Color.WHITE);              
            break;  
        case 4:  
        default:  
            // 当点击了设置tab时，改变控件的图片和文字颜色  
        	mPager.setCurrentItem(4);
            settingImage.setImageResource(R.drawable.setting_selected);  
            settingText.setTextColor(Color.WHITE);             
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
