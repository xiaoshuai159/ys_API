package com.ys.demo.mapper;

import com.ys.demo.pojo.Property;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PropertyMapper {
    @Select("select * from property")
    List<Property> selectAllProperty();

    List<Property> selectPartProperty(String org_name, String ip, LocalDate created, LocalDate updated,String cloud_server,String server,String org_region,String org_industry,String org_type,String record_num);

    List<Property> selectPropertyById(List<Integer> idList);
}
