package com.ys.demo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ys.demo.mapper.TaskMapper;
import com.ys.demo.pojo.Detail;
import com.ys.demo.pojo.Property;
import com.ys.demo.pojo.Task;
import com.ys.demo.pojo.TaskDetail;
import com.ys.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Override
    public Map getAllTask(Integer pageNum, Integer pageSize, LocalDate startTime, LocalDate stopTime, String taskName) {
        Map map = new HashMap();
        PageHelper.startPage(pageNum, pageSize);
        List<Task> list = taskMapper.getAllTask(startTime, stopTime, taskName);
        map.put("list", list);
        Page<Task> p = (Page<Task>)list;
        map.put("pageSize", p.getPageSize());
        map.put("total", (int) p.getTotal());
        return map;
    }

    @Override
    public void changeTaskStatus(Integer id, String status) {
        taskMapper.changeTaskStatus(id, status);
    }

    @Override
    @Transactional
    public void deleteTask(Integer id) {
        taskMapper.deleteTaskDetail(id);
        taskMapper.deleteTask(id);
    }

    @Override
    public Map getTaskDetailById(Integer id) {
        Map map = new HashMap();
        PageHelper.startPage(1, 10);
        List<TaskDetail> list = taskMapper.getTaskDetailById(id);
        map.put("list", list);
        Page<TaskDetail> p = (Page<TaskDetail>)list;
        map.put("pageSize", p.getPageSize());
        map.put("total", (int) p.getTotal());
        return map;
    }

    @Override
//    @Transactional
    public void addTask(String taskName, String[] ipArr, int arrLen) {
        Task t = new Task();
        t.setTask_name(taskName);
        t.setIp_num(arrLen);
        t.setStatus("等待下发");
        t.setCreate_time(LocalDateTime.now());
        t.setUpdate_time(LocalDateTime.now());
        taskMapper.addTask(t);
        Task curTask = taskMapper.getTaskByTaskName(taskName);
        int taskId = curTask.getId();
        for(String ip:ipArr){
            TaskDetail td = new TaskDetail();
            td.setIp(ip);
            td.setCreated_time(LocalDateTime.now());
            td.setUpdate_time(LocalDateTime.now());
            td.setDuration("0 hours");
            td.setStatus(false);
            td.setTaskListId(taskId);
            Detail detail = new Detail(0,0,0,0,0,0,"");
            td.setDetail(detail);
            taskMapper.addTaskdetail(td);
        }

    }
}
