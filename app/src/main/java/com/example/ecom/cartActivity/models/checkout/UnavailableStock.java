package com.example.ecom.cartActivity.models.checkout;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class UnavailableStock{

	@SerializedName("stock")
	private String stock;

	@SerializedName("productName")
	private String productName;

	public void setStock(String stock){
		this.stock = stock;
	}

	public String getStock(){
		return stock;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	@Override
 	public String toString(){
		return 
			"UnavailableStock{" + 
			"stock = '" + stock + '\'' + 
			",productName = '" + productName + '\'' + 
			"}";
		}
}