package com.mall.spzx.workflow.controller;

import com.mall.spzx.common.web.controller.BaseController;
import com.mall.spzx.model.TableDataInfo;
import com.mall.spzx.model.vo.flowable.TaskVo;
import com.mall.spzx.workflow.service.ITaskService;
import org.flowable.bpmn.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/flowable/task")
public class FlowTaskController extends BaseController {

    @Autowired
    ITaskService taskService;

    @GetMapping("/myInitiate")
    public TableDataInfo myInitiate(TaskVo taskVo) {

        return taskService.getMyToDoList(taskVo);
    }

    @GetMapping("/myToDo")
    public TableDataInfo myToDo(TaskVo taskVo) {
        return taskService.getMyToDoList(taskVo);
    }

    @GetMapping("/myDone")
    public TableDataInfo myDone() {
        return null;
    }
}
