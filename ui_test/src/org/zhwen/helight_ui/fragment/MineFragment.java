package org.zhwen.helight_ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
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

public class MineFragment extends Fragment {  

	private ListView list_view;
	private List<Map<String, Object>> mData;

	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
        View mineLayout = inflater.inflate(R.layout.mine_layout, container, false);  
        list_view = (ListView) mineLayout.findViewById(R.id.minelistView);  
        mData = getData();
        MyAdapter adapter = new MyAdapter(getActivity());
        list_view.setAdapter(adapter);
        return mineLayout;  
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

			// holder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
			holder.img.setImageResource((Integer) mData.get(position).get("img"));
			holder.title.setText((String) mData.get(position).get("title"));
			holder.info.setText((String) mData.get(position).get("info"));

			holder.viewBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showInfo();
				}
			});

			return convertView;
		}
	}
}