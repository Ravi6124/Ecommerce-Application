package com.example.ecommerceapplication.Pojos;

import com.google.gson.annotations.SerializedName;


public class Category{

	@SerializedName("imageURL")
	private String imageURL;

	@SerializedName("name")
	private String name;

	@SerializedName("categoryId")
	private String categoryId;

	public void setImageURL(String imageURL){
		this.imageURL = imageURL;
	}

	public String getImageURL(){
		return imageURL;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	@Override
 	public String toString(){
		return 
			"Category{" + 
			"imageURL = '" + imageURL + '\'' + 
			",name = '" + name + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			"}";
		}
}