package com.much.it.service;

import com.much.it.entity.Order;
import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
public interface OrderService {

    public Order selectById(Long id);

    Integer insertOrder(Order order);

    Integer insertBatch(Integer num);

    PageVO<Order> findByPage(PageParam pageParam);

    Integer deleteAll();

    Integer deleteById(Long id);
}
