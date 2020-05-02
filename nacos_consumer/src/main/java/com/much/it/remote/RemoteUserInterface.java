package com.much.it.remote;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@FeignClient(value = "nacos-provinder")
@RequestMapping("/user")
public interface RemoteUserInterface {

    @GetMapping("/{id}")
    public User selectById(@PathVariable("id") Long id);

    @PutMapping("/insert")
    public Integer insertUser(@RequestBody @Validated User user);

    @PutMapping("/insertBatch/{num}")
    public Integer insertBatch(@PathVariable("num") Integer num);

    @DeleteMapping("/deleteAll")
    public Integer deleteAll();

    @DeleteMapping("/{id}")
    public Integer deleteById(@PathVariable("id") long id);

    @PostMapping("/findByPage")
    public PageVO<User> findByPage(@Validated @RequestBody PageParam pageParam);
}
