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
 * 系统菜单表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 菜单名称中文
     */
    private String name;
    /**
     * 菜单链接地址
     */
    private String url;
    /**
     * 父菜单ID
     */
    private Integer parentId;
    /**
     * 菜单权限点
     */
    private String point;
    /**
     * 菜单级别
     */
    private Integer level;
    /**
     * 菜单排序索引
     */
    private Integer sortIndex;
    /**
     * 创建时间/注册时间
     */
    private Date createDate;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 最后更新时间
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
