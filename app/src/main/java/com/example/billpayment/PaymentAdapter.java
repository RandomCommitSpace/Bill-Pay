package com.example.billpayment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {

    List<PaymentModel> list;

    public PaymentAdapter(List<PaymentModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_payment, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PaymentModel item = list.get(position);

        holder.txtBillType.setText(item.billType);
        holder.txtAmount.setText("Amount: " + item.amount);
        holder.txtDate.setText(item.date);
        holder.txtStatus.setText(item.status);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtBillType, txtAmount, txtDate, txtStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtBillType = itemView.findViewById(R.id.txtBillType);
            txtAmount = itemView.findViewById(R.id.txtAmount);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtStatus = itemView.findViewById(R.id.txtStatus);
        }
    }
}
