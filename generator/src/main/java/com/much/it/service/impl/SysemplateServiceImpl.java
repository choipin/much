package com.much.it.service.impl;

import com.much.it.entity.Sysemplate;
import com.much.it.mapper.SysemplateMapper;
import com.much.it.service.SysemplateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 模板表（邮件/短信/富文本/文案等） 服务实现类
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@Service
public class SysemplateServiceImpl extends ServiceImpl<SysemplateMapper, Sysemplate> implements SysemplateService {

}
