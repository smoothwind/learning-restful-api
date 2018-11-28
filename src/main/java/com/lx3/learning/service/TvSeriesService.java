package com.lx3.learning.service;

import com.lx3.learning.dao.TvSeriesDao;
import com.lx3.learning.pojo.TvSeries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  //记得加注解
public class TvSeriesService {

    @Autowired
    private TvSeriesDao tvSeriesDao;

    public List<TvSeries> getAllTvSeries(){
        return tvSeriesDao.getAll();
    }
}
