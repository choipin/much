package com.much.it.controller;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.User;
import com.much.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @project: much
 * @date: 2020/5/1
 * @author: Wenxin
 * @version: 1.0
 */
@RestController
@RequestMapping("/local/user")
public class UserControler {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User selectById(@PathVariable("id") Long id) {
        return userService.selectById(id);
    }
    @PutMapping("/insertUser")
    public Integer insertUser(@RequestBody @Validated User user) {
        return userService.insertUser(user);
    }

    @PutMapping("/{num}")
    public Integer insertBatch(@PathVariable("num")  Integer num) {
        return userService.insertBatch(num);
    }

    @PostMapping("/findByPage")
    public PageVO<User> findByPage(@RequestBody @Validated PageParam pageParam) {
        return userService.findByPage(pageParam);
    }

    @DeleteMapping("/{id}")
    public Integer deleteById(@PathVariable("id") long id) {
        return userService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public Integer deleteAll() {
        return userService.deleteAll();
    }

}
