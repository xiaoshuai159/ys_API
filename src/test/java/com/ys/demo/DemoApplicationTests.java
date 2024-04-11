package com.ys.demo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ys.demo.mapper.TaskMapper;
import com.ys.demo.pojo.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {
//    @Autowired
//    private PropertyService propertyService;
//    @Test
//    void contextLoads() {
//        List<Property> list = propertyService.selectPartProperty(pageNum, pageSize);
//        System.out.println(list);
//    }
//    @Autowired
//    private TaskService taskService;
//    @Test
//    void contextLoads() {
//        List<Property> list = taskService.getAllTask(1, 10);
//        System.out.println(list);
//    }
//    @Autowired
//    private TaskMapper taskMapper;
//    @Test
//    public void getAllTask() {
//        Map map = new HashMap();
//        PageHelper.startPage(1, 10);
//        List<Task> list = taskMapper.getAllTask(2023-01-01 00:00:00, "2024-03-01", "Task");
//        map.put("list", list);
//        Page<Task> p = (Page<Task>)list;
//        System.out.println(p.getTotal());
//        System.out.println(p.getPageSize());
//        map.put("pageSize", p.getPageSize());
//        map.put("total",p.getTotal());
//        return map;
//    }
}
