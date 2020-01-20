package com.example.ecommerceapplication.homeActivity.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceapplication.R;
import com.example.ecommerceapplication.homeActivity.models.Category;

import java.util.List;

public class LandingAdapter extends RecyclerView.Adapter<LandingAdapter.MyViewHolder> {

    private List<Category> mDataset;
    private Context context;
    private CategoryInterface categoryInterface;
    //private RecyclerView.OnItemTouchListener onItemTouchListener;

    //public interface  OnItemTouchListener

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just an integer in this case
        public TextView textView;
        public ImageView imageView;
        public MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.categoryImage);
            textView = v.findViewById(R.id.categoryText);
        }
    }

    public LandingAdapter(Context context , List<Category> myDataset,CategoryInterface categoryInterface) {
        mDataset = myDataset;
        this.context = context;
        this.categoryInterface = categoryInterface;
    }

    public LandingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

        Glide.with(this.context)
                .load(mDataset.get(position).getImageURL())
                .into(holder.imageView);

        //click funcionality

        holder.imageView.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryInterface.onClick(mDataset.get(position));
            }
        });

    }


    public int getItemCount() {
        return mDataset.size();
    }

    public interface CategoryInterface {
        public  void onClick(Category category);
    }
}
