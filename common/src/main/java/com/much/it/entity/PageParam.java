package com.much.it.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageParam {

    @Min(1)
    private Integer pageNum;

    @Min(5)
    private Integer pageSize;
}
