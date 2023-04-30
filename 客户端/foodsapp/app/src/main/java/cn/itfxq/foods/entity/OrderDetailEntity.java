package cn.itfxq.foods.entity;

/**
 * @description: OrderDetailEntity
 */
public class OrderDetailEntity extends BaseEntity {

    //食物名称
    private String foodName;
    //总价
    private Double price;
    //订单号
    private String ordernum;
    //食品数量
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }
}
