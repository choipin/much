package com.much.it.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.much.it.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@Mapper
@Component
@DS("postgresql")
public interface UsersMapper {

    User selectById(Long id);

    Integer insertUser(User user);

    Integer insertBatch(List<User> users);

    Integer deleteAll();

    Integer deleteById(long id);

    List<User> findAll();
}
