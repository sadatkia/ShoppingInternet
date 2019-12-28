package com.example.shoppinginternet.Model;


public class GalleryItem{

	private String urlS;
	private String id;
	private String title;

	public String getUrlS() {
		return urlS;
	}

	public void setUrlS(String urlS) {
		this.urlS = urlS;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public GalleryItem(String urlS, String id, String title) {
		this.urlS = urlS;
		this.id = id;
		this.title = title;
	}

	public GalleryItem() {
	}
}