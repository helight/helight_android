package org.zhwen.helight_ui;



import java.util.ArrayList;

import org.zhwen.helight_ui.fragment.ContactsFragment;
import org.zhwen.helight_ui.fragment.MessageFragment;
import org.zhwen.helight_ui.fragment.NewsFragment;
import org.zhwen.helight_ui.fragment.SettingFragment;

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


/** 
 * ��Ŀ����Activity�����е�Fragment��Ƕ������� 
 *  
 */  
public class TabActivity extends FragmentActivity implements OnClickListener {  

	private ViewPager mPager;

    private View messageLayout;    // ��Ϣ���沼�� 
    private View contactsLayout;   // ��ϵ�˽��沼�� 
    private View newsLayout;       // ��̬���沼�� 
    private View settingLayout;    // ���ý��沼�� 
    
    private ImageView messageImage;  // ��Tab��������ʾ��Ϣͼ��Ŀؼ� 
    private ImageView contactsImage; // ��Tab��������ʾ��ϵ��ͼ��Ŀؼ� 
    private ImageView newsImage;     // ��Tab��������ʾ��̬ͼ��Ŀؼ� 
    private ImageView settingImage;  // ��Tab��������ʾ����ͼ��Ŀؼ� 
    
    private TextView messageText;   // ��Tab��������ʾ��Ϣ����Ŀؼ� 
    private TextView contactsText;  // ��Tab��������ʾ��ϵ�˱���Ŀؼ� 
    private TextView newsText;      // ��Tab��������ʾ��̬����Ŀؼ� 
    private TextView settingText;   // ��Tab��������ʾ���ñ���Ŀؼ� 
    
