package com.much.it.service;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
public interface RemoteUserService {

    public User selectById(@PathVariable("id") Long id);

    public Integer insertUser(@RequestBody @Validated User user);

    public Integer insertBatch(@PathVariable("num") Integer num);

    public Integer deleteAll();

    public Integer deleteById(@PathVariable("id") long id);

    public PageVO<User> findByPage(@Validated @RequestBody PageParam pageParam);
}
