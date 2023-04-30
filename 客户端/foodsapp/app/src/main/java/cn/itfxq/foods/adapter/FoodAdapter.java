package cn.itfxq.foods.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.itfxq.foods.R;
import cn.itfxq.foods.entity.FoodEntity;


public class FoodAdapter extends BaseAdapter {
    private Context mContext;
    //食物列表数据
    private List<FoodEntity> mFoodEntityList = new ArrayList<>();
    //加入购物车按钮的监听事件
    private OnSelectListener onSelectListener;

    public FoodAdapter(Context context) {
        this.mContext = context;
    }
    public FoodAdapter(Context context, OnSelectListener onSelectListener) {
        this.mContext = context;
        this.onSelectListener=onSelectListener;
    }
    /**
     * 设置数据更新界面
     */
    public void setData(List<FoodEntity> mFoodEntityList) {
        this.mFoodEntityList = mFoodEntityList;
        notifyDataSetChanged();
    }
    /**
     * 获取Item的总数
     */
    @Override
    public int getCount() {
        return mFoodEntityList == null ? 0 : mFoodEntityList.size();
    }
    /**
     * 根据position得到对应Item的对象
     */
    @Override
    public FoodEntity getItem(int position) {
        return mFoodEntityList == null ? null : mFoodEntityList.get(position);
    }
    /**
     * 根据position得到对应Item的id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * 得到相应position对应的Item视图，position是当前Item的位置，
     * convertView参数是滚出屏幕的Item的View
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        //复用convertView
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.menu_item,
                    null);
            vh.foodNameTv = (TextView) convertView.findViewById(R.id.foodNameTv);
            vh.tasteTv = (TextView) convertView.findViewById(R.id.tasteTv);
            vh.priceTv = (TextView) convertView.findViewById(R.id.priceTv);
            vh.addCarBtn = (Button) convertView.findViewById(R.id.addCarBtn);
            vh.foodPicIv = (ImageView) convertView.findViewById(R.id.foodPicIv);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取position对应的Item的数据对象
        final FoodEntity bean = getItem(position);
        if (bean != null) {
            vh.foodNameTv.setText(bean.getFoodName());
            vh.tasteTv.setText(bean.getTaste());
            vh.priceTv.setText("￥"+bean.getPrice());
            //http://192.xxx:80/static/upload/food/647aa13a-6f16-414f-b0b4-c29cb0d5b375.png
            Glide.with(mContext)
                    .load(bean.getFoodPic())
                    .into(vh.foodPicIv);
        }

        vh.addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //加入购物车按钮的点击事件
                onSelectListener.onSelectAddCar(position);
            }
        });
        return convertView;
    }
    class ViewHolder {
        public TextView foodNameTv, tasteTv, priceTv;
        public Button addCarBtn;
        public ImageView foodPicIv;
    }
    public interface OnSelectListener {
        //处理加入购物车按钮的方法
        void onSelectAddCar (int position);
    }
}
