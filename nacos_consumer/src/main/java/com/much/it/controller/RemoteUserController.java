package com.much.it.controller;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.User;
import com.much.it.service.RemoteUserService;
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
@RequestMapping("/remote/user")
public class RemoteUserController {

    @Autowired
    private RemoteUserService remoteUserService;

    @GetMapping("/{id}")
    public User selectById(@PathVariable("id") Long id) {
        return remoteUserService.selectById(id);
    }

    @PutMapping("/insert")
    public Integer insertUser(@RequestBody @Validated User user) {
        return remoteUserService.insertUser(user);
    }

    @PutMapping("/insertBatch/{num}")
    public Integer insertBatch(@PathVariable("num") Integer num) {
        return remoteUserService.insertBatch(num);
    }

    @DeleteMapping("/deleteAll")
    public Integer deleteAll() {
        return remoteUserService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public Integer deleteById(@PathVariable("id") long id) {
        return remoteUserService.deleteById(id);
    }

    @PostMapping("/findByPage")
    public PageVO<User> findByPage(@Validated @RequestBody PageParam pageParam) {
        return remoteUserService.findByPage(pageParam);
    }
}
