package cn.itfxq.business.order.entity;

import cn.itfxq.common.entity.BaseEntity;
import lombok.Data;


@Data
public class OrderDetail extends BaseEntity {
    //食物名称
    private String foodName;
    //总价
    private Double price;
    //订单号
    private String ordernum;
    //食物数量
    private Integer num;

}
