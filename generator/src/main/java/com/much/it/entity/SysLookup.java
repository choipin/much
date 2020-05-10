package com.much.it.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * LOOKUP表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sys_lookup")
public class SysLookup extends Model<SysLookup> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * LOOKUP编码
     */
    private String lookupCode;
    /**
     * LOOKUP名称-中文
     */
    private String lookupName;
    /**
     * LOOKUP描述
     */
    private String lookupDesc;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 最后更新日期
     */
    private Date lastupdateDate;
    /**
     * 最后更新人
     */
    private Long lastupdateBy;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
