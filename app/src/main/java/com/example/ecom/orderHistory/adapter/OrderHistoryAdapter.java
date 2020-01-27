package com.example.ecom.orderHistory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ecom.R;
import com.example.ecom.orderHistory.models.OrderHistoryResponse;

import java.util.List;

public class OrderHistoryAdapter extends  RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder>{


        private OrderHistoryResponse mDataset;
        private Context context;
        private OrderLogInterface orderLogInterface;
        //private RecyclerView.OnItemTouchListener onItemTouchListener;

        //public interface  OnItemTouchListener

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just an integer in this case
            public TextView orderDate;
            public TextView orderId;
            public TextView totalAmount;
            //public ImageView imageView;
            public MyViewHolder(View v) {
                super(v);
                //imageView = v.findViewById(R.id.categoryImage);
                orderId = v.findViewById(R.id.order_id);
                orderDate = v.findViewById(R.id.order_date);
                totalAmount = v.findViewById(R.id.order_total_amount);
            }
        }

        public OrderHistoryAdapter(Context context , OrderHistoryResponse myDataset,OrderLogInterface orderLogInterface){
            this.mDataset = myDataset;
            this.context = context;
            this.orderLogInterface = orderLogInterface;
    }

    public OrderHistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_history_recycler_view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.orderId.setText(mDataset.getOrders().get(position).getOrderId());
        holder.orderDate.setText(String.valueOf(mDataset.getOrders().get(position).getDate()));
        holder.totalAmount.setText(String.valueOf(mDataset.getOrders().get(position).getTotalAmount()));

//            Glide.with(this.context)
//                    .load(mDataset.get(position).getImageURL())
//                    .into(holder.imageView);

        //click funcionality

//            holder.imageView.getRootView().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    categoryInterface.onClick(mDataset.get(position));
//                }
//            });

    }


    public int getItemCount() {
        return 1;
    }

    public interface OrderLogInterface {
        public  void onClick(OrderHistoryResponse orderHistoryResponse);
    }
}

