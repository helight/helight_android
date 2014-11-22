package org.zhwen.listviewtest;

import java.io.Serializable;
import android.widget.ImageView;

public class HomeGalleryProject implements Serializable {
	private static final long serialVersionUID = 1L;
	public String imageUrl;// 广告图片地址\
	public int img;
	public String adUrl;// 广告跳转地址

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAdUrl() {
		return adUrl;
	}

	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

}
