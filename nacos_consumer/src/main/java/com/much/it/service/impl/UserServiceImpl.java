package com.much.it.service.impl;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.User;
import com.much.it.mapper.UsersMapper;
import com.much.it.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public User selectById(Long id) {
        return usersMapper.selectById(id);
    }

    @Override
    public Integer insertUser(User user) {
        return usersMapper.insertUser(user);
    }

    @Override
    public Integer insertBatch(Integer num) {
        List<User> users = IntStream.range(0, num).boxed().map(integer -> User.builder().createTime(new Date()).updateTime(new Date())
                .birthday(new Date()).sex(new Random().nextInt(2)).age(new Random().nextInt(100))
        .note(UUID.randomUUID().toString()).name(UUID.randomUUID().toString()).password(UUID.randomUUID().toString())
        .username(UUID.randomUUID().toString()).build()).collect(Collectors.toList());
        return usersMapper.insertBatch(users);
    }

    @Override
    public PageVO<User> findByPage(PageParam pageParam) {
        List<User> all = usersMapper.findAll();
        List<User> users = Optional.ofNullable(all).orElse(Collections.singletonList(new User())).stream()
                .skip(pageParam.getPageSize()*(pageParam.getPageNum()-1)).limit(pageParam.getPageSize()).collect(Collectors.toList());
        Integer total = all==null?0:all.size();
        return new PageVO<>(pageParam.getPageNum(),total,users);
    }

    @Override
    public Integer deleteById(long id) {
        return usersMapper.deleteById(id);
    }

    @Override
    public Integer deleteAll() {
        return usersMapper.deleteAll();
    }
}
