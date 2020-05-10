package com.much.it.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class PageParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
}
