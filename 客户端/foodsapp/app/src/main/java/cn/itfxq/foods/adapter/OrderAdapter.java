package cn.itfxq.foods.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.util.List;

import cn.itfxq.foods.R;
import cn.itfxq.foods.entity.FoodEntity;


public class OrderAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodEntity> foodEntityList;
    public OrderAdapter(Context context) {
        this.mContext = context;
    }
    /**
     * 设置数据更新界面
     */
    public void setData(List<FoodEntity> foodEntityList) {
        this.foodEntityList = foodEntityList;
        notifyDataSetChanged();
    }
    /**
     * 获取Item的总数
     */
    @Override
    public int getCount() {
        return foodEntityList == null ? 0 : foodEntityList.size();
    }
    /**
     * 根据position得到对应Item的对象
     */
    @Override
    public FoodEntity getItem(int position) {
        return foodEntityList == null ? null : foodEntityList.get(position);
    }
    /**
     * 根据position得到对应Item的id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        //复用convertView
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.order_item,
                    null);
            vh.food_nameTv = (TextView) convertView.findViewById(R.id.foodNameTv);
            vh.countTv = (TextView) convertView.findViewById(R.id.countTv);
            vh.moneyTv = (TextView) convertView.findViewById(R.id.moneyTv);
            vh.food_picIv = (ImageView) convertView.findViewById(R.id.foodPicIv);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取position对应的Item的数据对象
        final FoodEntity bean = getItem(position);
        if (bean != null) {
            vh.food_nameTv.setText(bean.getFoodName());
            vh.countTv.setText("x"+bean.getCount());
            vh.moneyTv.setText("￥"+bean.getPrice()*
                    bean.getCount());
            Glide.with(mContext)
                    .load(bean.getFoodPic())
                    .error(R.mipmap.ic_launcher)
                    .into(vh.food_picIv);
        }
        return convertView;
    }
    class ViewHolder {
        public TextView food_nameTv, countTv, moneyTv;
        public ImageView food_picIv;
    }
}
