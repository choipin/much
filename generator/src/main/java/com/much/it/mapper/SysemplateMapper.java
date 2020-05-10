package com.much.it.mapper;

import com.much.it.entity.Sysemplate;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 模板表（邮件/短信/富文本/文案等） Mapper 接口
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@Mapper
@Component
public interface SysemplateMapper extends BaseMapper<Sysemplate> {

}
