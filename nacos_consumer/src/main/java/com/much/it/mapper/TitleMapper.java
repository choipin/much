package com.much.it.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.much.it.entity.Title;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@Mapper
@Component
@DS("mysql")
public interface TitleMapper {

    Title selectById(Long empNo);

    Integer insertTitle(Title title);

    Integer insertBatch(List<Title> titles);

    List<Title> findAll();

    Integer deleteAll();

    Integer deleteByEmpNo(Long empNo);
}
