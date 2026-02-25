package com.mall.spzx.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.spzx.manager.service.OrderInfoService;
import com.mall.spzx.manager.service.mapper.OrderInfoMapper;
import com.mall.spzx.model.entity.order.OrderStatistics;
import com.mall.spzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Wrapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Override
    public OrderStatisticsVo getStatisticsData(String startTime, String endTime) {
        QueryWrapper<OrderStatistics> wrapper = new QueryWrapper<>();

        // 设置查询条件
        wrapper.between(startTime != null && !startTime.isEmpty() && endTime != null && !endTime.isEmpty(), "create_time", startTime, endTime);
        List<OrderStatistics> list = orderInfoMapper.selectList(wrapper);

        // 组装数据
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<String> xAxis = list.stream().map(item -> formatter.format(item.getOrderDate())).collect(Collectors.toList());
        List<BigDecimal> yAxis = list.stream().map(OrderStatistics::getTotalAmount).collect(Collectors.toList());

        OrderStatisticsVo vo = new OrderStatisticsVo();
        vo.setDateList(xAxis);
        vo.setAmountList(yAxis);
        return vo;
    }
}
