package com.much.it.mapper;

import com.much.it.entity.BusCaseDomain;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 案例领域表 Mapper 接口
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@Mapper
@Component
public interface BusCaseDomainMapper extends BaseMapper<BusCaseDomain> {

}
