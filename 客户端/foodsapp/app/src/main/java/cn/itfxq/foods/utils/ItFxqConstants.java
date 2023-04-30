package cn.itfxq.foods.utils;

/**
 * 常量配置
 */
public class ItFxqConstants {

    //编码
    public static final String CHARSET = "utf-8";
    //访问食物地址
    public static final String FOOD_URL = "http://192.168.43.51:8088/frontfood/all";
    //登录地址
    public static final String LOGIN_URL = "http://192.168.43.51:8088/frontuser/login";
    //添加订单
    public static final String ORDER_URL = "http://192.168.43.51:8088/frontorder/add";
    //查询我的订单
    public static final String MYORDER_URL = "http://192.168.43.51:8088/frontorder/queryOrderByUsername";
    //注册用户
    public static final String REG_URL = "http://192.168.43.51:8088/frontuser/reg";

    //返回的状态
    public static final int OK_STATUS = 200;
    //错误的状态
    public static final int ERROR_STATUS = 500;
    //登录的Key
    public static final String LOGIN_USER_KEY = "loginUser";


}
