package com.example.ecom.cartActivity.adaptor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecom.R;
//import com.example.ecom.cartActivity.models.CartProduct;
import com.example.ecom.RetrofitCart;
import com.example.ecom.RetrofitClass;
import com.example.ecom.cartActivity.CartActivity;
import com.example.ecom.cartActivity.apiInterface.CartInterface;
import com.example.ecom.cartActivity.models.CartProductRevised;
import com.example.ecom.cartActivity.models.ListCartProduct;
import com.example.ecom.cartActivity.models.removeFromCart.RemoveFromCart;
import com.example.ecom.cartActivity.models.removeRequest.RemoveFromCartRequest;
import com.example.ecom.productInfoActivity.apiInterface.ProductInfoInterface;
import com.example.ecom.productInfoActivity.models.CartProduct;
import com.example.ecom.productInfoActivity.models.apiExchanges.AddProductToCartRequest;
import com.example.ecom.productInfoActivity.models.apiExchanges.AddProductToCartResponse;
//import com.example.ecom.productInfoActivity.models.CartProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{

    private List<ListCartProduct> mDataset;
    private CartProductRevised cartProductRevised;
    private Context context;
    private CartInterface cartInterface;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just an integer in this case
        private TextView productName;
        private TextView productPrice;
        private TextView merchantName;
        private ImageView imageView;
        private Button increase;
        private Button decrease;
        private TextView stockValue;
        private MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.productImage);
            productName = v.findViewById(R.id.productText);
            productPrice = v.findViewById(R.id.productPrice);
            merchantName = v.findViewById(R.id.productMerchant);
            increase = v.findViewById(R.id.increase_stock);
            stockValue = v.findViewById(R.id.stock_value);
            decrease = v.findViewById(R.id.decrease_stock);
        }
    }

    public CartAdapter(Context context , CartProductRevised myDataset, CartInterface cartInterface) {
        if(null != myDataset){
        this.mDataset = myDataset.getCartProducts();
        cartProductRevised = myDataset;
        }
        this.context = context;
        this.cartInterface = cartInterface;
    }


    @NonNull
    public CartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orders_recycler_view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.productName.setText(mDataset.get(position).getProductName());
        holder.productPrice.setText(String.valueOf(mDataset.get(position).getPrice()));
        holder.merchantName.setText(mDataset.get(position).getMerchanrId());
        holder.stockValue.setText(String.valueOf(mDataset.get(position).getQuantity()));
        Glide.with(this.context)
                .load(mDataset.get(position).getImageURL())
                .into(holder.imageView);

        // TODO: 2020-01-20 future implementation for clicking on image in cart shows description of the product


        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListCartProduct item =  mDataset.get(position);
//                item.setQuantity(item.getQuantity()+1);
//                if(item.getQuantity()>5)
//                    item.setQuantity(5);
//                mDataset.set(position,item);
//                holder.stockValue.setText(String.valueOf(item.getQuantity()));
                String imageURL = item.getImageURL();
                String productId = item.getProductId();
                String defaultMerchantId = item.getMerchanrId();
                double defaultPrice = item.getPrice();
                String name = item.getProductName();
                CartProduct cartProduct = new CartProduct(1,imageURL,productId,defaultMerchantId,defaultPrice,name);
                AddProductToCartRequest addProductToCart = new AddProductToCartRequest("fakeId",cartProduct);

                // TODO: 2020-01-22 for now different retrofit class for differnet services
//                RetrofitClass retrofitClass = new RetrofitClass();
//                Retrofit retrofit = retrofitClass.getRetrofit();
                RetrofitCart retrofitCart = new RetrofitCart();
                Retrofit retrofit = retrofitCart.getRetrofit();
                ProductInfoInterface productInfoInterface = retrofit.create(ProductInfoInterface.class);

                Call<AddProductToCartResponse> call = productInfoInterface.addItemToCart(addProductToCart);
                call.enqueue(new Callback<AddProductToCartResponse>() {
                    @Override
                    public void onResponse(Call<AddProductToCartResponse> call, Response<AddProductToCartResponse> response) {
                        Log.d("CartResponse","item added to cart");
                        ((Activity)context).finish();
                        context.startActivity(new Intent(context, CartActivity.class));
                    }

                    @Override
                    public void onFailure(Call<AddProductToCartResponse> call, Throwable t) {
                        Log.d("CartResponse","item could not be added");
                    }
                });
            }
        });

        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListCartProduct item = mDataset.get(position);
                item.setQuantity(item.getQuantity()-1);
               // if(item.getQuantity()==0){
                    String productId = mDataset.get(position).getProductId();
                    String userId = cartProductRevised.getCustomerId();
                    RemoveFromCartRequest removeFromCartRequest = new RemoveFromCartRequest(productId,userId);
                    RetrofitClass retrofitClass = new RetrofitClass();
                    Retrofit retrofit = retrofitClass.getRetrofit();
                    com.example.ecom.cartActivity.apiInterface.CartInterface cartInterface = retrofit
                            .create(com.example.ecom.cartActivity.apiInterface.CartInterface.class);
                    Call<RemoveFromCart> call = cartInterface.removeFromCart(removeFromCartRequest);
                    call.enqueue(new Callback<RemoveFromCart>() {
                        @Override
                        public void onResponse(Call<RemoveFromCart> call, Response<RemoveFromCart> response) {
                            Log.d("Cart item","removed");
                            ((Activity)context).finish();
                            context.startActivity(new Intent(context, CartActivity.class));
                        }

                        @Override
                        public void onFailure(Call<RemoveFromCart> call, Throwable t) {
                            Log.d("Cart item","could not be removed: callback failure");
                        }
                    });
               // }
                //item.setQuantity(1);
                mDataset.set(position,item);
                holder.stockValue.setText(String.valueOf(item.getQuantity()));
            }
        });

    }


    public int getItemCount() {
        return mDataset.size();
    }

    public interface CartInterface {
        public  void onClick(TextView stock);
    }

}
