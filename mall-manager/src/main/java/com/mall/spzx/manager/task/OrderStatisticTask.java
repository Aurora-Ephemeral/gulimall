package com.mall.spzx.manager.task;

import com.mall.spzx.manager.service.mapper.OrderInfoMapper;
import com.mall.spzx.manager.service.mapper.OrderStatisticsMapper;
import com.mall.spzx.model.entity.order.OrderStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class OrderStatisticTask {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Scheduled(cron = "0 0 2 * * ?")
    // 每天凌晨执行2点执行
    public void insertOrderStatistic() {
        Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String yesterdayStr = format.format(yesterday);
        OrderStatistics result =  orderInfoMapper.selectStatisticsByDate(yesterdayStr);
        if(result != null) {
            orderStatisticsMapper.insert(result);
        }
    }
}
