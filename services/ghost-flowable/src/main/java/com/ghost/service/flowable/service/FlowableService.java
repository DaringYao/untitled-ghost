package com.ghost.service.flowable.service;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.history.HistoricProcessInstanceQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowableService {

    private final RepositoryService repositoryService;

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    private final HistoryService historyService;

    public FlowableService(RepositoryService repositoryService, RuntimeService runtimeService, TaskService taskService, HistoryService historyService) {
        this.repositoryService = repositoryService;
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.historyService = historyService;
    }

    // 部署流程定义
    public void deployProcess() {
        repositoryService.createDeployment()
                .addClasspathResource("processes/hello-world.bpmn20.xml")
                .deploy();
    }

    // 启动流程实例
    public void startProcess() {
        runtimeService.startProcessInstanceByKey("helloWorldProcess");
    }

    // 查询并完成任务
    public void completeTask() {
        String taskId = taskService.createTaskQuery()
                .taskAssignee("user1")
                .singleResult()
                .getId();
        taskService.complete(taskId);
    }

    public List<ProcessInstance> getRunningProcesses() {
        // 创建运行中流程实例查询
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        // 过滤条件（可选）
        // query.processDefinitionKey("yourProcessKey");
        // query.startedBy("userId");
        return query.list(); // 返回所有运行中流程
    }

    public List<HistoricProcessInstance> getCompletedProcesses() {
        // 创建已完成流程实例查询
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
        // 过滤条件（可选）
        // query.processDefinitionKey("yourProcessKey");
        // query.finishedAfter(new Date());
        return query.list(); // 返回所有已完成流程
    }

    public List<Task> getTodoTasks(String userId) {
        // 创建待办任务查询
        TaskQuery query = taskService.createTaskQuery();
        // 按用户过滤待办任务
        query.taskAssignee(userId);
        // 过滤条件（可选）
        // query.processInstanceId("processInstanceId");
        // query.taskName("taskName");
        return query.list(); // 返回所有待办任务
    }
}