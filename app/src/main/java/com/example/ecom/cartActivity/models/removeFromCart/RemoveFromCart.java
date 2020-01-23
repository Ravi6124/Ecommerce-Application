package com.example.ecom.cartActivity.models.removeFromCart;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class RemoveFromCart{

	@SerializedName("resultCode")
	private int resultCode;

	@SerializedName("cart")
	private Cart cart;

	public void setResultCode(int resultCode){
		this.resultCode = resultCode;
	}

	public int getResultCode(){
		return resultCode;
	}

	public void setCart(Cart cart){
		this.cart = cart;
	}

	public Cart getCart(){
		return cart;
	}

	@Override
 	public String toString(){
		return 
			"RemoveFromCart{" + 
			"resultCode = '" + resultCode + '\'' + 
			",cart = '" + cart + '\'' + 
			"}";
		}
}