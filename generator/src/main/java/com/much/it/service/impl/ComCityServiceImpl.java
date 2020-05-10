package com.much.it.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.much.it.entity.ComCity;
import com.much.it.entity.PageParam;
import com.much.it.mapper.ComCityMapper;
import com.much.it.service.ComCityService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 市级行政单位信息表 服务实现类
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@Service
public class ComCityServiceImpl extends ServiceImpl<ComCityMapper, ComCity> implements ComCityService {

    @Autowired
    private ComCityMapper comCityMapper;
    @Override
    public ComCity getCityById(Integer id) {
        return comCityMapper.selectById(id);
    }

    @Override
    public Integer deleteCityById(Integer id) {
        return comCityMapper.deleteById(id);
    }

    @Override
    public Integer updateCityById(ComCity comCity) {
        return comCityMapper.updateById(comCity);
    }

    @Override
    public Integer insertCity(ComCity comCity) {
        return comCityMapper.insert(comCity);
    }

    @Override
    public List<ComCity> selectByPage(PageParam pageParam) {
        List<ComCity> list = comCityMapper.selectPage(new RowBounds(pageParam.getPageNum(), pageParam.getPageSize()), null);
        return null;
    }
}
