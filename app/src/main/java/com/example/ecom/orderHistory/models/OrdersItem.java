package com.example.ecom.orderHistory.models;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class OrdersItem{

	@SerializedName("date")
	private String date;

	@SerializedName("totalAmount")
	private double totalAmount;

	@SerializedName("orderId")
	private String orderId;

	@SerializedName("userId")
	private String userId;

	@SerializedName("items")
	private List<ItemsItem> items;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setTotalAmount(double totalAmount){
		this.totalAmount = totalAmount;
	}

	public double getTotalAmount(){
		return totalAmount;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
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
			"OrdersItem{" + 
			"date = '" + date + '\'' + 
			",totalAmount = '" + totalAmount + '\'' + 
			",orderId = '" + orderId + '\'' + 
			",userId = '" + userId + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}