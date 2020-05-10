package com.much.it.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 关注表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@Data
@Accessors(chain = true)
@TableName("cus_solicitude")
public class CusSolicitude extends Model<CusSolicitude> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 关注类型。1-关注医生；2-关注医院；3-关注管家
     */
    private Integer type;
    /**
     * 关注人账号ID。
     */
    private Long sourceId;
    /**
     * 被关注对象账号ID。
     */
    private Integer targetId;
    /**
     * 最后更新时间
     */
    private Date lastupdateDate;
    /**
     * 创建时间
     */
    private Date createDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
