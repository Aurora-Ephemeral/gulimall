package com.mall.spzx.workflow.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mall.spzx.model.TableDataInfo;
import com.mall.spzx.model.dto.flowable.FlowableTaskDTO;
import com.mall.spzx.model.vo.flowable.TaskVo;
import com.mall.spzx.workflow.service.ITaskService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    TaskService taskService;
    @Autowired
    HistoryService historyService;
    @Override
    public TableDataInfo getMyToDoList(TaskVo taskVo) {
        // fetch assignee task list
        TaskQuery taskQuery = taskService.createTaskQuery()
                .active()
                .includeProcessVariables()
                .taskCandidateOrAssigned(taskVo.getAssignee())
                .orderByTaskCreateTime()
                .desc();
        // extra condition
        if(taskVo.getProcDefKey() != null) {
          taskQuery.processDefinitionKeyLikeIgnoreCase(taskVo.getProcDefKey() + "%");
        }
        if(taskVo.getStartTime() != null) {
          taskQuery.taskCreatedAfter(DateUtil.parse(taskVo.getStartTime()));
        }
        if(taskVo.getEndTime() != null) {
            taskQuery.taskCreatedBefore(DateUtil.parse(taskVo.getEndTime()));
        }


        TableDataInfo result = new TableDataInfo();
        List<Task> taskList = taskQuery.listPage((taskVo.getPageNum() - 1) * taskVo.getPageSize(), taskVo.getPageSize());
        List<FlowableTaskDTO> taskDTOList = taskList.stream().map(task -> {
            FlowableTaskDTO taskDTO = new FlowableTaskDTO();
            taskDTO.setTaskId(task.getId());
            taskDTO.setExecutionId(task.getExecutionId());
            taskDTO.setProcInstId(task.getProcessInstanceId());
            taskDTO.setStartTime(task.getCreateTime());
            taskDTO.setTaskDefKey(task.getTaskDefinitionKey());
            taskDTO.setAssigneeName(task.getAssignee());
            // 获取当前流程实例信息
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            taskDTO.setBusinessKey(historicProcessInstance.getBusinessKey());
            taskDTO.setStarterId(historicProcessInstance.getStartUserId());

            return taskDTO;
        }).toList();
        result.setTotal(taskQuery.count());
        result.setRows(taskDTOList);
        result.setCode(200);
        return result;
    }
}
