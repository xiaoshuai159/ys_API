package com.ys.demo.mapper;

import com.ys.demo.pojo.Detail;
import com.ys.demo.pojo.Task;
import com.ys.demo.pojo.TaskDetail;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TaskMapper {
    List<Task> getAllTask(LocalDate startTime, LocalDate stopTime, String taskName);

    @Update("UPDATE tasklist SET status = #{status} WHERE id = #{id}")
    void changeTaskStatus(Integer id, String status);


    @Insert("INSERT INTO taskdetail (ip, created_time, update_time, duration, status, task_list_id, port_scan, jarm_hash, web_info, cyberspace, monitor, cluster, error) " +
            "VALUES (#{ip}, #{created_time}, #{update_time}, #{duration}, #{status}, #{taskListId}, #{detail.port_scan}, #{detail.jarm_hash}, #{detail.web_info}, #{detail.cyberspace},#{detail.monitor},#{detail.cluster},#{detail.error})")
    void addTaskdetail(TaskDetail taskDetail);

    @Insert("INSERT INTO tasklist (task_name, ip_num, status, create_time, update_time) " +
            "VALUES (#{task_name}, #{ip_num}, #{status}, #{create_time}, #{update_time})")
    void addTask(Task task);

    @Select("select * from taskdetail where task_list_id = #{id}")
    @Results({
        @Result(property = "taskListId", column = "task_list_id"),
        @Result(property = "detail.port_scan", column = "port_scan"),
        @Result(property = "detail.jarm_hash", column = "jarm_hash"),
        @Result(property = "detail.web_info", column = "web_info"),
        @Result(property = "detail.cyberspace", column = "cyberspace"),
        @Result(property = "detail.monitor", column = "monitor"),
        @Result(property = "detail.cluster", column = "cluster"),
    })
    List<TaskDetail> getTaskDetailById(Integer id);

    @Delete("DELETE FROM taskdetail WHERE task_list_id = #{id}")
    void deleteTaskDetail(Integer id);


    @Delete("DELETE FROM tasklist WHERE id = #{id}")
    void deleteTask(Integer id);

    @Select("SELECT * from tasklist where task_name = #{taskName}")
    Task getTaskByTaskName(String taskName);

}
