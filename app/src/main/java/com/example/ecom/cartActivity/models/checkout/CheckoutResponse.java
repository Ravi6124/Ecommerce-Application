package com.example.ecom.cartActivity.models.checkout;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class CheckoutResponse{

	@SerializedName("orderId")
	private String orderId;

	@SerializedName("unavailableStock")
	private UnavailableStock unavailableStock;

	@SerializedName("status")
	private boolean status;

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setUnavailableStock(UnavailableStock unavailableStock){
		this.unavailableStock = unavailableStock;
	}

	public UnavailableStock getUnavailableStock(){
		return unavailableStock;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"CheckoutResponse{" + 
			"orderId = '" + orderId + '\'' + 
			",unavailableStock = '" + unavailableStock + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}