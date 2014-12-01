package org.zhwen.helight_ui.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.zhwen.helight_ui.R;
import org.zhwen.helight_ui.utiliys.HotListItem;

public class HotListViewAdapter extends BaseAdapter {
	
	public final class ViewHolder {
		public ImageView logo;
		public TextView title;
		public TextView info;
		public TextView detail;
		public ImageView img_but;
	}

	private LayoutInflater mInflater;
	private Context context;
	private List<HotListItem> mlist;
	
	public HotListViewAdapter(Context context) {
		this.context = context;
		this.mInflater = LayoutInflater.from(this.context);
	}

	public void setList(List<HotListItem> list) {
		this.mlist = list;
	}
	@Override
	public int getCount() {
		if (mlist != null) {
			Log.v("HotListViewAdapter", "mlist size: " + mlist.size());
			return mlist.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		if (mlist != null && arg0 < mlist.size()) {
			return mlist.get(arg0);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.date_item, null);
			holder.logo = (ImageView) convertView.findViewById(R.id.logo);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.info = (TextView) convertView.findViewById(R.id.info);
			holder.detail = (TextView) convertView.findViewById(R.id.detail);
			holder.img_but = (ImageView) convertView.findViewById(R.id.right_arrow);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.logo.setImageResource((Integer) mlist.get(position).getLogo());
		holder.title.setText((String) mlist.get(position).getTitle());
		holder.info.setText((String) mlist.get(position).getInfo());
		holder.detail.setText((String) mlist.get(position).getDetail());
		holder.img_but.setImageResource((Integer) mlist.get(position).getImg_but());

		return convertView;
	}
}


