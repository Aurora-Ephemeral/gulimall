package com.mall.spzx.model.dto.flowable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FlowableTaskDTO {

    private String taskId;

    private String executionId;

    private String procInstId;

    private String businessKey;

    private String taskDefKey;

    private String assigneeName;

    private String assigneeId;

    private String starterId;

    private String starterName;

    private String procDefKey;

    private String procDefId;

    private String procDeployId;

    private String duration;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dueDate;
    private String priority;
    private String dueFlag;

}
