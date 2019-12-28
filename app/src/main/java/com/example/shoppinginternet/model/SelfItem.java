package com.example.shoppinginternet.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SelfItem{

	@SerializedName("href")
	private String href;

	public void setHref(String href){
		this.href = href;
	}

	public String getHref(){
		return href;
	}

	@Override
 	public String toString(){
		return 
			"SelfItem{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}