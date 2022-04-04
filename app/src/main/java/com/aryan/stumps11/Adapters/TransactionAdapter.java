package com.aryan.stumps11.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.ApiModel.profile.transaction.MyTransactionData;
import com.aryan.stumps11.Model.TransactionModel;
import com.aryan.stumps11.R;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<MyTransactionData> transactionModels;
    private Context context;

    public TransactionAdapter(List<MyTransactionData> transactionModels, Context context) {
        this.transactionModels = transactionModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_transaction_balance,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MyTransactionData model=transactionModels.get(position);
        String status=model.getTxStatus().toString();

        if(status.equals("SUCCESS")){
            holder.tvMsg.setText(model.getTxMsg());
            holder.tvMsg.setTextColor(R.color.Color5);
            holder.tvTime.setText(model.getTxTime());
            holder.tvPrice.setText("₹ "+model.getOrderAmount());
            holder.tvPrice.setTextColor(R.color.Color5);

        }else if (status.equals("PENDING")){
            holder.tvMsg.setText(model.getTxMsg());
            holder.tvMsg.setTextColor(R.color.Color6);
            holder.tvTime.setText(model.getTxTime());
            holder.tvPrice.setText("₹ "+model.getOrderAmount());
            holder.tvPrice.setTextColor(R.color.Color6);

        }else if(status.equals("FAILED")){
            holder.tvMsg.setText(model.getTxMsg());
            holder.tvMsg.setTextColor(R.color.Color1);
            holder.tvTime.setText(model.getTxTime());
            holder.tvPrice.setText("₹ "+model.getOrderAmount());
            holder.tvPrice.setTextColor(R.color.Color1);

        }


//        holder.tvDate.setText(model.getT());

//        Date date = Calendar.getInstance().getTime();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        String strDate = dateFormat.format(date);
        String strDate = model.getTxTime();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        try {
//            Date date = format.parse(strDate);
//            System.out.println(">>>>>"+date);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }




    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMsg,tvDate,tvTime,tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMsg=itemView.findViewById(R.id.custom_transaction_msg);
//            tvDate=itemView.findViewById(R.id.custom_traction_date);
            tvTime=itemView.findViewById(R.id.custom_traction_time);
            tvPrice=itemView.findViewById(R.id.custom_traction_price);
        }
    }
}
