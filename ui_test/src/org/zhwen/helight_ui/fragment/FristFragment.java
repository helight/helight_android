
package org.zhwen.helight_ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.zhwen.helight_ui.R;
import org.zhwen.helight_ui.adapter.HotListViewAdapter;
import org.zhwen.helight_ui.utiliys.HotListItem;

public class FristFragment extends Fragment {  
	public static final String TAG = "FristFragment"; 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	Log.d(TAG, "onCreateView"); 
        View dateLayout = inflater.inflate(R.layout.layout1, container, false);       
        ListView listView = (ListView) dateLayout.findViewById(R.id.hotlistView);
        
        HotListViewAdapter listAdapter = new HotListViewAdapter(getActivity());
		listView.setAdapter(listAdapter);

		List<HotListItem> list = new ArrayList<HotListItem>();

		for (int i = 0; i < 5; i++) {
			HotListItem item = new HotListItem(R.drawable.logo_kaixin, "天天开心" + i
					, "中国最大的SNS社交网站" + i, "中国最大的SNS社交网站" + i, R.drawable.icon_right1);
			list.add(item);
		}
		listAdapter.setList(list);
		setListViewHeightBasedOnChildren(listView);
		
		listAdapter.notifyDataSetChanged();
		
        return dateLayout;  
    }  

	public void setListViewHeightBasedOnChildren(ListView listView) {

		HotListViewAdapter listAdapter = (HotListViewAdapter)listView.getAdapter();

		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;

		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();

		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

		// params.height += 5;// if without this statement,the listview will be
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度

		listView.setLayoutParams(params);
	}
	
    @Override  
    public void onAttach(Activity activity) {  
        super.onAttach(activity);  
        Log.d(TAG, "onAttach");  
    }  
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        Log.d(TAG, "onCreate");  
    }  
  
    @Override  
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
        Log.d(TAG, "onActivityCreated");  
    }  
  
    @Override  
    public void onStart() {  
        super.onStart();  
        Log.d(TAG, "onStart");  
    }  
  
    @Override  
    public void onResume() {  
        super.onResume();  
        Log.d(TAG, "onResume");  
    }  
  
    @Override  
    public void onPause() {  
        super.onPause();  
        Log.d(TAG, "onPause");  
    }  
  
    @Override  
    public void onStop() {  
        super.onStop();  
        Log.d(TAG, "onStop");  
    }  
  
    @Override  
    public void onDestroyView() {  
        super.onDestroyView();  
        Log.d(TAG, "onDestroyView");  
    }  
  
    @Override  
    public void onDestroy() {  
        super.onDestroy();  
        Log.d(TAG, "onDestroy");  
    }  
  
    @Override  
    public void onDetach() {  
        super.onDetach();  
        Log.d(TAG, "onDetach");  
    }  
}  