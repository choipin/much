package com.much.it.controller;

import com.much.it.entity.Order;
import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order selectById(@PathVariable("id") Long id) {
        return orderService.selectById(id);
    }

    @PutMapping("/insert")
    public Integer insertOrder(@RequestBody @Validated Order order) {
        return orderService.insertOrder(order);
    }
    @PutMapping("/insertBatch/{num}")
    public Integer insertBatch(@PathVariable("num") Integer num) {
        return orderService.insertBatch(num);
    }

    @PostMapping("/findByPage")
    public PageVO<Order> findByPage(@RequestBody @Validated PageParam pageParam) {
        return orderService.findByPage(pageParam);
    }

    @DeleteMapping("/deleteAll")
    public Integer deleteAll() {
        return orderService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public Integer deleteById(@PathVariable("id") Long id) {
        return orderService.deleteById(id);
    }
}
