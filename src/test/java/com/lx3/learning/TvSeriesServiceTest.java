package com.lx3.learning;

import com.lx3.learning.dao.TvSeriesDao;
import com.lx3.learning.pojo.TvCharacter;
import com.lx3.learning.pojo.TvSeries;
import com.lx3.learning.service.TvSeriesService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

public class TvSeriesServiceTest {
    @Autowired
    TvSeriesService tvSeriesService;

    /**
     *  mock 了dao的所有bean后数据访问层就被接管了，从而实现测试不受具体数据库内数据影响的效果
     */
    @MockBean
    TvSeriesDao tvSeriesDao;
    @MockBean
    TvCharacter tvCharacter;


    @Test
    public void testGetAll(){
        List<TvSeries> list = new ArrayList<>();
        TvSeries ts = new TvSeries();
        ts.setName("POI");
        System.out.println(ts);
        list.add(ts);

        //下面这个语句是告诉Mock出来tvSeriesDao当执行getAll()方法时，返回上面创建的那个list
        Mockito.when(tvSeriesDao.getAll()).thenReturn(list);
        System.out.println(ts);
        Assert.assertNull("is null",tvSeriesService);

        //测试tvSeries的getAllTvSeries()方法，获得返回值
        List<TvSeries> result = tvSeriesService.getAllTvSeries();
        System.out.println("1");
        //判断返回值是否和最初做的那个list的值相同，应该是一样的
        Assert.assertTrue(result.size() == list.size());
        Assert.assertTrue("POI".equals(result.get(0).getName()));

    }

}
