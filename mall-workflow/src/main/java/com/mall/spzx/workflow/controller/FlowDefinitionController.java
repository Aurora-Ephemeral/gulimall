package com.mall.spzx.workflow.controller;

import com.mall.spzx.model.dto.flowable.FlowableInstDto;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import com.mall.spzx.workflow.service.IProcessDefinitionService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("flowable")
public class  FlowDefinitionController {

    @Autowired
    IProcessDefinitionService processDefinitionService;

    /**
     * import bpmn file and deploy
     * @param file
     * @param name
     * @return
     */
    @PostMapping("/processDefinition/deploy/import")
    public Result importFile (@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
        String deployId = processDefinitionService.importFile(file, name);
        return Result.build("部署成功：" + deployId, ResultCodeEnum.SUCCESS);
    }

    /**
     * start process by deploymentId
     * @param deploymentId
     * @return
     */
    @GetMapping("/processDefinition/getDefinition/{deploymentId}")
    public Result getDefinition(@PathVariable("deploymentId") String deploymentId) {
        ProcessDefinition processDefinition = processDefinitionService.getDefinition(deploymentId);
        Map<String, String> result = new HashMap<>();
        result.put("id", processDefinition.getId());
        result.put("name", processDefinition.getName());
        result.put("key", processDefinition.getKey());
        result.put("version", processDefinition.getVersion() + "");

        return Result.build(result, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/processInstance/start")
    public Result startProcessInstance(@RequestBody FlowableInstDto flowableInstDto) {
        String procInstId = processDefinitionService.startProcessInstance(flowableInstDto);
        return Result.build("启动流程实例成功：" + procInstId, ResultCodeEnum.SUCCESS);
    }
}
