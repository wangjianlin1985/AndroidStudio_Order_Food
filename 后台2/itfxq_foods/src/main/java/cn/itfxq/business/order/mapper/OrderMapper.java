package cn.itfxq.business.order.mapper;

import cn.itfxq.business.order.entity.Order;
import cn.itfxq.business.order.entity.OrderDetail;
import cn.itfxq.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper  extends BaseMapper<Order> {


    @Insert("insert into t_orders(ordernum,isPay,createTime," +
            "price,address,username,tel) " +
            "values(#{ordernum},#{isPay},#{createTime}," +
            "#{price},#{address},#{username},#{tel})")
    void addOrder(Order order);



    @Insert("insert into t_orders_foods(foodName,price,ordernum,num)" +
            " values(#{foodName},#{price},#{ordernum},#{num})")
    void addOrderDetail(OrderDetail orderDetail);

    @Select("select * from t_orders where username=#{username}")
    List<Order> queryMyOrderByUsername(String username);
}
