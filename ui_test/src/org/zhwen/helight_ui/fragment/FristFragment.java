package org.zhwen.helight_ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zhwen.helight_ui.R;
import org.zhwen.helight_ui.adapter.FristListViewAdapter;
import org.zhwen.helight_ui.view.XListView;
import org.zhwen.helight_ui.view.XListView.IXListViewListener;
import org.zhwen.helight_ui.utiliys.DataParser;


public class FristFragment extends Fragment implements IXListViewListener {  
	public static final String TAG = "FristFragment";  
	private XListView mineListView;	
	private Handler mHandler;
	private FristListViewAdapter mAdapter;
	
	private DataParser data_pase = new DataParser();
	private List<Map<String, Object>> list_item = new ArrayList<Map<String, Object>>();
	
	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
		Log.d(TAG, "onCreateView"); 
        View mineLayout = inflater.inflate(R.layout.layout1, container, false);  
        
        data_pase.getData(list_item);
		mineListView = (XListView) mineLayout.findViewById(R.id.mineListView);
		mineListView.setPullLoadEnable(true);
        
        mAdapter = new FristListViewAdapter(getActivity(), list_item);
        mineListView.setAdapter(mAdapter);
        mineListView.setXListViewListener(this);
        mHandler = new Handler();
        
        return mineLayout;  
    } 
    
    private void geneItems() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "天天开心");
		map.put("info", "中国最大的SNS社交...");
		map.put("img", R.drawable.logo_kaixin);
		list_item.add(map);
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
				geneItems();
				mAdapter.notifyDataSetChanged();
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
