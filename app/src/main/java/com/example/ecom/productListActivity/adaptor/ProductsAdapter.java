package com.example.ecom.productListActivity.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecom.R;
import com.example.ecom.productListActivity.models.ContentItem;
//import com.example.ecommerceapplication.productListActivity.models.Product;
//import com.example.ecommerceapplication.productListActivity.models.ProductPage;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private List<ContentItem> mDataset;
    private Context context;
    private ProductInterface productInterface;
    //private CategoryInterface categoryInterface;
    //private RecyclerView.OnItemTouchListener onItemTouchListener;

    //public interface  OnItemTouchListener

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just an integer in this case
        private TextView textView;
        private TextView textViewPrice;
        private ImageView imageView;
        private MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.productImage);
            textView = v.findViewById(R.id.productText);
            textViewPrice = v.findViewById(R.id.productPrice);
        }
    }

    public ProductsAdapter(Context context , List<ContentItem> myDataset, ProductInterface productInterface) {
        mDataset = myDataset;
        this.context = context;
        this.productInterface = productInterface;
    }

    public ProductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.productlist_recycler_view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset.get(position).getProductName());
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
        return mDataset == null ? 0: mDataset.size();
    }

    public interface ProductInterface {
        public  void onClick(ContentItem product);
    }
}
