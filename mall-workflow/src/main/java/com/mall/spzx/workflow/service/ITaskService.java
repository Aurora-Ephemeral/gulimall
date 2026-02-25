package com.mall.spzx.workflow.service;

import com.mall.spzx.model.TableDataInfo;
import com.mall.spzx.model.vo.flowable.TaskVo;

public interface ITaskService {
    TableDataInfo getMyToDoList(TaskVo taskVo);
}
