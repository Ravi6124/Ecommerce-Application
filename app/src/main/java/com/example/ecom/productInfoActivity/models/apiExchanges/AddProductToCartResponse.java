package com.example.ecom.productInfoActivity.models.apiExchanges;

import com.google.gson.annotations.SerializedName;


public class AddProductToCartResponse{

	@SerializedName("resultCode")
	private int resultCode;

	@SerializedName("cart")
	private Cart cart;

	public AddProductToCartResponse(int resultCode, Cart cart) {
		this.resultCode = resultCode;
		this.cart = cart;
	}

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
			"AddProductToCartResponse{" + 
			"resultCode = '" + resultCode + '\'' + 
			",cart = '" + cart + '\'' + 
			"}";
		}
}