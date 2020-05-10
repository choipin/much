package com.much.it.controller;


import com.much.it.entity.ComCity;
import com.much.it.entity.PageParam;
import com.much.it.service.ComCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 市级行政单位信息表 前端控制器
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@RestController
@RequestMapping("/comCity")
public class ComCityController {

    @Autowired
    private ComCityService comCityService;

    @GetMapping("/{id}")
    private ComCity getCityById(@PathVariable Integer id){
        return comCityService.getCityById(id);
    }

    @DeleteMapping("/{id}")
    private Integer deleteCityById(@PathVariable Integer id){
        return comCityService.deleteCityById(id);
    }

    @PatchMapping("/update")
    private Integer updateCityById(@RequestBody @Validated ComCity comCity){
        return comCityService.updateCityById(comCity);
    }

    @PutMapping("/insert")
    private Integer insertCity(@RequestBody @Validated ComCity comCity){
        return comCityService.insertCity(comCity);
    }

    @PostMapping("/selectByPage")
    private List<ComCity> selectByPage(@RequestBody @Validated PageParam pageParam){
        return comCityService.selectByPage(pageParam);
    }
}

