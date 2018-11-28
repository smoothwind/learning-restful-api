package com.lx3.learning.dao;

import com.lx3.learning.pojo.TvSeries;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TvSeriesDao {
    @Select("select * from tv_series")
    List<TvSeries> getAll();

}
