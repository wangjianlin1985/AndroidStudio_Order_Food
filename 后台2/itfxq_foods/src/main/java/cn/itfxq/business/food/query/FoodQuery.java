package cn.itfxq.business.food.query;

import cn.itfxq.business.food.entity.Food;
import cn.itfxq.common.query.BaseQuery;
import lombok.Data;

@Data
public class FoodQuery  extends BaseQuery {
    private String foodName;
}
