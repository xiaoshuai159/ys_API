package com.ys.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public interface TaskService {
    Map getAllTask(Integer pageNum, Integer pageSize, LocalDate startTime, LocalDate stopTime, String taskName);

    void changeTaskStatus(Integer id, String status);

    void deleteTask(Integer id);

    Map getTaskDetailById(Integer id);

    void addTask(String taskName, String[] ipArr, int arrLen);
}
