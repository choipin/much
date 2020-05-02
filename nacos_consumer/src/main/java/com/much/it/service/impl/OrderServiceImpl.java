package com.much.it.service.impl;

import com.much.it.entity.Order;
import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.mapper.OrderMapper;
import com.much.it.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order selectById(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public Integer insertOrder(Order order) {
        return orderMapper.insertOrder(order.setCreateTime(new Date()).setUpdateTime(new Date()));
    }

    @Override
    public Integer insertBatch(Integer num) {
        List<Order> orders = IntStream.range(0, num).boxed().map(integer -> Order.builder().createTime(new Date())
                .img(UUID.randomUUID().toString()).orderNumber(new Random().nextLong())
                .price(new BigDecimal(new Random().nextDouble())).productId(new Random().nextLong())
                .updateTime(new Date()).userId(new Random(100).nextLong()).build())
                .collect(Collectors.toList());
        return orderMapper.insertBatch(orders);
    }

    @Override
    public PageVO<Order> findByPage(PageParam pageParam) {
        List<Order> all = orderMapper.findAll();
        List<Order> orders = all.stream().skip(pageParam.getPageSize()*(pageParam.getPageNum()-1))
                .limit(pageParam.getPageSize()).collect(Collectors.toList());
        Integer total = all == null ? 0 : all.size();
        return new PageVO<Order>(pageParam.getPageNum(),total,orders);
    }

    @Override
    public Integer deleteAll() {
        return orderMapper.deleteAll();
    }

    @Override
    public Integer deleteById(Long id) {
        return orderMapper.deleteById(id);
    }
}
