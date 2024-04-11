package com.ys.demo.controller;
import com.alibaba.excel.EasyExcel;
import com.ys.demo.pojo.PageBean;
import com.ys.demo.pojo.Property;
import com.ys.demo.pojo.Result;
import com.ys.demo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    @GetMapping("/ip-org-list")
    public Result<List<Property>> getAllPropertyList(@RequestParam(required = true) Integer pageNum,
                                                     @RequestParam(required = true) Integer pageSize,
                                                     @RequestParam(required = false, value = "org_name") @Pattern(regexp = "^.{0,25}$",message = "单位名必须小于25位！") String org_name,
                                                     @RequestParam(required = false, value = "ip") String ip,
                                                     @RequestParam(required = false, value = "start_created_time") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate created,
                                                     @RequestParam(required = false, value = "end_created_time") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate updated,
                                                     @RequestParam(required = false, value = "cloud_server") String cloud_server,
                                                     @RequestParam(required = false, value = "server") String server,
                                                     @RequestParam(required = false, value = "region") String org_region,
                                                     @RequestParam(required = false, value = "industry") String org_industry,
                                                     @RequestParam(required = false, value = "org_type") String org_type,
                                                     @RequestParam(required = false, value = "record_num") String record_num
    ){
        PageBean<Property> list = propertyService.selectPartProperty(pageNum, pageSize, org_name, ip, created, updated, cloud_server, server, org_region, org_industry, org_type, record_num); // 改啰嗦了
        List<Property> l = list.getList();
        Integer total = list.getTotal();
        Integer ps = list.getPageSize();
        return Result.success(l,total,ps);
    }
    @GetMapping("/export-ip-org-relations")
    public void exportExcel(HttpServletResponse response, @RequestParam("ids") String ids) throws IOException {
        String[] idArr = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for(String id:idArr){
            idList.add(Integer.parseInt(id));
        }
        List<Property> list = propertyService.selectPropertyById(idList); // 改啰嗦了
        String fileName = "test.xlsx";

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

        // 使用 EasyExcel 将数据写入到 HttpServletResponse 的输出流中
        EasyExcel.write(response.getOutputStream(), Property.class)
                .sheet("资产信息")
                .doWrite(list);
    }
}
