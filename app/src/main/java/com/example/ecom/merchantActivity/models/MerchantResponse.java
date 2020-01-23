package com.example.ecom.merchantActivity.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class MerchantResponse{

	@SerializedName("cost")
	private double cost;

	@SerializedName("productId")
	private String productId;

	@SerializedName("color")
	private String color;

	@SerializedName("size")
	private String size;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("merchantRating")
	private double merchantRating;

	@SerializedName("theme")
	private String theme;

	@SerializedName("productRating")
	private double productRating;

	@SerializedName("merchantName")
	private String merchantName;

	public void setCost(double cost){
		this.cost = cost;
	}

	public double getCost(){
		return cost;
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

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setMerchantRating(double merchantRating){
		this.merchantRating = merchantRating;
	}

	public double getMerchantRating(){
		return merchantRating;
	}

	public void setTheme(String theme){
		this.theme = theme;
	}

	public String getTheme(){
		return theme;
	}

	public void setProductRating(double productRating){
		this.productRating = productRating;
	}

	public double getProductRating(){
		return productRating;
	}

	public void setMerchantName(String merchantName){
		this.merchantName = merchantName;
	}

	public String getMerchantName(){
		return merchantName;
	}

	@Override
 	public String toString(){
		return 
			"MerchantResponse{" + 
			"cost = '" + cost + '\'' + 
			",productId = '" + productId + '\'' + 
			",color = '" + color + '\'' + 
			",size = '" + size + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",merchantRating = '" + merchantRating + '\'' + 
			",theme = '" + theme + '\'' + 
			",productRating = '" + productRating + '\'' + 
			",merchantName = '" + merchantName + '\'' + 
			"}";
		}
}