package com.much.it.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.Title;
import com.much.it.mapper.TitleMapper;
import com.much.it.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleMapper titleMapper;

    @Override
    public Title selectById(Long empNo) {
        return titleMapper.selectById(empNo);
    }

    @Override
    public Integer insertTitle(Title title) {
        return titleMapper.insertTitle(title);
    }

    @Override
    public Integer insertBatch(Integer num) {
        List<Title> titles = IntStream.range(0, num).boxed().map(integer -> Title.builder().title(UUID.randomUUID().toString())
        .fromDate(new Date()).toDate(new Date()).build()).collect(Collectors.toList());
        return titleMapper.insertBatch(titles);
    }

    @Override
    public PageVO<Title> findByPage(PageParam pageParam) {
        PageHelper.startPage(pageParam);
        List<Title> all = titleMapper.findAll();
        PageInfo<Title> pageInfo = new PageInfo<>(all);
        return new PageVO<Title>(pageParam.getPageNum(),pageInfo.getSize(),pageInfo.getList());
    }

    @Override
    public Integer deleteAll() {
        return titleMapper.deleteAll();
    }

    @Override
    public Integer deleteByEmpNo(Long empNo) {
        return titleMapper.deleteByEmpNo(empNo);
    }
}
