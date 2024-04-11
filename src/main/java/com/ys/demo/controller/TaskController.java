package com.ys.demo.controller;

import com.ys.demo.pojo.Result;
import com.ys.demo.pojo.Task;
import com.ys.demo.pojo.TaskDetail;
import com.ys.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping("/tasks")
    public Result<List<Task>> getAllTask(@RequestParam(required = true) Integer pageNum,
                                         @RequestParam(required = true) Integer pageSize,
                                         @RequestParam(required = false, value = "start_time") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTime,
                                         @RequestParam(required = false, value = "stop_time") @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate stopTime,
                                         @RequestParam(required = false, value = "name") String taskName
    ){
        Map map = taskService.getAllTask(pageNum, pageSize, startTime, stopTime, taskName);
        List<Task> list = (List<Task>) map.get("list");
        Integer total = (Integer) map.get("total");
        Integer ps = (Integer) map.get("pageSize");
        return Result.success(list,total,ps);
    };
    @PutMapping("/tasks/{id}")
    public Result changeTask(@PathVariable Integer id,
                             @RequestBody Map<String, Integer> rb
    ){
        Integer status = rb.get("status");
        if (status != null && status >= 0 && status <= 2) {
            // 执行相应的逻辑
            if(status == 0){
                // 执行 取消逻辑   ”状态“ 改成 ”已取消“
                taskService.changeTaskStatus(id, "已取消");
            }else if(status == 1){
                // 执行 下发逻辑   ”状态“ 改成 ”执行中“
                taskService.changeTaskStatus(id, "执行中");
            }else if(status == 2){
                // 执行 终止逻辑   ”状态“ 改成 ”终止“
                taskService.changeTaskStatus(id, "终止");
            }
            return Result.successMarker();
        } else {
            return Result.error("Status must be 0, 1, or 2");
        }
    }
    @DeleteMapping("/tasks/{id}")
    public Result deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);
        return Result.successMarker();
    }
    @PostMapping("/tasks")
    public Result addTask(@RequestBody @Validated Map<String,String> rb){
        @Pattern(regexp = "^.{1,25}$", message = "任务名长度应在1-25个字符之间")
        String taskName = rb.get("task_name");
        @Pattern(regexp = "^\\b(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\\b(,\\s*\\b(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\\b)*$", message = "IP地址格式不正确")
        String ipString = rb.get("ips");
        String []ipArr = ipString.split("\\s*,\\s*");
        int arrLen = ipArr.length;
        try {
            taskService.addTask(taskName, ipArr, arrLen);
            return Result.successMarker();
        } catch (Exception e) {
            System.out.println(e);
            // 处理异常
            return Result.error("插入失败");
        }
    }
    @GetMapping("/tasks/{id}/ips")
    public Result<List<TaskDetail>> getTaskDetailById(@PathVariable Integer id){
        Map map = taskService.getTaskDetailById(id);
        List<TaskDetail> list = (List<TaskDetail>) map.get("list");
        Integer total = (Integer) map.get("total");
        Integer ps = (Integer) map.get("pageSize");
        return Result.success(list,total,ps);
    }
}
