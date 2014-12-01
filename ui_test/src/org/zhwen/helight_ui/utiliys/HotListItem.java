package org.zhwen.helight_ui.utiliys;

public class HotListItem {

	private int logo;
	private String title;
	private String info;
	private String detail;
	private int img_but;

	public HotListItem(int logo, String title, String info, String detail, int img_but) {
		this.logo = logo;
		this.title = title;
		this.info = info;
		this.detail = detail;
		this.img_but = img_but;
	}

	public int getLogo() {
		return this.logo;
	}
	public String getTitle() {
		return this.title;
	}
	public String getInfo() {
		return this.info;
	}
	public String getDetail() {
		return this.detail;
	}
	public int getImg_but() {
		return this.img_but;
	}
}