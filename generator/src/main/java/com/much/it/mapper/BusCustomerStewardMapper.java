package com.much.it.mapper;

import com.much.it.entity.BusCustomerSteward;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * C端用户管家服务信息表 Mapper 接口
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@Mapper
@Component
public interface BusCustomerStewardMapper extends BaseMapper<BusCustomerSteward> {

}
