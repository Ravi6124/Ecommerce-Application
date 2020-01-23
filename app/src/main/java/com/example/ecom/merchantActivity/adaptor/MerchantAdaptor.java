package com.example.ecom.merchantActivity.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.example.ecom.R;
import com.example.ecom.merchantActivity.models.MerchantResponse;

import java.util.List;

public class MerchantAdaptor extends RecyclerView.Adapter<MerchantAdaptor.MyViewHolder> {

    private List<MerchantResponse> mDataset;
    private Context context;
    private MerchantAdaptorInterface merchantAdaptorInterface;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just an integer in this case
        public TextView textView;
        public TextView merhcantRating;
        //public ImageView imageView;
        public MyViewHolder(View v) {
            super(v);
            //imageView = v.findViewById(R.id.categoryImage);
            textView = v.findViewById(R.id.merchant_name);
            merhcantRating = v.findViewById(R.id.merchant_rating);
        }
    }

    public MerchantAdaptor(Context context , List<MerchantResponse> myDataset,MerchantAdaptorInterface merchantAdaptorInterface) {
        mDataset = myDataset;
        this.context = context;
        this.merchantAdaptorInterface = merchantAdaptorInterface;
    }

    public MerchantAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.merchant_list_recycler_view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset.get(position).getMerchantName());
        holder.merhcantRating.setText(String.valueOf(mDataset.get(position).getMerchantRating()));

//        Glide.with(this.context)
//                .load(mDataset.get(position).getImageURL())
//                .into(holder.imageView);

        //click funcionality

        holder.textView.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                merchantAdaptorInterface.onClick(mDataset.get(position));
            }
        });

    }


    public int getItemCount() {
        return mDataset.size();
    }

    public interface MerchantAdaptorInterface {
          void onClick(MerchantResponse merchantResponse);
    }
}
