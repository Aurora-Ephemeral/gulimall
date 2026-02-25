package com.mall.spzx.workflow.service;

import com.mall.spzx.model.dto.flowable.FlowableInstDto;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.web.multipart.MultipartFile;

public interface IProcessDefinitionService {
    String importFile(MultipartFile file, String name);

    ProcessDefinition getDefinition(String deploymentId);

    String startProcessInstance(FlowableInstDto flowableInstDto);
}
