package com.mall.spzx.manager.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderStatistics> {

    OrderStatistics selectStatisticsByDate(String yesterday);
}
