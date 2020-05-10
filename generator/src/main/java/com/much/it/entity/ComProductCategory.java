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
 * 项目类目信息表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("com_product_category")
public class ComProductCategory extends Model<ComProductCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 类目编码
     */
    private String code;
    /**
     * 类目图标。问卷取此图片
     */
    private String icon;
    /**
     * 类目图标。除问卷以外取此图片
     */
    private String logo;
    /**
     * 类目名称。除问卷以外取此图片
     */
    private String name;
    /**
     * 项目别称。问卷取此名称
     */
    private String alias;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否上线。1-是；0-否
     */
    private Integer onLine;
    /**
     * 父级类目ID，一级类目的父ID为-1
     */
    private Integer parentId;
    /**
     * 类目级别。
     */
    private Integer level;
    /**
     * 排序顺序
     */
    private Integer sortIndex;
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
