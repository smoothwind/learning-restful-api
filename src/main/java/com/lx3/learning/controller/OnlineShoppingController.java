package com.lx3.learning.controller;

import com.lx3.learning.model.Order;
import com.lx3.learning.service.OrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("shop")
public class OnlineShoppingController {
    private static final Log log = LogFactory.getLog(TvSeriesController.class);
    @Autowired
    private OrderService orderService;

    @PutMapping
    public Map<String,String> createOrder(HttpServletRequest httpServletRequest, @RequestBody Order order) {
        if (log.isTraceEnabled()){
            log.trace("createOrder():"+order);
        }
        Map<String,String> map = new HashMap<>();

        if (orderService.createOrder(order))
        {
            map.put("success",order.toString());
            return map;
        }
        map.put("failed",order.toString());
        return map;
    }

}
