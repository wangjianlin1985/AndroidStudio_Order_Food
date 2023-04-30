package cn.itfxq.business.food.service;

import cn.itfxq.auth.entity.User;
import cn.itfxq.business.food.entity.Food;
import cn.itfxq.common.service.IBaseService;

import java.util.List;

/**
 * 食物service层接口
 */
public interface IFoodService extends IBaseService<Food> {
    /**
     * 添加食物
     * @param food
     */
    void addFood(Food food);

    /**
     * 更新食物图片
     * @param food
     */
    void updateFoodPic(Food food);

    /**
     * 修改食物
     * @param food
     */
    void editSaveFood(Food food);

    /**
     * 删除食物
     * @param id
     */
    void deleteUser(Long id);
}
