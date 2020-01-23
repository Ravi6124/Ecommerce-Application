package com.example.ecom.productInfoActivity.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CartResponse{

	@SerializedName("totalAmount")
	private double totalAmount;

	@SerializedName("customerId")
	private String customerId;

	@SerializedName("items")
	private List<ItemsItem> items;



	public void setTotalAmount(double totalAmount){
		this.totalAmount = totalAmount;
	}

	public double getTotalAmount(){
		return totalAmount;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"CartResponse{" + 
			"totalAmount = '" + totalAmount + '\'' + 
			",customerId = '" + customerId + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}