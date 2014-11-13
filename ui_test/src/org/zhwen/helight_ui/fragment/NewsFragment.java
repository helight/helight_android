package org.zhwen.helight_ui.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.zhwen.helight_ui.R;


public class NewsFragment extends Fragment {  
  
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        View newsLayout = inflater.inflate(R.layout.news_layout, container, false);  
        WebView webView = (WebView) newsLayout.findViewById(R.id.news_web);
     
        webView.getSettings().setJavaScriptEnabled(true);//设置使用够执行JS脚本
		webView.getSettings().setBuiltInZoomControls(true);//设置使支持缩放
//		webView.getSettings().setDefaultFontSize(5);
		
		String url = "http://zhwen.org";
		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);// 使用当前WebView处理跳转
				return true;//true表示此事件在此处被处理，不需要再广播
			}
			@Override	//转向错误时的处理
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
			}
		});
        return newsLayout;  
    }  
  
}  