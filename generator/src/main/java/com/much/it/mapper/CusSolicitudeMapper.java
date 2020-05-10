package com.much.it.mapper;

import com.much.it.entity.CusSolicitude;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 关注表 Mapper 接口
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@Mapper
@Component
public interface CusSolicitudeMapper extends BaseMapper<CusSolicitude> {

}
