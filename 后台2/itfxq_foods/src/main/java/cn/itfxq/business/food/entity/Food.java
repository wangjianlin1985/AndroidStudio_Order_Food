package cn.itfxq.business.food.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
public class Food {

    //食物id
    private Long id;

    //食物名称
    private String foodName;

    //口味
    private String taste;

    //价格
    private Double price;

    //购物车数量
    private Long count;

    //食物图片
    private String foodPic;

    //食物类型 1-热菜 2-凉菜 3-甜品 4-干锅 5-汤 6-饮料 7-米饭
    private Long foodType;

    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

}
