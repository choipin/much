package com.much.it.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.much.it.blockhandler.UserBlockHandler;
import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.User;
import com.much.it.fallback.UserFallBack;
import com.much.it.remote.RemoteUserInterface;
import com.much.it.service.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RemoteUserServiceImpl implements RemoteUserService {

    @Autowired
    private RemoteUserInterface remoteUserInterface;

    @Override
    @SentinelResource(value = "selectById", fallback = "selectByIdFallBack", fallbackClass = UserFallBack.class,
            blockHandler = "selectByIdBlockHandler", blockHandlerClass = UserBlockHandler.class)
    public User selectById(Long id) {
        return remoteUserInterface.selectById(id);
    }

    @Override
    @SentinelResource(value = "insertUser", fallback = "insertUserFallBack", fallbackClass = UserFallBack.class,
            blockHandler = "insertUserBlockHandler", blockHandlerClass = UserBlockHandler.class)
    public Integer insertUser(User user) {
        return remoteUserInterface.insertUser(user);
    }

    @Override
    @SentinelResource(value = "insertBatch", fallback = "insertBatchFallBack", fallbackClass = UserFallBack.class,
            blockHandler = "insertBatchBlockHandler", blockHandlerClass = UserBlockHandler.class)
    public Integer insertBatch(Integer num) {
        return remoteUserInterface.insertBatch(num);
    }

    @Override
    @SentinelResource(value = "deleteAll", fallback = "deleteAllFallBack", fallbackClass = UserFallBack.class,
            blockHandler = "deleteAllBlockHandler", blockHandlerClass = UserBlockHandler.class)
    public Integer deleteAll() {
        return remoteUserInterface.deleteAll();
    }

    @Override
    @SentinelResource(value = "deleteById", fallback = "deleteByIdFallBack", fallbackClass = UserFallBack.class,
            blockHandler = "deleteByIdHandler", blockHandlerClass = UserBlockHandler.class)
    public Integer deleteById(long id) {
        return remoteUserInterface.deleteById(id);
    }

    @Override
    @SentinelResource(value = "findByPage", fallback = "findByPageFallBack", fallbackClass = UserFallBack.class,
            blockHandler = "findByPageBlockHandler", blockHandlerClass = UserBlockHandler.class)
    public PageVO<User> findByPage(PageParam pageParam) {
        return remoteUserInterface.findByPage(pageParam);
    }
}
