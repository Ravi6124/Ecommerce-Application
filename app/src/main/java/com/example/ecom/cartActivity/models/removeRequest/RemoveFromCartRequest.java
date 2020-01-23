package com.example.ecom.cartActivity.models.removeRequest;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class RemoveFromCartRequest{

	@SerializedName("productId")
	private String productId;

	@SerializedName("userId")
	private String userId;

	public RemoveFromCartRequest(String productId, String userId) {
		this.productId = productId;
		this.userId = userId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"RemoveFromCartRequest{" + 
			"productId = '" + productId + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}