package com.example.macbookpro.ttcn_tuyen.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.macbookpro.ttcn_tuyen.Activity.OrderActivity;
import com.example.macbookpro.ttcn_tuyen.Interface.ItemOnClick;
import com.example.macbookpro.ttcn_tuyen.Model.Order;
import com.example.macbookpro.ttcn_tuyen.R;

import java.util.List;

public class MyAdapterorder extends RecyclerView.Adapter<MyAdapterorder.ViewHolder> {
    private OrderActivity context;
    private List<Order> orderList;


    public MyAdapterorder(OrderActivity context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.stt.setText(i+"");
        viewHolder.ten.setText(orderList.get(i).getTen());
        viewHolder.sl.setText(orderList.get(i).getSoluong()+"");
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.removeById(i);
            }
        });
        viewHolder.setItemOnClick(new ItemOnClick() {
            @Override
            public void onClick(View v, int posstion) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView stt,ten,gia,thanhtien,sl;
        android.support.v7.widget.AppCompatImageView imageView;
        private ItemOnClick itemOnClick;

        public void setItemOnClick(ItemOnClick itemOnClick) {
            this.itemOnClick = itemOnClick;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stt = (TextView) itemView.findViewById(R.id.sttorder);
            ten = (TextView) itemView.findViewById(R.id.tenorder);
            gia = (TextView) itemView.findViewById(R.id.gia_order);
            sl = (TextView)itemView.findViewById(R.id.slorder);
            thanhtien = (TextView) itemView.findViewById(R.id.thanhtienorder);
            imageView = (AppCompatImageView) itemView.findViewById(R.id.removeorder);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemOnClick.onClick(v,getAdapterPosition());
        }
    }
}
