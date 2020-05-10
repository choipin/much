package com.much.it.mapper;

import com.much.it.entity.BusCaseProcess;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 案例过程表 Mapper 接口
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@Mapper
@Component
public interface BusCaseProcessMapper extends BaseMapper<BusCaseProcess> {

}
