package com.example.macbookpro.ttcn_tuyen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbookpro.ttcn_tuyen.Activity.OrderActivity;
import com.example.macbookpro.ttcn_tuyen.Common.Common;
import com.example.macbookpro.ttcn_tuyen.Fragment.HomeFragment;
import com.example.macbookpro.ttcn_tuyen.Interface.ItemOnClick;
import com.example.macbookpro.ttcn_tuyen.Interface.SenData;
import com.example.macbookpro.ttcn_tuyen.Model.TableList;
import com.example.macbookpro.ttcn_tuyen.R;

import java.util.List;

public class MyAdapterHome extends RecyclerView.Adapter<MyAdapterHome.ViewHolder> {
    private Context context;

    private List<TableList> listList;
    private HomeFragment homeFragment;
    SenData senData;

    public MyAdapterHome(Context context, List<TableList> listList, HomeFragment homeFragment) {
        this.context = context;
        this.listList = listList;
        this.homeFragment = homeFragment;
    }

    public MyAdapterHome(Context context, List<TableList> listList) {
        this.context = context;
        this.listList = listList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_table,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (listList.get(i).getTrangthai()==1){
            viewHolder.imageView.setImageResource(R.drawable.ic_usesing);
        }else {
            viewHolder.imageView.setImageResource(R.drawable.ic_ready);
        }
        viewHolder.textView.setText(listList.get(i).getSoban()+"");

        viewHolder.setItemOnClick(new ItemOnClick() {
            @Override
            public void onClick(View v, int posstion) {
                if (listList.get(posstion).getTrangthai()==1){
                    homeFragment.ThayDoi(posstion,listList.get(posstion).getId());
                }else if (listList.get(posstion).getTrangthai()==0){
                    Common.select_table = listList.get(posstion);
                    homeFragment.starOrder();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;
        private ItemOnClick itemOnClick;
        public void setItemOnClick(ItemOnClick itemOnClick) {
            this.itemOnClick = itemOnClick;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageviewitemtable);
            textView = (TextView) itemView.findViewById(R.id.txt_tenban);
            imageView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            itemOnClick.onClick(v,getAdapterPosition());
        }
    }

}
