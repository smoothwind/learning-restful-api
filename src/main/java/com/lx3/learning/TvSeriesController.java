package com.lx3.learning;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.io.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("tvseries")
public class TvSeriesController {
    private static final Log log = LogFactory.getLog(TvSeriesController.class);
    /*
    @GetMapping
    public Map<String, String> get(){
        Map<String,String> result = new HashMap<>();
        result.put("message","hello,world!");
        return result;
    }*/

    /*
    @GetMapping
    public List<TvSeriesDto> getAll(){
        List<TvSeriesDto> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,calendar.OCTOBER,2,0,0);
        calendar.setTimeZone(TimeZone.getDefault());
        list.add(new TvSeriesDto(1,"WestWorld",1,calendar.getTime()));
        return list;
    }*/

    @GetMapping
    public List<TvSeriesDto> getAll(){
        List<TvSeriesDto> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,calendar.OCTOBER,2,0,0);
        list.add(new TvSeriesDto(1,"WestWorld",1,calendar.getTime()));
        calendar.set(2011,calendar.SEPTEMBER,22,0,0);
        list.add(new TvSeriesDto(1,"Person of Internet",5,calendar.getTime()));
        return list;
    }


/*
    @PostMapping
    public TvSeriesDto insertOne(@RequestBody TvSeriesDto tvSeriesDto){
        if(log.isTraceEnabled()) {
            log.trace("这里应该重写新增TvSeriesDto对象的方法");
        }
        tvSeriesDto.setId(9999);
        return  tvSeriesDto;
    }*/

    @GetMapping("/{id}")
    public TvSeriesDto getOne(@PathVariable int id)
    {
        if(log.isTraceEnabled()){
            log.trace("getOne" + id);

        }
        if(id == 101){
            return createWestWorld();
        } else if (id == 102){
            return createPoi();
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    @PutMapping("/{id}")
    public TvSeriesDto updateOne(@PathVariable int id, @RequestBody TvSeriesDto tvSeriesDto)
    {
        if(log.isTraceEnabled()){
            log.trace("updateOne" + id);
            log.trace("updateOne" + tvSeriesDto);
        }
        if(id == 101 || id == 102){
            //TO-DO 根据tvSeriesDto的内容更新数据库
            return createPoi();
        }
        else{
            throw new ResourceNotFoundException();
        }
    }


    @DeleteMapping("/{id}")
    public Map<String,String> deleteOne(@PathVariable int id, HttpServletRequest request, @RequestParam(value = "delete_reason",required = false) String deleteReason) throws Exception{
        if(log.isTraceEnabled()){
            log.trace("deleteOne" + id);
        }
        Map<String,String> result = new HashMap<>();

        if(id == 101){
            //TODO:执行删除的代码
            result.put("message","#101被"+request.getRemoteAddr()+"删除（原因:"+deleteReason+")");
        } else if(id == 102){
            //不能删除这个,RuntimeException不如org.springframework.security.access.AccessDeniedException更合适
            //但此处还没有到Spring security。所以先用Runtime Exception
            throw new RuntimeException();
        } else {
            throw  new ResourceNotFoundException();
        }

        return result;
    }
    private TvSeriesDto createPoi() {
        Calendar cl =Calendar.getInstance();
        cl.set(2011,cl.SEPTEMBER,22,0,0);
        return new TvSeriesDto(102,"Person of Interest",5, cl.getTime());
    }

    private TvSeriesDto createWestWorld(){
        Calendar cl =Calendar.getInstance();
        cl.set(2016,cl.OCTOBER,2,0,0);
        return new TvSeriesDto(101,"West World",1, cl.getTime());
    }

    @PostMapping(value = "/{id}/photos",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addPhoto(@PathVariable int id, @RequestParam("photo")MultipartFile imgfile) throws Exception{
        if(log.isTraceEnabled()){
            log.trace("addPhoto" + id);
        }
        //保存文件
        FileOutputStream fos = new FileOutputStream("target/"+imgfile.getOriginalFilename());
        IOUtils.copy(imgfile.getInputStream(),fos);
        fos.close();
    }
    @GetMapping(value="/{id}/icon",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getIcon(@PathVariable int id) throws Exception{
        if(log.isTraceEnabled()){
            log.trace("getIcon(" + id + ")");
        }
        String iconFile="src/main/resources/icon.jpg";
        InputStream is = new FileInputStream(iconFile);
        return IOUtils.toByteArray(is);
    }

    /**
     * @Valid 注解表示需要验证传入的参数TvSeriesDto，需要验证的Field在TvSeriesDto内通过注解定义(@NotNull,@DecimalMin等)
     * @param tvSeriesDto
     * @return
     */
    @PostMapping
    public TvSeriesDto insertOne(@Valid @RequestBody TvSeriesDto tvSeriesDto){
        if(log.isTraceEnabled()) {
            log.trace("这里应该重写新增TvSeriesDto对象的方法");
        }
        //TODO:待修改
        tvSeriesDto.setId(9999);
        return  tvSeriesDto;
    }
}
