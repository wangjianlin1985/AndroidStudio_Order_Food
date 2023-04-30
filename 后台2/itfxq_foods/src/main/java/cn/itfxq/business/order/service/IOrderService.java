package cn.itfxq.business.order.service;

import cn.itfxq.business.order.entity.Order;
import cn.itfxq.common.mapper.BaseMapper;
import cn.itfxq.common.service.IBaseService;

import java.util.List;

public interface IOrderService extends IBaseService<Order> {

    void addOrder(Order order);

    List<Order> queryMyOrderByUsername(String username);
}
