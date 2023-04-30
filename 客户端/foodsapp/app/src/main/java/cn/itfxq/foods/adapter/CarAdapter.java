package cn.itfxq.foods.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import cn.itfxq.foods.R;
import cn.itfxq.foods.entity.FoodEntity;


public class CarAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodEntity> foodEntityList;
    private CarOptListener carOptListener;
    public CarAdapter(Context context, CarOptListener carOptListener) {
        this.mContext = context;
        this.carOptListener=carOptListener;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.car_item, null);
            vh.foodNameTv = (TextView) convertView.findViewById(R.id.foodNameTv);
            vh.foodCountTv = (TextView) convertView.findViewById(R.id.foodCountTv);
            vh.foodPriceTv = (TextView) convertView.findViewById(R.id.foodPriceTv);
            vh.addIv = (ImageView) convertView.findViewById(R.id.addIv);
            vh.minusIv = (ImageView) convertView.findViewById(R.id.minusIv);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取position对应的Item的数据对象
        final FoodEntity bean = getItem(position);
        if (bean != null) {
            vh.foodNameTv.setText(bean.getFoodName());
            vh.foodCountTv.setText(bean.getCount()+"");
            Integer count= bean.getCount();
            vh.foodPriceTv.setText("￥" + bean.getPrice() * count);
        }
        vh.addIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carOptListener.onAdd(position,vh.foodCountTv,vh.
                        foodPriceTv);
            }
        });
        vh.minusIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carOptListener.onMis(position,vh.foodCountTv,vh.
                        foodPriceTv);
            }
        });
        return convertView;
    }
    class ViewHolder {
        public TextView foodNameTv, foodCountTv,foodPriceTv;
        public ImageView addIv,minusIv;
    }
    public interface CarOptListener {
        void onAdd(int position, TextView foodPriceTv, TextView foodCountTv);
        void onMis(int position, TextView foodPriceTv, TextView foodCountTv);
    }
}
