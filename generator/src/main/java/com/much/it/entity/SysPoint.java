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
 * 权限点表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sys_point")
public class SysPoint extends Model<SysPoint> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 域（微服务模块）
     */
    private String domain;
    /**
     * 权限模块编码@PermissionModule.code()
     */
    private String pmCode;
    /**
     * 权限模块名称@PermissionModule.name()
     */
    private String pmName;
    /**
     * 权限点编码@PermissionPoint.code()
     */
    private String ppCode;
    /**
     * 权限点名称@PermissionPoint.name()
     */
    private String ppName;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 最后更新时间
     */
    private Date lastupdateDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
