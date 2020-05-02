package com.much.it.service;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.Title;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
public interface TitleService {

    Title selectById(Long empNo);

    Integer insertTitle(Title title);

    Integer insertBatch(Integer num);

    PageVO<Title> findByPage(PageParam pageParam);

    Integer deleteAll();

    Integer deleteByEmpNo(Long empNo);
}
