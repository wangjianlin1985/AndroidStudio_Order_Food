package cn.itfxq.business.order.service.impl;

import cn.itfxq.business.food.entity.Food;
import cn.itfxq.business.food.service.IFoodService;
import cn.itfxq.business.order.entity.Order;
import cn.itfxq.business.order.entity.OrderDetail;
import cn.itfxq.business.order.mapper.OrderMapper;
import cn.itfxq.business.order.service.IOrderService;
import cn.itfxq.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void addOrder(Order order) {
        order.setCreateTime(new Date());
        for (OrderDetail orderDetail : order.getOrderDetailList()) {
            orderMapper.addOrderDetail(orderDetail);
        }
        orderMapper.addOrder(order);
    }

    @Override
    public List<Order> queryMyOrderByUsername(String username) {
        return orderMapper.queryMyOrderByUsername(username);
    }
}
