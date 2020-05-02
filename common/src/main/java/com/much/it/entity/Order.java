package com.much.it.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class Order implements Serializable {

    private Long id;
    private Long orderNumber;
    private BigDecimal price;
    private String img;
    private Long userId;
    private Long productId;
    private Date createTime;
    private Date updateTime;
}
