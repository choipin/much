package com.much.it.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@Builder
@Data
@Accessors(chain = true)
public class Title implements Serializable {

    private Long empNo;
    private String title;
    private Date fromDate;
    private Date toDate;


}
