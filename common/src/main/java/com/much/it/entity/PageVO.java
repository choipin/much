package com.much.it.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description:
 * @project: much
 * @date: 2020/5/1
 * @author: Wenxin
 * @version: 1.0
 */
@Builder
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> {

    private Integer page;
    private Integer size;
    private List<T> date;
}
