package com.lx3.learning.dao;

import com.lx3.learning.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDAO {
    public int insertOrder(@Param("order") Order order);
}
