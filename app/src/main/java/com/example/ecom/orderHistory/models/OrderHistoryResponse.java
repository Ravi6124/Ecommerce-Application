package com.example.ecom.orderHistory.models;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class OrderHistoryResponse {

	@SerializedName("orders")
	private List<OrdersItem> orders;

	public void setOrders(List<OrdersItem> orders){
		this.orders = orders;
	}

	public List<OrdersItem> getOrders(){
		return orders;
	}

	@Override
 	public String toString(){
		return 
			"OrderHistoryResponse{" +
			"orders = '" + orders + '\'' + 
			"}";
		}
}