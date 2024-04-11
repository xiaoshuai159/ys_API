package com.ys.demo.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @ExcelProperty("用户编号")
    @ColumnWidth(20)
    private Integer id;
    @ExcelProperty("创建时间")
    @ColumnWidth(30)
    private LocalDateTime created;
    @ExcelProperty("更新时间")
    @ColumnWidth(30)
    private LocalDateTime updated;
    @ExcelProperty("IP")
    @ColumnWidth(20)
    private String ip;
    @ExcelProperty("域名")
    @ColumnWidth(20)
    private String domain;
    @ExcelProperty("端口")
    @ColumnWidth(20)
    private Integer port;
    @ExcelIgnore
    private String server;
    @ExcelIgnore
    private String jarm_hash;
    @ExcelIgnore
    private String cloud_server;
    @ExcelProperty("单位名称")
    @ColumnWidth(20)
    private String org_name;
    @ExcelIgnore
    private String org_url;
    @ExcelIgnore
    private String org_domain;
    @ExcelIgnore
    private String dns_resolution;
    @ExcelIgnore
    private Integer record_num;
    @ExcelIgnore
    private String org_industry;
    @ExcelIgnore
    private String org_type;
    @ExcelIgnore
    private String org_region;
    @ExcelIgnore
    private String org_server;
    @ExcelIgnore
    private String org_cloud_server;
}
