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


public class ChuaThanhToan extends RecyclerView.Adapter<ChuaThanhToan.ViewHolder> {
    private Context context;

    private List<Order> orderList;


    public ChuaThanhToan(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hoadon,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.stt.setText(i+"");
        viewHolder.ten.setText(orderList.get(i).getTen());
        viewHolder.sl.setText(orderList.get(i).getSoluong()+"");
        viewHolder.gia.setText(orderList.get(i).getGia()+"");

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

        private ItemOnClick itemOnClick;

        public void setItemOnClick(ItemOnClick itemOnClick) {
            this.itemOnClick = itemOnClick;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stt = (TextView) itemView.findViewById(R.id.stthoadon);
            ten = (TextView) itemView.findViewById(R.id.tenhoadon);
            gia = (TextView) itemView.findViewById(R.id.gia_hoadon);
            sl = (TextView)itemView.findViewById(R.id.slhoadon);
            thanhtien = (TextView) itemView.findViewById(R.id.thanhtienhoadon);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemOnClick.onClick(v,getAdapterPosition());
        }
    }
}
