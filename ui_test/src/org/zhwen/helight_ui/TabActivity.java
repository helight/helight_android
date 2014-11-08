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
 * 项目的主Activity，所有的Fragment都嵌入在这里。 
 *  
 */  
public class TabActivity extends Activity implements OnClickListener {  
  
    /** 
     * 用于展示消息的Fragment 
     */  
    private MessageFragment messageFragment;  
  
    /** 
     * 用于展示联系人的Fragment 
     */  
    private ContactsFragment contactsFragment;  
  
    /** 
     * 用于展示动态的Fragment 
     */  
    private NewsFragment newsFragment;  
  
    /** 
     * 用于展示设置的Fragment 
     */  
    private SettingFragment settingFragment;  
  
    /** 
     * 消息界面布局 
     */  
    private View messageLayout;  
  
    /** 
     * 联系人界面布局 
     */  
    private View contactsLayout;  
  
    /** 
     * 动态界面布局 
     */  
    private View newsLayout;  
  
    /** 
     * 设置界面布局 
     */  
    private View settingLayout;  
  
    /** 
     * 在Tab布局上显示消息图标的控件 
     */  
    private ImageView messageImage;  
  
    /** 
     * 在Tab布局上显示联系人图标的控件 
     */  
    private ImageView contactsImage;  
  
    /** 
     * 在Tab布局上显示动态图标的控件 
     */  
    private ImageView newsImage;  
  
    /** 
     * 在Tab布局上显示设置图标的控件 
     */  
    private ImageView settingImage;  
  
    /** 
     * 在Tab布局上显示消息标题的控件 
     */  
    private TextView messageText;  
  
    /** 
     * 在Tab布局上显示联系人标题的控件 
     */  
    private TextView contactsText;  
  
    /** 
     * 在Tab布局上显示动态标题的控件 
     */  
    private TextView newsText;  
  
    /** 
     * 在Tab布局上显示设置标题的控件 
     */  
    private TextView settingText;  
  
