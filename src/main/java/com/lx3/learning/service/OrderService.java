package com.lx3.learning.service;

import com.lx3.learning.dao.OrderDao;
import com.lx3.learning.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public boolean createOrder(Order order){
         if (orderDao.insertOrder(order)>= 0) {
             return true;
         }
         return false;
    }

}
