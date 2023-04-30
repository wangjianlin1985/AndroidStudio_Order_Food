package cn.itfxq.common.service.impl;

import cn.itfxq.common.mapper.BaseMapper;
import cn.itfxq.common.page.PageList;
import cn.itfxq.common.query.BaseQuery;
import cn.itfxq.common.service.IBaseService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: BaseServiceImpl
 */


public class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public List<T> queryAll() {
        return baseMapper.queryAll();
    }

    @Override
    public PageList listpage(BaseQuery baseQuery) {
        PageList pageList  = new PageList();
        //查询总的条数
        Long total = baseMapper.queryTotal(baseQuery);
        List<T> users = baseMapper.queryData(baseQuery);
        pageList.setTotal(total);
        pageList.setRows(users);
        //分页查询的数据
        return pageList;
    }
}
