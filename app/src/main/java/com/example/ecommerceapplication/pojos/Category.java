package com.example.ecommerceapplication.pojos;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//@Generated("com.robohorse.robopojogenerator")
public class Category {

	@SerializedName("imageURL")
	private String imageURL;

	@SerializedName("name")
	private String name;

	@SerializedName("categoryId")
	private String categoryId;

	@SerializedName("attributeList")
	private List<String> attributeList;

	public List<String> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<String> attributeList) {
		this.attributeList = attributeList;
	}

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