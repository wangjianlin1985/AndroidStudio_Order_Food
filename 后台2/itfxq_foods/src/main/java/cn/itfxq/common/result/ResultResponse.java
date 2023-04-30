package cn.itfxq.common.result;

import lombok.Data;

import java.util.HashMap;

/**
 * ResultResponse:返回类封装
 */
@Data
public class ResultResponse extends HashMap {

    public ResultResponse(String message) {
        put("isSuccess",false);
        put("msg",message);
    }

    public ResultResponse() {
        put("isSuccess",true);
        put("msg","操作成功");
    }
    //成功
    public static ResultResponse ok(){
        return new ResultResponse();
    }
    public static ResultResponse ok(String msg){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.put("msg",msg);
        return resultResponse;
    }
    //失败
    public static ResultResponse fail(String msg){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.put("isSuccess",false);
        resultResponse.put("msg",msg);
        return resultResponse;
    }
    //成功设置对象值
    public ResultResponse put(String paramkey,Object paramValue){
        super.put(paramkey,paramValue);
        return this;
    }


}