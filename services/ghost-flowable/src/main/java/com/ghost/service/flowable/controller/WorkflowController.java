package com.ghost.service.flowable.controller;

import com.ghost.service.flowable.service.FlowableService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

    @Autowired
    private FlowableService flowableService;

    @GetMapping("/deploy")
    public String deployProcess() {
        flowableService.deployProcess();
        return "Process deployed!";
    }

    @GetMapping("/start")
    public String startProcess() {
        flowableService.startProcess();
        return "Process started!";
    }

    @GetMapping("/complete")
    public String completeTask() {
        flowableService.completeTask();
        return "Task completed!";
    }

    // 查询运行中流程
    @GetMapping("/running")
    public List<ProcessInstance> getRunningProcesses() {
        return flowableService.getRunningProcesses();
    }

    // 查询已完成流程
    @GetMapping("/completed")
    public List<HistoricProcessInstance> getCompletedProcesses() {
        return flowableService.getCompletedProcesses();
    }

    // 查询待办任务
    @GetMapping("/todo")
    public List<Task> getTodoTasks(@RequestParam String userId) {
        return flowableService.getTodoTasks(userId);
    }

}