    private int tab_index = 0;
    
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.main_frame);  
        mPager = (ViewPager) findViewById(R.id.viewpager);
		
		ArrayList<Fragment> fragmentArray = new ArrayList<Fragment>();
		fragmentArray.add(new MessageFragment());
		fragmentArray.add(new ContactsFragment());
		fragmentArray.add(new NewsFragment());
		fragmentArray.add(new SettingFragment());
		mPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentArray));		
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());		
        
		initViews();  // ��ʼ������Ԫ��   
		
		setTabSelection(0);
    }  
  
    /** 
     * �������ȡ��ÿ����Ҫ�õ��Ŀؼ���ʵ���������������úñ�Ҫ�ĵ���¼��� 
     */  
    private void initViews() {  
        messageLayout = findViewById(R.id.message_layout);  
        contactsLayout = findViewById(R.id.contacts_layout);  
        newsLayout = findViewById(R.id.news_layout);  
        settingLayout = findViewById(R.id.setting_layout);  
        messageImage = (ImageView) findViewById(R.id.message_image);  
        contactsImage = (ImageView) findViewById(R.id.contacts_image);  
        newsImage = (ImageView) findViewById(R.id.news_image);  
        settingImage = (ImageView) findViewById(R.id.setting_image);  
        messageText = (TextView) findViewById(R.id.message_text);  
        contactsText = (TextView) findViewById(R.id.contacts_text);  
        newsText = (TextView) findViewById(R.id.news_text);  
        settingText = (TextView) findViewById(R.id.setting_text);  
        
        messageLayout.setOnClickListener(this);  
        contactsLayout.setOnClickListener(this);  
        newsLayout.setOnClickListener(this);  
        settingLayout.setOnClickListener(this);  
    }  
  
    @Override  
    public void onClick(View v) {  
        switch (v.getId()) {  
        case R.id.message_layout:               
        	tab_index = 0;// ���������Ϣtabʱ��ѡ�е�1��tab 
            break;  
        case R.id.contacts_layout:                
        	tab_index = 1;// ���������ϵ��tabʱ��ѡ�е�2��tab
            break;  
        case R.id.news_layout:              
        	tab_index = 2;// ������˶�̬tabʱ��ѡ�е�3��tab
            break;  
        case R.id.setting_layout:               
        	tab_index = 3;// �����������tabʱ��ѡ�е�4��tab  
            break;  
        default:  
            break;  
        }  
        setTabSelection(tab_index);
    }     
  
    /** 
     * ���ݴ����index����������ѡ�е�tabҳ�� 
     *  
     * @param index  ÿ��tabҳ��Ӧ���±ꡣ0��ʾ��Ϣ��1��ʾ��ϵ�ˣ�2��ʾ��̬��3��ʾ���á� 
     */  
    private void setTabSelection(int index) {  
    	clearSelection();
        switch (index) {  
        case 0:  
            // ���������Ϣtabʱ���ı�ؼ���ͼƬ��������ɫ  
        	mPager.setCurrentItem(0);
            messageImage.setImageResource(R.drawable.message_selected);  
            messageText.setTextColor(Color.WHITE);              
            break;  
        case 1:  
            // ���������ϵ��tabʱ���ı�ؼ���ͼƬ��������ɫ
        	mPager.setCurrentItem(1);
            contactsImage.setImageResource(R.drawable.contacts_selected);  
            contactsText.setTextColor(Color.WHITE);              
            break;  
        case 2:  
            // ������˶�̬tabʱ���ı�ؼ���ͼƬ��������ɫ 
        	mPager.setCurrentItem(2);
            newsImage.setImageResource(R.drawable.news_selected);  
            newsText.setTextColor(Color.WHITE);              
            break;  
        case 3:  
        default:  
            // �����������tabʱ���ı�ؼ���ͼƬ��������ɫ  
        	mPager.setCurrentItem(3);
            settingImage.setImageResource(R.drawable.setting_selected);  
            settingText.setTextColor(Color.WHITE);             
            break;  
        }  
    }  
  
    /** 
     * ��������е�ѡ��״̬�� 
     */  
    private void clearSelection() {  
        messageImage.setImageResource(R.drawable.message_unselected);  
        messageText.setTextColor(Color.parseColor("#82858b"));  
        contactsImage.setImageResource(R.drawable.contacts_unselected);  
        contactsText.setTextColor(Color.parseColor("#82858b"));  
        newsImage.setImageResource(R.drawable.news_unselected);  
        newsText.setTextColor(Color.parseColor("#82858b"));  
        settingImage.setImageResource(R.drawable.setting_unselected);  
        settingText.setTextColor(Color.parseColor("#82858b"));  
    }  
  
   	/**
	 * ���ذ�ť�ļ���������ѯ���û��Ƿ��˳�����
	 * */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				Builder builder = new Builder(TabActivity.this);
				builder.setTitle("��ʾ");
				builder.setMessage("��ȷ��Ҫ�˳���");
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
				builder.setPositiveButton("ȡ��", dialog);
				builder.setNegativeButton("ȷ��", dialog);
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
			}
		}
		return false;
	}
	
	/**
	 * ҳ���л�����
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			clearSelection();
	        switch (arg0) {  
	        case 0:  
	            // ���������Ϣtabʱ���ı�ؼ���ͼƬ��������ɫ  
	            messageImage.setImageResource(R.drawable.message_selected);  
	            messageText.setTextColor(Color.WHITE);              
	            break;  
	        case 1:  
	            // ���������ϵ��tabʱ���ı�ؼ���ͼƬ��������ɫ
	            contactsImage.setImageResource(R.drawable.contacts_selected);  
	            contactsText.setTextColor(Color.WHITE);              
	            break;  
	        case 2:  
	            // ������˶�̬tabʱ���ı�ؼ���ͼƬ��������ɫ 
	            newsImage.setImageResource(R.drawable.news_selected);  
	            newsText.setTextColor(Color.WHITE);              
	            break;  
	        case 3:  
	        default:  
	            // �����������tabʱ���ı�ؼ���ͼƬ��������ɫ  
	            settingImage.setImageResource(R.drawable.setting_selected);  
	            settingText.setTextColor(Color.WHITE);             
	            break;  
	        }  
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
}  
