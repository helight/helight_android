package org.zhwen.helight_ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.zhwen.helight_ui.R;
import org.zhwen.helight_ui.xlistview.XListView;
import org.zhwen.helight_ui.xlistview.XListView.IXListViewListener;

public class MineFragment extends Fragment implements IXListViewListener {  
	
	public final class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView info;
		public Button viewBtn;
	}
	private XListView mineListView;	
	private Handler mHandler;
	private MyAdapter mAdapter;

	private List<Map<String, Object>> list_item = new ArrayList<Map<String, Object>>();
	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
        View mineLayout = inflater.inflate(R.layout.mine_layout, container, false);  

        getData();
		mineListView = (XListView) mineLayout.findViewById(R.id.mineListView);
		mineListView.setPullLoadEnable(true);
        
        mAdapter = new MyAdapter(getActivity());
        mineListView.setAdapter(mAdapter);
        mineListView.setXListViewListener(this);
        mHandler = new Handler();
        
        return mineLayout;  
    } 
    
    private void getData() { 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "天天开心");
        map.put("info", "中国最大的SNS社交...");
        map.put("img", R.drawable.logo_kaixin);
        list_item.add(map);
 
        map = new HashMap<String, Object>();
        map.put("title", "QQ同学");
        map.put("info", "中国浏览量最大的中文门...");
        map.put("img", R.drawable.logo_qq);
        list_item.add(map);
 
        map = new HashMap<String, Object>();
        map.put("title", "明道");
        map.put("info", "为中国企业开发的社会...");
        map.put("img", R.drawable.logo_mingdao);
        list_item.add(map);
    }
      
    // ListView 中某项被选中后的逻辑
    protected void onListItemClick(ListView l, View v, int position, long id) {         
        Log.v("MyListView4-click", (String)list_item.get(position).get("title"));
    }
    private void geneItems() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "天天开心");
		map.put("info", "中国最大的SNS社交...");
		map.put("img", R.drawable.logo_kaixin);
		list_item.add(map);
	}
     
	public class MyAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list_item.size();
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

			// holder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
			holder.img.setImageResource((Integer) list_item.get(position).get("img"));
			holder.title.setText((String) list_item.get(position).get("title"));
			holder.info.setText((String) list_item.get(position).get("info"));

			holder.viewBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.v("MinneFragment", "viewBtn press" + v.getId() + " pos: " + pos);
				}
			});
			return convertView;
		}
	}
	
	private void onLoad() {
		mineListView.stopRefresh();
		mineListView.stopLoadMore();
		mineListView.setRefreshTime("刚刚");
	}
	
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// items.clear();
				geneItems();
				// mAdapter.notifyDataSetChanged();
				mAdapter = new MyAdapter(getActivity());
				mineListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems();
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}
}