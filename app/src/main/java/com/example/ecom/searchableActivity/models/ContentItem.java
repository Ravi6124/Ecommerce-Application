package com.example.ecom.searchableActivity.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class ContentItem{

	@SerializedName("itemId")
	private Object itemId;

	@SerializedName("productId")
	private String productId;

	@SerializedName("color")
	private String color;

	@SerializedName("size")
	private String size;

	@SerializedName("price")
	private double price;

	@SerializedName("imageURL")
	private String imageURL;

	@SerializedName("description")
	private String description;

	@SerializedName("theme")
	private String theme;

	@SerializedName("categoryName")
	private Object categoryName;

	@SerializedName("productName")
	private String productName;

	@SerializedName("categoryId")
	private String categoryId;

	public void setItemId(Object itemId){
		this.itemId = itemId;
	}

	public Object getItemId(){
		return itemId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setSize(String size){
		this.size = size;
	}

	public String getSize(){
		return size;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setImageURL(String imageURL){
		this.imageURL = imageURL;
	}

	public String getImageURL(){
		return imageURL;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTheme(String theme){
		this.theme = theme;
	}

	public String getTheme(){
		return theme;
	}

	public void setCategoryName(Object categoryName){
		this.categoryName = categoryName;
	}

	public Object getCategoryName(){
		return categoryName;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
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
			"ContentItem{" + 
			"itemId = '" + itemId + '\'' + 
			",productId = '" + productId + '\'' + 
			",color = '" + color + '\'' + 
			",size = '" + size + '\'' + 
			",price = '" + price + '\'' + 
			",imageURL = '" + imageURL + '\'' + 
			",description = '" + description + '\'' + 
			",theme = '" + theme + '\'' + 
			",categoryName = '" + categoryName + '\'' + 
			",productName = '" + productName + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			"}";
		}
}