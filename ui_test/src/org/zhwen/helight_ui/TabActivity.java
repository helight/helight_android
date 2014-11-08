package org.zhwen.helight_ui;



import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
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
public class TabActivity extends Activity implements OnClickListener {  
  
    /** 
     * ����չʾ��Ϣ��Fragment 
     */  
    private MessageFragment messageFragment;  
  
    /** 
     * ����չʾ��ϵ�˵�Fragment 
     */  
    private ContactsFragment contactsFragment;  
  
    /** 
     * ����չʾ��̬��Fragment 
     */  
    private NewsFragment newsFragment;  
  
    /** 
     * ����չʾ���õ�Fragment 
     */  
    private SettingFragment settingFragment;  
  
    /** 
     * ��Ϣ���沼�� 
     */  
    private View messageLayout;  
  
    /** 
     * ��ϵ�˽��沼�� 
     */  
    private View contactsLayout;  
  
    /** 
     * ��̬���沼�� 
     */  
    private View newsLayout;  
  
    /** 
     * ���ý��沼�� 
     */  
    private View settingLayout;  
  
    /** 
     * ��Tab��������ʾ��Ϣͼ��Ŀؼ� 
     */  
    private ImageView messageImage;  
  
    /** 
     * ��Tab��������ʾ��ϵ��ͼ��Ŀؼ� 
     */  
    private ImageView contactsImage;  
  
    /** 
     * ��Tab��������ʾ��̬ͼ��Ŀؼ� 
     */  
    private ImageView newsImage;  
  
    /** 
     * ��Tab��������ʾ����ͼ��Ŀؼ� 
     */  
    private ImageView settingImage;  
  
    /** 
     * ��Tab��������ʾ��Ϣ����Ŀؼ� 
     */  
    private TextView messageText;  
  
    /** 
     * ��Tab��������ʾ��ϵ�˱���Ŀؼ� 
     */  
    private TextView contactsText;  
  
    /** 
     * ��Tab��������ʾ��̬����Ŀؼ� 
     */  
    private TextView newsText;  
  
    /** 
     * ��Tab��������ʾ���ñ���Ŀؼ� 
     */  
    private TextView settingText;  
  
