package com.example.ecommerceapplication.cartActivity.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceapplication.R;
import com.example.ecommerceapplication.cartActivity.apiInterface.CartInterface;
import com.example.ecommerceapplication.cartActivity.models.CartProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{

    private List<CartProduct> mDataset;
    private Context context;
    private CartInterface cartInterface;
    //private RecyclerView.OnItemTouchListener onItemTouchListener;

    //public interface  OnItemTouchListener

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just an integer in this case
        private TextView productName;
        private TextView productPrice;
        private TextView merchantName;
        private ImageView imageView;
        private MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.productImage);
            productName = v.findViewById(R.id.productText);
            productPrice = v.findViewById(R.id.productPrice);
            merchantName = v.findViewById(R.id.productMerchant);
        }
    }

    public CartAdapter(Context context , List<CartProduct> myDataset, CartInterface cartInterface) {
//        this.mDataset = new ArrayList<>();
//        mDataset.addAll(myDataset);
        this.mDataset = myDataset;
        this.context = context;
        this.cartInterface = cartInterface;
    }


    @NonNull
    public CartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.landing_recycler_view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.productName.setText(mDataset.get(position).getProductName());
        holder.productPrice.setText(String.valueOf(mDataset.get(position).getPrice()));
        holder.merchantName.setText(mDataset.get(position).getMerchantName());
        Glide.with(this.context)
                .load(mDataset.get(position).getImageURL())
                .into(holder.imageView);

        // TODO: 2020-01-20 future implementation for clicking on image in cart shows description of the product

//        holder.imageView.getRootView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                categoryInterface.onClick(mDataset.get(position));
//            }
//        });

    }


    public int getItemCount() {
        return mDataset.size();
    }

    public interface CartInterface {
        public  void onClick(CartProduct cartProduct);
    }

}
