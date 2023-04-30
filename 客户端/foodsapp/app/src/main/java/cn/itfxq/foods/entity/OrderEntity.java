package cn.itfxq.foods.entity;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
public class OrderEntity extends BaseEntity{
    //订单号
    private String ordernum;
    //是否付款
    private String isPay;
    //创建时间
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
    private List<OrderDetailEntity> orderDetailList ;

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<OrderDetailEntity> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailEntity> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}