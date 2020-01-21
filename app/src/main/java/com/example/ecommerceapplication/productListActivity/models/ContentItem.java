package com.example.ecommerceapplication.productListActivity.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class ContentItem{

	@SerializedName("defaultPrice")
	private double defaultPrice;

	@SerializedName("productId")
	private String productId;

	@SerializedName("imageURL")
	private String imageURL;

	@SerializedName("totalStock")
	private int totalStock;

	@SerializedName("description")
	private String description;

	@SerializedName("categoryId")
	private String categoryId;

	@SerializedName("productName")
	private String productName;

	@SerializedName("defaultMerchantId")
	private String defaultMerchantId;

	public void setDefaultPrice(double defaultPrice){
		this.defaultPrice = defaultPrice;
	}

	public double getDefaultPrice(){
		return defaultPrice;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setImageURL(String imageURL){
		this.imageURL = imageURL;
	}

	public String getImageURL(){
		return imageURL;
	}

	public void setTotalStock(int totalStock){
		this.totalStock = totalStock;
	}

	public int getTotalStock(){
		return totalStock;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setDefaultMerchantId(String defaultMerchantId){
		this.defaultMerchantId = defaultMerchantId;
	}

	public String getDefaultMerchantId(){
		return defaultMerchantId;
	}

	@Override
 	public String toString(){
		return 
			"ContentItem{" + 
			"defaultPrice = '" + defaultPrice + '\'' + 
			",productId = '" + productId + '\'' + 
			",imageURL = '" + imageURL + '\'' + 
			",totalStock = '" + totalStock + '\'' + 
			",description = '" + description + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			",productName = '" + productName + '\'' + 
			",defaultMerchantId = '" + defaultMerchantId + '\'' + 
			"}";
		}
}