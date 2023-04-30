package cn.itfxq.business.food.service.impl;

import cn.itfxq.business.food.entity.Food;
import cn.itfxq.business.food.mapper.FoodMapper;
import cn.itfxq.business.food.service.IFoodService;
import cn.itfxq.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("foodService")
public class FoodServiceImpl extends BaseServiceImpl<Food> implements IFoodService {

    @Autowired
    private FoodMapper foodMapper;


    @Override
    public void addFood(Food food) {
        //设置创建时间
        food.setCreateTime(new Date());
        foodMapper.addFood(food);
    }

    /**
     * 更新食物图片
     * @param food
     */
    @Override
    public void updateFoodPic(Food food) {
        foodMapper.updateFoodPic(food);
    }

    /**
     * 修改保存食物
     * @param food
     */
    @Override
    public void editSaveFood(Food food) {
        foodMapper.editSaveFood(food);
    }

    /**
     * 删除食物
     * @param id
     */
    @Override
    public void deleteUser(Long id) {
        foodMapper.deleteUser(id);
    }
}
