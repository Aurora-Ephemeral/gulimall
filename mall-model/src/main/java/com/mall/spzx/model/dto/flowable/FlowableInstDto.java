package com.mall.spzx.model.dto.flowable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class FlowableInstDto {

    private String procDefKey;

    private String businessKey;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dueDate;

    private Map<String, Object> variables;
}
