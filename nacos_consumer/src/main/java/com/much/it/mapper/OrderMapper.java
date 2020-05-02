package com.much.it.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.much.it.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@Mapper
@Component
@DS("master")
public interface OrderMapper {

    @DS("master")
    public Order selectById(Long id);

    Integer insertOrder(Order order);

    Integer insertBatch(List<Order> orders);

    List<Order> findAll();

    Integer deleteAll();

    Integer deleteById(Long id);
}
