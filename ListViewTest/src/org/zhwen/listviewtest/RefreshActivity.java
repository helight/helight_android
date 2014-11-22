package org.zhwen.listviewtest;

import java.util.ArrayList;  
import java.util.Arrays;  
import android.app.Activity;  
import android.os.Bundle;  
import android.os.Handler;  
import android.support.v4.widget.SwipeRefreshLayout;  
import android.util.Log;  
import android.widget.ArrayAdapter;  
import android.widget.ListView;  
  
public class RefreshActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener  
{  

	private ArrayAdapter<String> mAdapter;
	private SwipeRefreshLayout mSwipeLayout;
	private ListView mListView;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> getData() {
		list.add("Hello");
		list.add("This is stormzhang");
		list.add("An Android Developer");
		list.add("Love Open Source");
		list.add("My GitHub: stormzhang");
		list.add("weibo: googdev");
		return list;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListView = (ListView) findViewById(R.id.listview);
		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		

		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData());
		mListView.setAdapter(mAdapter);
		
		mSwipeLayout.setOnRefreshListener(this);
		mSwipeLayout.setColorSchemeResources(
				android.R.color.holo_blue_bright, android.R.color.holo_green_light, 
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
	}	

	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// mSwipeLayout.setRefreshing(false);
				list.addAll(Arrays.asList("Lucene", "Canvas", "Bitmap"));
				mAdapter.notifyDataSetChanged();
				mSwipeLayout.setRefreshing(false);
			}
		}, 2000);
	}
}  
