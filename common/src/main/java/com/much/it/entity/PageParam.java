package com.much.it.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @description:
 * @project: much
 * @date: 2020/5/1
 * @author: Wenxin
 * @version: 1.0
 */
@Builder
@Data
public class PageParam {

    @Min(1)
    private Integer pageNum;

    @Min(5)
    private Integer pageSize;
}
