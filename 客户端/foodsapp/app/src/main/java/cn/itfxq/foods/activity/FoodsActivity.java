package cn.itfxq.foods.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.itfxq.foods.R;
import cn.itfxq.foods.adapter.CarAdapter;
import cn.itfxq.foods.adapter.FoodAdapter;
import cn.itfxq.foods.entity.FoodEntity;
import cn.itfxq.foods.utils.ItFxqConstants;
import cn.itfxq.foods.utils.RemoteDatas;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FoodsActivity extends AppCompatActivity implements View.
        OnClickListener {

    private FoodEntity mFoodEntity;
    private TextView foodNameTv, priceTv;
    private ImageView foodPicIv;
    private FoodAdapter mFoodAdapter;
    public static final int OKSTATUS = 1;
    public static final int ADDCARSTATUS = 2;
    private Integer foodType;
    private FoodsHandler mFoodsHandler;

    private CarAdapter carAdapter;
    private ListView foodsListView;
    //总的食物
    private List<FoodEntity> foodList = new ArrayList<>();
    //临时过滤食物
    private List<FoodEntity> tempFoodList = new ArrayList<>();
    //购物车中的菜品数据
    private List<FoodEntity> carFoodList = new ArrayList<>();
    private int totalCount = 0;
    //购物车中菜品的总价格
    private double totalMoney = 0.0;
    private TextView rcTv,lcTv,tpTv,ggTv,tangTv,ylTv,riceTv;
    private TextView settleTv, countTv, moneyTv,
            notfillTv;
    private ImageView  carIv;
    private ListView  carLv,listLv;
    private RelativeLayout carListRl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);

        mFoodsHandler = new FoodsHandler();
        mFoodAdapter=new FoodAdapter(this);
        foodsListView = findViewById(R.id.listLv);
        //从店铺详情界面传递过来的菜的数据
        foodType =  getIntent().getIntExtra("foodType",0);
        init();

    }

    private void init(){
        //初始化adapter
        initAdapter();
        //初始化数据
        initData();
        //初始化视图
        initView();
        //设置数据
        setData();
    }

    private void initAdapter(){
        carAdapter = new CarAdapter(this, new CarAdapter.CarOptListener() {
            @Override
            public void onAdd(int position, TextView foodCountTv, TextView
                    foodPriceTv) {
                FoodEntity foodEntity = carFoodList.get(position);
                foodCountTv.setText(foodEntity.getCount() + 1 + "");
                Integer count = (foodEntity.getCount() + 1);

                foodPriceTv.setText("￥" + foodEntity.getPrice() * count);
                foodEntity.setCount(foodEntity.getCount() + 1);
                Iterator<FoodEntity> iterator = carFoodList.iterator();

                while (iterator.hasNext()) {
                    FoodEntity food = iterator.next();
                    if (food.getId() == foodEntity.getId()) {
                        iterator.remove();
                    }
                }
                carFoodList.add(position, foodEntity);
                totalCount = totalCount + 1;
                totalMoney = totalMoney+ foodEntity.getPrice();
                uploadCarData();
            }
            @Override
            public void onMis(int position, TextView foodCountTv, TextView
                    foodPriceTv) {
                FoodEntity bean = carFoodList.get(position);
                foodCountTv.setText(bean.getCount() - 1 + "");
                Integer count = bean.getCount() - 1;

                foodPriceTv.setText("￥" + bean.getPrice()*count);
                int count1 = bean.getCount() - 1;

                bean.setCount(count1);
                Iterator<FoodEntity> iterator = carFoodList.iterator();

                while (iterator.hasNext()) {     
                    FoodEntity food = iterator.next();
                    if (food.getId() == bean.getId()) {
                        iterator.remove();         
                    }
                }

                if (count > 0) carFoodList.add(position, bean);
                else carAdapter.notifyDataSetChanged();
                totalCount = totalCount - 1;

                totalMoney = totalMoney - bean.getPrice();

                uploadCarData();                  
            }
        });

        mFoodAdapter = new FoodAdapter(this, new FoodAdapter.OnSelectListener() {
            @Override
            public void onSelectAddCar(int position) {
                FoodEntity fb = tempFoodList.get(position);
                fb.setCount(fb.getCount() + 1);
                Iterator<FoodEntity> iterator = carFoodList.iterator();
                while (iterator.hasNext()) {
                    FoodEntity food = iterator.next();
                    if (food.getId() == fb.getId()) {
                        iterator.remove();
                    }
                }
                carFoodList.add(fb);
                totalCount = totalCount + 1;
                totalMoney = totalMoney + fb.getPrice();
                uploadCarData();
            }
        });
        foodsListView.setAdapter(mFoodAdapter);

    }

    /**
     * 将购物车中菜品的总数量和总价格通过Handler传递到主线程中
     */
    private void uploadCarData() {
        Message msg = Message.obtain();
        msg.what = ADDCARSTATUS;
        Bundle bundle = new Bundle();
        bundle.putString("totalCount", totalCount + "");
        bundle.putString("totalMoney", totalMoney + "");
        msg.setData(bundle);
        mFoodsHandler.sendMessage(msg);
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        rcTv=findViewById(R.id.rcTv);
        lcTv=findViewById(R.id.lcTv);
        tpTv=findViewById(R.id.tpTv);
        ggTv=findViewById(R.id.ggTv);
        tangTv=findViewById(R.id.tangTv);
        ylTv=findViewById(R.id.ylTv);
        riceTv=findViewById(R.id.riceTv);

        foodNameTv = findViewById(R.id.foodNameTv);
        priceTv =  findViewById(R.id.priceTv);
        foodPicIv =  findViewById(R.id.foodPicIv);


        listLv = (ListView) findViewById(R.id.listLv);
        settleTv = (TextView) findViewById(R.id.tv_settle_accounts);
        countTv = (TextView) findViewById(R.id.countTv);
        carIv = (ImageView) findViewById(R.id.carIv);
        moneyTv = (TextView) findViewById(R.id.monenyTv);
        notfillTv = (TextView) findViewById(R.id.notfillTv);

        carLv = (ListView) findViewById(R.id.carLv);
        carListRl = (RelativeLayout) findViewById(R.id.carList_rl);
        carListRl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (carListRl.getVisibility() == View.VISIBLE) {
                    carListRl.setVisibility(View.GONE);
                }
                return false;
            }
        });
        
        //绑定事件
        settleTv.setOnClickListener(this);
        carIv.setOnClickListener(this);
        rcTv.setOnClickListener(this);
        lcTv.setOnClickListener(this);
        tpTv.setOnClickListener(this);
        ggTv.setOnClickListener(this);
        tangTv.setOnClickListener(this);
        ylTv.setOnClickListener(this);
        riceTv.setOnClickListener(this);

    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(ItFxqConstants.FOOD_URL).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Message msg = new Message();
                msg.what = OKSTATUS;
                msg.obj = res;
                mFoodsHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }

    /**
     * 设置界面数据
     */
    private void setData() {
        if (mFoodEntity == null) return;
        foodNameTv.setText(mFoodEntity.getFoodName());
        priceTv.setText("￥" + mFoodEntity.getPrice());
        Glide.with(this)
                .load(mFoodEntity.getFoodPic())
                .error(R.mipmap.ic_launcher)
                .into(foodPicIv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rcTv:

                mFoodAdapter.setData(getFoodByType(1));
                mFoodAdapter.notifyDataSetChanged();

                break;
            case R.id.lcTv:

                mFoodAdapter.setData(getFoodByType(2));
                mFoodAdapter.notifyDataSetChanged();
                break;
            case R.id.tpTv:

                mFoodAdapter.setData(getFoodByType(3));
                mFoodAdapter.notifyDataSetChanged();
                break;
            case R.id.ggTv:
                mFoodAdapter.setData(getFoodByType(4));
                mFoodAdapter.notifyDataSetChanged();
                break;
            case R.id.tangTv:
                mFoodAdapter.setData(getFoodByType(5));
                mFoodAdapter.notifyDataSetChanged();
                break;
            case R.id.ylTv:
                mFoodAdapter.setData(getFoodByType(6));
                mFoodAdapter.notifyDataSetChanged();
                break;
            case R.id.riceTv:
                mFoodAdapter.setData(getFoodByType(7));
                mFoodAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_settle_accounts:

                if (totalCount > 0) {
                    Intent intent = new Intent(FoodsActivity.this, OrderActivity.class);
                    intent.putExtra("carFoodList", (Serializable) carFoodList);
                    intent.putExtra("totalMoney", totalMoney + "");
                    startActivity(intent);
                }
                break;
            case R.id.carIv:
                if (totalCount <= 0) return;
                if (carListRl != null) {
                    if (carListRl.getVisibility() == View.VISIBLE) {
                        carListRl.setVisibility(View.GONE);
                    } else {
                        carListRl.setVisibility(View.VISIBLE);

                        Animation animation = AnimationUtils.loadAnimation(
                                FoodsActivity.this, R.anim.car_expant_anim);
                        carListRl.startAnimation(animation);
                    }
                }
                carAdapter.setData(carFoodList);
                carLv.setAdapter(carAdapter);
                break;

        }
    }

    private List<FoodEntity> getFoodByType(int i) {
        List list  = new ArrayList();
        if(i == 0){
            tempFoodList=foodList;
            return  tempFoodList;
        }
        foodList.forEach(foodEntity -> {
            if(foodEntity.getFoodType().equals(i+"")){
                list.add(foodEntity);
            }
        });
        tempFoodList = list;
        return list;
    }




    class FoodsHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case OKSTATUS:
                    if (msg.obj != null) {
                        String resultJson = (String) msg.obj;
                        //解析获取的JSON数据
                         foodList = RemoteDatas.INSTANCE.getFoodsList(resultJson);
                        tempFoodList =getFoodByType(foodType);
                        mFoodAdapter.setData(tempFoodList);
                        mFoodAdapter.notifyDataSetChanged();

                    }
                    break;
                case ADDCARSTATUS:
                    Bundle bundle = msg.getData();
                    String count = bundle.getString("totalCount", "");
                    String money = bundle.getString("totalMoney", "");
                    if (bundle != null) {
                        if (Integer.parseInt(count) > 0) {
                            carIv.setImageResource(R.drawable.car);
                            countTv.setVisibility(View.VISIBLE);
                            moneyTv.setTextColor(Color.parseColor("#ffffff"));
                            moneyTv.getPaint().setFakeBoldText(true);
                            moneyTv.setText("￥" + money);
                            countTv.setText(count);
                            //将变量money的类型转换为BigDecimal类型
                            BigDecimal bdm = new BigDecimal(money);

                                //显示去结算按钮
                                settleTv.setVisibility(View.VISIBLE);
                               //隐藏差价文本
                                notfillTv.setVisibility(View.GONE);

                        } else { //如果购物车中没有菜品
                            if (carListRl.getVisibility() == View.VISIBLE) {
                                //隐藏购物车列表
                                carListRl.setVisibility(View.GONE);
                            } else {
                                //显示购物车列表
                                carListRl.setVisibility(View.VISIBLE);
                            }
                            carIv.setImageResource(R.drawable.uncar);
                            //隐藏去结算按钮
                            settleTv.setVisibility(View.GONE);
                            //显示差价文本
                            notfillTv.setVisibility(View.VISIBLE);
                            notfillTv.setText("￥3起送");
                            notfillTv.setTextColor(Color.BLACK);
                            //隐藏购物中的菜品数量控件
                            countTv.setVisibility(View.GONE);

                            moneyTv.setTextColor(Color.BLACK);
                            moneyTv.setText("未选购食物");
                        }
                    }
                    break;
            }
        }
    }




}