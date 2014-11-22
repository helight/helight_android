package org.zhwen.bannerview;

import org.zhwen.bannerview.R;

/**
 * 解析新闻数据列表
 * @Description: 解析新闻数据列表，这里只是个示例，具体地不再实现。

 * @File: NewsXmlParser.java
 * @Version V1.0
 */
public class NewsXmlParser {
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
			"http://mobile.csdn.net/a/20120616/2806676.html",
			"http://cloud.csdn.net/a/20120614/2806646.html",
			"http://mobile.csdn.net/a/20120613/2806603.html",
			"http://news.csdn.net/a/20120612/2806565.html",
			"http://mobile.csdn.net/a/20120615/2806659.html",
	};
	
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
