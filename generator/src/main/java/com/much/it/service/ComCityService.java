package com.much.it.service;

import com.baomidou.mybatisplus.service.IService;
import com.much.it.dto.PageParam;
import com.much.it.entity.ComCity;

import java.util.List;

/**
 * <p>
 * 市级行政单位信息表 服务类
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
public interface ComCityService extends IService<ComCity> {

    ComCity getCityById(Integer id);

    Integer deleteCityById(Integer id);

    Integer updateCityById(ComCity comCity);

    Integer insertCity(ComCity comCity);

    List<ComCity> selectByPage(PageParam pageParam);
}
