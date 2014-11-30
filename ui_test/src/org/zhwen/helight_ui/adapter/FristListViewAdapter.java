package org.zhwen.helight_ui.adapter;

import java.util.List;
import java.util.Map;

import org.zhwen.helight_ui.ItemDetailActivity;
import org.zhwen.helight_ui.R;

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

public class FristListViewAdapter extends BaseAdapter {
	public final class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView info;
		public Button viewBtn;
	}

	private LayoutInflater mInflater;
	private Context context;
	private List<Map<String, Object>> mData;
	
	public FristListViewAdapter(Context context, List<Map<String, Object>> data) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		this.mData = data;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		if (null != mData)
			return mData.get(arg0);
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
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

		holder.img.setImageResource((Integer) mData.get(position).get("img"));
		holder.title.setText((String) mData.get(position).get("title"));
		holder.info.setText((String) mData.get(position).get("info"));

		holder.viewBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v("ShowFragment", "viewBtn press" + v.getId() + " pos: " + pos);
				Intent intent = new Intent(context, ItemDetailActivity.class);
				intent.putExtra("title", (String)mData.get(pos).get("title"));
				intent.putExtra("info", (String)mData.get(pos).get("info"));
				// intent.putExtra("investitem", mInvestListItem);
				// intent.putExtra("position", InvestListAdapter.myPosition);
				// intent.putExtra("status", InvestListAdapter.myStats);
				// intent.putExtra("codevo", paramObject);
				context.startActivity(intent);
			}
		});
		return convertView;
	}
}