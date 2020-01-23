package com.example.ecom.productInfoActivity.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class CartProduct {

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private String productId;

	@SerializedName("imageURL")
	private String imageURL;

	@SerializedName("productName")
	private String productName;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("price")
	private double price;

	public CartProduct(int quantity, String imageURL, String productId, String merchantId, double price,String productName) {
		this.quantity = quantity;
		this.productId = productId;
		this.merchantId = merchantId;
		this.price = price;
		this.productName= productName;
		this.imageURL=imageURL;
	}

	public CartProduct() {
	}



	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	@Override
	public String toString() {
		return "CartProduct{" +
				"quantity=" + quantity +
				", productId='" + productId + '\'' +
				", imageURL='" + imageURL + '\'' +
				", productName='" + productName + '\'' +
				", merchantId='" + merchantId + '\'' +
				", price=" + price +
				'}';
	}
}