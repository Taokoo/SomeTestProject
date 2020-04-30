package com.taokoo.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testLeave")
public class TestLeaveDemo {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private HistoryService historyService;
    
    /**
     * 发起一个流程
     * @param userId
     * @param money
     * @param descption
     * @return
     */
    @RequestMapping(value = "add")
    public String addExpense(String userId, String descption) {
        //启动流程
        HashMap<String, Object> map = new HashMap<>();
        map.put("leaveUser", userId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("TaokooTestLeave", map);
        return "提交成功.流程Id为：" + processInstance.getId();
    }
    
    /**
     * 获取审批管理列表
     * @param userId
     * @return
     */
    @RequestMapping(value = "list")
    public Object list(String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        return tasks.toArray()[0].toString();
    }
    
    /**
     * 批准
     * @param taskId 任务ID
     * @param assignee 审核人（编号）
     * @return
     */
    @RequestMapping(value = "apply")
    public String apply(@RequestParam("taskId")String taskId,@RequestParam("assignee")String assignee,@RequestBody Map<String, Object> map) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        taskService.setAssignee(taskId, assignee);//设置审核人
        // 设置任务参数，也可不设置：key value，只是示例
        // 带 Local 为局部参数，只适用于本任务，不带 Local 为全局任务，可在其他任务调用参数
        taskService.setVariableLocal(taskId, "status", true);
        //通过审核
        map.put("outcome", "通过");
        taskService.complete(taskId, map);
        return "申请通过";
    }
    
    /**
     * 拒绝
     * @param taskId
     * @param assignee 审核人（编号）
     * @return
     */
    @RequestMapping(value = "reject")
    public String reject(@RequestParam("taskId")String taskId,@RequestParam("assignee")String assignee,@RequestBody Map<String, Object> map) {
    	taskService.setAssignee(taskId, assignee);//设置审核人
    	map.put("outcome", "驳回");
        taskService.complete(taskId, map);
        return "申请驳回";
    } 
    
    /**
     * 生成流程图
     * @param httpServletResponse
     * @param processId 任务ID
     * @throws Exception
     */
    @RequestMapping(value = "processDiagram")
    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
 
        //流程走完的不显示图
        if (pi == null) {
            return;
        }
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        String InstanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService
                .createExecutionQuery()
                .processInstanceId(InstanceId)
                .list();
 
        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }
 
        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = httpServletResponse.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
    
    /**
     * 通过流程实例 Id，判断流程是否结束
     * @param processInstanceId 流程实例 Id
     * @return true 结束，false 未结束
     */
    @RequestMapping(value = "/checkProcessInstanceFinish/{processInstanceId}", method = RequestMethod.GET)
    public boolean checkProcessInstanceFinish(@PathVariable(value = "processInstanceId") String processInstanceId) {
        boolean isFinish = false;
        // 根据流程 ID 获取未完成的流程中是否存在此流程
        long count = historyService.createHistoricProcessInstanceQuery().unfinished().processInstanceId(processInstanceId).count();
        // 不存在说明没有结束
        if (count == 0) {
            isFinish = true;
        }
        return isFinish;
    }
}
