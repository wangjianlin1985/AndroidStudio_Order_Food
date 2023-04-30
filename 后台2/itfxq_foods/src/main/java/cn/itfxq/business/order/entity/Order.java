package cn.itfxq.business.order.entity;

import cn.itfxq.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order extends BaseEntity{
    //订单号
    private String ordernum;
    //是否付款
    private String isPay;
    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //订单总价格
    private Double price;
    //配送地址
    private String address;
    //下单人
    private String username;
    //下单电话
    private String tel;
    //一个单据多个订单
    private List<OrderDetail> orderDetailList ;

}
