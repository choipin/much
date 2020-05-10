package com.much.it.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description:
 * @project: much
 * @date: 2020/5/10
 * @author: Wenxin
 * @version: 1.0
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> {

    private int pageNum;
    //总记录数
    private long total;
    //结果集
    private List<T> list;
}