    /** 
     * ���ڶ�Fragment���й��� 
     */  
    private FragmentManager fragmentManager;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.frame_test);  
        // ��ʼ������Ԫ��  
        initViews();  
        fragmentManager = getFragmentManager();  
        // ��һ������ʱѡ�е�0��tab  
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
            // ���������Ϣtabʱ��ѡ�е�1��tab  
            setTabSelection(0);  
            break;  
        case R.id.contacts_layout:  
            // ���������ϵ��tabʱ��ѡ�е�2��tab  
            setTabSelection(1);  
            break;  
        case R.id.news_layout:  
            // ������˶�̬tabʱ��ѡ�е�3��tab  
            setTabSelection(2);  
            break;  
        case R.id.setting_layout:  
            // �����������tabʱ��ѡ�е�4��tab  
            setTabSelection(3);  
            break;  
        default:  
            break;  
        }  
    }  
  
    /** 
     * ���ݴ����index����������ѡ�е�tabҳ�� 
     *  
     * @param index 
     *            ÿ��tabҳ��Ӧ���±ꡣ0��ʾ��Ϣ��1��ʾ��ϵ�ˣ�2��ʾ��̬��3��ʾ���á� 
     */  
    private void setTabSelection(int index) {  
        // ÿ��ѡ��֮ǰ��������ϴε�ѡ��״̬  
        clearSelection();  
        // ����һ��Fragment����  
        FragmentTransaction transaction = fragmentManager.beginTransaction();  
        // �����ص����е�Fragment���Է�ֹ�ж��Fragment��ʾ�ڽ����ϵ����  
        hideFragments(transaction);  
        switch (index) {  
        case 0:  
            // ���������Ϣtabʱ���ı�ؼ���ͼƬ��������ɫ  
            messageImage.setImageResource(R.drawable.message_selected);  
            messageText.setTextColor(Color.WHITE);  
            if (messageFragment == null) {  
                // ���MessageFragmentΪ�գ��򴴽�һ������ӵ�������  
                messageFragment = new MessageFragment();  
                transaction.add(R.id.content, messageFragment);  
            } else {  
                // ���MessageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����  
                transaction.show(messageFragment);  
            }  
            break;  
        case 1:  
            // ���������ϵ��tabʱ���ı�ؼ���ͼƬ��������ɫ  
            contactsImage.setImageResource(R.drawable.contacts_selected);  
            contactsText.setTextColor(Color.WHITE);  
            if (contactsFragment == null) {  
                // ���ContactsFragmentΪ�գ��򴴽�һ������ӵ�������  
                contactsFragment = new ContactsFragment();  
                transaction.add(R.id.content, contactsFragment);  
            } else {  
                // ���ContactsFragment��Ϊ�գ���ֱ�ӽ�����ʾ����  
                transaction.show(contactsFragment);  
            }  
            break;  
        case 2:  
            // ������˶�̬tabʱ���ı�ؼ���ͼƬ��������ɫ  
            newsImage.setImageResource(R.drawable.news_selected);  
            newsText.setTextColor(Color.WHITE);  
            if (newsFragment == null) {  
                // ���NewsFragmentΪ�գ��򴴽�һ������ӵ�������  
                newsFragment = new NewsFragment();  
                transaction.add(R.id.content, newsFragment);  
            } else {  
                // ���NewsFragment��Ϊ�գ���ֱ�ӽ�����ʾ����  
                transaction.show(newsFragment);  
            }  
            break;  
        case 3:  
        default:  
            // �����������tabʱ���ı�ؼ���ͼƬ��������ɫ  
            settingImage.setImageResource(R.drawable.setting_selected);  
            settingText.setTextColor(Color.WHITE);  
            if (settingFragment == null) {  
                // ���SettingFragmentΪ�գ��򴴽�һ������ӵ�������  
                settingFragment = new SettingFragment();  
                transaction.add(R.id.content, settingFragment);  
            } else {  
                // ���SettingFragment��Ϊ�գ���ֱ�ӽ�����ʾ����  
                transaction.show(settingFragment);  
            }  
            break;  
        }  
        transaction.commit();  
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
     * �����е�Fragment����Ϊ����״̬�� 
     *  
     * @param transaction 
     *            ���ڶ�Fragmentִ�в��������� 
     */  
    private void hideFragments(FragmentTransaction transaction) {  
        if (messageFragment != null) {  
            transaction.hide(messageFragment);  
        }  
        if (contactsFragment != null) {  
            transaction.hide(contactsFragment);  
        }  
        if (newsFragment != null) {  
            transaction.hide(newsFragment);  
        }  
        if (settingFragment != null) {  
            transaction.hide(settingFragment);  
        }  
    }
}  


