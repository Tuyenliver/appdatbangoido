package com.example.macbookpro.ttcn_tuyen.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbookpro.ttcn_tuyen.Interface.ItemOnClick;
import com.example.macbookpro.ttcn_tuyen.Model.Danhsach;
import com.example.macbookpro.ttcn_tuyen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapterList extends RecyclerView.Adapter<MyAdapterList.ViewHolder> {
    private Context context;
    private List<Danhsach> danhSachList;

    public MyAdapterList(Context context, List<Danhsach> danhSachList) {
        this.context = context;
        this.danhSachList = danhSachList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dood_drink,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.get().load("https://s8.mkklcdnv8.com/mangakakalot/d2/doraemon/vol0_chapter_11/2.jpg").into(viewHolder.imageView);
        viewHolder.textViewten.setText(danhSachList.get(i).getTen());
        viewHolder.textViewgia.setText(danhSachList.get(i).getGia());

        viewHolder.setItemOnClick(new ItemOnClick() {
            @Override
            public void onClick(View v, int posstion) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSachList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textViewten,textViewgia;
        private ItemOnClick itemOnClick;

        public void setItemOnClick(ItemOnClick itemOnClick) {
            this.itemOnClick = itemOnClick;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view_item);
            textViewten = (TextView) itemView.findViewById(R.id.txt_ten_item);
            textViewgia = (TextView) itemView.findViewById(R.id.txt_gia_item);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemOnClick.onClick(v,getAdapterPosition());
        }
    }
}
