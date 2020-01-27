package com.example.ecom.loginhistoryActivity.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ecom.R;
import com.example.ecom.loginhistoryActivity.models.LogHistoryResponse;

import java.util.List;

public class LoginHistoryAdapter extends  RecyclerView.Adapter<LoginHistoryAdapter.MyViewHolder>{


        private List<LogHistoryResponse> mDataset;
        private Context context;
        private LogInterface logInterface;
        //private RecyclerView.OnItemTouchListener onItemTouchListener;

        //public interface  OnItemTouchListener

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just an integer in this case
            public TextView textView;
            public TextView loginType;
            public TextView loginSource;
            //public ImageView imageView;
            public MyViewHolder(View v) {
                super(v);
                //imageView = v.findViewById(R.id.categoryImage);
                textView = v.findViewById(R.id.login_time);
                loginType = v.findViewById(R.id.login_type);
                loginSource = v.findViewById(R.id.login_source);
            }
        }

        public LoginHistoryAdapter(Context context , List<LogHistoryResponse> myDataset,LogInterface logInterface) {
            mDataset = myDataset;
            this.context = context;
            this.logInterface = logInterface;
        }

        public LoginHistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.login_history_recycler_view_item, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }



        public void onBindViewHolder(MyViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.textView.setText(mDataset.get(position).getLoginTime());
            holder.loginType.setText(mDataset.get(position).getType());
            holder.loginSource.setText(mDataset.get(position).getLoginSource());

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
            return mDataset.size();
        }

        public interface LogInterface {
            public  void onClick(LogHistoryResponse logHistoryResponse);
        }
    }

