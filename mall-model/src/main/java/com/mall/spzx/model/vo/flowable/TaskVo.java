package com.mall.spzx.model.vo.flowable;

import lombok.Data;

@Data
public class TaskVo {
    private String procDefKey;

    private String assignee;

    private String StartTime;

    private String EndTime;

    private Integer pageNum;

    private Integer pageSize;
}
