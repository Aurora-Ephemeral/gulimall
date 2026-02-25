package com.mall.spzx.workflow.service.impl;

import com.mall.spzx.model.dto.flowable.FlowableInstDto;
import com.mall.spzx.workflow.service.IProcessDefinitionService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.spring.boot.ProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProcessDefinitionServiceImpl implements IProcessDefinitionService {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Override
    public String importFile(MultipartFile file, String name) {
        try {
            // 名称要以bpmn20结尾
           Deployment deployment =  repositoryService.createDeployment()
                   .addInputStream(file.getOriginalFilename(), file.getInputStream())
                   .name(name)
                   .deploy();
           return deployment.getId();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProcessDefinition getDefinition(String deploymentId) {
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .deploymentId(deploymentId)
                .singleResult();
        return processDefinition;
    }

    @Override
    public String startProcessInstance(FlowableInstDto flowableInstDto) {
        //TODO: get user info from spring security later
        identityService.setAuthenticatedUserId("dummy-id");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(flowableInstDto.getProcDefKey(), flowableInstDto.getBusinessKey(), flowableInstDto.getVariables());

        return processInstance.getProcessInstanceId();
    }
}
