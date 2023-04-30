package cn.itfxq.business.food.mapper;

import cn.itfxq.business.food.entity.Food;
import cn.itfxq.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {

    /**
     * 新增食物
     * @param food
     */
    @Insert("insert into t_foods(foodName,taste,price,foodType,createTime)" +
            " values(#{foodName},#{taste},#{price},#{foodType},#{createTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addFood(Food food);

    /**
     * 更新食物图片
     * @param food
     */
    @Update("update t_foods set foodPic=#{foodPic} where id=#{id}")
    void updateFoodPic(Food food);

    /**
     * 修改食物
     * @param food
     */
    @Update("update t_foods set foodName=#{foodName}," +
            "taste=#{taste}," +
            "price=#{price}," +
            "foodType=#{foodType}  " +
            "where id=#{id}")
    void editSaveFood(Food food);

    /**
     * 删除食物
     * @param id
     */
    @Delete("delete from t_foods where id=#{id}")
    void deleteUser(Long id);
}
