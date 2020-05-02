package com.much.it.service.impl;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.User;
import com.much.it.mapper.UserMapper;
import com.much.it.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @project: much
 * @date: 2020/5/1
 * @author: Wenxin
 * @version: 1.0
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public Integer insertUser(User user) {
        return userMapper.insertUser(user.setBirthday(new Date()).setCreateTime(new Date()).setUpdateTime(new Date()));
    }

    @Override
    public Integer insertBatch(Integer num) {
        List<User> users = IntStream.range(0, num).boxed().map(integer -> User.builder().username(UUID.randomUUID().toString())
                .password(UUID.randomUUID().toString()).name(UUID.randomUUID().toString()).note(UUID.randomUUID().toString())
                .age(new Random().nextInt(100)).sex(new Random().nextInt(2)).birthday(new Date()).updateTime(new Date())
                .createTime(new Date()).build()).collect(toList());
        return userMapper.insertBatch(users);
    }

    @Override
    public Integer deleteAll() {
        return userMapper.deleteAll();
    }

    @Override
    public Integer deleteById(long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public PageVO<User> findByPage(PageParam pageParam) {
        List<User> all = userMapper.findAll();
        List<User> users = Optional.ofNullable(all).orElse(Arrays.asList(new User())).stream().skip(pageParam.getPageSize() * (pageParam.getPageNum() - 1)).limit(pageParam.getPageSize()).collect(toList());
        Integer total = all == null ? 0 : all.size();
        return new PageVO<User>(pageParam.getPageNum(), total, users);
    }
}
