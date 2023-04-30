package cn.itfxq.foods.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class FoodEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 菜品Id
     */
    private int id;
    /**
     * 菜品名称
     */
    private String foodName;

    /**
     * 菜品口味
     */
    private String taste;

    /**
     * 价格
     */
    private Double price;
    /**
     * 添加到购物车中的数量
     */
    private int count;
    /**
     *  菜的图片
     */
    private String foodPic;

    /**
     * 菜单类型
     * @return
     */
    private String foodType;

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public String getTaste() {
        return taste;
    }
    public void setTaste(String taste) {
        this.taste = taste;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFoodPic() {
        return foodPic;
    }
    public void setFoodPic(String foodPic) {
        this.foodPic = foodPic;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}