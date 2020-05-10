package com.much.it.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.much.it.entity.ComCity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 市级行政单位信息表 Mapper 接口
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@Mapper
@Component
public interface ComCityMapper extends BaseMapper<ComCity> {

}
