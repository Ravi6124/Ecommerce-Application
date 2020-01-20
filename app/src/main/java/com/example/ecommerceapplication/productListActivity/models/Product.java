package com.example.ecommerceapplication.productListActivity.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class Product {

	@SerializedName("defaultPrice")
	private double defaultPrice;

	@SerializedName("productId")
	private String productId;

//	@SerializedName("averageProductRating")
//	private double averageProductRating;

	@SerializedName("imageURL")
	private String imageURL;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("categoryId")
	private String categoryId;

	@SerializedName("defaultMerchantId")
	private String defaultMerchantId;

	@SerializedName("totalStock")
	private int totalStock;

//	@SerializedName("numberOfRatings")
//	private int numberOfRatings;



	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public String getDefaultMerchantId() {
		return defaultMerchantId;
	}

	public void setDefaultMerchantId(String defaultMerchantId) {
		this.defaultMerchantId = defaultMerchantId;
	}

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

//	public void setAverageProductRating(double averageProductRating){
//		this.averageProductRating = averageProductRating;
//	}
//
//	public double getAverageProductRating(){
//		return averageProductRating;
//	}

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

	@Override
	public String toString() {
		return "Product{" +
				"defaultPrice=" + defaultPrice +
				", productId='" + productId + '\'' +
				", imageURL='" + imageURL + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", categoryId='" + categoryId + '\'' +
				", defaultMerchantId='" + defaultMerchantId + '\'' +
				", totalStock=" + totalStock +
				'}';
	}

//	public void setNumberOfRatings(int numberOfRatings){
//		this.numberOfRatings = numberOfRatings;
//	}
//
//	public int getNumberOfRatings(){
//		return numberOfRatings;
//	}

}