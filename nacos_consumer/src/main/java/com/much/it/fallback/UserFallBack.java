package com.much.it.fallback;

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
public class UserFallBack {

    public User selectByIdFallBack(Long id, Throwable throwable) {
        throwable.printStackTrace();
        return User.builder().id(id).username("default").password("default").name("default").note("default").age(1).sex(2).birthday(new Date()).updateTime(new Date()).createTime(new Date()).build();
    }

    public Integer insertUserFallBack(User user, Throwable throwable) {
        throwable.printStackTrace();
        return 666;
    }

    public Integer insertBatchFallBack(Integer num, Throwable throwable) {
        throwable.printStackTrace();
        return 1888;
    }

    public Integer deleteAllFallBack(Throwable throwable) {
        throwable.printStackTrace();
        return 2888;
    }

    public Integer deleteByIdFallBack(long id, Throwable throwable) {
        throwable.printStackTrace();
        return 3888;
    }

    public PageVO<User> findByPageFallBack(PageParam pageParam, Throwable throwable) {
        throwable.printStackTrace();
        return new PageVO<User>(1, 588, Collections.singletonList(User.builder().username("default").password("default").name("default").note("default").age(1).sex(2).birthday(new Date()).updateTime(new Date()).createTime(new Date()).build()));
    }
}
