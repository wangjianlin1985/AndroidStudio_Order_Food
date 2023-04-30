package cn.itfxq.foods.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.itfxq.foods.R;
import cn.itfxq.foods.entity.OrderEntity;


public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyOrderHolder>{

    Context context;
    List<OrderEntity> mDatas;

    OnItemClickListener onItemClickListener;

    private OrderEntity mOrderEntity;



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public void OnItemClick(View view, int position);
    }
    public MyOrderAdapter(Context context, List<OrderEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public MyOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_myorder,parent,false);
        MyOrderHolder holder = new MyOrderHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderHolder holder, final int position) {
         mOrderEntity = mDatas.get(position);
        holder.item_myordernumTv.setText(mOrderEntity.getOrdernum());
        holder.item_myorder_priceTv.setText(mOrderEntity.getPrice()+"");
        holder.item_myorder_usernameTv.setText(mOrderEntity.getUsername());
        holder.item_myorder_telTv.setText(mOrderEntity.getTel());
        holder.item_myorder_addressTv.setText(mOrderEntity.getAddress());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyOrderHolder extends RecyclerView.ViewHolder{
        TextView item_myordernumTv,item_myorder_priceTv,
                item_myorder_usernameTv,item_myorder_telTv,item_myorder_addressTv;
        public MyOrderHolder(View itemView) {
            super(itemView);
            item_myordernumTv = itemView.findViewById(R.id.item_myordernum);
            item_myorder_priceTv = itemView.findViewById(R.id.item_myorder_price);
            item_myorder_usernameTv = itemView.findViewById(R.id.item_myorder_username);
            item_myorder_telTv = itemView.findViewById(R.id.item_myorder_tel);
            item_myorder_addressTv = itemView.findViewById(R.id.item_myorder_address);
        }
    }
}
