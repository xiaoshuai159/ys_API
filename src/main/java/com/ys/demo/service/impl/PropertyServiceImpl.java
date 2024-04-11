package com.ys.demo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ys.demo.mapper.PropertyMapper;
import com.ys.demo.pojo.PageBean;
import com.ys.demo.pojo.Property;
import com.ys.demo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyMapper propertyMapper;
    @Override
    public List getAllProperty() {
        List<Property> list = propertyMapper.selectAllProperty();
        return list;
    }


    @Override
    public PageBean<Property> selectPartProperty(Integer pageNum, Integer pageSize, String org_name, String ip, LocalDate created, LocalDate updated,String cloud_server,String server,String org_region,String org_industry,String org_type,String record_num) {
        PageBean<Property> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Property> list = propertyMapper.selectPartProperty(org_name, ip, created, updated, cloud_server, server, org_region, org_industry, org_type, record_num);
        // 强转目的： Page<>也是PageHelper提供的，可以拿到总页数和当前页数据等信息。
        // Page<>是list<>的子类方法，如果不强转的话（多态）父类list是拿不到子类page的方法。
        Page<Property> p = (Page<Property>) list;
        pb.setList(list);
        pb.setTotal((int) p.getTotal());
        pb.setPageSize(p.getPageSize());
        return pb;
    }

    @Override
    public List<Property> selectPropertyById(List<Integer> idList) {
        List<Property> l = propertyMapper.selectPropertyById(idList);
        return l;
    }
}
