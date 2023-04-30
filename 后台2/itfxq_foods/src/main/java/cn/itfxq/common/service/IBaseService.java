package cn.itfxq.common.service;


import cn.itfxq.common.page.PageList;
import cn.itfxq.common.query.BaseQuery;

import java.util.List;

/**
 * @description: 课程列表Service接口层
 */
public interface IBaseService<T> {

    //查询所有
    List<T> queryAll();
    //分页查询
    PageList  listpage(BaseQuery baseQuery);


}
