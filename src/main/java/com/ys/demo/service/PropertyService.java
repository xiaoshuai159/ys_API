package com.ys.demo.service;

import com.ys.demo.pojo.PageBean;
import com.ys.demo.pojo.Property;

import java.time.LocalDate;
import java.util.List;

public interface PropertyService {
    List getAllProperty();
    PageBean<Property> selectPartProperty(Integer pageNum, Integer pageSize, String org_name, String ip, LocalDate created, LocalDate updated,String cloud_server,String server,String org_region,String org_industry,String org_type,String record_num);
    List<Property> selectPropertyById(List<Integer> idList);
}
