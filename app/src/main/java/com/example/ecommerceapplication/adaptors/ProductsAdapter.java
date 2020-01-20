package com.example.ecommerceapplication.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceapplication.R;
import com.example.ecommerceapplication.api.ProductInterface;
import com.example.ecommerceapplication.pojos.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private List<Product> mDataset;
    private Context context;
    private ProductInterface productInterface;
    //private CategoryInterface categoryInterface;
    //private RecyclerView.OnItemTouchListener onItemTouchListener;

    //public interface  OnItemTouchListener

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just an integer in this case
        public TextView textView;
        public TextView textViewPrice;
        public ImageView imageView;
        public MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.productImage);
            textView = v.findViewById(R.id.productText);
            textViewPrice = v.findViewById(R.id.productPrice);
        }
    }

    public ProductsAdapter(Context context , List<Product> myDataset, ProductInterface productInterface) {
        mDataset = myDataset;
        this.context = context;
        this.productInterface = productInterface;
    }

    public ProductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.landing_recycler_view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset.get(position).getName());
        holder.textViewPrice.setText(String.valueOf(mDataset.get(position).getDefaultPrice()));
        Glide.with(this.context)
                .load(mDataset.get(position).getImageURL())
                .into(holder.imageView);

        //click funcionality

        holder.imageView.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productInterface.onClick(mDataset.get(position));
            }
        });

    }


    public int getItemCount() {
        return mDataset.size();
    }

    public interface ProductInterface {
        public  void onClick(Product product);
    }
}
