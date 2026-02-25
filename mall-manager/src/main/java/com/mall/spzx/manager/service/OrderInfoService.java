package com.mall.spzx.manager.service;

import com.mall.spzx.model.vo.order.OrderStatisticsVo;

public interface OrderInfoService {
    OrderStatisticsVo getStatisticsData(String startTime, String endTime);
}
