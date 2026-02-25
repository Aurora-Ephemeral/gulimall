package com.mall.spzx.manager.controller;

import com.mall.spzx.log.enums.BizType;
import com.mall.spzx.manager.service.OrderInfoService;
import com.mall.spzx.model.vo.common.Result;
import com.mall.spzx.model.vo.common.ResultCodeEnum;
import com.mall.spzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mall.spzx.log.annotation.Log;
import java.util.List;

@RestController
@RequestMapping("/admin/order/info")
public class OrderInfoController {

    @Autowired
    OrderInfoService orderInfoService;

    @Log(title="订单统计", bizType = BizType.QUERY)
    @GetMapping("/getStatisticsData")
    public Result getStatisticsData(String startTime, String endTime) {
        OrderStatisticsVo result = orderInfoService.getStatisticsData(startTime, endTime);

        return Result.build(result, ResultCodeEnum.SUCCESS);
    }
}
