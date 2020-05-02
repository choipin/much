package com.much.it.blockhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.User;

import java.util.Collections;
import java.util.Date;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
public class UserBlockHandler {

    public User selectByIdBlockHandler(Long id, BlockException exception) {
        exception.printStackTrace();
        return User.builder().id(id).username("default").password("default").name("default").note("default").age(1).sex(2).birthday(new Date()).updateTime(new Date()).createTime(new Date()).build();
    }

    public Integer insertUserBlockHandler(User user, BlockException exception) {
        exception.printStackTrace();
        return 666;
    }

    public Integer insertBatchBlockHandler(Integer num, BlockException exception) {
        exception.printStackTrace();
        return 8888;
    }

    public Integer deleteAllBlockHandler(BlockException exception) {
        exception.printStackTrace();
        return 8848;
    }

    public Integer deleteByIdBlockHandler(long id, BlockException exception) {
        exception.printStackTrace();
        return 9999;
    }

    public PageVO<User> findByPageBlockHandler(PageParam pageParam, BlockException exception) {
        return new PageVO<>(66, 88, Collections.singletonList(User.builder().id(1L).username("default").password("default").name("default").note("default").age(1).sex(2).birthday(new Date()).updateTime(new Date()).createTime(new Date()).build()));
    }
}
