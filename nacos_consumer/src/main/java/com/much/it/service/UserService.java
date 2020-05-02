package com.much.it.service;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.User;

/**
 * @description:
 * @project: much
 * @date: 2020/5/1
 * @author: Wenxin
 * @version: 1.0
 */
public interface UserService {

    User selectById(Long id);

    Integer insertUser(User user);

    Integer insertBatch(Integer num);

    PageVO<User> findByPage(PageParam pageParam);

    Integer deleteById(long id);

    Integer deleteAll();
}
