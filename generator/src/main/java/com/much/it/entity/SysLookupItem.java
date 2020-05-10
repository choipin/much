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
 * LOOKUPITEM表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sys_lookup_item")
public class SysLookupItem extends Model<SysLookupItem> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * LOOKUPITEM编码
     */
    private String itemCode;
    /**
     * LOOKUPITEM名称-中文
     */
    private String itemName;
    /**
     * LOOKUPITEM描述
     */
    private String itemDesc;
    /**
     * 序号
     */
    private Integer sortIndex;
    /**
     * LOOKUPID
     */
    private Long lookupId;
    /**
     * 扩展属性1
     */
    private String attr1;
    /**
     * 扩展属性2
     */
    private String attr2;
    /**
     * 扩展属性3
     */
    private String attr3;
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