    /** 
     * 用于对Fragment进行管理 
     */  
    private FragmentManager fragmentManager;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.frame_test);  
        // 初始化布局元素  
        initViews();  
        fragmentManager = getFragmentManager();  
        // 第一次启动时选中第0个tab  
        setTabSelection(0);  
    }  
  
    /** 
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。 
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
            // 当点击了消息tab时，选中第1个tab  
            setTabSelection(0);  
            break;  
        case R.id.contacts_layout:  
            // 当点击了联系人tab时，选中第2个tab  
            setTabSelection(1);  
            break;  
        case R.id.news_layout:  
            // 当点击了动态tab时，选中第3个tab  
            setTabSelection(2);  
            break;  
        case R.id.setting_layout:  
            // 当点击了设置tab时，选中第4个tab  
            setTabSelection(3);  
            break;  
        default:  
            break;  
        }  
    }  
  
    /** 
     * 根据传入的index参数来设置选中的tab页。 
     *  
     * @param index 
     *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。 
     */  
    private void setTabSelection(int index) {  
        // 每次选中之前先清楚掉上次的选中状态  
        clearSelection();  
        // 开启一个Fragment事务  
        FragmentTransaction transaction = fragmentManager.beginTransaction();  
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况  
        hideFragments(transaction);  
        switch (index) {  
        case 0:  
            // 当点击了消息tab时，改变控件的图片和文字颜色  
            messageImage.setImageResource(R.drawable.message_selected);  
            messageText.setTextColor(Color.WHITE);  
            if (messageFragment == null) {  
                // 如果MessageFragment为空，则创建一个并添加到界面上  
                messageFragment = new MessageFragment();  
                transaction.add(R.id.content, messageFragment);  
            } else {  
                // 如果MessageFragment不为空，则直接将它显示出来  
                transaction.show(messageFragment);  
            }  
            break;  
        case 1:  
            // 当点击了联系人tab时，改变控件的图片和文字颜色  
            contactsImage.setImageResource(R.drawable.contacts_selected);  
            contactsText.setTextColor(Color.WHITE);  
            if (contactsFragment == null) {  
                // 如果ContactsFragment为空，则创建一个并添加到界面上  
                contactsFragment = new ContactsFragment();  
                transaction.add(R.id.content, contactsFragment);  
            } else {  
                // 如果ContactsFragment不为空，则直接将它显示出来  
                transaction.show(contactsFragment);  
            }  
            break;  
        case 2:  
            // 当点击了动态tab时，改变控件的图片和文字颜色  
            newsImage.setImageResource(R.drawable.news_selected);  
            newsText.setTextColor(Color.WHITE);  
            if (newsFragment == null) {  
                // 如果NewsFragment为空，则创建一个并添加到界面上  
                newsFragment = new NewsFragment();  
                transaction.add(R.id.content, newsFragment);  
            } else {  
                // 如果NewsFragment不为空，则直接将它显示出来  
                transaction.show(newsFragment);  
            }  
            break;  
        case 3:  
        default:  
            // 当点击了设置tab时，改变控件的图片和文字颜色  
            settingImage.setImageResource(R.drawable.setting_selected);  
            settingText.setTextColor(Color.WHITE);  
            if (settingFragment == null) {  
                // 如果SettingFragment为空，则创建一个并添加到界面上  
                settingFragment = new SettingFragment();  
                transaction.add(R.id.content, settingFragment);  
            } else {  
                // 如果SettingFragment不为空，则直接将它显示出来  
                transaction.show(settingFragment);  
            }  
            break;  
        }  
        transaction.commit();  
    }  
  
    /** 
     * 清除掉所有的选中状态。 
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
     * 将所有的Fragment都置为隐藏状态。 
     *  
     * @param transaction 
     *            用于对Fragment执行操作的事务 
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
	private List<View> list = new ArrayList<View>();// 相当于数据源
	
	private View view = null;
	private View view1 = null;
	private View view2 = null;
	private View view3 = null;
	private View view4 = null;
	
	private android.support.v4.view.ViewPager mViewPager;
	private PagerAdapter pagerAdapter = null;// 数据源和viewpager之间的桥梁
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setContentView(R.layout.activity_frame);
		//initView();
	}

	// 初始化控件
	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.FramePager);
		// 查找以linearlayout为按钮作用的控件
		mMyBottemSearchBtn = (LinearLayout) findViewById(R.id.MyBottemSearchBtn);
		mMyBottemTuanBtn = (LinearLayout) findViewById(R.id.MyBottemTuanBtn);
		mMyBottemCheckinBtn = (LinearLayout) findViewById(R.id.MyBottemCheckinBtn);
		mMyBottemMyBtn = (LinearLayout) findViewById(R.id.MyBottemMyBtn);
		mMyBottemMoreBtn = (LinearLayout) findViewById(R.id.MyBottemMoreBtn);
		// 查找linearlayout中的imageview
		mMyBottemSearchImg = (ImageView) findViewById(R.id.MyBottemSearchImg);
		mMyBottemTuanImg = (ImageView) findViewById(R.id.MyBottemTuanImg);
		mMyBottemCheckinImg = (ImageView) findViewById(R.id.MyBottemCheckinImg);
		mMyBottemMyImg = (ImageView) findViewById(R.id.MyBottemMyImg);
		mMyBottemMoreImg = (ImageView) findViewById(R.id.MyBottemMoreImg);
		// 查找linearlayout中的textview
		mMyBottemSearchTxt = (TextView) findViewById(R.id.MyBottemSearchTxt);
		mMyBottemTuanTxt = (TextView) findViewById(R.id.MyBottemTuanTxt);
		mMyBottemCheckinTxt = (TextView) findViewById(R.id.MyBottemCheckinTxt);
		mMyBottemMyTxt = (TextView) findViewById(R.id.MyBottemMyTxt);
		mMyBottemMoreTxt = (TextView) findViewById(R.id.MyBottemMoreTxt);
		createView();
		// 写一个内部类pageradapter
		pagerAdapter = new PagerAdapter() {
			// 判断再次添加的view和之前的view 是否是同一个view
			// arg0新添加的view，arg1之前的
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			// 返回数据源长度
			@Override
			public int getCount() {
				return list.size();
			}

			// 销毁被滑动走的view
			
			 * ViewGroup 代表了我们的viewpager 相当于activitygroup当中的view容器， 添加之前先移除。
			 * position 第几条数据 Object 被移出的view
			 * 
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// 移除view
				container.removeView(list.get(position));
			}

			/**
			 * instantiateItem viewpager要现实的view ViewGroup viewpager position
			 * 第几条数据
			 * 
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// 获取view添加到容器当中，并返回
				View v = list.get(position);
				container.addView(v);
				return v;
			}
		};
		// 设置我们的adapter
		mViewPager.setAdapter(pagerAdapter);

		MyBtnOnclick mytouchlistener = new MyBtnOnclick();
		mMyBottemSearchBtn.setOnClickListener(mytouchlistener);
		mMyBottemTuanBtn.setOnClickListener(mytouchlistener);
		mMyBottemCheckinBtn.setOnClickListener(mytouchlistener);
		mMyBottemMyBtn.setOnClickListener(mytouchlistener);
		mMyBottemMoreBtn.setOnClickListener(mytouchlistener);

		// 设置viewpager界面切换监听,监听viewpager切换第几个界面以及滑动的
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			// arg0 获取 viewpager里面的界面切换到第几个的
			@Override
			public void onPageSelected(int arg0) {
				// 先清除按钮样式
				initBottemBtn();
				// 按照对应的view的tag来判断到底切换到哪个界面。
				// 更改对应的button状态
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
			 * 监听页面滑动的 arg0 表示当前滑动的view arg1 表示滑动的百分比 arg2 表示滑动的距离
			 * 
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			/**
			 * 监听滑动状态 arg0 表示我们的滑动状态 0:默认状态 1:滑动状态 2:滑动停止
			 * 
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	// 把viewpager里面要显示的view实例化出来，并且把相关的view添加到一个list当中
	private void createView() {
		view = this
				.getLocalActivityManager()
				.startActivity("search",
						new Intent(FrameActivity.this, SearchActivity.class))
				.getDecorView();
		// 用来更改下面button的样式的标志
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
	 * 用linearlayout作为按钮的监听事件
	 * 
	private class MyBtnOnclick implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			int mBtnid = arg0.getId();
			switch (mBtnid) {
			case R.id.MyBottemSearchBtn:
				// //设置我们的viewpager跳转那个界面0这个参数和我们的list相关,相当于list里面的下标
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
	 * 初始化控件的颜色
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
	 * 返回按钮的监听，用来询问用户是否退出程序
	 * 
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				Builder builder = new Builder(FrameActivity.this);
				builder.setTitle("提示");
				builder.setMessage("你确定要退出吗？");
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
				builder.setPositiveButton("取消", dialog);
				builder.setNegativeButton("确定", dialog);
				AlertDialog alertDialog = builder.create();
				alertDialog.show();

			}
		}
		return false;
	}
}*/