/*
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class FrameActivity {

	private LinearLayout mMyBottemSearchBtn, mMyBottemTuanBtn,
			mMyBottemCheckinBtn, mMyBottemMyBtn, mMyBottemMoreBtn;
	private ImageView mMyBottemSearchImg, mMyBottemTuanImg,
			mMyBottemCheckinImg, mMyBottemMyImg, mMyBottemMoreImg;
	private TextView mMyBottemSearchTxt, mMyBottemTuanTxt, mMyBottemCheckinTxt,
			mMyBottemMyTxt, mMyBottemMoreTxt;
	private List<View> list = new ArrayList<View>();// �൱������Դ
	
	private View view = null;
	private View view1 = null;
	private View view2 = null;
	private View view3 = null;
	private View view4 = null;
	
	private android.support.v4.view.ViewPager mViewPager;
	private PagerAdapter pagerAdapter = null;// ����Դ��viewpager֮�������
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setContentView(R.layout.activity_frame);
		//initView();
	}

	// ��ʼ���ؼ�
	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.FramePager);
		// ������linearlayoutΪ��ť���õĿؼ�
		mMyBottemSearchBtn = (LinearLayout) findViewById(R.id.MyBottemSearchBtn);
		mMyBottemTuanBtn = (LinearLayout) findViewById(R.id.MyBottemTuanBtn);
		mMyBottemCheckinBtn = (LinearLayout) findViewById(R.id.MyBottemCheckinBtn);
		mMyBottemMyBtn = (LinearLayout) findViewById(R.id.MyBottemMyBtn);
		mMyBottemMoreBtn = (LinearLayout) findViewById(R.id.MyBottemMoreBtn);
		// ����linearlayout�е�imageview
		mMyBottemSearchImg = (ImageView) findViewById(R.id.MyBottemSearchImg);
		mMyBottemTuanImg = (ImageView) findViewById(R.id.MyBottemTuanImg);
		mMyBottemCheckinImg = (ImageView) findViewById(R.id.MyBottemCheckinImg);
		mMyBottemMyImg = (ImageView) findViewById(R.id.MyBottemMyImg);
		mMyBottemMoreImg = (ImageView) findViewById(R.id.MyBottemMoreImg);
		// ����linearlayout�е�textview
		mMyBottemSearchTxt = (TextView) findViewById(R.id.MyBottemSearchTxt);
		mMyBottemTuanTxt = (TextView) findViewById(R.id.MyBottemTuanTxt);
		mMyBottemCheckinTxt = (TextView) findViewById(R.id.MyBottemCheckinTxt);
		mMyBottemMyTxt = (TextView) findViewById(R.id.MyBottemMyTxt);
		mMyBottemMoreTxt = (TextView) findViewById(R.id.MyBottemMoreTxt);
		createView();
		// дһ���ڲ���pageradapter
		pagerAdapter = new PagerAdapter() {
			// �ж��ٴ���ӵ�view��֮ǰ��view �Ƿ���ͬһ��view
			// arg0����ӵ�view��arg1֮ǰ��
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			// ��������Դ����
			@Override
			public int getCount() {
				return list.size();
			}

			// ���ٱ������ߵ�view
			
			 * ViewGroup ���������ǵ�viewpager �൱��activitygroup���е�view������ ���֮ǰ���Ƴ���
			 * position �ڼ������� Object ���Ƴ���view
			 * 
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// �Ƴ�view
				container.removeView(list.get(position));
			}

			/**
			 * instantiateItem viewpagerҪ��ʵ��view ViewGroup viewpager position
			 * �ڼ�������
			 * 
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// ��ȡview��ӵ��������У�������
				View v = list.get(position);
				container.addView(v);
				return v;
			}
		};
		// �������ǵ�adapter
		mViewPager.setAdapter(pagerAdapter);

		MyBtnOnclick mytouchlistener = new MyBtnOnclick();
		mMyBottemSearchBtn.setOnClickListener(mytouchlistener);
		mMyBottemTuanBtn.setOnClickListener(mytouchlistener);
		mMyBottemCheckinBtn.setOnClickListener(mytouchlistener);
		mMyBottemMyBtn.setOnClickListener(mytouchlistener);
		mMyBottemMoreBtn.setOnClickListener(mytouchlistener);

		// ����viewpager�����л�����,����viewpager�л��ڼ��������Լ�������
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			// arg0 ��ȡ viewpager����Ľ����л����ڼ�����
			@Override
			public void onPageSelected(int arg0) {
				// �������ť��ʽ
				initBottemBtn();
				// ���ն�Ӧ��view��tag���жϵ����л����ĸ����档
				// ���Ķ�Ӧ��button״̬
				int flag = (Integer) list.get((arg0)).getTag();
				if (flag == 0) {
					mMyBottemSearchImg
							.setImageResource(R.drawable.main_index_search_pressed);
					mMyBottemSearchTxt.setTextColor(Color.parseColor("#FF8C00"));
				} else if (flag == 1) {
					mMyBottemTuanImg
							.setImageResource(R.drawable.main_index_tuan_pressed);
					mMyBottemTuanTxt.setTextColor(Color.parseColor("#FF8C00"));
				} else if (flag == 2) {
					mMyBottemCheckinImg
							.setImageResource(R.drawable.main_index_checkin_pressed);
					mMyBottemCheckinTxt.setTextColor(Color
							.parseColor("#FF8C00"));
				} else if (flag == 3) {
					mMyBottemMyImg
							.setImageResource(R.drawable.main_index_my_pressed);
					mMyBottemMyTxt.setTextColor(Color.parseColor("#FF8C00"));
				} else if (flag == 4) {
					mMyBottemMoreImg
							.setImageResource(R.drawable.main_index_more_pressed);
					mMyBottemMoreTxt.setTextColor(Color.parseColor("#FF8C00"));
				}
			}

			/**
			 * ����ҳ�滬���� arg0 ��ʾ��ǰ������view arg1 ��ʾ�����İٷֱ� arg2 ��ʾ�����ľ���
			 * 
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			/**
			 * ��������״̬ arg0 ��ʾ���ǵĻ���״̬ 0:Ĭ��״̬ 1:����״̬ 2:����ֹͣ
			 * 
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	// ��viewpager����Ҫ��ʾ��viewʵ�������������Ұ���ص�view��ӵ�һ��list����
	private void createView() {
		view = this
				.getLocalActivityManager()
				.startActivity("search",
						new Intent(FrameActivity.this, SearchActivity.class))
				.getDecorView();
		// ������������button����ʽ�ı�־
		view.setTag(0);
		list.add(view);
		view1 = FrameActivity.this
				.getLocalActivityManager()
				.startActivity("tuan",
						new Intent(FrameActivity.this, TuanActivity.class))
				.getDecorView();
		view1.setTag(1);
		list.add(view1);
		view2 = FrameActivity.this
				.getLocalActivityManager()
				.startActivity("sign",
						new Intent(FrameActivity.this, CheckinActivity.class))
				.getDecorView();
		view2.setTag(2);
		list.add(view2);
		view3 = FrameActivity.this
				.getLocalActivityManager()
				.startActivity("my",
						new Intent(FrameActivity.this, MyActivity.class))
				.getDecorView();
		view3.setTag(3);
		list.add(view3);
		view4 = FrameActivity.this
				.getLocalActivityManager()
				.startActivity("more",
						new Intent(FrameActivity.this, MoreActivity.class))
				.getDecorView();
		view4.setTag(4);
		list.add(view4);
	}

	/**
	 * ��linearlayout��Ϊ��ť�ļ����¼�
	 * 
	private class MyBtnOnclick implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			int mBtnid = arg0.getId();
			switch (mBtnid) {
			case R.id.MyBottemSearchBtn:
				// //�������ǵ�viewpager��ת�Ǹ�����0������������ǵ�list���,�൱��list������±�
				mViewPager.setCurrentItem(0);
				initBottemBtn();
				mMyBottemSearchImg
						.setImageResource(R.drawable.main_index_search_pressed);
				mMyBottemSearchTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
			case R.id.MyBottemTuanBtn:
				mViewPager.setCurrentItem(1);
				initBottemBtn();
				mMyBottemTuanImg
						.setImageResource(R.drawable.main_index_tuan_pressed);
				mMyBottemTuanTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
			case R.id.MyBottemCheckinBtn:
				mViewPager.setCurrentItem(2);
				initBottemBtn();
				mMyBottemCheckinImg
						.setImageResource(R.drawable.main_index_checkin_pressed);
				mMyBottemCheckinTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
			case R.id.MyBottemMyBtn:
				mViewPager.setCurrentItem(3);
				initBottemBtn();
				mMyBottemMyImg
						.setImageResource(R.drawable.main_index_my_pressed);
				mMyBottemMyTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
			case R.id.MyBottemMoreBtn:
				mViewPager.setCurrentItem(4);
				initBottemBtn();
				mMyBottemMoreImg
						.setImageResource(R.drawable.main_index_more_pressed);
				mMyBottemMoreTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
			}

		}

	}

	/**
	 * ��ʼ���ؼ�����ɫ
	 * 
	private void initBottemBtn() {
		mMyBottemSearchImg.setImageResource(R.drawable.search_bottem_search);
		mMyBottemTuanImg.setImageResource(R.drawable.search_bottem_tuan);
		mMyBottemCheckinImg.setImageResource(R.drawable.search_bottem_checkin);
		mMyBottemMyImg.setImageResource(R.drawable.search_bottem_my);
		mMyBottemMoreImg.setImageResource(R.drawable.search_bottem_more);
		mMyBottemSearchTxt.setTextColor(getResources().getColor(
				R.color.search_bottem_textcolor));
		mMyBottemTuanTxt.setTextColor(getResources().getColor(
				R.color.search_bottem_textcolor));
		mMyBottemCheckinTxt.setTextColor(getResources().getColor(
				R.color.search_bottem_textcolor));
		mMyBottemMyTxt.setTextColor(getResources().getColor(
				R.color.search_bottem_textcolor));
		mMyBottemMoreTxt.setTextColor(getResources().getColor(
				R.color.search_bottem_textcolor));
	}

	/**
	 * ���ذ�ť�ļ���������ѯ���û��Ƿ��˳�����
	 * 
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				Builder builder = new Builder(FrameActivity.this);
				builder.setTitle("��ʾ");
				builder.setMessage("��ȷ��Ҫ�˳���");
				builder.setIcon(R.drawable.ic_launcher);

				DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						if (arg1 == DialogInterface.BUTTON_POSITIVE) {
							arg0.cancel();
						} else if (arg1 == DialogInterface.BUTTON_NEGATIVE) {
							FrameActivity.this.finish();
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
}*/