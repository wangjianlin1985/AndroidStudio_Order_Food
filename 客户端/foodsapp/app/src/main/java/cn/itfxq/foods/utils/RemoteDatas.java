package cn.itfxq.foods.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.itfxq.foods.entity.FoodEntity;

public enum RemoteDatas {
    //对象
    INSTANCE;

    /**
     * 得到食物数据
     * @param resultJson
     * @return
     */
    public List<FoodEntity> getFoodsList(String resultJson) {
        Gson gson = new Gson();
        //通过反射得到type对象
        Type listType = new TypeToken<List<FoodEntity>>() {
        }.getType();
        // 得到食物数据
        List<FoodEntity> foodList = gson.fromJson(resultJson, listType);
        return foodList;
    }
}
