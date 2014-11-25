package org.zhwen.helight_ui.utiliys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zhwen.helight_ui.R;

/**
 * @Description: 解析数据列表，这里只是个示例，具体地不再实现。

 * @File: NewsXmlParser.java
 * @Version V1.0
 */
public class DataParser {
	// 新闻列表
	// private List<HashMap<String, News>> newsList = null;
	
	// 滑动图片的集合，这里设置成了固定加载，当然也可动态加载。
	private int[] slideImages = {
			R.drawable.image01,
			R.drawable.image02,
			R.drawable.image03,
			R.drawable.image04,
			R.drawable.image05};
	
	// 滑动标题的集合
	private String[] slideTitles = {
			"R.string.title1",
			"R.string.title2",
			"R.string.title3",
			"R.string.title4",
			"R.string.title5",
	};
	
	// 滑动链接的集合
	private String[] slideUrls = {
			"http://zhwen.org/?page_id=713",
			"http://zhwen.org/?page_id=718",
			"http://zhwen.org/?page_id=716",
			"http://zhwen.org/?p=830",
			"http://zhwen.org/?p=821",
	};
    
	public void getData(List<Map<String, Object>> list) {
        // List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 
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
    }
	
	public int[] getSlideImages(){
		return slideImages;
	}
	
	public String[] getSlideTitles(){
		return slideTitles;
	}
	
	public String[] getSlideUrls(){
		return slideUrls;
	}
}
